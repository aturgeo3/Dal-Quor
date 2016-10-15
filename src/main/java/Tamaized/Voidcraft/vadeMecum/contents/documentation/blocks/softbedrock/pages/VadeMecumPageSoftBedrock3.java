package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.softbedrock.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VadeMecumPageSoftBedrock3 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Soft Bedrock Stairs", new ItemStack[] {
			new ItemStack(voidCraft.blocks.blockFakeBedrock),
			null,
			null,
			new ItemStack(voidCraft.blocks.blockFakeBedrock),
			new ItemStack(voidCraft.blocks.blockFakeBedrock),
			null,
			new ItemStack(voidCraft.blocks.blockFakeBedrock),
			new ItemStack(voidCraft.blocks.blockFakeBedrock),
			new ItemStack(voidCraft.blocks.blockFakeBedrock) }, new ItemStack(voidCraft.blocks.blockFakeBedrockStairs, 6));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x+craftXoffset, y);
	}

}
