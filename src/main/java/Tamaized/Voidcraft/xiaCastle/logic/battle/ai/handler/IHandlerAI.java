package Tamaized.Voidcraft.xiaCastle.logic.battle.ai.handler;

import net.minecraft.util.math.BlockPos;

public interface IHandlerAI {
	
	public void Init();
	
	public void update();
	
	public void removeTileEntity(BlockPos pos);

	public void kill();

}
