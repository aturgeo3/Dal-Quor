package Tamaized.Voidcraft.world.dim.Xia.castle.logic.doors;

import net.minecraft.util.math.AxisAlignedBB;
import Tamaized.Voidcraft.world.dim.Xia.castle.XiaCastleHandler;

public class XiaCastleDoorLogicXia extends XiaCastleDoorLogicBase {

	public XiaCastleDoorLogicXia(XiaCastleHandler handler) {
		super(handler, handler.locations[handler.door_Xia], new AxisAlignedBB(0, 0, 0, 0, 0, 0));
	}

}
