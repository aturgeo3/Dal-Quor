package tamaized.voidcraft.common.entity.boss;

import com.google.common.base.Predicate;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import tamaized.tammodized.common.entity.EntityDragonOld;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.entity.EntityVoidBoss;
import tamaized.voidcraft.common.entity.EntityVoidMob;
import tamaized.voidcraft.common.entity.EntityVoidNPC;
import tamaized.voidcraft.common.entity.boss.xia.finalphase.EntityWitherbrine;
import tamaized.voidcraft.common.sound.VoidSoundEvents;

public class EntityBossCorruptedPawn extends EntityVoidMob implements IVoidBossData, IMob {

	private static final DataParameter<Integer> INVULNERABILITY_TIME = EntityDataManager.createKey(EntityBossCorruptedPawn.class, DataSerializers.VARINT);
	private static final Predicate<Entity> NOT_UNDEAD = p_apply_1_ -> !(p_apply_1_ instanceof EntityBossCorruptedPawn) && !(p_apply_1_ instanceof EntityDragonOld) && !(p_apply_1_ instanceof EntityVoidBoss) && !(p_apply_1_ instanceof EntityVoidNPC) && p_apply_1_ instanceof EntityLivingBase && ((EntityLivingBase) p_apply_1_).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD;

	public EntityBossCorruptedPawn(World p_i1738_1_) {
		super(p_i1738_1_);
		this.setHealth(this.getMaxHealth());
		this.setSize(0.9F, 3.0F);
		this.isImmuneToFire = true;
		((PathNavigateGround) this.getNavigator()).setCanSwim(true);
		this.experienceValue = 50;
		enablePersistence();
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityBossCorruptedPawn.AIDoNothing());
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));

		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true, false, NOT_UNDEAD));

	}

	class AIDoNothing extends EntityAIBase {
		public AIDoNothing() {
			this.setMutexBits(7);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		@Override
		public boolean shouldExecute() {
			return EntityBossCorruptedPawn.this.getInvulTime() > 0;
		}
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(INVULNERABILITY_TIME, Integer.valueOf(0));
	}

	public static void registerFixesWither(DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, EntityBossCorruptedPawn.class);
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		super.writeEntityToNBT(p_70014_1_);
		p_70014_1_.setInteger("Invul", this.getInvulTime());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		super.readEntityFromNBT(p_70037_1_);
		this.setInvulTime(p_70037_1_.getInteger("Invul"));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		setStats();
	}

	private void setStats() {
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.50000000298023224D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	@Override
	protected void updateAITasks() {
		if (this.getInvulTime() > 0) {
			int j1 = this.getInvulTime() - 1;

			if (j1 <= 0) {
				this.world.newExplosion(this, this.posX, this.posY + (double) this.getEyeHeight(), this.posZ, 7.0F, false, this.world.getGameRules().getBoolean("mobGriefing"));
				this.world.playBroadcastSound(1023, new BlockPos(this), 0);
			}

			this.setInvulTime(j1);

			if (this.ticksExisted % 10 == 0) {
				this.heal(20.0F);
			}
		} else {
			super.updateAITasks();
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) {
			return false;
		} else if (source != DamageSource.DROWN && !(source.getTrueSource() instanceof EntityWitherbrine)) {
			if (this.getInvulTime() > 0 && source != DamageSource.OUT_OF_WORLD) {
				return false;
			} else {
				if (this.isArmored()) {
					Entity entity = source.getImmediateSource();

					if (entity instanceof EntityArrow) {
						return false;
					}
				}

				Entity entity1 = source.getTrueSource();

				if (entity1 != null && !(entity1 instanceof EntityPlayer) && entity1 instanceof EntityLivingBase && ((EntityLivingBase) entity1).getCreatureAttribute() == this.getCreatureAttribute()) {
					return false;
				} else {
					return super.attackEntityFrom(source, amount);
				}
			}
		} else {
			return false;
		}
	}

	public void ignite() {
		setStats();
		this.setInvulTime(200);
		this.setHealth(this.getMaxHealth() / 3.0F);
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	public boolean isArmored() {
		return this.getHealth() <= this.getMaxHealth() / 2.0F;
	}

	public int getInvulTime() {
		return ((Integer) this.dataManager.get(INVULNERABILITY_TIME)).intValue();
	}

	public void setInvulTime(int time) {
		this.dataManager.set(INVULNERABILITY_TIME, Integer.valueOf(time));
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobVoidBossSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return VoidSoundEvents.EntityMobVoidBossSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobVoidBossSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected Item getDropItem() {
		return VoidCraft.items.voidStar;
	}

	@Override
	protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
		this.dropItem(getDropItem(), 1);
	}

	@Override
	public float getPercentHPForBossBar() {
		return this.getHealth() / this.getMaxHealth();
	}

	@Override
	public ITextComponent getNameForBossBar() {
		return new TextComponentTranslation("entity.VoidBoss.name");
	}

	@Override
	public float getMaxHealthForBossBar() {
		return getMaxHealth();
	}

	@Override
	public float getHealthForBossBar() {
		return getHealth();
	}

}