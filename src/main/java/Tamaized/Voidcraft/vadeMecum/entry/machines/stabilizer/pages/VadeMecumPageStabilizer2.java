package Tamaized.Voidcraft.vadeMecum.entry.machines.stabilizer.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VadeMecumPageStabilizer2 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			new ItemStack(voidCraft.blocks.voidInfuserInert),
			new ItemStack(voidCraft.items.diamondDust),
			new ItemStack(voidCraft.blocks.voidInfuserInert),
			new ItemStack(voidCraft.items.diamondDust),
			new ItemStack(voidCraft.items.voidStar),
			new ItemStack(voidCraft.items.diamondDust),
			new ItemStack(voidCraft.blocks.voidInfuserInert),
			new ItemStack(voidCraft.blocks.voidicCharger),
			new ItemStack(voidCraft.blocks.voidInfuserInert) }, new ItemStack(voidCraft.blocks.realityStabilizer, 1));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
