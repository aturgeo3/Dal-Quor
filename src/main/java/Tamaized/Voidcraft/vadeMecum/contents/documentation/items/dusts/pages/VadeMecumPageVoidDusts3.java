package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.dusts.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingMacerator;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageVoidDusts3 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingMacerator("Macerator Recipe", new ItemStack(Blocks.IRON_ORE), new ItemStack(voidCraft.items.ironDust, 4));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y, mx, my);
	}

}
