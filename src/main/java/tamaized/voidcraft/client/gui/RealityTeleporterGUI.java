package tamaized.voidcraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.CapabilityList;
import tamaized.voidcraft.common.capabilities.voidicPower.IVoidicPowerCapability;
import tamaized.voidcraft.common.gui.container.RealityTeleporterContainer;
import tamaized.voidcraft.common.items.RealityTeleporter;
import tamaized.voidcraft.network.server.ServerPacketHandlerLinkClear;

@SideOnly(Side.CLIENT)
public class RealityTeleporterGUI extends GuiContainer {

	private static final ResourceLocation daTexture = new ResourceLocation(VoidCraft.modid, "textures/gui/voidCharger.png");

	private final ItemStack parent;
	private final int slotID;
	private final IVoidicPowerCapability cap;

	private GuiButton button_clearLink;

	private static final int BUTTON_LINK_CLEAR = 0;

	public RealityTeleporterGUI(InventoryPlayer inventoryPlayer, ItemStack host) {
		super(new RealityTeleporterContainer(inventoryPlayer, host));
		parent = host;
		cap = parent.hasCapability(CapabilityList.VOIDICPOWER, null) ? parent.getCapability(CapabilityList.VOIDICPOWER, null) : null;
		xSize = 347;
		ySize = 320;
		int temp = -2;
		for (int i = 0; i < inventoryPlayer.mainInventory.size(); i++) {
			if (ItemStack.areItemStacksEqual(parent, inventoryPlayer.mainInventory.get(i))) {
				temp = i;
				break;
			}
		}
		if (temp == -2) {
			if (ItemStack.areItemStacksEqual(parent, inventoryPlayer.offHandInventory.get(0)))
				temp = -1;
		}
		slotID = temp;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	public void initGui() {
		super.initGui();

		buttonList.clear();
		buttonList.add(button_clearLink = new GuiButton(BUTTON_LINK_CLEAR, guiLeft + 170, guiTop + 122, 56, 20, I18n.format("voidcraft.gui.misc.button.clearlink")));
	}

	@Override
	public void updateScreen() {

		button_clearLink.enabled = RealityTeleporter.hasLink(parent);

		{
			float scale = 47;
			int k = cap == null ? 0 : (int) (cap.getAmountPerc() * scale);
			drawTexturedModalRect(guiLeft + 124, guiTop + 128 - k, 12, 470 - (k), 12, k + 1);
		}

		super.updateScreen();
	}

	@Override
	public void actionPerformed(GuiButton button) {
		switch (button.id) {
			case BUTTON_LINK_CLEAR:
				VoidCraft.network.sendToServer(new ServerPacketHandlerLinkClear.Packet(slotID));
				RealityTeleporter.clearLink(parent);
				break;
			default:
				break;
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = I18n.format("voidcraft.gui.realityteleporter.title");
		fontRenderer.drawString(text, xSize / 2 - fontRenderer.getStringWidth(text) / 2, ySize - 260, 0xAAAAAA);
		BlockPos linkPos = RealityTeleporter.getLink(parent);
		if (linkPos != null) {
			text = I18n.format("voidcraft.gui.realityteleporterblock.link") + ":";
			fontRenderer.drawString(text, (xSize / 2) + 30, ySize / 2 - 70, 0xAAAAAA);
			text = "{ x: " + linkPos.getX() + ", y:" + linkPos.getY() + ", z:" + linkPos.getZ() + " }";
			fontRenderer.drawString(text, (xSize / 2) + 30, ySize / 2 - 60, 0xAAAAAA);
		} else {
			text = I18n.format("voidcraft.gui.realityteleporterblock.nolink") + ":";
			fontRenderer.drawString(text, (xSize / 2) + 30, ySize / 2 - 70, 0xAAAAAA);
		}
		text = I18n.format("voidcraft.gui.misc.power") + ":";
		fontRenderer.drawString(text, (xSize / 2 - fontRenderer.getStringWidth(text)) - 55, ySize / 2 - 70, 0xFF0000);
		text = cap == null ? "N/A" : cap.getCurrentPower() + "/";
		fontRenderer.drawString(text, (xSize / 2 - fontRenderer.getStringWidth(text)) - 55, ySize / 2 - 60, 0xFF0000);
		text = cap == null ? "N/A" : cap.getMaxPower() + "";
		fontRenderer.drawString(text, (xSize / 2 - fontRenderer.getStringWidth(text)) - 55, ySize / 2 - 50, 0xFF0000);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GlStateManager.pushMatrix();
		{
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			Minecraft.getMinecraft().getTextureManager().bindTexture(daTexture);
			drawTexturedModalRect(guiLeft + 78, guiTop + 66, 0, 0, xSize / 2, ySize / 2);
			updateScreen();
		}
		GlStateManager.popMatrix();
	}

}
