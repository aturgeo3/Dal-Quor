package tamaized.voidcraft.common.vademecum.contents.documentation.items.dusts;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListVoidDusts implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.voidDusts", VoidCraft.modid + ".VadeMecum.docs.desc.voidDusts"), new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(VoidCraft.modid + ".VadeMecum.recipe.macerator", new ItemStack(VoidCraft.items.ironDust, 4))), new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(VoidCraft.modid + ".VadeMecum.recipe.macerator", new ItemStack(VoidCraft.items.diamondDust))), new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(VoidCraft.modid + ".VadeMecum.recipe.macerator", new ItemStack(VoidCraft.items.coalDust, 8))), new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(VoidCraft.modid + ".VadeMecum.recipe.macerator", new ItemStack(VoidCraft.items.coalDust, 4)))};
	}

}
