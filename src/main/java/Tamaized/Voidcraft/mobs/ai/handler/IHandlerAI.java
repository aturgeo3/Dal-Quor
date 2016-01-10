package Tamaized.Voidcraft.mobs.ai.handler;

import net.minecraft.util.BlockPos;

public interface IHandlerAI {
	
	public void Init();
	
	public void update();
	
	public void removeTileEntity(BlockPos pos);

	public void kill();

}
