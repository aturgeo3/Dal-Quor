package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.dusts;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingMacerator;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidDusts implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(voidCraft.modid+".VadeMecum.docs.title.voidDusts", voidCraft.modid+".VadeMecum.docs.desc.voidDusts"),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(voidCraft.modid+".VadeMecum.recipe.macerator", new ItemStack(Blocks.IRON_ORE), new ItemStack(voidCraft.items.ironDust, 4))),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(voidCraft.modid+".VadeMecum.recipe.macerator", new ItemStack(Items.DIAMOND), new ItemStack(voidCraft.items.diamondDust))),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(voidCraft.modid+".VadeMecum.recipe.macerator", new ItemStack(Blocks.COAL_ORE), new ItemStack(voidCraft.items.coalDust, 8))),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator(voidCraft.modid+".VadeMecum.recipe.macerator", new ItemStack(Items.COAL), new ItemStack(voidCraft.items.coalDust, 4))) };
	}

}
