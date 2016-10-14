package Tamaized.Voidcraft.vadeMecum.entry.tools.voidicdrill.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageVoidicDrill2 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			new ItemStack(voidCraft.blocks.blockVoidcrystal),
			new ItemStack(voidCraft.blocks.realityHole),
			new ItemStack(voidCraft.blocks.blockVoidcrystal),
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(voidCraft.blocks.voidicCharger),
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.voidStar),
			new ItemStack(voidCraft.items.ectoplasm) }, new ItemStack(voidCraft.items.voidicDrill));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
