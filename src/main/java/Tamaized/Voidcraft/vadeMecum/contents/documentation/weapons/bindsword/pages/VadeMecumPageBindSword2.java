package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.bindsword.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VadeMecumPageBindSword2 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			new ItemStack(voidCraft.items.voidChain),
			new ItemStack(voidCraft.items.voidChain),
			new ItemStack(voidCraft.items.voidChain),
			new ItemStack(voidCraft.items.voidChain),
			new ItemStack(voidCraft.tools.voidSword),
			new ItemStack(voidCraft.items.voidChain),
			new ItemStack(voidCraft.items.voidChain),
			new ItemStack(voidCraft.items.voidChain),
			new ItemStack(voidCraft.items.voidChain) }, new ItemStack(voidCraft.tools.chainSword));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		crafting.render(gui, render, x + offset, y, mx, my);
	}

}
