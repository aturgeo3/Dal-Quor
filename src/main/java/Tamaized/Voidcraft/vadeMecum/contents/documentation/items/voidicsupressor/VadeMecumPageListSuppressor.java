package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidicsupressor;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListSuppressor implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.items.voidicSuppressor).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.voidicSuppressor"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(Items.COMPASS),
						ItemStack.EMPTY,
						new ItemStack(Items.REDSTONE),
						new ItemStack(voidCraft.items.voidCloth),
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						ItemStack.EMPTY }, new ItemStack(voidCraft.items.voidicSuppressor)))};
	}

}
