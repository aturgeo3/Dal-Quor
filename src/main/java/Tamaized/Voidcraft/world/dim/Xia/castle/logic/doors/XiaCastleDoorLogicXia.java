package Tamaized.Voidcraft.world.dim.Xia.castle.logic.doors;

import net.minecraft.util.AxisAlignedBB;
import Tamaized.Voidcraft.world.dim.Xia.castle.XiaCastleBase;
import Tamaized.Voidcraft.world.dim.Xia.castle.XiaCastleHandler;

public class XiaCastleDoorLogicXia extends XiaCastleDoorLogicBase {

	public XiaCastleDoorLogicXia(XiaCastleHandler handler) {
		super(handler, handler.locations[handler.door_Xia], AxisAlignedBB.getBoundingBox(0, 0, 0, 0, 0, 0));
	}

}
