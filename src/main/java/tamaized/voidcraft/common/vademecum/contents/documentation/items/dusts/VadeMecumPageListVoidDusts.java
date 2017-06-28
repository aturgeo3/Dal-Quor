package tamaized.voidcraft.common.vademecum.contents.documentation.items.dusts;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumCraftingMacerator;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import tamaized.voidcraft.common.vademecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidDusts implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(VoidCraft.modid+".VadeMecum.docs.title.voidDusts", VoidCraft.modid+".VadeMecum.docs.desc.voidDusts"),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(VoidCraft.modid+".VadeMecum.recipe.macerator", new ItemStack(VoidCraft.items.ironDust, 4))),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(VoidCraft.modid+".VadeMecum.recipe.macerator", new ItemStack(VoidCraft.items.diamondDust))),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(VoidCraft.modid+".VadeMecum.recipe.macerator", new ItemStack(VoidCraft.items.coalDust, 8))),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(VoidCraft.modid+".VadeMecum.recipe.macerator", new ItemStack(VoidCraft.items.coalDust, 4))) };
	}

}
