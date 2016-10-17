package Tamaized.Voidcraft.handlers.rituals;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VadeMecumRitualIntro {
	
	public static void invoke(IVadeMecumCapability cap, World world, BlockPos pos){
		world.setBlockToAir(pos);
		cap.addCategory(IVadeMecumCapability.Category.INTRO);
	}

}
