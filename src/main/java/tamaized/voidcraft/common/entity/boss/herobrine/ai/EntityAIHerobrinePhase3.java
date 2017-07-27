package tamaized.voidcraft.common.entity.boss.herobrine.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import tamaized.voidcraft.common.entity.boss.herobrine.EntityBossHerobrine;
import tamaized.voidcraft.common.entity.boss.herobrine.extra.*;
import tamaized.voidcraft.common.entity.ghost.EntityGhostPlayer;
import tamaized.voidcraft.common.entity.ghost.EntityGhostPlayerBase;
import tamaized.voidcraft.common.handlers.SkinHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class EntityAIHerobrinePhase3 extends EntityAIBase {

	private static final int tick_doAction = 3 * 20;
	private final EntityBossHerobrine boss;
	private List<EntityGhostPlayerBase> ghosts = new ArrayList<>();
	private int tick = 1;
	private int tick_Spawn = 30 * 20;

	public EntityAIHerobrinePhase3(EntityBossHerobrine entity) {
		boss = entity;
		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		return boss.getPhase() == 2;
	}

	@Override
	public void resetTask() {
		for (EntityGhostPlayerBase ghost : ghosts) {
			ghost.setDead();
		}
		ghosts.clear();
	}

	@Override
	public void startExecuting() {
		boss.setPositionAndUpdate(boss.getInitialPos().add(0.5F, 0, 0.5F));
		for (int x = -10; x <= 10; x++)
			for (int z = -10; z <= 10; z++) {
				if ((x == 0 && z == 0) || Math.floor(Math.random() * 10) != 0)
					continue;
				boss.world.setBlockState(boss.getInitialPos().add(x, -1, z), Blocks.LAVA.getDefaultState());
			}
		boss.getMoveHelper().setMoveTo(boss.getInitialPos().getX(), boss.getInitialPos().getY() + 10, boss.getInitialPos().getZ(), 0.5F);
		tick_Spawn = (10 + boss.getRNG().nextInt(21)) * 20;
	}

	@Override
	public void updateTask() {
		if (tick % tick_doAction == 0)
			updateAction();
		if (ghosts.isEmpty() && tick >= tick_Spawn) {
			EntityGhostPlayerBase ghost1 = EntityGhostPlayer.newInstance(boss.world, getRandomUnusedUUID(0), true, boss, 20 * 10);
			ghost1.setPositionAndUpdate(boss.getInitialPos().getX() + 8.5, boss.getInitialPos().getY(), boss.getInitialPos().getZ() + 0.5);
			ghosts.add(ghost1);
			boss.world.spawnEntity(ghost1);

			EntityGhostPlayerBase ghost2 = EntityGhostPlayer.newInstance(boss.world, getRandomUnusedUUID(0), true, boss, 20 * 10);
			ghost2.setPositionAndUpdate(boss.getInitialPos().getX() - 7.5, boss.getInitialPos().getY(), boss.getInitialPos().getZ() + 0.5);
			ghosts.add(ghost2);
			boss.world.spawnEntity(ghost2);

			EntityGhostPlayerBase ghost3 = EntityGhostPlayer.newInstance(boss.world, getRandomUnusedUUID(0), true, boss, 20 * 10);
			ghost3.setPositionAndUpdate(boss.getInitialPos().getX() + 0.5, boss.getInitialPos().getY(), boss.getInitialPos().getZ() + 8.5);
			ghosts.add(ghost3);
			boss.world.spawnEntity(ghost3);

			EntityGhostPlayerBase ghost4 = EntityGhostPlayer.newInstance(boss.world, getRandomUnusedUUID(0), true, boss, 20 * 10);
			ghost4.setPositionAndUpdate(boss.getInitialPos().getX() + 0.5, boss.getInitialPos().getY(), boss.getInitialPos().getZ() - 7.5);
			ghosts.add(ghost4);
			boss.world.spawnEntity(ghost4);
		}
		int flag = 0;
		for (EntityGhostPlayerBase ghost : ghosts) {
			if (ghost.isRuneCharged())
				flag++;
		}
		if (flag >= 4) {
			for (EntityGhostPlayerBase ghost : ghosts)
				ghost.setDead();
			ghosts.clear();
			boss.doDamage(25);
			tick = 0;
		}
		tick++;
	}

	private void updateAction() {
		if (boss.getAttackTarget() != null) {

			double watcherX = boss.getPosition().getX();
			double watcherY = boss.getPosition().getZ();
			double watcherZ = boss.getPosition().getX();

			Random rand = new Random();
			switch (rand.nextInt(5)) {
				case 0: // Fireball
					boss.world.playRecord(new BlockPos((int) boss.posX, (int) boss.posY, (int) boss.posZ), null);// ((EntityPlayer)null, 1008, new BlockPos((int)theWatcher.posX, (int)theWatcher.posY, (int)theWatcher.posZ), 0);
					double d5 = boss.getAttackTarget().posX - boss.posX;
					double d6 = boss.getAttackTarget().getEntityBoundingBox().minY + (double) (boss.getAttackTarget().height / 2.0F) - (boss.posY + (double) (boss.height / 2.0F));
					double d7 = boss.getAttackTarget().posZ - boss.posZ;

					EntityHerobrineFireball entitylargefireball = new EntityHerobrineFireball(boss.world, boss, d5, d6, d7);
					double d8 = 4.0D;
					Vec3d vec3 = boss.getLook(1.0F);
					entitylargefireball.posX = boss.posX;// + vec3.xCoord * d8;
					entitylargefireball.posY = boss.posY + (double) (boss.height / 2.0F) + 0.5D;
					entitylargefireball.posZ = boss.posZ;// + vec3.zCoord * d8;
					boss.world.spawnEntity(entitylargefireball);
					break;
				case 1: // Spawn Creepers
					for (int i = 0; i < 4; i++) {
						EntityHerobrineCreeper creeper = new EntityHerobrineCreeper(boss.world);
						creeper.setPosition(boss.getPosition().getX() + rand.nextInt(18) - 8, boss.getPosition().getY() - 6, boss.getPosition().getZ() + rand.nextInt(18) - 8);
						boss.world.spawnEntity(creeper);
					}
					break;
				case 2: // Primed TNT
					for (int i = 0; i < 2; i++) {
						EntityHerobrineTNTPrimed tnt = new EntityHerobrineTNTPrimed(boss.world);
						tnt.setPosition(boss.getPosition().getX() + rand.nextInt(18) - 8, boss.getPosition().getY() - 6, boss.getPosition().getZ() + rand.nextInt(18) - 8);
						boss.world.spawnEntity(tnt);
					}
					break;
				case 3: // Shadow Clone
					double d52 = boss.getAttackTarget().posX - boss.posX;
					double d62 = boss.getAttackTarget().getEntityBoundingBox().minY + (double) (boss.getAttackTarget().height / 2.0F) - (boss.posY + (double) (boss.height / 2.0F));
					double d72 = boss.getAttackTarget().posZ - boss.posZ;

					EntityHerobrineShadow entityHerobrineShadow = new EntityHerobrineShadow(boss.world, boss, d52, d62, d72);
					double d82 = 4.0D;
					Vec3d vec32 = boss.getLook(1.0F);
					entityHerobrineShadow.posX = boss.posX;// + vec3.xCoord * d8;
					entityHerobrineShadow.posY = boss.posY + (double) (boss.height / 2.0F) + 0.5D;
					entityHerobrineShadow.posZ = boss.posZ;// + vec3.zCoord * d8;
					entityHerobrineShadow.prevRotationYaw = boss.prevRotationYaw - 180;
					entityHerobrineShadow.rotationYaw = boss.rotationYaw - 180;
					entityHerobrineShadow.prevRotationYawHead = boss.prevRotationYawHead - 180;
					entityHerobrineShadow.rotationYawHead = boss.rotationYawHead - 180;
					boss.world.spawnEntity(entityHerobrineShadow);
					break;
				case 4: // Wither Skeleton Spawns with EntityWitherSkulls from the walls (TODO: Currently fires from Herobrine, look into what we want to do here)
					for (int i = 0; i < 4; i++) {
						EntityWitherSkeleton skele = new EntityWitherSkeleton(boss.world);
						skele.setPosition(boss.getPosition().getX() + rand.nextInt(18) - 8, boss.getPosition().getY() - 6, boss.getPosition().getZ() + rand.nextInt(18) - 8);
						boss.world.spawnEntity(skele);
					}
					EntityHerobrineWitherSkull skull1 = new EntityHerobrineWitherSkull(boss.world, boss, (watcherX - 7) - watcherX, (watcherY - 10) - watcherY, (watcherZ - 0) - watcherZ);
					EntityHerobrineWitherSkull skull2 = new EntityHerobrineWitherSkull(boss.world, boss, (watcherX + 7) - watcherX, (watcherY - 10) - watcherY, (watcherZ - 0) - watcherZ);
					EntityHerobrineWitherSkull skull3 = new EntityHerobrineWitherSkull(boss.world, boss, (watcherX - 0) - watcherX, (watcherY - 10) - watcherY, (watcherZ - 7) - watcherZ);
					EntityHerobrineWitherSkull skull4 = new EntityHerobrineWitherSkull(boss.world, boss, (watcherX - 0) - watcherX, (watcherY - 10) - watcherY, (watcherZ + 7) - watcherZ);
					boss.world.spawnEntity(skull1);
					boss.world.spawnEntity(skull2);
					boss.world.spawnEntity(skull3);
					boss.world.spawnEntity(skull4);
					break;
				default:
					break;
			}
		}
	}

	private UUID getRandomUnusedUUID(int j) {
		int i;
		if (j == 0)
			i = (int) Math.floor(Math.random() * SkinHandler.getSize());
		else
			i = j;
		if (i >= SkinHandler.getSize())
			i = 0;
		boolean flag = false;
		for (EntityGhostPlayerBase ghost : ghosts)
			if (ghost.getUUID() == SkinHandler.getUUID(i))
				flag = true;
		return flag ? getRandomUnusedUUID(i + 1) : SkinHandler.getUUID(i);
	}

}
