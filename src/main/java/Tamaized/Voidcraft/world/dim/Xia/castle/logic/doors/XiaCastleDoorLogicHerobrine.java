package Tamaized.Voidcraft.world.dim.Xia.castle.logic.doors;

import net.minecraft.util.math.AxisAlignedBB;
import Tamaized.Voidcraft.world.dim.Xia.castle.XiaCastleHandler;

public class XiaCastleDoorLogicHerobrine extends XiaCastleDoorLogicBase {

	public XiaCastleDoorLogicHerobrine(XiaCastleHandler handler) {
		super(handler, handler.locations[handler.door_Herobrine], new AxisAlignedBB(0, 0, 0, 0, 0, 0));
	}

}
