package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.dusts;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingMacerator;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidDusts {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Void Dusts", "Void Dusts are the result of using a Void Infused Macerator. Using the device with ores provides four dust. Using it with Ingots or Gems results in one. The types of dusts that can be obtained are Iron, Gold, Copper, Tin, Lead, Quartz, Emerald, Lapis, and Diamond. The non-gem based dusts can then be smelted into ingot form. This effectively allows, to an extent, quadruple ore processing."),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator("Macerator Recipe", new ItemStack(Blocks.IRON_ORE), new ItemStack(voidCraft.items.ironDust, 4))),
				new VadeMecumPageCrafting(new VadeMecumCraftingMacerator("Macerator Recipe", new ItemStack(Items.DIAMOND), new ItemStack(voidCraft.items.diamondDust))) };
	}

}
