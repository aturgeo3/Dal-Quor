package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidspade;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidSpade {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Void Shovel", new ItemStack[] {
						null,
						new ItemStack(voidCraft.items.voidcrystal),
						null,
						null,
						new ItemStack(Blocks.OBSIDIAN),
						null,
						null,
						new ItemStack(Items.DIAMOND),
						null }, new ItemStack(voidCraft.tools.voidSpade))) };
	}

}
