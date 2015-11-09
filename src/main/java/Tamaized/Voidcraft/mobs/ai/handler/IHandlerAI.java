package Tamaized.Voidcraft.mobs.ai.handler;

import net.minecraft.tileentity.TileEntity;
import Tamaized.Voidcraft.mobs.ai.EntityAIHandler;

public interface IHandlerAI {
	
	public void Init();
	
	public void update();
	
	public void removeTileEntity(int[] i);

	public void kill();

}
