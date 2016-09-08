package Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.flight;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineFireball;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;

public class EntityAIPathHerobrineFlightPhase1 extends EntityVoidNPCAIBase{

	private double[][] loc = new double[8][3];
	private int currPath = 0;
	private boolean xPos = true;
	private boolean zPos = true;
	
	private int currTick = 0;
	private int tick_Fireball = 0;
	
	private int callTick = 2*20;
	private int callTick_Fireball = 3*20;
	
	public EntityAIPathHerobrineFlightPhase1(EntityVoidBoss entityMobHerobrine, ArrayList<Class> c) {
		super(entityMobHerobrine, c);
	}

	@Override
	public void Init(){
		super.Init();
		
		loc[0][0] = entity.posX+10;
		loc[0][1] = entity.posY+10;
		loc[0][2] = entity.posZ;
	
		loc[1][0] = entity.posX+5;
		loc[1][1] = entity.posY+10;
		loc[1][2] = entity.posZ+5;
	
		loc[2][0] = entity.posX;
		loc[2][1] = entity.posY+10;
		loc[2][2] = entity.posZ+10;
	
		loc[3][0] = entity.posX-5;
		loc[3][1] = entity.posY+10;
		loc[3][2] = entity.posZ+5;
	
		loc[4][0] = entity.posX-10;
		loc[4][1] = entity.posY+10;
		loc[4][2] = entity.posZ;
	
		loc[5][0] = entity.posX-5;
		loc[5][1] = entity.posY+10;
		loc[5][2] = entity.posZ-5;
	
		loc[6][0] = entity.posX;
		loc[6][1] = entity.posY+10;
		loc[6][2] = entity.posZ-10;
	
		loc[7][0] = entity.posX+5;
		loc[7][1] = entity.posY+10;
		loc[7][2] = entity.posZ-5;
	}

	@Override
	public void updateTask(){
		super.updateTask();
		if(currTick==callTick){
			for(Class c : watchedClass){
				Entity e = theWatcher.worldObj.findNearestEntityWithinAABB(c, theWatcher.getEntityBoundingBox().expand((double)maxDistanceForPlayer, 30.0D, (double)maxDistanceForPlayer), theWatcher);
				if(e != null){
					closestEntity = e;
					break;
				}
				closestEntity = null;
			}
			currTick=0;
    	}
		
		if(closestEntity != null){
			theWatcher.getLookHelper().setLookPosition(closestEntity.posX, closestEntity.posY + (double)closestEntity.getEyeHeight(), closestEntity.posZ, 10.0F, (float)theWatcher.getVerticalFaceSpeed());
			
			double d0 = closestEntity.posX - theWatcher.posX;
            double d2 = closestEntity.posZ - theWatcher.posZ;
			float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			
			float f3 = MathHelper.wrapDegrees(f - theWatcher.rotationYaw);
	        
			theWatcher.rotationYaw = theWatcher.rotationYaw + f3;
		}
		
		if(tick_Fireball >= callTick_Fireball){
			if(closestEntity != null){
				theWatcher.worldObj.playRecord(new BlockPos((int)theWatcher.posX, (int)theWatcher.posY, (int)theWatcher.posZ), null);//((EntityPlayer)null, 1008, new BlockPos((int)theWatcher.posX, (int)theWatcher.posY, (int)theWatcher.posZ), 0);
	    		double d5 = closestEntity.posX - theWatcher.posX;
	    		double d6 = closestEntity.getEntityBoundingBox().minY + (double)(closestEntity.height / 2.0F) - (theWatcher.posY + (double)(theWatcher.height / 2.0F));
	    		double d7 = closestEntity.posZ - theWatcher.posZ;
	                
	    		EntityHerobrineFireball entitylargefireball = new EntityHerobrineFireball(theWatcher.worldObj, theWatcher, d5, d6, d7);
	    		double d8 = 4.0D;
	    		Vec3d vec3 = theWatcher.getLook(1.0F);
	    		entitylargefireball.posX = theWatcher.posX;// + vec3.xCoord * d8;
	    		entitylargefireball.posY = theWatcher.posY + (double)(theWatcher.height / 2.0F) + 0.5D;
	    		entitylargefireball.posZ = theWatcher.posZ;// + vec3.zCoord * d8;
	    		theWatcher.worldObj.spawnEntityInWorld(entitylargefireball);
			}
			tick_Fireball = 0;
		}
		
		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;
		
		double px = loc[currPath][0];
		double py = loc[currPath][1];
		double pz = loc[currPath][2];
		
		double dx = 0;
		double dy = 0;
		double dz = 0;
		
		if(x==px && y==py && z==pz){
			currPath = (int) Math.floor(Math.random()*7);
			if(currPath > 7) currPath = 0;
			
			if(loc[currPath][0] > x) xPos = true;
			else xPos = false;
			
			if(loc[currPath][2] > z) zPos = true;
			else zPos = false;
		}
		
		if(x < px) dx = entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
		else if(x == px) dx = 0;
		else if(!xPos && (x-px) < entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()){
			dx = 0;
			entity.posX = px;
		}
		else if(xPos && (px-x) < entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()){
			dx = 0;
			entity.posX = px;
		}
		else if(px < x) dx = -(entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
		
		if(y < py) dy = 0.2;
		else if(y == py) dy = 0.0;
		else{
			entity.posY = py;
			dy = 0;
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
		
		if(z < pz) dz = entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
		else if(z == pz) dz = 0;
		else if(!zPos && (z-pz) < entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()){
			dz = 0;
			entity.posZ = pz;
		}
		else if(zPos && (pz-z) < entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()){
			dz = 0;
			entity.posZ = pz;
		}
		else if(pz < z) dz = -(entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
		

		//entity.setVelocity(0, dy, 0);
		//entity.velocityChanged = true;
		entity.posX += dx;
		entity.posZ += dz;
		entity.posY += dy;

    	currTick++;
    	tick_Fireball++;
	}

}
