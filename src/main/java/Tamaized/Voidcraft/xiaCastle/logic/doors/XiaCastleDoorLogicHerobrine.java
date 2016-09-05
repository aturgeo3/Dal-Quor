package Tamaized.Voidcraft.xiaCastle.logic.doors;

import net.minecraft.util.math.AxisAlignedBB;
import Tamaized.Voidcraft.xiaCastle.XiaCastleHandler;

public class XiaCastleDoorLogicHerobrine extends XiaCastleDoorLogicBase {

	public XiaCastleDoorLogicHerobrine(XiaCastleHandler handler) {
		super(handler, handler.locations[handler.door_Herobrine], new AxisAlignedBB(0, 0, 0, 0, 0, 0));
	}

}
