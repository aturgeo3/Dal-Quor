package Tamaized.Voidcraft.mobs;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class EntityVoidNPC extends EntityCreature implements IMob
{
	
	private boolean invulnerable = false;
	protected boolean canDie = true;
	protected boolean canPush = true;
	protected boolean isFlying = false;
    private static final String __OBFID = "CL_00001692";

	private int[] spawnLoc;
	private boolean firstSpawn = true;

    public EntityVoidNPC(World p_i1738_1_){
        super(p_i1738_1_);
        this.experienceValue = 10;
        this.ignoreFrustumCheck = true;
    }
    
    public void writeEntityToNBT(NBTTagCompound nbt){
		super.writeEntityToNBT(nbt);
	}
	
	public void readEntityFromNBT(NBTTagCompound nbt){
		super.readEntityFromNBT(nbt);
	}

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate(){
        this.updateArmSwingProgress();
        
        float f = this.getBrightness(1.0F);

        if (f > 0.5F)
        {
            this.entityAge += 2;
        }

        super.onLivingUpdate();
    }
    
    public boolean isEntityFlying(){
    	return isFlying;
    }
    
    @Override
    public boolean canBePushed(){
        return canPush;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate(){
        super.onUpdate();
    		
        if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL){
            this.setDead();
        }
    }
    
    /**
     * Checks whether target entity is alive.
     */
    public boolean isEntityAlive()
    {
        return !canDie ? true : !this.isDead && this.getHealth() > 0.0F;
    }
    
    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_){
    	/*if(isFlying){
    		jumpMovementFactor = 0.0F;
    		double d3 = this.motionY;
        	
        	this.motionY = d3 * 0.6D;
    	}else{
    		jumpMovementFactor = 0.2F;
    		super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
    	}*/
    	//super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
    	this.prevLimbSwingAmount = this.limbSwingAmount;
        double d0 = this.posX - this.prevPosX;
        double d1 = this.posZ - this.prevPosZ;
        float f6 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

        if (f6 > 1.0F)
        {
            f6 = 1.0F;
        }

        this.limbSwingAmount += (f6 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    protected String getSwimSound(){
        return "game.hostile.swim";
    }

    protected String getSplashSound(){
        return "game.hostile.swim.splash";
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack(){
        EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 16.0D);
        return entityplayer != null && this.canEntityBeSeen(entityplayer) ? entityplayer : null;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_){
        if (this.isEntityInvulnerable()){
            return false;
        }else if (super.attackEntityFrom(p_70097_1_, p_70097_2_)){
            Entity entity = p_70097_1_.getEntity();
            if (this.riddenByEntity != entity && this.ridingEntity != entity){
                if (entity != this && entity instanceof EntityLivingBase){
                    this.setAttackTarget((EntityLivingBase) entity);
                }
                return true;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
    
    /**
     * I'm lazy
     * 
     * @param b
     */
    public void setInvul(boolean b){
    	this.invulnerable = b;
    }
    
    /**
     * Return whether this entity is invulnerable to damage. Again, im lazy
     */
    public boolean isEntityInvulnerable(){
        return this.invulnerable;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound(){
        return "game.hostile.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound(){
        return "game.hostile.die";
    }

    protected String func_146067_o(int p_146067_1_){
        return p_146067_1_ > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
    }

    public boolean attackEntityAsMob(Entity p_70652_1_){
    	//if(p_70652_1_ instanceof VoidChain) return false;
    	
    	float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;

        if (p_70652_1_ instanceof EntityLivingBase){
        	f += EnchantmentHelper.func_152377_a(this.getHeldItem(), ((EntityLivingBase)p_70652_1_).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag){
            if (i > 0){
                p_70652_1_.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0){
                p_70652_1_.setFire(j * 4);
            }
            
            if (p_70652_1_ instanceof EntityLivingBase){
                EnchantmentHelper.applyThornEnchantments((EntityLivingBase)p_70652_1_, this);
            }
            
            EnchantmentHelper.applyThornEnchantments(this, p_70652_1_);
        }

        return flag;
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     *//*
    protected void attackEntity(Entity p_70785_1_, float p_70785_2_){
        if (this.attackTime <= 0 && p_70785_2_ < 2.0F && p_70785_1_.boundingBox.maxY > this.boundingBox.minY && p_70785_1_.boundingBox.minY < this.boundingBox.maxY){
            this.attackTime = 20;
            this.attackEntityAsMob(p_70785_1_);
        }
    }*/

    /**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
    public float getBlockPathWeight(BlockPos pos){
        return 0.5F - this.worldObj.getLightBrightness(pos);
    }

    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     * BUT! MY NPCS DONT CARE SO YEA; this always returns false bro
     */
    protected boolean isValidLightLevel(){
        return false;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere(){
        return this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.getCanSpawnHere();
    }

    protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
    }

    protected boolean func_146066_aG(){
        return true;
    }
}