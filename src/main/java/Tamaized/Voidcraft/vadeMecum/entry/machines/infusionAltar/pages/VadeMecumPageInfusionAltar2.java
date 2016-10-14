package Tamaized.Voidcraft.vadeMecum.entry.machines.infusionAltar.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageInfusionAltar2 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Inert Infusion Altar Recipe", new ItemStack[] {
			new ItemStack(voidCraft.blocks.blockVoidbrick),
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(voidCraft.blocks.blockVoidbrick),
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(Items.CAULDRON),
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(voidCraft.blocks.blockVoidbrick),
			new ItemStack(voidCraft.items.voidCloth),
			new ItemStack(voidCraft.blocks.blockVoidbrick) }, new ItemStack(voidCraft.blocks.voidInfuserInert, 1));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
