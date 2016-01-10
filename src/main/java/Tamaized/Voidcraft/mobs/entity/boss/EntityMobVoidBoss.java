package Tamaized.Voidcraft.mobs.entity.boss;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.mobs.EntityVoidBossMob;


public class EntityMobVoidBoss extends EntityVoidBossMob implements IBossDisplayData{
	
	private boolean hasExploded = false;

	public EntityMobVoidBoss(World p_i1738_1_) {
		super(p_i1738_1_);
		
		this.isImmuneToFire = true;
        this.setSize(0.9F, 3.0F);
        this.experienceValue = 5000;
        
        //Begin Spawn Sequence
        this.setInvul(true);
        this.setHealth(1);
	}
	
	/**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setFloat("Health", this.getHealth());
        p_70014_1_.setBoolean("HasExploded", this.hasExploded);
        p_70014_1_.setBoolean("Invul", this.getInvul());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        
        this.setHealth(p_70037_1_.getFloat("Health"));
        this.setInvul(p_70037_1_.getBoolean("Invul"));
        this.hasExploded = p_70037_1_.getBoolean("HasExploded");
        
        if(hasExploded) BeginAI();
    }
	
	private void BeginAI(){
		//this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIBreakDoor(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityGolem.class, 1.0D, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityMob.class, 1.0D, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityAnimal.class, 1.0D, true));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntitySlime.class, 1.0D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGolem.class, true)); //IronGolem and Snowman extend this
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, true)); //Normal Minecraft mobs
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAnimal.class, true)); //Passive animals
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySlime.class, true)); //Slime extends living so need to add it manually
	}
	
	
	public boolean isAIEnabled() {
        return true;
	}

	
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.50000000298023224D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(100.0D);
    }
    
    @Override
    public void onLivingUpdate(){	
    	//The Spawn Explosion stuff
    	if(!hasExploded){
    		if(this.getHealth() < this.getMaxHealth()){
    			if (this.ticksExisted % 10 == 0)
                {
                    this.heal(30.0F);
                }
    		}
    		
    		if(this.getHealth() >= this.getMaxHealth()){
    			this.hasExploded = true;
    			this.worldObj.newExplosion(this, this.posX, this.posY + (double)this.getEyeHeight(), this.posZ, 7.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
                this.worldObj.playBroadcastSound(1013, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
                this.BeginAI();
                this.setInvul(false);
    		}
    	}/*else{
    		EntityLivingBase target = this.getAITarget();
        	
        	//If target is higher, begin flight
        	if(target != null && target.posY > this.posY){
        		if (this.motionY < 0.0D)
                {
                    this.motionY = 0.0D;
                }

                this.motionY += (0.5D - this.motionY) * 0.4000000238418579D;
        	}
    	}
    	*/
    	
    	super.onLivingUpdate();
    }
    
 
    

    @Override
    protected String getLivingSound()
    {
    	return "mob.wither.idle";
    }

    @Override
    protected String getHurtSound()
    {
    	return "mob.wither.hurt";
    }

    @Override
    protected String getDeathSound()
    {
    	return "mob.wither.death";
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }
    
    protected Item getDropItem() {
        return voidCraft.items.voidStar;
    }
    
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_){
    	this.dropItem(getDropItem(), 1);
    }
    
    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }
	
	
	
	
	
	
	
	
}