package Tamaized.Voidcraft.vadeMecum.entry.machines.macerator.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingInfuser;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageMacerator2 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingInfuser("Infusion Recipe", new ItemStack(Blocks.FURNACE), new ItemStack(voidCraft.blocks.voidMacerator, 1));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
