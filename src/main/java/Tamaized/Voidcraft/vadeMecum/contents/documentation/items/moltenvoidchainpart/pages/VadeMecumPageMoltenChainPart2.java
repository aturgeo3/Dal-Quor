package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.moltenvoidchainpart.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingFurnace;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VadeMecumPageMoltenChainPart2 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingFurnace("Furnace Recipe", new ItemStack(voidCraft.items.voidChain), new ItemStack(voidCraft.items.MoltenvoidChainPart));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
