package tamaized.dalquor.common.entity.boss.xia.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import tamaized.dalquor.common.entity.boss.xia.EntityBossXia;

public class EntityAIXia1Phase2 extends EntityAIBase implements EntityBossXia.IDamageListener {

	private static final AxisAlignedBB teleportationBox = new AxisAlignedBB(-18, 0, -25, 18, 5, 6);
	private final EntityBossXia boss;

	public EntityAIXia1Phase2(EntityBossXia entity) {
		boss = entity;
		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		return boss.getPhase() == 1;
	}

	@Override
	public void startExecuting() {
		boss.setPositionAndUpdate(boss.getInitialPos().getX() + 0.5F, boss.getInitialPos().getY() - 0.5F, boss.getInitialPos().getZ() + 0.5F);
	}

	@Override
	public boolean execute() {
		return shouldExecute();
	}

	@Override
	public void onTakeDamage() {
		doTeleport();
	}

	@Override
	public void onDoDamage(Entity entity) {
		doTeleport();
	}

	private void doTeleport() {
		BlockPos pos = getNextTeleportLocation();
		boss.setPositionAndUpdate(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
	}

	private BlockPos getNextTeleportLocation() {
		if (boss.getInitialPos() == null)
			return boss.getPosition();
		double x = (boss.world.rand.nextDouble() * (teleportationBox.maxX - teleportationBox.minX)) + teleportationBox.minX;
		double y = teleportationBox.maxY;
		double z = (boss.world.rand.nextDouble() * (teleportationBox.maxZ - teleportationBox.minZ)) + teleportationBox.minZ;
		BlockPos pos = new BlockPos(boss.getInitialPos().getX() + x, boss.getInitialPos().getY() + y, boss.getInitialPos().getZ() + z);
		while (boss.world.isAirBlock(pos.down()))
			pos = pos.down();
		return pos;
	}

	private void updateMotion() {
		EntityMoveHelper moveHelper = boss.getMoveHelper();
		if (boss.getAttackTarget() != null) {
			moveHelper.setMoveTo(boss.getAttackTarget().posX, boss.getAttackTarget().posY, boss.getAttackTarget().posZ, 1);
		}
	}

	@Override
	public void updateTask() {
		if (boss.getAttackTarget() != null)
			updateMotion();
	}
}
