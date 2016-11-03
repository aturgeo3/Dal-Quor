package Tamaized.Voidcraft.GUI.client;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.server.RealityTeleporterContainer;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import Tamaized.Voidcraft.items.RealityTeleporter;
import Tamaized.Voidcraft.items.inventory.InventoryItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RealityTeleporterGUI extends GuiContainer {

	private static final ResourceLocation daTexture = new ResourceLocation(voidCraft.modid, "textures/gui/voidCharger.png");
	
	private final ItemStack parent;
	private final InventoryItem itemInventory;
	private final IVoidicPowerCapability cap;

	public RealityTeleporterGUI(InventoryPlayer inventoryPlayer, ItemStack host) {
		super(new RealityTeleporterContainer(inventoryPlayer, host));
		parent = host;
		itemInventory = RealityTeleporter.createInventory(parent);
		cap = parent.hasCapability(CapabilityList.VOIDICPOWER, null) ? parent.getCapability(CapabilityList.VOIDICPOWER, null) : null;
		xSize = 347;
		ySize = 320;
	}

	@Override
	public void updateScreen() {

		{
			float scale = 47;
			int k = cap == null ? 0 : (int) (cap.getAmountPerc() * scale);
			drawTexturedModalRect(guiLeft + 124, guiTop + 128 - k, 12, 470 - (k), 12, k + 1);
		}

		super.updateScreen();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = "Reality Teleporter";
		this.fontRendererObj.drawString(text, this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 2, this.ySize - 260, 0xAAAAAA);
		text = "Voidic Power:";
		this.fontRendererObj.drawString(text, (this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 1) - 55, this.ySize / 2 - 70, 0xFF0000);
		text = cap == null ? "N/A" : cap.getCurrentPower() + "/";
		this.fontRendererObj.drawString(text, (this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 1) - 55, this.ySize / 2 - 60, 0xFF0000);
		text = cap == null ? "N/A" : cap.getMaxPower()+"";
		this.fontRendererObj.drawString(text, (this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 1) - 55, this.ySize / 2 - 50, 0xFF0000);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		{
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			Minecraft.getMinecraft().getTextureManager().bindTexture(daTexture);
			drawTexturedModalRect(guiLeft + 78, guiTop + 66, 0, 0, xSize / 2, ySize / 2);
			this.updateScreen();
		}
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}

}
