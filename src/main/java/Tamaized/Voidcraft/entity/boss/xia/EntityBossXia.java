package Tamaized.Voidcraft.entity.boss.xia;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Nullable;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases.EntityAIXiaPhase1;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases.EntityAIXiaPhase2;

public class EntityBossXia extends EntityVoidBoss {

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

	private Action currAction = Action.IDLE;

	public static enum Action {
		IDLE, SWORD_PROJECTION_RIGHT
	}

	public static int getActionID(Action action) {
		return action.ordinal();
	}

	public static Action getActionFromID(int id) {
		return Action.values()[id];
	}

	public EntityBossXia(World par1World) {
		super(par1World);
		this.setInvul(true);
	}

	public EntityBossXia(World world, IBattleHandler handler) {
		super(world, handler);
		this.setInvul(true);
	}

	public Action getAction() {
		return currAction;
	}

	public void setAction(Action action) {
		currAction = action;
	}

	public void setArmRotations(float leftArmPitch, float rightArmPitch, float leftArmYaw, float rightArmYaw) {
		this.leftArmYaw = leftArmYaw;
		this.leftArmPitch = leftArmPitch;
		this.rightArmYaw = rightArmYaw;
		this.rightArmPitch = rightArmPitch;
		sendPacketUpdates();
	}

	private void sendPacketUpdates() {
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.XIA_ARMSTATE));
			outputStream.writeInt(getEntityId());
			outputStream.writeFloat(leftArmPitch);
			outputStream.writeFloat(rightArmPitch);
			outputStream.writeFloat(leftArmYaw);
			outputStream.writeFloat(rightArmYaw);
			outputStream.writeInt(getActionID(getAction()));
			FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);
			if (voidCraft.channel != null && packet != null) voidCraft.channel.sendToAllAround(packet, new TargetPoint(worldObj.provider.getDimension(), posX, posY, posZ, 64));
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void triggerOnDamage(int phase, DamageSource source, float amount) {
		sendPacketToBus(new XiaTookDamagePacket());
	}

	public class XiaTookDamagePacket implements IVoidBossAIPacket {

	}

	@Override
	public void addPotionEffect(PotionEffect potioneffectIn) {

	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack) {
		// start();
		return super.processInteract(player, hand, stack);
	}

	@Override
	protected void initPhase(int phase) {
		setAction(Action.IDLE);
		if (phase == 1) {
			/**
			 * Cycle: - Teleports around, does various attacks, can be hit directly
			 */
			isFlying = true;
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
			this.setHealth(this.getMaxHealth());
			// BossMusicManager.PlayTheSound(this.worldObj, this, new ItemStack(voidCraft.items.voidDiscs.get(10)), new int[]{(int) this.posX, (int) this.posY, (int) this.posZ}, true);
			setInvul(false);
			addAI(new EntityAIXiaPhase1(this, getFilters()));
		} else if (phase == 2) {
			/**
			 * Cycle: - Teleports a short distance from a targetted player, walks towards them with a giant sword. do some sword mechanic stuff that can be dodged
			 */
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
			this.setHealth(this.getMaxHealth());
			setInvul(false);
			addAI(new EntityAIXiaPhase2(this, getFilters()));
		} else if (phase == 3) {
			/**
			 * Cycle: - Stands still at his throne, various attacks, can take direct hits, upon taking a hit cause a massive blast that throws everyone back
			 */
			isFlying = true;
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
			this.setHealth(this.getMaxHealth());

			// addAI(EntityAIPathHerobrineFlightPhase3.class); TODO

		}
		setArmRotations(0, 0, 0, 0);
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