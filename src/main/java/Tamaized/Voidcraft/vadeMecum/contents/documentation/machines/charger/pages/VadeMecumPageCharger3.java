package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.charger.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageCharger3 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			new ItemStack(voidCraft.items.diamondDust),
			new ItemStack(voidCraft.items.voidStar),
			new ItemStack(voidCraft.items.diamondDust),
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(Items.END_CRYSTAL),
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(voidCraft.items.diamondDust),
			new ItemStack(voidCraft.blocks.voidicCable),
			new ItemStack(voidCraft.items.diamondDust) }, new ItemStack(voidCraft.blocks.voidicCharger, 1));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		crafting.render(gui, render, x + offset, y, mx, my);
	}

}
