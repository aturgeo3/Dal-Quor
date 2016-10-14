package Tamaized.Voidcraft.vadeMecum;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumCraftingNormal implements IVadeMecumCrafting {

	private static final ResourceLocation TEXTURE = new ResourceLocation(voidCraft.modid, "textures/gui/VadeMecum/Crafting_Normal.png");

	private final String title;
	private final ItemStack[] input;
	private final ItemStack output;

	public VadeMecumCraftingNormal(String title, ItemStack[] input, ItemStack output) {
		this.title = title;
		this.input = input;
		this.output = output;
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65, y, 0x000000);
		GlStateManager.color(1, 1, 1, 1);
		gui.mc.getTextureManager().bindTexture(TEXTURE);
		RenderHelper.enableGUIStandardItemLighting();
		gui.drawTexturedModalRect(x, y + 35, 128, 128, 0, 0, 256, 256);

		if (input[0] != null) gui.renderItemStack(input[0], x + 15, y + 50);
		if (input[1] != null) gui.renderItemStack(input[1], x + 55, y + 50);
		if (input[2] != null) gui.renderItemStack(input[2], x + 95, y + 50);

		if (input[3] != null) gui.renderItemStack(input[3], x + 15, y + 90);
		if (input[4] != null) gui.renderItemStack(input[4], x + 55, y + 90);
		if (input[5] != null) gui.renderItemStack(input[5], x + 95, y + 90);

		if (input[6] != null) gui.renderItemStack(input[6], x + 15, y + 130);
		if (input[7] != null) gui.renderItemStack(input[7], x + 55, y + 130);
		if (input[8] != null) gui.renderItemStack(input[8], x + 95, y + 130);

		gui.renderItemStack(output, x + 55, y + 20);
		RenderHelper.disableStandardItemLighting();
	}

}
