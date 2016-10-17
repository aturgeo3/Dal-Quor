package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidaxe;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidAxe {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Void Axe", new ItemStack[] {
						null,
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						null,
						new ItemStack(Blocks.OBSIDIAN),
						new ItemStack(voidCraft.items.voidcrystal),
						null,
						new ItemStack(Items.DIAMOND),
						null }, new ItemStack(voidCraft.tools.voidAxe)))};
	}

}
