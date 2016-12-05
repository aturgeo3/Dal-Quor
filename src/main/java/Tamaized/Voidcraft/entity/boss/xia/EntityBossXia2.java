package Tamaized.Voidcraft.entity.boss.xia;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.entity.ghost.EntityGhostPlayerBase;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.Xia2BattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.phases.EntityAIXia2Phase1;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.phases.EntityAIXia2Phase2;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class EntityBossXia2 extends EntityVoidBoss<Xia2BattleHandler> {

	/**
	 * Degrees
	 */
	public float leftArmYaw = 0.0f;

	/**
	 * Degrees
	 */
	public float leftArmPitch = 0.0f;

	/**
	 * Degrees
	 */
	public float rightArmYaw = 0.0f;

	/**
	 * Degrees
	 */
	public float rightArmPitch = 0.0f;

	private boolean sphereState = false;

	private List<EntityGhostPlayerBase> ghostList = new ArrayList<EntityGhostPlayerBase>();

	public EntityBossXia2(World par1World) {
		super(par1World);
		this.setInvul(true);
	}

	public EntityBossXia2(World world, Xia2BattleHandler handler) {
		super(world, handler, false);
		this.setInvul(true);
	}

	public void setSphereState(boolean state) {
		sphereState = state;
		if (!world.isRemote) sendPacketUpdates();
	}

	public boolean shouldSphereRender() {
		return sphereState;
	}

	public void setArmRotations(float leftArmPitch, float rightArmPitch, float leftArmYaw, float rightArmYaw, boolean sendUpdates) {
		this.leftArmYaw = leftArmYaw;
		this.leftArmPitch = leftArmPitch;
		this.rightArmYaw = rightArmYaw;
		this.rightArmPitch = rightArmPitch;
		if (sendUpdates) sendPacketUpdates();
	}

	private void sendPacketUpdates() {
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.XIA_UPDATES));
			outputStream.writeInt(getEntityId());
			outputStream.writeFloat(leftArmPitch);
			outputStream.writeFloat(rightArmPitch);
			outputStream.writeFloat(leftArmYaw);
			outputStream.writeFloat(rightArmYaw);
			outputStream.writeBoolean(sphereState);
			FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);
			if (voidCraft.channel != null && packet != null) voidCraft.channel.sendToAllAround(packet, new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 32 * 6));
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void decodePacket(ByteBufInputStream stream) throws IOException {
		setArmRotations(stream.readFloat(), stream.readFloat(), stream.readFloat(), stream.readFloat(), false);
		setSphereState(stream.readBoolean());
	}

	@Override
	protected void triggerOnDamage(int phase, DamageSource source, float amount) {
		sendPacketToBus(new Xia2TookDamagePacket());
	}

	@Override
	protected void deathHook() {

	}

	public class Xia2TookDamagePacket implements IVoidBossAIPacket {

	}

	@Override
	public void addPotionEffect(PotionEffect potioneffectIn) {
		Potion pot = potioneffectIn.getPotion();
		if (pot == voidCraft.potions.fireSheath || pot == voidCraft.potions.frostSheath || pot == voidCraft.potions.litSheath || pot == voidCraft.potions.acidSheath) super.addPotionEffect(potioneffectIn);
		if (!world.isRemote) {
			ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
			DataOutputStream outputStream = new DataOutputStream(bos);
			try {
				outputStream.writeInt(ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.XIA_SHEATHE));
				outputStream.writeInt(getEntityId());
				outputStream.writeInt(Potion.getIdFromPotion(pot));
				outputStream.writeInt(potioneffectIn.getDuration());
				FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);
				if (voidCraft.channel != null && packet != null) voidCraft.channel.sendToAllAround(packet, new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 64));
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addGhost(EntityGhostPlayerBase ghost) {
		ghostList.add(ghost);
	}

	public List<EntityGhostPlayerBase> getGhostList() {
		return ghostList;
	}

	public void clearGhosts() {
		ghostList.clear();
	}

	@Override
	protected void initPhase(int phase) {
		setSphereState(false);
		clearGhosts();
		switch (phase) {
			case 1: {
				/**
				 * Cycle: - Teleports around, does various attacks, can be hit directly
				 */
				isFlying = true;
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
				setHealth(getMaxHealth());
				// BossMusicManager.PlayTheSound(this.worldObj, this, new ItemStack(voidCraft.items.voidDiscs.get(10)), new int[]{(int) this.posX, (int) this.posY, (int) this.posZ}, true);
				setInvul(false);
				addAI(new EntityAIXia2Phase1(this, getFilters()));
			}
				break;
			case 2: {
				/**
				 * Cycle: - Teleports a short distance from a target player, walks towards them with a demonic sword.
				 */
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
				setHealth(getMaxHealth());
				setInvul(false);
				addAI(new EntityAIXia2Phase2(this, getFilters()));
			}
				break;
			case 3: {
				/**
				 * Cycle: - Stands still at his throne, various attacks, can take direct hits, upon taking a hit cause a massive blast that throws everyone back His attacks may involve the Vade Mecum spells
				 */
				isFlying = true;
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
				setHealth(getMaxHealth());
				setInvul(false);
				// addAI(new EntityAIXiaPhase3(this, getFilters()));
			}
				break;
			default:
				break;
		}
		setArmRotations(0, 0, 0, 0, true);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobXiaSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound() {
		return VoidSoundEvents.EntityMobXiaSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobXiaSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.0F;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString("Xia");
	}

	@Override
	protected void updatePhase(int phase) {

	}

	@Override
	protected ArrayList<Class> getFilters() {
		ArrayList<Class> filter = new ArrayList<Class>();
		filter.add(EntityPlayer.class);
		return filter;
	}

	@Override
	protected boolean immuneToFire() {
		return true;
	}

	@Override
	protected float sizeWidth() {
		return 0.6F;
	}

	@Override
	protected float sizeHeight() {
		return 1.8F;
	}

	@Override
	protected int maxPhases() {
		return 3;
	}
}