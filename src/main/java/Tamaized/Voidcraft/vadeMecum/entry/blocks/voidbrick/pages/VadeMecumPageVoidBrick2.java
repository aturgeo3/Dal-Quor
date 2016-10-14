package Tamaized.Voidcraft.vadeMecum.entry.blocks.voidbrick.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VadeMecumPageVoidBrick2 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			new ItemStack(voidCraft.blocks.blockVoidcrystal),
			new ItemStack(voidCraft.blocks.blockVoidcrystal),
			null,
			new ItemStack(voidCraft.blocks.blockVoidcrystal),
			new ItemStack(voidCraft.blocks.blockVoidcrystal),
			null,
			null,
			null,
			null }, new ItemStack(voidCraft.blocks.blockVoidbrick, 1));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
