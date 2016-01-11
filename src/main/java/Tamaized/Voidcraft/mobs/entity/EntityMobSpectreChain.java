package Tamaized.Voidcraft.mobs.entity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import Tamaized.Voidcraft.blocks.BlockVoidbrick;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.mobs.EntityVoidMob;
import Tamaized.Voidcraft.projectiles.VoidChain;

import com.google.common.base.Predicate;

public class EntityMobSpectreChain extends EntityVoidMob implements IRangedAttackMob{
	
    public EntityMobSpectreChain(World par1World) {
    	
    	 super(par1World);
    	 
    	 this.isImmuneToFire = true;
    	 
         this.setSize(0.9F, 2.0F);
         
         this.tasks.addTask(1, new EntityAISwimming(this));
         //this.tasks.addTask(2, new EntityAIRestrictSun(this));
         //this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
         this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
         this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
         this.tasks.addTask(6, new EntityAILookIdle(this));
         this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.0D, 20, 50, 15.0F));
         this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
         
         Predicate ies = new Predicate()
         {
             private static final String __OBFID = "CL_00001621";
             /**
              * Return whether the specified entity is applicable to this filter.
              */
             public boolean apply(Entity p_82704_1_)
             {
            	 if(p_82704_1_ instanceof EntitySkeleton && Integer.valueOf(((EntitySkeleton)p_82704_1_).getDataWatcher().getWatchableObjectByte(13))==1) return false;
            	 else return true;
             }
             public boolean apply(Object p_apply_1_)
             {
                 return p_apply_1_ instanceof Entity ? this.apply((Entity)p_apply_1_) : false;
             }
         };
         
         this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, ies));
         this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, 0, true, false, ies));
         this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySnowman.class, 0, true, false, ies));
         this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true, false, ies));
        
         }
    
    public boolean isAIEnabled() {
        return true;
    }

	
    @Override
    protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(45.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(50.0D);
    }
    
 
    public void onUpdate(){
        super.onUpdate();
        if(this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY-0.2D - (double)this.getYOffset()) + 1, MathHelper.floor_double(this.posZ))).getBlock() instanceof BlockVoidbrick) this.setDead();
    }

    @Override
    protected String getLivingSound(){
        return "VoidCraft:chain.chainLive";
    }

    @Override
    protected String getHurtSound(){
    	return "VoidCraft:wraith.wraithHurt";
    }

    @Override
    protected String getDeathSound(){
    	return "VoidCraft:wraith.wraithDeath";
    }

    @Override
    protected float getSoundVolume(){
        return 0.4F;
    }
    
    protected Item getDropItem() {
        return voidCraft.items.voidChain;
    }
    
    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }
    
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2){
    	if(!canAttack(par1EntityLivingBase)) return;
    	VoidChain entityarrow = new VoidChain(this.worldObj, this, par1EntityLivingBase, 1.6F, (float)(14 - this.worldObj.getDifficulty().getDifficultyId() * 4));
    	//EntityArrow entityarrow = new EntityArrow(this.worldObj, this, par1EntityLivingBase, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        //entityarrow.setDamage((double)(par2 * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting.getDifficultyId() * 0.11F));
        entityarrow.setDamage(50);

        if (i > 0){
        	entityarrow.setDamage(entityarrow.getDamage() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0){
        	entityarrow.setKnockbackStrength(j);
        }

        this.playSound("VoidCraft:random.chain", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }
    
}