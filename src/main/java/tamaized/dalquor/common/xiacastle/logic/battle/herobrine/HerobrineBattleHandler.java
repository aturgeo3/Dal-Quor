package tamaized.dalquor.common.xiacastle.logic.battle.herobrine;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tamaized.dalquor.common.entity.boss.herobrine.EntityBossHerobrine;
import tamaized.dalquor.common.world.dim.xia.WorldProviderXia;
import tamaized.dalquor.common.xiacastle.logic.battle.IBattleHandler;

public class HerobrineBattleHandler implements IBattleHandler {

	private int phase = 0;
	private int tick = 0;
	private int childPhase = 0;
	private int childPhaseModulate = 20;
	private boolean readyForInput = false;

	private boolean running = false;
	private boolean isDone = false;

	private World worldObj;
	private BlockPos pos;

	private EntityBossHerobrine herobrine;

	@Override
	public void update() {
		if (worldObj != null && !worldObj.isRemote && running) {
			if (herobrine == null || herobrine.isDead) {
				setDone();
			}
		}
	}

	@Override
	public void start(World world, BlockPos p) {
		worldObj = world;
		pos = p;
		stop();
		phase = 0;
		isDone = false;
		readyForInput = false;
		for (int z = -2; z <= 2; z++) {
			for (int y = 5; y > 0; y--) {
				world.setBlockState(p.add(11, y, z), Blocks.NETHER_BRICK.getDefaultState());
			}
		}
		herobrine = new EntityBossHerobrine(worldObj);
		herobrine.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
		worldObj.spawnEntity(herobrine);
		running = true;
	}

	@Override
	public void stop() {
		if (pos == null || !worldObj.isAreaLoaded(pos.add(-50, -50, -50), pos.add(50, 50, 50)))
			return;
		readyForInput = false;
		isDone = false;
		if (herobrine != null) {
			herobrine.setDead();
			herobrine = null;
		}
		for (EntityBossHerobrine boss : worldObj.getEntitiesWithinAABB(EntityBossHerobrine.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50))))
			boss.setDead();
		for (int z = -2; z <= 2; z++) {
			for (int y = 5; y > 0; y--) {
				worldObj.setBlockToAir(pos.add(11, y, z));
			}
		}
		for (int x = -10; x <= 10; x++)
			for (int z = -10; z <= 10; z++)
				for (int y = 1; y <= 3; y++)
					worldObj.setBlockToAir(pos.add(x, y, z));
		running = false;
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

	@Override
	public void setDone() {
		stop();
		isDone = true;
		if (worldObj != null && worldObj.provider instanceof WorldProviderXia)
			worldObj.provider.onWorldSave();
	}

}
