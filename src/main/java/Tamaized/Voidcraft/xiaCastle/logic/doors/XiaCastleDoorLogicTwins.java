package Tamaized.Voidcraft.xiaCastle.logic.doors;

import net.minecraft.util.math.AxisAlignedBB;
import Tamaized.Voidcraft.xiaCastle.XiaCastleHandler;

public class XiaCastleDoorLogicTwins extends XiaCastleDoorLogicBase {

	public XiaCastleDoorLogicTwins(XiaCastleHandler handler) {
		super(handler, handler.locations[handler.door_Twins], new AxisAlignedBB(0, 0, 0, 0, 0, 0));
	}

}
