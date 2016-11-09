package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.obsidianflask;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListObsidianFlask implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.items.emptyObsidianFlask).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.emptyObsidianFlask.pg1"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.emptyObsidianFlask.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						null,
						null,
						null,
						new ItemStack(Blocks.OBSIDIAN),
						new ItemStack(Blocks.GLASS),
						new ItemStack(Blocks.OBSIDIAN),
						null,
						new ItemStack(Blocks.OBSIDIAN),
						null }, new ItemStack(voidCraft.items.emptyObsidianFlask, 4))) };
	}

}
