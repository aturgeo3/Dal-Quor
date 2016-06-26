package Tamaized.Voidcraft.mobs.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.SkeletonType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.mobs.EntityVoidMob;
import Tamaized.Voidcraft.sound.VoidSoundEvents;

import com.google.common.base.Predicate;

public class EntityMobLich extends EntityVoidMob implements IRangedAttackMob{
	
	double xAdd = 0;
	double zAdd = 0;
	
	public EntityMobLich(World par1World) {
		super(par1World);
		
		this.isImmuneToFire = true;
		this.setSize(0.9F, 3.0F);
		
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(4, new EntityAIRestrictSun(this));
		this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(2, new EntityAILookIdle(this));
		this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 20, 20, 15.0F));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		
		Predicate ies = new Predicate(){
			/**
			 * Return whether the specified entity is applicable to this filter.
			 */
			public boolean apply(Entity p_82704_1_){
				if(p_82704_1_ instanceof EntitySkeleton && ((EntitySkeleton)p_82704_1_).func_189771_df() == SkeletonType.WITHER) return false;
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
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMobLich.class, 0, true, false, ies)); //Lich hate Lich
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, true, false, ies)); //Passive animals
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySlime.class, 0, true, false, ies)); //Slime extends EntityLiving so need to add it manually
	}
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000000298023224D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(65.0D);
	}
    
    @Override
    protected SoundEvent getAmbientSound(){
    	return VoidSoundEvents.EntityMobLichSoundEvents.ambientSound;
    }
    
    @Override
    protected SoundEvent getHurtSound(){
    	return VoidSoundEvents.EntityMobLichSoundEvents.hurtSound;
    }
    
    @Override
    protected SoundEvent getDeathSound(){
    	return VoidSoundEvents.EntityMobLichSoundEvents.deathSound;
    }
    
    @Override
    protected float getSoundVolume(){
    	return 0.6F;
    }

    @Override
    protected Item getDropItem() {
    	return voidCraft.items.voidCloth;
    }
    
    //TODO: overhaul this
    //TODO Potion like attacks using a new throwable entity.
    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float par2){	
    	if(!canAttack(target)) return;
    	
    	double randAttk = Math.random()*5;
    	randAttk = Math.round(randAttk);
    	//randAttk = 0;
    	
    	if(randAttk == 5){ //Call the Inferno
    		int r = 6;
    		for(double nx=posX-r; nx<posX+r; nx++){
    			for(double nz=posZ-r; nz<posZ+r; nz++){
    				if(worldObj.isAirBlock(new BlockPos((int) nx, (int) posY-1, (int) nz))){
    					worldObj.setBlockState(new BlockPos((int) nx, (int) posY-1, (int) nz), voidCraft.blocks.fireVoid.getDefaultState());
    				}else if(worldObj.isAirBlock(new BlockPos((int) nx, (int) posY, (int) nz))){
    					worldObj.setBlockState(new BlockPos((int) nx, (int) posY, (int) nz), voidCraft.blocks.fireVoid.getDefaultState());
    				}else if(worldObj.isAirBlock(new BlockPos((int) nx, (int) posY+1, (int) nz))){
    					worldObj.setBlockState(new BlockPos((int) nx, (int) posY+1, (int) nz), voidCraft.blocks.fireVoid.getDefaultState());
    				}
    			}
    		}
    	}else if(randAttk == 4){ //Call forth the Undead to my aid
    		if(target instanceof EntityMobLich) return; //Do not summon the undead if i'm fighting another lich
    		EntityMobWraith wraith;
    		EntityMobSpectreChain chain;
    		EntityMobVoidWrath wrath;
    		EntitySkeleton skelly;
    		
    		wraith = new EntityMobWraith(worldObj);
    		chain = new EntityMobSpectreChain(worldObj);
    		wrath = new EntityMobVoidWrath(worldObj);
    		skelly = new EntitySkeleton(worldObj);
    		
    		wraith.setPosition(this.posX-2, this.posY, this.posZ-2);
    		wraith.setAttackTarget(target);
    		
    		chain.setPosition(this.posX-2, this.posY, this.posZ+2);
    		chain.setAttackTarget(target);

    		wrath.setPosition(this.posX+2, this.posY, this.posZ-2);
    		wrath.setAttackTarget(target);

    		skelly.setPosition(this.posX+2, this.posY, this.posZ+2);
    		skelly.func_189768_a(SkeletonType.WITHER);
    		skelly.setAttackTarget(target);
    		
    		this.worldObj.spawnEntityInWorld(wraith);
    		this.worldObj.spawnEntityInWorld(chain);
    		this.worldObj.spawnEntityInWorld(wrath);
    		this.worldObj.spawnEntityInWorld(skelly);
    		
    	}else if(randAttk == 3){ //Incase Target in Stone
    		if(target instanceof EntityMobLich) return; //Don't bother if against a lich
    		
    		int j = (int) MathHelper.floor_double(target.posX);
    		int k = (int) MathHelper.floor_double(target.posY);
    		int l = (int) MathHelper.floor_double(target.posZ);
    		
    		for(int xj=-1; xj<2; xj++){
    			for(int yj=-1; yj<1; yj++){
    				for(int zj=-1; yj<1; yj++){
    					if(this.worldObj.isAirBlock(new BlockPos(xj, yj, zj))) this.worldObj.setBlockState(new BlockPos(xj, yj, zj), Blocks.STONE.getDefaultState(), 3);
    				}
    			}
    		}
    	}else if(randAttk == 2){ //EntityLightningBolt at Target
    		double x = target.posX;
    		double y = target.posY;
    		double z = target.posZ;
    		
    		EntityLightningBolt entitylightningbolt = new EntityLightningBolt(worldObj, x, y, z, false);
    		entitylightningbolt.setLocationAndAngles(x, y + 1 +entitylightningbolt.getYOffset(), z, target.rotationYaw, target.rotationPitch);
    		worldObj.addWeatherEffect(entitylightningbolt);
    		
    	}else if(randAttk == 1){ //EntityLargeFireball at Target
    		double d5 = target.posX - this.posX;
    		double d6 = target.getEntityBoundingBox().minY + (double)(target.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
    		double d7 = target.posZ - this.posZ;
    		this.worldObj.playEvent((EntityPlayer)null, 1016, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
    		EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.worldObj, this, d5, d6, d7);
    		double d8 = 4.0D;
    		//Vec3d vec3 = this.getLook(1.0F);
    		entitylargefireball.posX = this.posX;// + vec3.xCoord * d8;
    		entitylargefireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
    		entitylargefireball.posZ = this.posZ;// + vec3.zCoord * d8;
    		this.worldObj.spawnEntityInWorld(entitylargefireball);
    		
    	}else if(randAttk == 0){ //Speak TODO
    		
    	}
    }
}