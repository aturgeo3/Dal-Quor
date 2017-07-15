package tamaized.voidcraft.common.vademecum.contents.documentation.machines.macerator;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListMacerator implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidMacerator).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.voidMacerator"), new VadeMecumPageCrafting(new VadeMecumCraftingInfuser(VoidCraft.modid + ".VadeMecum.recipe.infusion", new ItemStack(VoidCraft.blocks.voidMacerator, 1)))};
	}

}
