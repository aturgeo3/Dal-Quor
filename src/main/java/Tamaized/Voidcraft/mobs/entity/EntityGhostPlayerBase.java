package Tamaized.Voidcraft.mobs.entity;

import io.netty.buffer.ByteBuf;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.handlers.SkinHandler.PlayerNameAlias;
import Tamaized.Voidcraft.mobs.EntityVoidNPC;
import Tamaized.Voidcraft.sound.VoidSoundEvents;

import com.mojang.authlib.GameProfile;

public class EntityGhostPlayerBase extends EntityVoidNPC implements IEntityAdditionalSpawnData {

	private GameProfile profile;
	private PlayerNameAlias alias;

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

	protected EntityGhostPlayerBase(World world, PlayerNameAlias alias) {
		this(world);
		profile = voidCraft.skinHandler.getGameProfile(alias);
		this.alias = alias;
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

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (!worldObj.isRemote) {
			if (profile == null) {
				this.setDead();
				return;
			}
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack) {
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

	public static EntityGhostPlayerBase newInstance(World world, PlayerNameAlias alias) {
		return voidCraft.skinHandler.isBipedModel(alias) ? new EntityGhostBiped(world, alias) : new EntityGhostPlayer(world, alias);
	}

}