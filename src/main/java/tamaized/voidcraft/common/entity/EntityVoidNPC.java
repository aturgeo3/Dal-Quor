package tamaized.voidcraft.common.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.client.entity.animation.AnimatableModel;
import tamaized.voidcraft.client.entity.animation.AnimationRegistry;
import tamaized.voidcraft.client.entity.animation.IAnimation;
import tamaized.voidcraft.network.IEntitySync;
import tamaized.voidcraft.network.client.ClientPacketHandlerAnimation;

public abstract class EntityVoidNPC extends EntityFlying implements IMob, IEntitySync {

	protected boolean canDie = true;
	protected boolean canPush = true;
	protected boolean canMove = true;
	private boolean invulnerable = false;

	private int animationID;
	private IAnimation animation;

	public EntityVoidNPC(World world) {
		super(world);
		experienceValue = 10;
		ignoreFrustumCheck = true;
		enablePersistence();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("canDie", canDie);
		nbt.setBoolean("canPush", canPush);
		nbt.setBoolean("invulnerable", invulnerable);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		canDie = nbt.getBoolean("canDie");
		canPush = nbt.getBoolean("canPush");
		invulnerable = nbt.getBoolean("invulnerable");
	}

	public IAnimation constructAnimation(int a) {
		animationID = a;
		if (animation == null && animationID >= 0) {
			Class<? extends IAnimation> ani = AnimationRegistry.getAnimation(animationID);
			if (ani != null) {
				try {
					animation = ani.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		if (animationID < 0 && animation != null)
			animation = null;
		return animation;
	}

	/**
	 * Call this (on the container) after calling {@link #constructAnimation(int)} to play the animation
	 */
	public void playAnimation() {
		if (!world.isRemote && animationID >= 0 && animation != null) {
			VoidCraft.network.sendToAllAround(new ClientPacketHandlerAnimation.Packet(getEntityId(), animationID, animation), new TargetPoint(dimension, posX, posY, posZ, 64));
			animationID = -1;
			animation = null;
		}
	}

	public IAnimation getAnimation() {
		return animation;
	}

	public void setAnimation(IAnimation a) {
		animation = a;
		animationID = AnimationRegistry.getAnimationID(animation);
	}

	@SideOnly(Side.CLIENT)
	public void renderAnimation(AnimatableModel model) {
		IAnimation a = getAnimation();
		if (a != null) {
			a.render(this, model);
		}
	}

	@Override
	public final void encodePacket(ByteBuf stream) {
		encodePacketData(stream);
	}

	protected abstract void encodePacketData(ByteBuf stream);

	@Override
	public final void decodePacket(ByteBuf stream) {
		decodePacketData(stream);
	}

	protected abstract void decodePacketData(ByteBuf stream);

	@Override
	public void onLivingUpdate() {
		IAnimation a = getAnimation();
		if (animationID >= 0 && (a == null || a.update(this)))
			animationID = -1;
		updateArmSwingProgress();
		float f = getBrightness();
		if (f > 0.5F) {
			idleTime += 2;
		}
		super.onLivingUpdate();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double x, double y, double z) {
		if (canMove)
			super.setVelocity(x, y, z);
	}

	@Override
	public void addVelocity(double x, double y, double z) {
		if (canMove)
			super.addVelocity(x, y, z);
	}

	@Override
	public void move(MoverType type, double x, double y, double z) {
		if (canMove)
			super.move(type, x, y, z);
	}

	@Override
	public void moveRelative(float strafe, float up, float forward, float friction) {
		if (canMove)
			super.moveRelative(strafe, up, forward, friction);
	}

	@Override
	public void moveToBlockPosAndAngles(BlockPos pos, float rotationYawIn, float rotationPitchIn) {
		if (canMove)
			moveToBlockPosAndAngles(pos, rotationYawIn, rotationPitchIn);
	}

	@Override
	public boolean canBePushed() {
		return canPush;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			setDead();
		}
	}

	@Override
	public boolean isEntityAlive() {
		return !canDie || !isDead && getHealth() > 0.0F;
	}

	/*@Override
	public void travel(float p_191986_1_, float p_191986_2_, float p_191986_3_) {
		prevLimbSwingAmount = limbSwingAmount;
		double d0 = posX - prevPosX;
		double d1 = posZ - prevPosZ;
		float f6 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;

		if (f6 > 1.0F) {
			f6 = 1.0F;
		}

		limbSwingAmount += (f6 - limbSwingAmount) * 0.4F;
		limbSwing += limbSwingAmount;
	}*/

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (isInvulnerable()) {
			return false;
		} else if (super.attackEntityFrom(source, amount)) {
			Entity entity = source.getTrueSource();
			if (entity != this && entity instanceof EntityLivingBase) {
				setAttackTarget((EntityLivingBase) entity);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean isInvulnerable() {
		return invulnerable;
	}

	public void setInvulnerable(boolean b) {
		invulnerable = b;
	}

	@Override
	public SoundCategory getSoundCategory() {
		return SoundCategory.HOSTILE;
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.ENTITY_HOSTILE_SWIM;
	}

	@Override
	protected SoundEvent getSplashSound() {
		return SoundEvents.ENTITY_HOSTILE_SPLASH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_HOSTILE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_HOSTILE_DEATH;
	}

	@Override
	protected SoundEvent getFallSound(int heightIn) {
		return heightIn > 4 ? SoundEvents.ENTITY_HOSTILE_BIG_FALL : SoundEvents.ENTITY_HOSTILE_SMALL_FALL;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		float f = (float) getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
		int i = 0;

		if (entityIn instanceof EntityLivingBase) {
			f += EnchantmentHelper.getModifierForCreature(getHeldItemMainhand(), ((EntityLivingBase) entityIn).getCreatureAttribute());
			i += EnchantmentHelper.getKnockbackModifier(this);
		}

		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

		if (flag) {

			if (i > 0) {
				entityIn.addVelocity((double) (-MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F), 0.1D, (double) (MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F));
				motionX *= 0.6D;
				motionZ *= 0.6D;
			}

			int j = EnchantmentHelper.getFireAspectModifier(this);

			if (j > 0) {
				entityIn.setFire(j * 4);
			}

			if (entityIn instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer) entityIn;
				ItemStack itemstack = getHeldItemMainhand();
				ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

				if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem() instanceof ItemAxe && itemstack1.getItem() == Items.SHIELD) {
					float f1 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

					if (rand.nextFloat() < f1) {
						entityplayer.getCooldownTracker().setCooldown(Items.SHIELD, 100);
						world.setEntityState(entityplayer, (byte) 30);
					}
				}
			}

			applyEnchantments(this, entityIn);
		}
		return flag;
	}

	protected boolean isValidLightLevel() {
		return false;
	}

	@Override
	public boolean getCanSpawnHere() {
		return world.getDifficulty() != EnumDifficulty.PEACEFUL && isValidLightLevel() && super.getCanSpawnHere();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	}

	@Override
	protected boolean canDropLoot() {
		return true;
	}

	@Override
	protected void despawnEntity() {

	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	protected static class AILookAround extends EntityAIBase {
		private final EntityVoidNPC parentEntity;

		public AILookAround(EntityVoidNPC ghast) {
			this.parentEntity = ghast;
			this.setMutexBits(2);
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}

		@Override
		public void updateTask() {
			if (this.parentEntity.getAttackTarget() == null) {
				this.parentEntity.rotationYaw = -((float) MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ)) * (180F / (float) Math.PI);
				this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
			} else {
				EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
				double d0 = 64.0D;

				if (entitylivingbase.getDistanceSqToEntity(this.parentEntity) < 4096.0D) {
					double d1 = entitylivingbase.posX - this.parentEntity.posX;
					double d2 = entitylivingbase.posZ - this.parentEntity.posZ;
					this.parentEntity.rotationYaw = -((float) MathHelper.atan2(d1, d2)) * (180F / (float) Math.PI);
					this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
				}
			}
		}
	}

	public static class BossFlyNoclipMoveHelper extends EntityMoveHelper {
		private final EntityVoidNPC parentEntity;
		private int courseChangeCooldown;

		public BossFlyNoclipMoveHelper(EntityVoidNPC herobrine) {
			super(herobrine);
			this.parentEntity = herobrine;
		}

		@Override
		public void onUpdateMoveHelper() {
			if (this.action == EntityMoveHelper.Action.MOVE_TO) {
				double d0 = this.posX - this.parentEntity.posX;
				double d1 = this.posY - this.parentEntity.posY;
				double d2 = this.posZ - this.parentEntity.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;

				if (this.courseChangeCooldown-- <= 0) {
					this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
					d3 = (double) MathHelper.sqrt(d3);
					double movementSpeed = parentEntity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
					this.parentEntity.motionX += d0 / d3 * (movementSpeed * speed);
					this.parentEntity.motionY += d1 / d3 * (movementSpeed * speed);
					this.parentEntity.motionZ += d2 / d3 * (movementSpeed * speed);
				}
			}
		}

	}

}