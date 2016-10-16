package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.moltensword.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VadeMecumPageMoltenSword2 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			new ItemStack(voidCraft.items.MoltenvoidChain),
			new ItemStack(voidCraft.items.MoltenvoidChain),
			new ItemStack(voidCraft.items.MoltenvoidChain),
			new ItemStack(voidCraft.items.MoltenvoidChain),
			new ItemStack(voidCraft.tools.chainSword),
			new ItemStack(voidCraft.items.MoltenvoidChain),
			new ItemStack(voidCraft.items.MoltenvoidChain),
			new ItemStack(voidCraft.items.MoltenvoidChain),
			new ItemStack(voidCraft.items.MoltenvoidChain) }, new ItemStack(voidCraft.tools.moltenSword));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		crafting.render(gui, render, x + offset, y, mx, my);
	}

}
