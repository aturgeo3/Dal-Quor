package tamaized.voidcraft.common.vademecum.contents.documentation.blocks.ritual;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListRitualBlock implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.blocks.ritualBlock).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.ritualBlock"), new VadeMecumPageCrafting(new VadeMecumCraftingNormal("recipe.normal", new ItemStack(VoidCraft.blocks.ritualBlock, 8)))};
	}

}
