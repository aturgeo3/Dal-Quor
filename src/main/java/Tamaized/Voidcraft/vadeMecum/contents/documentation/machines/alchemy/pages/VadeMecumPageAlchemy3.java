package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.alchemy.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageAlchemy3 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			new ItemStack(voidCraft.blocks.blockVoidbrick),
			new ItemStack(voidCraft.blocks.voidicCable),
			new ItemStack(voidCraft.blocks.blockVoidbrick),
			new ItemStack(voidCraft.blocks.voidicCable),
			new ItemStack(Items.BREWING_STAND),
			new ItemStack(voidCraft.blocks.voidicCable),
			new ItemStack(voidCraft.blocks.blockVoidbrick),
			new ItemStack(voidCraft.blocks.voidInfuserInert),
			new ItemStack(voidCraft.blocks.blockVoidbrick) }, new ItemStack(voidCraft.blocks.voidicAlchemyTable, 1));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y, mx, my);
	}

}
