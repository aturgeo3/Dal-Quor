package Tamaized.Voidcraft.world.dim.Xia.castle;

import java.util.ArrayList;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import Tamaized.Voidcraft.world.dim.Xia.castle.logic.XiaCastleMain;
import Tamaized.Voidcraft.world.dim.Xia.castle.logic.doors.XiaCastleDoorLogicHerobrine;
import Tamaized.Voidcraft.world.dim.Xia.castle.logic.doors.XiaCastleDoorLogicTwins;
import Tamaized.Voidcraft.world.dim.Xia.castle.logic.doors.XiaCastleDoorLogicXia;


public class XiaCastleHandler {
	
	private World world;
	private ArrayList<XiaCastleBase> xiaLogic;
	
	public static final int[][][] locations = {
			{ {0, 0, 0}, {0, 0, 0} }, // Herobrine's Door
			{ {0, 0, 0}, {0, 0, 0} }, // Twins' Door
			{ {0, 0, 0}, {0, 0, 0} } // Xia's Door
			};
	
	public static final int door_Herobrine = 0;
	public static final int door_Twins = 1;
	public static final int door_Xia = 2;
	
	public XiaCastleHandler(World w){
		world = w;
		reset();
		xiaLogic = new ArrayList<XiaCastleBase>();
		xiaLogic.add(new XiaCastleMain(this));
		xiaLogic.add(new XiaCastleDoorLogicHerobrine(this));
		xiaLogic.add(new XiaCastleDoorLogicTwins(this));
		xiaLogic.add(new XiaCastleDoorLogicXia(this));
	}
	
	public World getWorld(){
		return world;
	}
	
	public void update(){
		for(XiaCastleBase logic : xiaLogic){
			logic.update();
		}
	}
	
	private void reset(){
		int x1, y1, z1, x2, y2, z2;
		x1 = y1 = z1 = x2 = y2 = z2 = 0;
		for(int i=0; i<locations.length; i++){
			x1 = locations[i][0][0];
			y1 = locations[i][0][1];
			z1 = locations[i][0][2];
			x2 = locations[i][1][0];
			y2 = locations[i][1][1];
			z2 = locations[i][1][2];
			for(int cx = x1; cx <= x2; cx++){
				for(int cy = y1; cy <= y2; cy++){
					for(int cz = z1; cz <= z2; cz++){
						world.setBlockToAir(new BlockPos(cx, cy, cz));
					}
				}
			}
		}
	}

}
