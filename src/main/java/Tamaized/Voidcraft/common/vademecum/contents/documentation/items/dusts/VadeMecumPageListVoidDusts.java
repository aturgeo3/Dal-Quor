package Tamaized.Voidcraft.common.vademecum.contents.documentation.items.dusts;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.common.vademecum.VadeMecumCraftingMacerator;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPageCrafting;
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
