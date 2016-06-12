package Tamaized.Voidcraft.mobs.entity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import Tamaized.Voidcraft.blocks.BlockVoidbrick;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.mobs.EntityVoidMob;
import Tamaized.Voidcraft.projectiles.VoidChain;
import Tamaized.Voidcraft.sound.VoidSoundEvents;

import com.google.common.base.Predicate;

public class EntityMobSpectreChain extends EntityVoidMob implements IRangedAttackMob{
	
	public EntityMobSpectreChain(World par1World) {
		super(par1World);
		
		this.isImmuneToFire = true;
		this.setSize(0.9F, 2.0F);
		
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(4, new EntityAIRestrictSun(this));
		this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(2, new EntityAILookIdle(this));
		this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 20, 50, 15.0F));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		
		Predicate ies = new Predicate(){
			/**
			 * Return whether the specified entity is applicable to this filter.
			 */
			public boolean apply(Entity p_82704_1_){
				if(p_82704_1_ instanceof EntitySkeleton && ((EntitySkeleton)p_82704_1_).getSkeletonType()==1) return false;
				else return true;
			}
			public boolean apply(Object p_apply_1_){
				return p_apply_1_ instanceof Entity ? this.apply((Entity)p_apply_1_) : false;
			}
		};
		
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySnowman.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true, false, ies));
	}
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(25.0D);
	}
    
    @Override
    protected SoundEvent getAmbientSound(){
    	return VoidSoundEvents.EntityMobSpectreChainSoundEvents.ambientSound;
    }
    
    @Override
    protected SoundEvent getHurtSound(){
    	return VoidSoundEvents.EntityMobSpectreChainSoundEvents.hurtSound;
    }
    
    @Override
    protected SoundEvent getDeathSound(){
    	return VoidSoundEvents.EntityMobSpectreChainSoundEvents.deathSound;
    }
    
    @Override
    protected float getSoundVolume(){
    	return 0.4F;
    }
    
    @Override
    protected Item getDropItem() {
    	return voidCraft.items.voidChain;
    }
    
    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2){
    	if(!canAttack(par1EntityLivingBase)) return;
    	VoidChain entityarrow = new VoidChain(this.worldObj, this, (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
    	//EntityArrow entityarrow = new EntityArrow(this.worldObj, this, par1EntityLivingBase, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
    	this.playSound(VoidSoundEvents.MiscSoundEvents.chain, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
    	this.worldObj.spawnEntityInWorld(entityarrow);
    }
}