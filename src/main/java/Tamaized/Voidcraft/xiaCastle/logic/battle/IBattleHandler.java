package Tamaized.Voidcraft.xiaCastle.logic.battle;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBattleHandler {
	
	public void update();
	
	public void start(World worldObj, BlockPos pos);
	
	public void stop();
	
	public boolean isRunning();
	
	public boolean isDone();
	
	public void setDone();

}
