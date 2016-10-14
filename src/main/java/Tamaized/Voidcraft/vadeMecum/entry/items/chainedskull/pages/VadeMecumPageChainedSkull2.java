package Tamaized.Voidcraft.vadeMecum.entry.items.chainedskull.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageChainedSkull2 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			new ItemStack(voidCraft.items.MoltenvoidChain),
			new ItemStack(voidCraft.items.burnBone),
			new ItemStack(voidCraft.items.MoltenvoidChain),
			new ItemStack(voidCraft.items.burnBone),
			new ItemStack(Items.SKULL, 1, 1),
			new ItemStack(voidCraft.items.burnBone),
			new ItemStack(voidCraft.items.MoltenvoidChain),
			new ItemStack(voidCraft.items.burnBone),
			new ItemStack(voidCraft.items.MoltenvoidChain) }, new ItemStack(voidCraft.items.ChainedSkull));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
