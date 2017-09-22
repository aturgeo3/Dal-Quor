package tamaized.voidcraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.gui.container.VoidBlastFurnaceContainer;
import tamaized.voidcraft.common.machina.tileentity.TileEntityVoidBlastFurnace;

public class VoidBlastFurnaceGUI extends GuiContainer {

	public TileEntityVoidBlastFurnace te;

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/voidblastfurnace.png");

	public VoidBlastFurnaceGUI(InventoryPlayer inventoryPlayer, TileEntityVoidBlastFurnace tileEntity) {
		super(new VoidBlastFurnaceContainer(inventoryPlayer, tileEntity));

		this.te = tileEntity;

		this.xSize = 347;
		this.ySize = 320;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	public void updateScreen() {

		{
			float scale = 45;
			int k = (int) (((float) te.getPowerAmount() / (float) te.getMaxPower()) * scale);
			drawTexturedModalRect(guiLeft + 114, guiTop + 134 - k, 12, 497 - (k), 12, k);
		}

		{
			float scale = 16;
			int k = (int) (((float) te.cookingTick / (float) te.finishTick) * scale);
			drawTexturedModalRect(guiLeft + 188, guiTop + 117 - k, 0, 450 - k, 26, k);
		}

		super.updateScreen();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = I18n.format("voidcraft.gui.blastfurnace.title");
		this.fontRenderer.drawString(text, this.xSize / 2 - this.fontRenderer.getStringWidth(text) / 2, this.ySize - 260, 0xAAAAAA);
		text = I18n.format("voidcraft.gui.misc.power") + ":";
		this.fontRenderer.drawString(text, (this.xSize / 2 - this.fontRenderer.getStringWidth(text)) - 60, this.ySize / 2 - 65, 0xFF0000);
		text = te.getPowerAmount() + "/";
		this.fontRenderer.drawString(text, (this.xSize / 2 - this.fontRenderer.getStringWidth(text)) - 60, this.ySize / 2 - 55, 0xFF0000);
		text = "" + te.getMaxPower();
		this.fontRenderer.drawString(text, (this.xSize / 2 - this.fontRenderer.getStringWidth(text)) - 60, this.ySize / 2 - 45, 0xFF0000);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GlStateManager.pushMatrix();
		{
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
			drawTexturedModalRect(guiLeft + 78, guiTop + 66, 0, 0, xSize / 2, ySize / 2);
			this.updateScreen();
		}
		GlStateManager.popMatrix();

	}

}
