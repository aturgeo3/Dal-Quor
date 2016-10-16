package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.obsidianflask.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageObsidianFlask3 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			null,
			null,
			null,
			new ItemStack(Blocks.OBSIDIAN),
			new ItemStack(Blocks.GLASS),
			new ItemStack(Blocks.OBSIDIAN),
			null,
			new ItemStack(Blocks.OBSIDIAN),
			null }, new ItemStack(voidCraft.items.emptyObsidianFlask, 4));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		crafting.render(gui, render, x + offset, y, mx, my);
	}

}
