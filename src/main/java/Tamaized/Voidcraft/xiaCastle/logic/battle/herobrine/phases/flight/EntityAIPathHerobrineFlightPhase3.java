package Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.flight;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.SkeletonType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineFireball;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineShadow;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineTNTPrimed;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineWitherSkull;
import Tamaized.Voidcraft.network.VoidBossAIBus.Packet;
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
		loc = getEntity().getPosition().add(0, 10, 0);
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

				double watcherX = getEntity().getPosition().getX();
				double watcherY = getEntity().getPosition().getZ();
				double watcherZ = getEntity().getPosition().getX();

				Random rand = new Random();
				switch (rand.nextInt(5)) {
					case 0: // Fireball
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
						break;
					case 1: // Spawn Creepers
						for (int i = 0; i < 4; i++) {
							EntityHerobrineCreeper creeper = new EntityHerobrineCreeper(getEntity().worldObj);
							creeper.setPosition(getEntity().getPosition().getX() + rand.nextInt(18) - 8, getEntity().getPosition().getY() - 6, getEntity().getPosition().getZ() + rand.nextInt(18) - 8);
							getEntity().worldObj.spawnEntityInWorld(creeper);
						}
						break;
					case 2: // Primed TNT
						for (int i = 0; i < 2; i++) {
							EntityHerobrineTNTPrimed tnt = new EntityHerobrineTNTPrimed(getEntity().worldObj);
							tnt.setPosition(getEntity().getPosition().getX() + rand.nextInt(18) - 8, getEntity().getPosition().getY() - 6, getEntity().getPosition().getZ() + rand.nextInt(18) - 8);
							getEntity().worldObj.spawnEntityInWorld(tnt);
						}
						break;
					case 3: // Shadow Clone
						double d52 = closestEntity.posX - getEntity().posX;
						double d62 = closestEntity.getEntityBoundingBox().minY + (double) (closestEntity.height / 2.0F) - (getEntity().posY + (double) (getEntity().height / 2.0F));
						double d72 = closestEntity.posZ - getEntity().posZ;

						EntityHerobrineShadow entityHerobrineShadow = new EntityHerobrineShadow(getEntity().worldObj, getEntity(), d52, d62, d72);
						double d82 = 4.0D;
						Vec3d vec32 = getEntity().getLook(1.0F);
						entityHerobrineShadow.posX = getEntity().posX;// + vec3.xCoord * d8;
						entityHerobrineShadow.posY = getEntity().posY + (double) (getEntity().height / 2.0F) + 0.5D;
						entityHerobrineShadow.posZ = getEntity().posZ;// + vec3.zCoord * d8;
						entityHerobrineShadow.prevRotationYaw = getEntity().prevRotationYaw - 180;
						entityHerobrineShadow.rotationYaw = getEntity().rotationYaw - 180;
						entityHerobrineShadow.prevRotationYawHead = getEntity().prevRotationYawHead - 180;
						entityHerobrineShadow.rotationYawHead = getEntity().rotationYawHead - 180;
						getEntity().worldObj.spawnEntityInWorld(entityHerobrineShadow);
						break;
					case 4: // Wither Skeleton Spawns with EntityWitherSkulls from the walls
						for (int i = 0; i < 4; i++) {
							EntitySkeleton skele = new EntitySkeleton(getEntity().worldObj);
							skele.setSkeletonType(SkeletonType.WITHER);
							skele.setPosition(getEntity().getPosition().getX() + rand.nextInt(18) - 8, getEntity().getPosition().getY() - 6, getEntity().getPosition().getZ() + rand.nextInt(18) - 8);
							getEntity().worldObj.spawnEntityInWorld(skele);
						}
						EntityHerobrineWitherSkull skull1 = new EntityHerobrineWitherSkull(getEntity().worldObj, getEntity(), (watcherX - 7) - watcherX, (watcherY - 10) - watcherY, (watcherZ - 0) - watcherZ);
						EntityHerobrineWitherSkull skull2 = new EntityHerobrineWitherSkull(getEntity().worldObj, getEntity(), (watcherX + 7) - watcherX, (watcherY - 10) - watcherY, (watcherZ - 0) - watcherZ);
						EntityHerobrineWitherSkull skull3 = new EntityHerobrineWitherSkull(getEntity().worldObj, getEntity(), (watcherX - 0) - watcherX, (watcherY - 10) - watcherY, (watcherZ - 7) - watcherZ);
						EntityHerobrineWitherSkull skull4 = new EntityHerobrineWitherSkull(getEntity().worldObj, getEntity(), (watcherX - 0) - watcherX, (watcherY - 10) - watcherY, (watcherZ + 7) - watcherZ);
						getEntity().worldObj.spawnEntityInWorld(skull1);
						getEntity().worldObj.spawnEntityInWorld(skull2);
						getEntity().worldObj.spawnEntityInWorld(skull3);
						getEntity().worldObj.spawnEntityInWorld(skull4);
						break;
					default:
						break;
				}
			}
			tick_Fireball = 0;
		}

		double y = getEntity().posY;
		double py = loc.getY();
		double dy = 0;

		if (y < py) dy = 0.2;
		else if (y == py) dy = 0.0;
		else {
			getEntity().posY = py;
			dy = 0;
		}
		getEntity().posY += dy;

		currTick++;
		tick_Fireball++;
	}

}
