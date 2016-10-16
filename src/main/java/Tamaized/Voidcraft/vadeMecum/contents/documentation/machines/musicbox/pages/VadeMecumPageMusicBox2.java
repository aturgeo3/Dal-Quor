package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.musicbox.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageMusicBox2 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(Blocks.JUKEBOX),
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(voidCraft.items.voidStar),
			new ItemStack(voidCraft.items.voidCloth) }, new ItemStack(voidCraft.blocks.voidBox, 1));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		crafting.render(gui, render, x + offset, y, mx, my);
	}

}
