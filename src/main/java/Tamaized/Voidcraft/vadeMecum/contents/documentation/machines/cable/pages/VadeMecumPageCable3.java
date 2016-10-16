package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.cable.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageCable3 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			new ItemStack(Items.REDSTONE),
			new ItemStack(Items.REDSTONE),
			new ItemStack(Items.REDSTONE),
			new ItemStack(Items.REDSTONE),
			new ItemStack(voidCraft.blocks.blockVoidcrystal),
			new ItemStack(Items.REDSTONE),
			new ItemStack(Items.REDSTONE),
			new ItemStack(Items.REDSTONE),
			new ItemStack(Items.REDSTONE) }, new ItemStack(voidCraft.blocks.voidicCable, 8));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		crafting.render(gui, render, x + offset, y, mx, my);
	}

}
