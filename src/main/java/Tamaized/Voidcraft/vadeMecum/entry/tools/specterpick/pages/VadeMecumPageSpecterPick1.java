package Tamaized.Voidcraft.vadeMecum.entry.tools.specterpick.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VadeMecumPageSpecterPick1 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Specter Pickaxe", new ItemStack[] {
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.tools.voidPickaxe),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm),
			new ItemStack(voidCraft.items.ectoplasm) }, new ItemStack(voidCraft.tools.spectrePickaxe));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
