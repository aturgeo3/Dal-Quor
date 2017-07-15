package tamaized.voidcraft.common.xiacastle.logic.battle;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBattleHandler {

	void update();

	void start(World worldObj, BlockPos pos);

	void stop();

	boolean isRunning();

	boolean isDone();

	void setDone();

}
