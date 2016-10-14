package Tamaized.Voidcraft.vadeMecum.entry.weapons.angelicsword.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VadeMecumPageAngelicSword2 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.tools.voidSword),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm) }, new ItemStack(voidCraft.tools.angelicSword));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
