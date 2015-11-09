package Tamaized.Voidcraft.mobs.xia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import Tamaized.Voidcraft.mobs.EntityVoidBossMob;
import Tamaized.Voidcraft.mobs.EntityVoidMob;

public class EntityMobXia2 extends EntityVoidBossMob{
	
	double xAdd = 0;
	double zAdd = 0;
	
    public EntityMobXia2(World par1World) {
    	super(par1World);
    	
    	
    	 
    	this.isImmuneToFire = true;
    	 
    	this.hurtResistantTime = 10;
    	 
        this.setSize(0.6F, 1.8F);
         
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        //this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.setInvul(true);
        
         
         
       
	}
    
    public boolean isAIEnabled() {
        return true;
}
    
    @Override
    public boolean canBePushed()
    {
        return false;
    }
    
    @Override
    protected void collideWithEntity(Entity par1Entity){}
    
    @Override
    public void applyEntityCollision(Entity par1Entity){}

	
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(999.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(999.0D);
    }

    @Override
    protected String getLivingSound()
    {
        return "";
    }

    @Override
    protected String getHurtSound()
    {
    	return "";
    }

    @Override
    protected String getDeathSound()
    {
    	return "";
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.0F;
    }
    
    protected int getDropItemId() {
        return -1;
}
    
    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
}

	public String getDisplayName() {
		return "Xia";
	}

    
    
}