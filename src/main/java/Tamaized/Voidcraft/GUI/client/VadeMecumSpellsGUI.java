package Tamaized.Voidcraft.GUI.client;

import java.io.DataOutputStream;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability.Category;
import Tamaized.Voidcraft.handlers.VadeMecumPacketHandler;
import Tamaized.Voidcraft.network.ServerPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class VadeMecumSpellsGUI extends GuiScreen {

	private static final int BUTTON_CLOSE = 0;
	private static final int BUTTON_BACK = 1;
	private static final int BUTTON_SPELL = 2;

	@Override
	public void initGui() {
		int margin = 20;
		int padding = 100;
		float workW = width - padding;
		int loc1 = (int) (workW * .25) + margin;
		int loc2 = (int) (workW * .75) + margin;
		buttonList.add(new GuiButton(BUTTON_BACK, loc1, height - 25, 80, 20, "Back"));
		buttonList.add(new GuiButton(BUTTON_CLOSE, loc2, height - 25, 80, 20, "Close"));

		int xLoc = 50;
		int yLoc = 50;

		if (mc == null || mc.player == null || !mc.player.hasCapability(CapabilityList.VADEMECUM, null)) return;
		IVadeMecumCapability cap = mc.player.getCapability(CapabilityList.VADEMECUM, null);
		int index = 0;
		for (Category spell : cap.getAvailableActivePowers()) {
			buttonList.add(new SpellButton(cap, BUTTON_SPELL, xLoc + (100 * ((int) Math.floor(index / 7))), yLoc + (25 * (index % 7)), spell));
			index++;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			switch (button.id) {
				case BUTTON_CLOSE:
					mc.displayGuiScreen((GuiScreen) null);
					break;
				case BUTTON_BACK:
					mc.displayGuiScreen(new Tamaized.Voidcraft.GUI.client.VadeMecumGUI(mc.player));
					break;
				case BUTTON_SPELL:
					if (button instanceof SpellButton) sendPacket(((SpellButton) button).getSpell());
					break;
				default:
					break;
			}
		}
	}

	private void sendPacket(IVadeMecumCapability.Category spell) {
		if (IVadeMecumCapability.isActivePower(spell)) VadeMecumPacketHandler.ClientToServerRequest(VadeMecumPacketHandler.RequestType.ACTIVE_SET, IVadeMecumCapability.getCategoryID(spell));
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void onGuiClosed() {

	}

	@Override
	public void updateScreen() {

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, "Words of Power", width / 2, 15, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	public class SpellButton extends GuiButton {

		private final IVadeMecumCapability.Category spell;
		private final IVadeMecumCapability data;

		public SpellButton(IVadeMecumCapability cap, int buttonId, int x, int y, IVadeMecumCapability.Category theSpell) {
			super(buttonId, x, y, 90, 18, "");
			data = cap;
			spell = theSpell;
		}

		public IVadeMecumCapability.Category getSpell() {
			return spell;
		}

		public boolean isSet() {
			return data != null && data.getCurrentActive() == spell;
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {
			if (visible) {
				FontRenderer fontrenderer = mc.fontRendererObj;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				hovered = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
				int i = getHoverState(hovered);
				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
				GlStateManager.blendFunc(770, 771);
				// drawRect(xPosition + width / 2, yPosition, width / 2, height, 0xFFFFFFFF);
				mouseDragged(mc, mouseX, mouseY);
				int j = 0xBBFFFFFF;

				if (isSet()) {
					j = 0xFF00FF00;
				} else if (hovered) {
					j = 0xFFFFFFFF;
				}

				drawRect(xPosition, yPosition, xPosition + width, yPosition + height, j);
				GlStateManager.pushMatrix();
				fontrenderer.drawString(IVadeMecumCapability.getCategoryName(spell).substring(6), xPosition + 2, yPosition + (height / 2) - (mc.fontRendererObj.FONT_HEIGHT / 2), 0x7700FF);
				GlStateManager.popMatrix();
			}
		}

	}

}
