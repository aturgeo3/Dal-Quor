package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.heimdall.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingInfuser;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageHeimdall3 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingInfuser("Infusion Recipe", new ItemStack(Blocks.BEACON), new ItemStack(voidCraft.blocks.Heimdall, 1));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		crafting.render(gui, render, x + offset, y, mx, my);
	}

}
