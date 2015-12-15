package Tamaized.Voidcraft.world.dim.Xia.castle.logic.doors;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import Tamaized.Voidcraft.world.dim.Xia.castle.XiaCastleBase;
import Tamaized.Voidcraft.world.dim.Xia.castle.XiaCastleHandler;

public class XiaCastleDoorLogicBase extends XiaCastleBase{
	
	protected final int[][] door;
	protected final AxisAlignedBB trigger;
	protected boolean closed = false;

	public XiaCastleDoorLogicBase(XiaCastleHandler handler, int[][] d, AxisAlignedBB bounds) {
		super(handler);
		door = d;
		trigger = bounds;
	}

	@Override
	public void update() {
		if(!closed && !handler.getWorld().getEntitiesWithinAABB(EntityPlayer.class, trigger).isEmpty()){
			closed = true;
			setDoor(true);
		}
	}
	
	private void setDoor(boolean b){
		int x1 = door[0][0];
		int y1 = door[0][1];
		int z1 = door[0][2];
		int x2 = door[1][0];
		int y2 = door[1][1];
		int z2 = door[1][2];
		for(int cx = x1; cx <= x2; cx++){
			for(int cy = y1; cy <= y2; cy++){
				for(int cz = z1; cz <= z2; cz++){
					if(b)handler.getWorld().setBlock(cx, cy, cz, Blocks.bedrock);
					else handler.getWorld().setBlockToAir(cx, cy, cz);
				}
			}
		}
	}

}
