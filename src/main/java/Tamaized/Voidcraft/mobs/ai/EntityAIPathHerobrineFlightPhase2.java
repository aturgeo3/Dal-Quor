package Tamaized.Voidcraft.mobs.ai;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.mobs.EntityVoidNPC;

public class EntityAIPathHerobrineFlightPhase2 extends EntityVoidNPCAIBase{
	
	private EntityLiving theWatcher;
    /** The closest entity which is being watched by this one. */
    protected Entity closestEntity;
    /** This is the Maximum distance that the AI will look for the Entity */
    private float maxDistanceForPlayer = 30;
    private ArrayList<Class> watchedClass = new ArrayList<Class>();
    
    private EntityAIHandler ai;
    
	private boolean xPos = true;
	private boolean zPos = true;
	
	private int currTick = 0;
	private int tick_Damage = 0;
	
	private int callTick = 2*20;
	
	private boolean inBlock = false;
	private int blockTick = 0;
	
	public EntityAIPathHerobrineFlightPhase2(EntityVoidNPC entityMobHerobrine, ArrayList<Class> c) {
		watchedClass = new ArrayList<Class>();
		watchedClass.addAll(c);
		entity = entityMobHerobrine;
		theWatcher = entity;
		ai = new EntityAIHandler(entityMobHerobrine, (int) entityMobHerobrine.posX, (int) entityMobHerobrine.posY, (int) entityMobHerobrine.posZ);
	}
	
	public void kill() {
		ai.kill();
		super.kill();
	}
	
	public void Init(){
		super.Init();
		ai.Init(2);
		execute = true;
	}
	
	public void updateTask(){
		ai.update();
		
		if(currTick>=callTick){
			for(Class c : watchedClass){
				Entity e = theWatcher.worldObj.findNearestEntityWithinAABB(c, theWatcher.getBoundingBox().expand((double)maxDistanceForPlayer, (double)maxDistanceForPlayer, (double)maxDistanceForPlayer), theWatcher);
				if(e != null){
					closestEntity = e;
					break;
				}
				closestEntity = null;
			}
			currTick=0;
    	}else{
        	currTick++;
    	}
		
		if(closestEntity != null){
			
			theWatcher.getLookHelper().setLookPosition(closestEntity.posX, closestEntity.posY + (double)closestEntity.getEyeHeight() - 1, closestEntity.posZ, 10.0F, (float)theWatcher.getVerticalFaceSpeed());
			
			double d0 = closestEntity.posX - theWatcher.posX;
            double d2 = closestEntity.posZ - theWatcher.posZ;
			float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			
			float f3 = MathHelper.wrapAngleTo180_float(f - theWatcher.rotationYaw);
	        
			theWatcher.rotationYaw = theWatcher.rotationYaw + f3;
		
		
			double x = entity.posX;
			double y = entity.posY;
			double z = entity.posZ;
		
			double px = closestEntity.posX;
			double py = y;
			double pz = closestEntity.posZ;
		
			double dx = 0;
			double dy = 0;
			double dz = 0;
		
			if(px > x) xPos = true;
			else xPos = false;
			
			if(pz > z) zPos = true;
			else zPos = false;
		
			if(x < px) dx = entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
			else if(x == px) dx = 0;
			else if(!xPos && (x-px) < entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()){
				dx = 0;
				entity.posX = px;
			}
			else if(xPos && (px-x) < entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()){
				dx = 0;
				entity.posX = px;
			}
			else if(px < x) dx = -(entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
		
			if(y < py) dy = 0.2;
			else if(y == py) dy = 0.0;
			else{
				entity.posY = py;
				dy = 0;
			}
		
			double ezMin = closestEntity.getBoundingBox().minZ;
			double ezMax = closestEntity.getBoundingBox().maxZ;
			double exMin = closestEntity.getBoundingBox().minX;
			double exMax = closestEntity.getBoundingBox().maxX;
			if(tick_Damage <= 0){
				if(z >= ezMin && z <= ezMax && x >= exMin && x <= exMax){
					closestEntity.attackEntityFrom(DamageSource.causeMobDamage(entity), 45);
					tick_Damage = 20;
				}
			}else{
				tick_Damage -= tick_Damage > 0 ? 1 : 0;
			}
			
			/*
			if(zPos){
				if(z < pz) dz = entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
				else if(z==pz) dz = 0;
				else dz = -(z-pz);
			}else{
				if(pz < z) dz = -entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
				else if(z==pz) dz = 0;
				else dz = (pz-z);
			}
		 	*/
		
			if(z < pz) dz = entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
			else if(z == pz) dz = 0;
			else if(!zPos && (z-pz) < entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()){
				dz = 0;
				entity.posZ = pz;
			}
			else if(zPos && (pz-z) < entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()){
				dz = 0;
				entity.posZ = pz;
			}
			else if(pz < z) dz = -(entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());

			//entity.setVelocity(0, dy, 0);
			//entity.velocityChanged = true;
			entity.posX += dx;
			entity.posZ += dz;
			entity.posY += dy;
		}
		
		if(!entity.worldObj.isRemote){
			Block b = entity.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY), MathHelper.floor_double(entity.posZ))).getBlock();
			if(b instanceof AIBlock){
				if(!inBlock){
					TileEntity te = ((AIBlock) b).getMyTileEntity(entity.worldObj, new BlockPos(MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY), MathHelper.floor_double(entity.posZ)));
					if(te instanceof TileEntityAIBlock){
						((TileEntityAIBlock) te).boom();
						inBlock = true;
						blockTick = 40;
					}
				}
			}else{
				if(blockTick <= 0) inBlock = false;
				else blockTick--;
			}
		}

	}

}
