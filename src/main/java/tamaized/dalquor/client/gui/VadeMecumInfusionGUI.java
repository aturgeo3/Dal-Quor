package tamaized.dalquor.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.network.server.ServerPacketHandlerVadeMecum;

public class VadeMecumInfusionGUI extends GuiScreen {

	private static final int BUTTON_CLOSE = 0;
	private static final int BUTTON_BACK = 1;
	private static final int BUTTON_ABILITY = 2;
	private final IVadeMecumCapability capability;

	VadeMecumInfusionGUI(IVadeMecumCapability cap) {
		capability = cap;
	}

	@Override
	public void initGui() {
		super.initGui();

		buttonList.add(new GuiButton(BUTTON_BACK, 5, height - 50, 80, 20, I18n.format("voidcraft.gui.misc.back")));
		buttonList.add(new GuiButton(BUTTON_CLOSE, 5, height - 25, 80, 20, I18n.format("voidcraft.gui.misc.close")));

		int xLoc = (width / 2) - 55;
		int yLoc = 28;

		int index = 0;
		for (IVadeMecumCapability.Passive passive : IVadeMecumCapability.Passive.values()) {
			if (capability.canHavePassive(passive))
				buttonList.add(new PassiveButton(capability, BUTTON_ABILITY, xLoc, yLoc + (25 * index), passive));
			index++;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
				case BUTTON_CLOSE:
					mc.displayGuiScreen(null);
					break;
				case BUTTON_BACK:
					mc.displayGuiScreen(new VadeMecumGUI(mc.player));
					break;
				case BUTTON_ABILITY:
					if (button instanceof PassiveButton)
						sendPacket(((PassiveButton) button).getSpell());
					break;
				default:
					break;
			}
		}
	}

	private void sendPacket(IVadeMecumCapability.Passive spell) {
		if (capability.hasPassive(spell) || capability.canHavePassive(spell))
			DalQuor.network.sendToServer(new ServerPacketHandlerVadeMecum.Packet(ServerPacketHandlerVadeMecum.RequestType.PASSIVE, IVadeMecumCapability.getPassiveID(spell)));
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void onGuiClosed() {

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCenteredString(fontRenderer, I18n.format("voidcraft.gui.voidicInfusionControl.title"), width / 2, 15, 16777215);
	}

	public class PassiveButton extends GuiButton {

		private final IVadeMecumCapability.Passive passive;
		private final IVadeMecumCapability data;

		PassiveButton(IVadeMecumCapability cap, int buttonId, int x, int y, IVadeMecumCapability.Passive thePassive) {
			super(buttonId, x, y, 110, 18, "");
			data = cap;
			passive = thePassive;
		}

		public IVadeMecumCapability.Passive getSpell() {
			return passive;
		}

		public boolean isSet() {
			return data != null && data.hasPassive(passive);
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float p_191745_4_) {
			if (visible) {
				FontRenderer fontrenderer = mc.fontRenderer;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
				int i = getHoverState(hovered);
				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
				GlStateManager.blendFunc(770, 771);
				// drawRect(x + width / 2, y, width / 2, height, 0xFFFFFFFF);
				mouseDragged(mc, mouseX, mouseY);
				int j = 0xBBFFFFFF;

				if (isSet()) {
					j = hovered ? 0xFF99FF99 : 0xFF00FF00;
				} else if (hovered) {
					j = 0xFFFFFFFF;
				}

				drawRect(x, y, x + width, y + height, j);
				GlStateManager.pushMatrix();
				fontrenderer.drawString(I18n.format(IVadeMecumCapability.getPassiveName(passive)), x + 18, y + (height / 2) - (mc.fontRenderer.FONT_HEIGHT / 2), 0x7700FF);
				GlStateManager.popMatrix();
			}
		}

	}

}
