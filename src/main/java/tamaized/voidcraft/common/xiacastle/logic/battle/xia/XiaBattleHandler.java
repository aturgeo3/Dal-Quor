package tamaized.voidcraft.common.xiacastle.logic.battle.xia;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tamaized.voidcraft.common.entity.boss.xia.EntityBossXia;
import tamaized.voidcraft.common.xiacastle.logic.battle.IBattleHandler;
import tamaized.voidcraft.registry.VoidCraftBlocks;

import java.util.ArrayList;
import java.util.List;

public class XiaBattleHandler implements IBattleHandler {

	private boolean running = false;
	private boolean isDone = false;

	private World worldObj;

	private AxisAlignedBB checkBB;
	private List<EntityPlayer> players = new ArrayList<>();
	private EntityBossXia xia;

	public List<EntityPlayer> getPlayers() {
		return players;
	}

	@Override
	public void update() {
		if (worldObj != null && !worldObj.isRemote && running) {
			if (checkBB != null)
				players.removeIf(player -> !checkBB.contains(player.getPositionVector()));
			if(players.isEmpty())
				stop();
			if (xia == null || xia.isDead)
				setDone();
		}
	}

	@Override
	public void start(World world, BlockPos p) {
		worldObj = world;
		stop();
		BlockPos doorPos = new BlockPos(54, 76, 82);
		for (int x = 0; x > -5; x--) {
			for (int y = 0; y < 4; y++) {
				worldObj.setBlockState(doorPos.add(x, y, 0), (x == 0 || x == -4 || y == 0 || y == 3) ? VoidCraftBlocks.realityHole.getDefaultState() : VoidCraftBlocks.blockNoBreak.getDefaultState());
			}
		}
		isDone = false;
		running = true;
		xia = new EntityBossXia(worldObj);
		xia.setPositionAndUpdate(p.getX() + 0.5, p.getY() + 17.5, p.getZ() + 43.5);
		worldObj.spawnEntity(xia);
		checkBB = new AxisAlignedBB(p.add(-19, -1, -3), p.add(19, 26, 51));
		players.addAll(worldObj.getEntitiesWithinAABB(EntityPlayer.class, checkBB));
	}

	@Override
	public void stop() {
		players.clear();
		isDone = false;
		if(xia != null) {
			xia.setDead();
			xia = null;
		}
		if (worldObj != null) {
			BlockPos doorPos = new BlockPos(54, 76, 82);
			for (int x = 0; x > -5; x--) {
				for (int y = 0; y < 4; y++) {
					worldObj.setBlockToAir(doorPos.add(x, y, 0));
				}
			}
		}
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
	}

}
