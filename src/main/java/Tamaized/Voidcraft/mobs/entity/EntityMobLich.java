package Tamaized.Voidcraft.mobs.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
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
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.mobs.EntityVoidMob;

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
         this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 20, 20, 15.0F));
         this.tasks.addTask(5, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
         this.tasks.addTask(5, new EntityAIAttackOnCollide(this, EntityMob.class, 1.0D, true));
         this.tasks.addTask(5, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
         this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
         
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
         this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMobLich.class, 0, true, false, ies)); //Lich no like lich
         this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, true, false, ies)); //Passive animals
         this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySlime.class, 0, true, false, ies)); //Slime extends living so need to add it manually
	}
    
    public boolean isAIEnabled() {
        return true;
    }

	
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(120.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000000298023224D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(65.0D);
    }
    
 
    

    @Override
    protected String getLivingSound()
    {
        return "VoidCraft:lich.lichLive";
    }

    @Override
    protected String getHurtSound()
    {
    	return "VoidCraft:wraith.wraithHurt";
    }

    @Override
    protected String getDeathSound()
    {
    	return "VoidCraft:lich.lichDeath";
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.6F;
    }
    
    protected Item getDropItem() {
        return voidCraft.items.voidCloth;
    }
    
    public EntityAgeable createChild(EntityAgeable var1) {
        return null;	
    }
    
    
    //TODO Potion like attacks using a new throwable entity.
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2){	
    	if(!canAttack(par1EntityLivingBase)) return;
    	
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
    		if(par1EntityLivingBase instanceof EntityMobLich) return; //Do not summon the undead if i'm fighting another lich
    		EntityMobWraith zamby1;
    		EntityMobSpectreChain skelly1;
    		EntityMobVoidWrath zamby2;
    		EntitySkeleton skelly2;
    				
    		zamby1 = new EntityMobWraith(worldObj);
    		skelly1 = new EntityMobSpectreChain(worldObj);
    		zamby2 = new EntityMobVoidWrath(worldObj);
    		skelly2 = new EntitySkeleton(worldObj);
    				
    		skelly2.setSkeletonType(1);
    				
    		zamby1.setPosition(this.posX-2, this.posY, this.posZ-2);
    		this.worldObj.spawnEntityInWorld(zamby1);
    		skelly1.setPosition(this.posX-2, this.posY, this.posZ+2);
    		this.worldObj.spawnEntityInWorld(skelly1);
    		zamby2.setPosition(this.posX+2, this.posY, this.posZ-2);
    		this.worldObj.spawnEntityInWorld(zamby2);
    		skelly2.setPosition(this.posX+2, this.posY, this.posZ+2);
    		this.worldObj.spawnEntityInWorld(skelly2);
    				
    	}else if(randAttk == 3){ //Incase Target in Stone
    				
    		if(par1EntityLivingBase instanceof EntityMobLich) return;
    				
    		int j = (int) MathHelper.floor_double(par1EntityLivingBase.posX);
    		int k = (int) MathHelper.floor_double(par1EntityLivingBase.posY);
    		int l = (int) MathHelper.floor_double(par1EntityLivingBase.posZ);
    				
    		for(int xj=-1; xj<2; xj++){
    			for(int yj=-1; yj<1; yj++){
    				for(int zj=-1; yj<1; yj++){
    					if(this.worldObj.isAirBlock(new BlockPos(xj, yj, zj))) this.worldObj.setBlockState(new BlockPos(xj, yj, zj), Blocks.stone.getDefaultState(), 3);
    				}
    			}
    		}
    				/*
    				if(this.worldObj.isAirBlock(j-1, k, l-1)) this.worldObj.setBlock(j-1, k, l-1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j, k, l-1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j+1, k, l-1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j-1, k, l, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j, k, l, Blocks.water, 0, 3);
    				this.worldObj.setBlock(j+1, k, l, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j-1, k, l+1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j, k, l+1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j+1, k, l+1, Blocks.stone, 0, 3);
    				
    				this.worldObj.setBlock(j-1, k+1, l-1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j, k+1, l-1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j+1, k+1, l-1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j-1, k+1, l, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j, k+1, l, Blocks.water, 0, 3);
    				this.worldObj.setBlock(j+1, k+1, l, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j-1, k+1, l+1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j, k+1, l+1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j+1, k+1, l+1, Blocks.stone, 0, 3);
    				
    				this.worldObj.setBlock(j-1, k+2, l-1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j, k+2, l-1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j+1, k+2, l-1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j-1, k+2, l, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j, k+2, l, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j+1, k+2, l, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j-1, k+2, l+1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j, k+2, l+1, Blocks.stone, 0, 3);
    				this.worldObj.setBlock(j+1, k+2, l+1, Blocks.stone, 0, 3);
    				*/
    	}else if(randAttk == 2){ //EntityLightningBolt at Target
    			
    		double x = par1EntityLivingBase.posX;
    		double y = par1EntityLivingBase.posY;
    		double z = par1EntityLivingBase.posZ;
    	
    		EntityLightningBolt entitylightningbolt = new EntityLightningBolt(worldObj, x, y, z);
    		entitylightningbolt.setLocationAndAngles(x, y + 1 +entitylightningbolt.getYOffset(), z, par1EntityLivingBase.rotationYaw, par1EntityLivingBase.rotationPitch);
    		worldObj.addWeatherEffect(entitylightningbolt);
    	}
    			
    	if(randAttk == 1){ //EntityLargeFireball at Target
    		this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
    		double d5 = par1EntityLivingBase.posX - this.posX;
    		double d6 = par1EntityLivingBase.getBoundingBox().minY + (double)(par1EntityLivingBase.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
    		double d7 = par1EntityLivingBase.posZ - this.posZ;
                
    		EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.worldObj, this, d5, d6, d7);
    		double d8 = 4.0D;
    		Vec3 vec3 = this.getLook(1.0F);
    		entitylargefireball.posX = this.posX;// + vec3.xCoord * d8;
    		entitylargefireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
    		entitylargefireball.posZ = this.posZ;// + vec3.zCoord * d8;
    		this.worldObj.spawnEntityInWorld(entitylargefireball);
    	}else if(randAttk == 0){ //Speak (TODO: broken)
    				
    		double instance = Math.random()*4;
    		instance = Math.round(instance);
        			
    		if(instance == 1){
    			this.playCustomSound("VoidCraft:sounds.mobs.lich.lichActive");
    		}
        			
    		if(instance == 2){
    			this.playCustomSound("VoidCraft:sounds.mobs.lich.lichActive");
    		}
        			
    		if(instance == 3){
    			this.playCustomSound("VoidCraft:sounds.mobs.lich.lichActive");
    		}
        			
    		if(instance == 4){
    			this.playCustomSound("VoidCraft:sounds.mobs.lich.lichActive");
    		}
    				
    	}
    }
    
    
    
    public void playCustomSound(String s)
    {
        

        if (s != null)
        {
            this.playSound(s, this.getSoundVolume(), this.getSoundPitch());
        }
    }
    
    public void onUpdate(){
    	super.onUpdate();
    	/*
    	int yaw = (int)this.rotationYaw;
        if (yaw < 0) yaw += 360; //due to the yaw running a -360 to positive 360

        yaw += 22;     //centers coordinates
        yaw %= 360;  //strict interpretation of the zones
        
        int facing = yaw/90;   //  360degrees divided by 90 == 4 zones
        
        int xI = 1;
        int zI = 1;
        
        if(facing == 0) {
        	
        }else if(facing == 1) {
        	xI=-1;
        	zI=-1;
        }else if(facing == 2) {
        	
        }else if(facing == 3) {
        	xI=1;
        	zI=1;
        }
        
        double x = 1*Math.cos(yaw) + this.posX; //radius * cos(angle) + circle center X
        double z = 1*Math.sin(yaw) + this.posZ; //radius * cos(angle) + circle center Z
        
        DebugEvent.textL = x+" : "+z;
        
        //Place Particles into world
        double d0 = (double) x;
        double d1 = (double)((float)this.posY + 1.5);
        double d2 = (double) z;
        double d3 = 0.3D;
        double d4 = -0.5D;
        double d5 = -0.3D;
        
        this.worldObj.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
        */
    }
    
}