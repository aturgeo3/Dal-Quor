package Tamaized.Voidcraft.vadeMecum.entry.machines.generator.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageGenerator3 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			new ItemStack(Items.REDSTONE),
			new ItemStack(Items.REDSTONE),
			new ItemStack(Items.REDSTONE),
			new ItemStack(Items.REDSTONE),
			new ItemStack(voidCraft.blocks.voidInfuser),
			new ItemStack(Items.REDSTONE),
			new ItemStack(Items.REDSTONE),
			new ItemStack(Items.REDSTONE),
			new ItemStack(Items.REDSTONE) }, new ItemStack(voidCraft.blocks.voidicGen, 1));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
