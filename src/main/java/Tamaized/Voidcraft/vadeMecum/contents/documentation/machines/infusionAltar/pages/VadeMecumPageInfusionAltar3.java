package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.infusionAltar.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageInfusionAltar3 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Infusion Altar Recipe", new ItemStack[] {
			new ItemStack(voidCraft.blocks.voidInfuserInert),
			new ItemStack(voidCraft.items.voidStar),
			null,
			null,
			null,
			null,
			null,
			null,
			null }, new ItemStack(voidCraft.blocks.voidInfuser, 1));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		crafting.render(gui, render, x + offset, y, mx, my);
	}

}
