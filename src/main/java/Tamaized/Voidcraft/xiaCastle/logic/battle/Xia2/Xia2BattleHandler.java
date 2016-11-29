package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2;

import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia2;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Xia2BattleHandler implements IBattleHandler {

	private int phase = 0;
	private int tick = 0;
	private int childPhase = 0;
	private int childPhaseModulate = 20;
	private boolean readyForInput = false;

	private boolean running;
	private boolean isDone = false;

	private World worldObj;
	private BlockPos pos;

	private EntityBossXia2 xia;

	@Override
	public void update() {
		if (worldObj != null && !worldObj.isRemote && running) {
			if (xia == null || !xia.isActive()) {
				stop();
				return;
			}
		}
	}

	@Override
	public void start(World worldObj, BlockPos pos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDone() {
		// TODO Auto-generated method stub

	}

}
