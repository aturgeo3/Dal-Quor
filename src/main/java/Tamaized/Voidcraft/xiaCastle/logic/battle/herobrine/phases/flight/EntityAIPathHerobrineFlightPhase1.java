package Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.flight;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineFireball;
import Tamaized.Voidcraft.network.VoidBossAIBus.Packet;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;

public class EntityAIPathHerobrineFlightPhase1 extends EntityVoidNPCAIBase {

	private double[][] loc = new double[8][3];
	private int currPath = 0;
	private boolean xPos = true;
	private boolean zPos = true;

	private int currTick = 0;
	private int tick_Fireball = 0;

	private int callTick = 2 * 20;
	private int callTick_Fireball = 3 * 20;

	public EntityAIPathHerobrineFlightPhase1(EntityVoidBoss entityMobHerobrine, ArrayList<Class> c) {
		super(entityMobHerobrine, c);
	}

	@Override
	public void Init() {
		super.Init();

		loc[0][0] = getEntity().posX + 10;
		loc[0][1] = getEntity().posY + 10;
		loc[0][2] = getEntity().posZ;

		loc[1][0] = getEntity().posX + 5;
		loc[1][1] = getEntity().posY + 10;
		loc[1][2] = getEntity().posZ + 5;

		loc[2][0] = getEntity().posX;
		loc[2][1] = getEntity().posY + 10;
		loc[2][2] = getEntity().posZ + 10;

		loc[3][0] = getEntity().posX - 5;
		loc[3][1] = getEntity().posY + 10;
		loc[3][2] = getEntity().posZ + 5;

		loc[4][0] = getEntity().posX - 10;
		loc[4][1] = getEntity().posY + 10;
		loc[4][2] = getEntity().posZ;

		loc[5][0] = getEntity().posX - 5;
		loc[5][1] = getEntity().posY + 10;
		loc[5][2] = getEntity().posZ - 5;

		loc[6][0] = getEntity().posX;
		loc[6][1] = getEntity().posY + 10;
		loc[6][2] = getEntity().posZ - 10;

		loc[7][0] = getEntity().posX + 5;
		loc[7][1] = getEntity().posY + 10;
		loc[7][2] = getEntity().posZ - 5;
	}

	@Override
	public void readPacket(Packet packet) {
		
	}

	@Override
	public void updateTask() {
		super.updateTask();
		if (currTick == callTick) {
			for (Class c : watchedClass) {
				Entity e = getEntity().worldObj.findNearestEntityWithinAABB(c, getEntity().getEntityBoundingBox().expand((double) maxDistanceForPlayer, 30.0D, (double) maxDistanceForPlayer), getEntity());
				if (e != null) {
					closestEntity = e;
					break;
				}
				closestEntity = null;
			}
			currTick = 0;
		}

		if (closestEntity != null) {
			getEntity().getLookHelper().setLookPosition(closestEntity.posX, closestEntity.posY + (double) closestEntity.getEyeHeight(), closestEntity.posZ, 10.0F, (float) getEntity().getVerticalFaceSpeed());

			double d0 = closestEntity.posX - getEntity().posX;
			double d2 = closestEntity.posZ - getEntity().posZ;
			float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;

			float f3 = MathHelper.wrapDegrees(f - getEntity().rotationYaw);

			getEntity().rotationYaw = getEntity().rotationYaw + f3;
		}

		if (tick_Fireball >= callTick_Fireball) {
			if (closestEntity != null) {
				getEntity().worldObj.playRecord(new BlockPos((int) getEntity().posX, (int) getEntity().posY, (int) getEntity().posZ), null);// ((EntityPlayer)null, 1008, new BlockPos((int)theWatcher.posX, (int)theWatcher.posY, (int)theWatcher.posZ), 0);
				double d5 = closestEntity.posX - getEntity().posX;
				double d6 = closestEntity.getEntityBoundingBox().minY + (double) (closestEntity.height / 2.0F) - (getEntity().posY + (double) (getEntity().height / 2.0F));
				double d7 = closestEntity.posZ - getEntity().posZ;

				EntityHerobrineFireball entitylargefireball = new EntityHerobrineFireball(getEntity().worldObj, getEntity(), d5, d6, d7);
				double d8 = 4.0D;
				Vec3d vec3 = getEntity().getLook(1.0F);
				entitylargefireball.posX = getEntity().posX;// + vec3.xCoord * d8;
				entitylargefireball.posY = getEntity().posY + (double) (getEntity().height / 2.0F) + 0.5D;
				entitylargefireball.posZ = getEntity().posZ;// + vec3.zCoord * d8;
				getEntity().worldObj.spawnEntityInWorld(entitylargefireball);
			}
			tick_Fireball = 0;
		}

		double x = getEntity().posX;
		double y = getEntity().posY;
		double z = getEntity().posZ;

		double px = loc[currPath][0];
		double py = loc[currPath][1];
		double pz = loc[currPath][2];

		double dx = 0;
		double dy = 0;
		double dz = 0;

		if (x == px && y == py && z == pz) {
			currPath = (int) Math.floor(Math.random() * 7);
			if (currPath > 7) currPath = 0;

			if (loc[currPath][0] > x) xPos = true;
			else xPos = false;

			if (loc[currPath][2] > z) zPos = true;
			else zPos = false;
		}

		if (x < px) dx = getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
		else if (x == px) dx = 0;
		else if (!xPos && (x - px) < getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()) {
			dx = 0;
			getEntity().posX = px;
		} else if (xPos && (px - x) < getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()) {
			dx = 0;
			getEntity().posX = px;
		} else if (px < x) dx = -(getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

		if (y < py) dy = 0.2;
		else if (y == py) dy = 0.0;
		else {
			getEntity().posY = py;
			dy = 0;
		}

		/*
		 * if(zPos){ if(z < pz) dz = entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(); else if(z==pz) dz = 0; else dz = -(z-pz); }else{ if(pz < z) dz = -entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(); else if(z==pz) dz = 0; else dz = (pz-z); }
		 */

		if (z < pz) dz = getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
		else if (z == pz) dz = 0;
		else if (!zPos && (z - pz) < getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()) {
			dz = 0;
			getEntity().posZ = pz;
		} else if (zPos && (pz - z) < getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()) {
			dz = 0;
			getEntity().posZ = pz;
		} else if (pz < z) dz = -(getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

		// entity.setVelocity(0, dy, 0);
		// entity.velocityChanged = true;
		getEntity().posX += dx;
		getEntity().posZ += dz;
		getEntity().posY += dy;

		currTick++;
		tick_Fireball++;
	}

}
