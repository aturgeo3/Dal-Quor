package Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.flight;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.network.VoidBossAIBus.Packet;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;

public class EntityAIPathHerobrineFlightPhase2 extends EntityVoidNPCAIBase {

	private boolean xPos = true;
	private boolean zPos = true;

	private int currTick = 0;
	private int tick_Damage = 0;

	private int callTick = 2 * 20;

	private boolean inBlock = false;
	private int blockTick = 0;

	public EntityAIPathHerobrineFlightPhase2(EntityVoidBoss entityBoss, ArrayList<Class> c) {
		super(entityBoss, c);
	}

	@Override
	public void readPacket(Packet packet) {
		
	}

	@Override
	public void updateTask() {
		super.updateTask();
		if (currTick >= callTick) {
			for (Class c : watchedClass) {
				Entity e = getEntity().worldObj.findNearestEntityWithinAABB(c, getEntity().getEntityBoundingBox().expand((double) maxDistanceForPlayer, (double) maxDistanceForPlayer, (double) maxDistanceForPlayer), getEntity());
				if (e != null) {
					closestEntity = e;
					break;
				}
				closestEntity = null;
			}
			currTick = 0;
		} else {
			currTick++;
		}

		if (closestEntity != null) {

			getEntity().getLookHelper().setLookPosition(closestEntity.posX, closestEntity.posY + (double) closestEntity.getEyeHeight() - 1, closestEntity.posZ, 10.0F, (float) getEntity().getVerticalFaceSpeed());

			double d0 = closestEntity.posX - getEntity().posX;
			double d2 = closestEntity.posZ - getEntity().posZ;
			float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;

			float f3 = MathHelper.wrapDegrees(f - getEntity().rotationYaw);

			getEntity().rotationYaw = getEntity().rotationYaw + f3;

			double x = getEntity().posX;
			double y = getEntity().posY;
			double z = getEntity().posZ;

			double px = closestEntity.posX;
			double py = y;
			double pz = closestEntity.posZ;

			double dx = 0;
			double dy = 0;
			double dz = 0;

			if (px > x) xPos = true;
			else xPos = false;

			if (pz > z) zPos = true;
			else zPos = false;

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

			double ezMin = closestEntity.getEntityBoundingBox().minZ;
			double ezMax = closestEntity.getEntityBoundingBox().maxZ;
			double exMin = closestEntity.getEntityBoundingBox().minX;
			double exMax = closestEntity.getEntityBoundingBox().maxX;
			if (tick_Damage <= 0) {
				if (z >= ezMin && z <= ezMax && x >= exMin && x <= exMax) {
					closestEntity.attackEntityFrom(DamageSource.causeMobDamage(getEntity()), 45);
					tick_Damage = 20;
				}
			} else {
				tick_Damage -= tick_Damage > 0 ? 1 : 0;
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
		}

		if (!getEntity().worldObj.isRemote) {
			Block b = getEntity().worldObj.getBlockState(new BlockPos(MathHelper.floor_double(getEntity().posX), MathHelper.floor_double(getEntity().posY), MathHelper.floor_double(getEntity().posZ))).getBlock();
			if (b instanceof AIBlock) {
				if (!inBlock) {
					TileEntity te = ((AIBlock) b).getMyTileEntity(getEntity().worldObj, new BlockPos(MathHelper.floor_double(getEntity().posX), MathHelper.floor_double(getEntity().posY), MathHelper.floor_double(getEntity().posZ)));
					if (te instanceof TileEntityAIBlock) {
						((TileEntityAIBlock) te).boom();
						inBlock = true;
						blockTick = 40;
					}
				}
			} else {
				if (blockTick <= 0) inBlock = false;
				else blockTick--;
			}
		}

	}

}
