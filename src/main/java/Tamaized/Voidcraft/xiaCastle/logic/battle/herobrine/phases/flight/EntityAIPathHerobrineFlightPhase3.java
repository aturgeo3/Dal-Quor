package Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.flight;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.SkeletonType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineFireball;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineShadow;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineTNTPrimed;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineWitherSkull;
import Tamaized.Voidcraft.entity.nonliving.EntityVoidBoss;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;

public class EntityAIPathHerobrineFlightPhase3 extends EntityVoidNPCAIBase {

	private BlockPos loc;

	private int currTick = 0;
	private int tick_Fireball = 0;

	private int callTick = 2 * 20;
	private int callTick_Fireball = 3 * 20;

	public EntityAIPathHerobrineFlightPhase3(EntityVoidBoss entityBoss, ArrayList<Class> c) {
		super(entityBoss, c);
	}

	@Override
	public void Init() {
		super.Init();
		loc = entity.getPosition().add(0, 10, 0);
	}

	@Override
	public void updateTask() {
		super.updateTask();
		if (currTick == callTick) {
			for (Class c : watchedClass) {
				Entity e = theWatcher.worldObj.findNearestEntityWithinAABB(c, theWatcher.getEntityBoundingBox().expand((double) maxDistanceForPlayer, 30.0D, (double) maxDistanceForPlayer), theWatcher);
				if (e != null) {
					closestEntity = e;
					break;
				}
				closestEntity = null;
			}
			currTick = 0;
		}

		if (closestEntity != null) {
			theWatcher.getLookHelper().setLookPosition(closestEntity.posX, closestEntity.posY + (double) closestEntity.getEyeHeight(), closestEntity.posZ, 10.0F, (float) theWatcher.getVerticalFaceSpeed());

			double d0 = closestEntity.posX - theWatcher.posX;
			double d2 = closestEntity.posZ - theWatcher.posZ;
			float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;

			float f3 = MathHelper.wrapDegrees(f - theWatcher.rotationYaw);

			theWatcher.rotationYaw = theWatcher.rotationYaw + f3;
		}

		if (tick_Fireball >= callTick_Fireball) {
			if (closestEntity != null) {

				double watcherX = theWatcher.getPosition().getX();
				double watcherY = theWatcher.getPosition().getZ();
				double watcherZ = theWatcher.getPosition().getX();

				Random rand = new Random();
				switch (rand.nextInt(5)) {
					case 0: // Fireball
						theWatcher.worldObj.playRecord(new BlockPos((int) theWatcher.posX, (int) theWatcher.posY, (int) theWatcher.posZ), null);// ((EntityPlayer)null, 1008, new BlockPos((int)theWatcher.posX, (int)theWatcher.posY, (int)theWatcher.posZ), 0);
						double d5 = closestEntity.posX - theWatcher.posX;
						double d6 = closestEntity.getEntityBoundingBox().minY + (double) (closestEntity.height / 2.0F) - (theWatcher.posY + (double) (theWatcher.height / 2.0F));
						double d7 = closestEntity.posZ - theWatcher.posZ;

						EntityHerobrineFireball entitylargefireball = new EntityHerobrineFireball(theWatcher.worldObj, theWatcher, d5, d6, d7);
						double d8 = 4.0D;
						Vec3d vec3 = theWatcher.getLook(1.0F);
						entitylargefireball.posX = theWatcher.posX;// + vec3.xCoord * d8;
						entitylargefireball.posY = theWatcher.posY + (double) (theWatcher.height / 2.0F) + 0.5D;
						entitylargefireball.posZ = theWatcher.posZ;// + vec3.zCoord * d8;
						theWatcher.worldObj.spawnEntityInWorld(entitylargefireball);
						break;
					case 1: // Spawn Creepers
						for (int i = 0; i < 4; i++) {
							EntityHerobrineCreeper creeper = new EntityHerobrineCreeper(theWatcher.worldObj);
							creeper.setPosition(theWatcher.getPosition().getX() + rand.nextInt(18) - 8, theWatcher.getPosition().getY() - 6, theWatcher.getPosition().getZ() + rand.nextInt(18) - 8);
							theWatcher.worldObj.spawnEntityInWorld(creeper);
						}
						break;
					case 2: // Primed TNT
						for (int i = 0; i < 2; i++) {
							EntityHerobrineTNTPrimed tnt = new EntityHerobrineTNTPrimed(theWatcher.worldObj);
							tnt.setPosition(theWatcher.getPosition().getX() + rand.nextInt(18) - 8, theWatcher.getPosition().getY() - 6, theWatcher.getPosition().getZ() + rand.nextInt(18) - 8);
							theWatcher.worldObj.spawnEntityInWorld(tnt);
						}
						break;
					case 3: // Shadow Clone
						double d52 = closestEntity.posX - theWatcher.posX;
						double d62 = closestEntity.getEntityBoundingBox().minY + (double) (closestEntity.height / 2.0F) - (theWatcher.posY + (double) (theWatcher.height / 2.0F));
						double d72 = closestEntity.posZ - theWatcher.posZ;

						EntityHerobrineShadow entityHerobrineShadow = new EntityHerobrineShadow(theWatcher.worldObj, theWatcher, d52, d62, d72);
						double d82 = 4.0D;
						Vec3d vec32 = theWatcher.getLook(1.0F);
						entityHerobrineShadow.posX = theWatcher.posX;// + vec3.xCoord * d8;
						entityHerobrineShadow.posY = theWatcher.posY + (double) (theWatcher.height / 2.0F) + 0.5D;
						entityHerobrineShadow.posZ = theWatcher.posZ;// + vec3.zCoord * d8;
						entityHerobrineShadow.prevRotationYaw = theWatcher.prevRotationYaw - 180;
						entityHerobrineShadow.rotationYaw = theWatcher.rotationYaw - 180;
						entityHerobrineShadow.prevRotationYawHead = theWatcher.prevRotationYawHead - 180;
						entityHerobrineShadow.rotationYawHead = theWatcher.rotationYawHead - 180;
						theWatcher.worldObj.spawnEntityInWorld(entityHerobrineShadow);
						break;
					case 4: // Wither Skeleton Spawns with EntityWitherSkulls from the walls
						for (int i = 0; i < 4; i++) {
							EntitySkeleton skele = new EntitySkeleton(theWatcher.worldObj);
							skele.setSkeletonType(SkeletonType.WITHER);
							skele.setPosition(theWatcher.getPosition().getX() + rand.nextInt(18) - 8, theWatcher.getPosition().getY() - 6, theWatcher.getPosition().getZ() + rand.nextInt(18) - 8);
							theWatcher.worldObj.spawnEntityInWorld(skele);
						}
						EntityHerobrineWitherSkull skull1 = new EntityHerobrineWitherSkull(theWatcher.worldObj, theWatcher, (watcherX - 7) - watcherX, (watcherY - 10) - watcherY, (watcherZ - 0) - watcherZ);
						EntityHerobrineWitherSkull skull2 = new EntityHerobrineWitherSkull(theWatcher.worldObj, theWatcher, (watcherX + 7) - watcherX, (watcherY - 10) - watcherY, (watcherZ - 0) - watcherZ);
						EntityHerobrineWitherSkull skull3 = new EntityHerobrineWitherSkull(theWatcher.worldObj, theWatcher, (watcherX - 0) - watcherX, (watcherY - 10) - watcherY, (watcherZ - 7) - watcherZ);
						EntityHerobrineWitherSkull skull4 = new EntityHerobrineWitherSkull(theWatcher.worldObj, theWatcher, (watcherX - 0) - watcherX, (watcherY - 10) - watcherY, (watcherZ + 7) - watcherZ);
						theWatcher.worldObj.spawnEntityInWorld(skull1);
						theWatcher.worldObj.spawnEntityInWorld(skull2);
						theWatcher.worldObj.spawnEntityInWorld(skull3);
						theWatcher.worldObj.spawnEntityInWorld(skull4);
						break;
					default:
						break;
				}
			}
			tick_Fireball = 0;
		}

		double y = entity.posY;
		double py = loc.getY();
		double dy = 0;

		if (y < py) dy = 0.2;
		else if (y == py) dy = 0.0;
		else {
			entity.posY = py;
			dy = 0;
		}
		entity.posY += dy;

		currTick++;
		tick_Fireball++;
	}

}
