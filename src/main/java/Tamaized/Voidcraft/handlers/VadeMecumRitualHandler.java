package Tamaized.Voidcraft.handlers;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.handlers.rituals.VadeMecumRitualIntro;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class VadeMecumRitualHandler {
	
	public static enum Ritual {
		INTRO
	}

	public static void invokeRitual(EntityPlayer player, World world, BlockPos pos) {
		IVadeMecumCapability cap = player.getCapability(CapabilityList.VADEMECUM, null);
		if (cap == null || world.getBlockState(pos).getBlock() != voidCraft.blocks.ritualBlock) return;
		switch(getRitual(world, pos)){
			case INTRO:
				VadeMecumRitualIntro.invoke(cap, world, pos);
				break;
			default:
				player.addChatMessage(new TextComponentTranslation(TextFormatting.RED+"Unknown Ritual"));
				break;
		}
	}
	
	public static Ritual getRitual(World world, BlockPos pos){
		return Ritual.INTRO;
	}

}
