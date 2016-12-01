package Tamaized.Voidcraft.entity.ghost;

import com.mojang.authlib.GameProfile;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.handlers.SkinHandler.PlayerNameAlias;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGhostPlayerBase extends EntityVoidNPC implements IEntityAdditionalSpawnData {

	private GameProfile profile;
	private PlayerNameAlias alias;

	private boolean canInteract = false;
	private boolean hasInteracted = false;
	private boolean running = false;
	private int tick = 0;
	private int finalTick = 20 * 6;

	private boolean rune = false;
	private Entity runeTarget;
	private int runeState = 0;
	private int maxRuneState = 100;

	private int runeRotation = 0;

	public EntityGhostPlayerBase(World par1World) {
		super(par1World);

		this.isImmuneToFire = true;
		this.hurtResistantTime = 10;
		this.setSize(0.6F, 1.8F);

		// this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 50.0F));
		// this.tasks.addTask(6, new EntityAILookIdle(this));
		// this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.setInvul(true);

	}

	protected EntityGhostPlayerBase(World world, PlayerNameAlias alias, boolean interactable) {
		this(world);
		profile = voidCraft.skinHandler.getGameProfile(alias);
		this.alias = alias;
		canInteract = interactable;
	}

	public boolean isInteractable() {
		return canInteract;
	}

	public void setRuneState(Entity target, int length) {
		rune = true;
		runeTarget = target;
		runeState = 0;
		maxRuneState = length;
		running = true;
		tick = 0;
	}

	public boolean hasRuneState() {
		return rune;
	}

	public int getRuneState() {
		return runeState;
	}

	public int getMaxRuneState() {
		return maxRuneState;
	}

	public float getRuneStatePerc() {
		return (float) runeState / (float) maxRuneState;
	}

	public Entity getRuneTarget() {
		return runeTarget;
	}

	public int getRuneRotationForRender() {
		return runeRotation;
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, alias.toString());
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		alias = PlayerNameAlias.valueOf(ByteBufUtils.readUTF8String(additionalData));
		profile = voidCraft.skinHandler.getGameProfile(alias);
	}

	public PlayerNameAlias getAlias() {
		return alias;
	}

	public boolean hasInteracted() {
		return hasInteracted;
	}

	public boolean isRunning() {
		return running;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (!world.isRemote) {
			if (profile == null) {
				this.setDead();
				return;
			}
			if (running) {
				if (rune && runeState < maxRuneState) runeState++;
				tick++;
			}
			if (tick >= finalTick) hasInteracted = true;
			if (tick % (maxRuneState - runeState) == 0) runeRotation++;
			if (runeRotation >= 360) runeRotation = 0;
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (running || !canInteract) return false;
		running = true;
		return true;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void collideWithEntity(Entity par1Entity) {
	}

	@Override
	public void applyEntityCollision(Entity par1Entity) {
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(999.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobZolSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound() {
		return VoidSoundEvents.EntityMobZolSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobZolSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.5F;
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {

	}

	@Override
	@SideOnly(Side.CLIENT)
	public ITextComponent getDisplayName() {
		return new TextComponentString(profile == null ? "null" : profile.getName() == null ? "null" : profile.getName());
	}

	public static EntityGhostPlayerBase newInstance(World world, PlayerNameAlias alias, boolean interactable) {
		return voidCraft.skinHandler.isBipedModel(alias) ? new EntityGhostBiped(world, alias, interactable) : new EntityGhostPlayer(world, alias, interactable);
	}

}