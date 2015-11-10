package Tamaized.Voidcraft.mobs.entity;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
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
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.mobs.EntityVoidMob;

public class EntityMobVoidWrath extends EntityVoidMob implements IMob{
	
	
	
    public EntityMobVoidWrath(World par1World) {
    	
    	 super(par1World);
    	 
    	 this.isImmuneToFire = true;
    	 
         this.setSize(0.9F, 2.0F);
         
         this.getNavigator().setBreakDoors(true);
         this.tasks.addTask(0, new EntityAISwimming(this));
         this.tasks.addTask(1, new EntityAIBreakDoor(this));
         this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
         this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
         this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
         this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
         this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
         this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
         this.tasks.addTask(7, new EntityAILookIdle(this));
         this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
         
         IEntitySelector ies = new IEntitySelector()
         {
             private static final String __OBFID = "CL_00001621";
             /**
              * Return whether the specified entity is applicable to this filter.
              */
             public boolean isEntityApplicable(Entity p_82704_1_){
            	 if(p_82704_1_ instanceof EntitySkeleton && Integer.valueOf(((EntitySkeleton)p_82704_1_).getDataWatcher().getWatchableObjectByte(13))==1) return false;
            	 else return true;
             }
         };
         
         this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, ies));
         this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true, false, ies));
         this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, 0, true, false, ies));
         this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySnowman.class, 0, true, false, ies));
         this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true, false, ies));
	}
    
    public boolean isAIEnabled() {
        return true;
}

	
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000000298023224D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(40.0D);
    }
    
 
    

    @Override
    protected String getLivingSound()
    {
  
    	return "VoidCraft:wrath.breathe1";
    }

    @Override
    protected String getHurtSound()
    {
    	return "VoidCraft:wrath.hit";
    }

    @Override
    protected String getDeathSound()
    {
    	return "VoidCraft:wrath.death";
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }
    
    protected Item getDropItem() {
        return voidCraft.items.burnBone;
}
    
    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
}
    
    
}