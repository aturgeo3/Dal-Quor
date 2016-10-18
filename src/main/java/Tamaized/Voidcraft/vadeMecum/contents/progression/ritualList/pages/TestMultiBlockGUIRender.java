package Tamaized.Voidcraft.vadeMecum.contents.progression.ritualList.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class TestMultiBlockGUIRender extends VadeMecumPage {

	private static final ResourceLocation TEXTURE = new ResourceLocation(voidCraft.modid, "textures/gui/VadeMecum/Ritual.png");

	public TestMultiBlockGUIRender(String title) {
		super(title, "");
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		x+=offset;
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65, y, 0x000000);
		GlStateManager.color(1, 1, 1, 1);
		
		int layerX = x;
		int layerY = y+50;

		gui.mc.getTextureManager().bindTexture(TEXTURE);
		gui.drawTexturedModalRect(layerX, layerY + 35, 128, 128, 0, 0, 256, 256);
		
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+23, layerY+90, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+39, layerY+82, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+55, layerY+74, mx, my);

		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+39, layerY+98, mx, my);
		gui.renderItemStack(new ItemStack(voidCraft.blocks.ritualBlock), layerX+55, layerY+90, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+71, layerY+82, mx, my);

		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+55, layerY+106, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+71, layerY+98, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+87, layerY+90, mx, my);
		
		layerX = x;
		layerY = y-5;

		gui.mc.getTextureManager().bindTexture(TEXTURE);
		gui.drawTexturedModalRect(layerX, layerY + 35, 128, 128, 0, 0, 256, 256);
		
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+23, layerY+90, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+39, layerY+82, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+55, layerY+74, mx, my);

		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+39, layerY+98, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+55, layerY+90, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+71, layerY+82, mx, my);

		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+55, layerY+106, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+71, layerY+98, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+87, layerY+90, mx, my);
		
		layerX = x;
		layerY = y-60;

		gui.mc.getTextureManager().bindTexture(TEXTURE);
		gui.drawTexturedModalRect(layerX, layerY + 35, 128, 128, 0, 0, 256, 256);
		
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+23, layerY+90, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+39, layerY+82, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+55, layerY+74, mx, my);

		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+39, layerY+98, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+55, layerY+90, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+71, layerY+82, mx, my);

		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+55, layerY+106, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+71, layerY+98, mx, my);
		gui.renderItemStack(new ItemStack(Blocks.DIAMOND_BLOCK), layerX+87, layerY+90, mx, my);

	}

}
