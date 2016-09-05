package Tamaized.Voidcraft.xiaCastle.logic.doors;

import net.minecraft.util.math.AxisAlignedBB;
import Tamaized.Voidcraft.xiaCastle.XiaCastleHandler;

public class XiaCastleDoorLogicXia extends XiaCastleDoorLogicBase {

	public XiaCastleDoorLogicXia(XiaCastleHandler handler) {
		super(handler, handler.locations[handler.door_Xia], new AxisAlignedBB(0, 0, 0, 0, 0, 0));
	}

}
