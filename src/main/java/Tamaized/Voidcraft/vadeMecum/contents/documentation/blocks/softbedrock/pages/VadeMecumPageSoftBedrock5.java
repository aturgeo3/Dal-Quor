package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.softbedrock.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VadeMecumPageSoftBedrock5 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Soft Bedrock Slab", new ItemStack[] {
			null,
			null,
			null,
			null,
			null,
			null,
			new ItemStack(voidCraft.blocks.blockFakeBedrock),
			new ItemStack(voidCraft.blocks.blockFakeBedrock),
			new ItemStack(voidCraft.blocks.blockFakeBedrock) }, new ItemStack(voidCraft.blocks.blockFakeBedrockHalfSlab, 6));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		crafting.render(gui, render, x + offset, y, mx, my);
	}

}
