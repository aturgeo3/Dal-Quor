package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.specteraxe.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageSpecterAxe1 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Specter Axe", new ItemStack[] {
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.tools.voidAxe),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm) }, new ItemStack(voidCraft.tools.spectreAxe));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
