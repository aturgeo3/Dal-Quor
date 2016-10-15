package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.voidsword.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageVoidSword1 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Void Sword", new ItemStack[] {
			null,
			new ItemStack(voidCraft.items.voidcrystal),
			null,
			new ItemStack(Blocks.OBSIDIAN),
			new ItemStack(voidCraft.items.voidcrystal),
			new ItemStack(Blocks.OBSIDIAN),
			null,
			new ItemStack(Items.DIAMOND),
			null }, new ItemStack(voidCraft.tools.voidSword));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
