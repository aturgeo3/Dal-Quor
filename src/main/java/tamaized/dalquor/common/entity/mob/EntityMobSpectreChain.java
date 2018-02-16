package tamaized.dalquor.common.entity.mob;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import tamaized.dalquor.common.entity.EntityVoidMob;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import tamaized.dalquor.common.entity.nonliving.EntityVoidChain;
import tamaized.dalquor.common.sound.VoidSoundEvents;
import tamaized.dalquor.registry.VoidCraftItems;

public class EntityMobSpectreChain extends EntityVoidMob implements IRangedAttackMob {

	public EntityMobSpectreChain(World par1World) {
		super(par1World);

		isImmuneToFire = true;
		setSize(0.9F, 2.0F);

		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(4, new EntityAIRestrictSun(this));
		tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
		tasks.addTask(2, new EntityAIWander(this, 1.0D));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(2, new EntityAILookIdle(this));
		tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 20, 50, 15.0F));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

		Predicate ies = new Predicate() {
			/**
			 * Return whether the specified entity is applicable to this filter.
			 */
			boolean apply(Entity p_82704_1_) {
				return !(p_82704_1_ instanceof EntityWitherSkeleton) && !(p_82704_1_ instanceof EntityHerobrineCreeper || p_82704_1_ instanceof EntityShulker);
			}

			@Override
			public boolean apply(Object p_apply_1_) {
				return p_apply_1_ instanceof Entity && apply((Entity) p_apply_1_);
			}
		};

		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, ies));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, 0, true, false, ies));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySnowman.class, 0, true, false, ies));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true, false, ies));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobSpectreChainSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return VoidSoundEvents.EntityMobSpectreChainSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobSpectreChainSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected Item getDropItem() {
		return VoidCraftItems.voidChain;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float par2) {
		if (!canAttack(target))
			return;
		EntityVoidChain entityarrow = new EntityVoidChain(world, this, target, (float) getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
		playSound(VoidSoundEvents.MiscSoundEvents.chain, 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
		world.spawnEntity(entityarrow);
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {

	}
}