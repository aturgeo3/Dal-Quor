package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.obsidianflask;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListObsidianFlask {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Obsidian Flask", "It is actually possible to collect 'Void' from within the Overworld. You need a special type of flask to do so. Simply get yourself some Empty Obsidian Flasks, head down near bedrock, and Right Click. What do these Flasks do you may ask? Well if you toss one (via right click) they explode on contact. They do not seem to damage the world at all but they cast a purple flame that does not seem to burn. It is a cold flame. Though coming in contact with such a flame"),
				new VadeMecumPage("", "yields interesting effects such as blindness and wither."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
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
