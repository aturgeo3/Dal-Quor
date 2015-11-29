package Tamaized.Voidcraft.world.dim.Xia.castle.logic.doors;

import net.minecraft.util.AxisAlignedBB;
import Tamaized.Voidcraft.world.dim.Xia.castle.XiaCastleBase;
import Tamaized.Voidcraft.world.dim.Xia.castle.XiaCastleHandler;

public class XiaCastleDoorLogicTwins extends XiaCastleDoorLogicBase {

	public XiaCastleDoorLogicTwins(XiaCastleHandler handler) {
		super(handler, handler.locations[handler.door_Twins], AxisAlignedBB.getBoundingBox(0, 0, 0, 0, 0, 0));
	}

}
