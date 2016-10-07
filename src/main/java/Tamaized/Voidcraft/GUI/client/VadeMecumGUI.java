package Tamaized.Voidcraft.GUI.client;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.handlers.VadeMecumPacketHandler;
import Tamaized.Voidcraft.network.ServerPacketHandler;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumBodyObject;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VadeMecumGUI extends GuiScreen {

	private static final ResourceLocation TEXTURES = new ResourceLocation(voidCraft.modid + ":textures/gui/VadeMecum.png");
	private final EntityPlayer player;
	private IVadeMecumCapability playerStats;
	private VadeMecumEntry entry;
	private int pageNumber = 0;

	private VadeMecumGUI.TestButton testButton1;
	private VadeMecumGUI.TestButton testButton2;

	private boolean displayIntro = true;

	public VadeMecumGUI(EntityPlayer p) {
		player = p;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void initGui() {
		ClientProxy.vadeMecum = this;
		playerStats = player.getCapability(CapabilityList.VADEMECUM, null);
		entry = ClientProxy.vadeMecumEntryList.INTRO;
		int i = (this.width - 192) / 2;
		this.testButton1 = (VadeMecumGUI.TestButton) this.addButton(new VadeMecumGUI.TestButton(1, i + 120, 182, true));
		this.testButton2 = (VadeMecumGUI.TestButton) this.addButton(new VadeMecumGUI.TestButton(2, i + 38, 182, false));
		this.updateButtons();
	}

	@Override
	public void onGuiClosed() {
		ClientProxy.vadeMecum = null;
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
	}

	private void updateButtons() {
		this.testButton2.visible = canDrawPage() ? pageNumber > 0 : false;
		this.testButton1.visible = canDrawPage() ? pageNumber < entry.getPages()-1 : false;
	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
	 */
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.enabled) {
			if (button.id == testButton1.id) {
				pageNumber++;
				//sendPacketUpdates(VadeMecumPacketHandler.RequestType.CATEGORY_ADD, IVadeMecumCapability.getCategoryID(IVadeMecumCapability.Category.TEST));
			} else if (button.id == testButton2.id) {
				pageNumber--;
				if(pageNumber < 0) pageNumber = 0;
				//sendPacketUpdates(VadeMecumPacketHandler.RequestType.CATEGORY_CLEAR, 0);
			}
			this.updateButtons();
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		int i = (this.width - 192) / 2;
		int j = 28;
		this.drawTexturedModalRect(i, j, 0, 0, 192, 192);
		super.drawScreen(mouseX, mouseY, partialTicks);
		if (canDrawPage()) {
			drawCenteredStringNoShadow(mc.fontRendererObj, TextFormatting.UNDERLINE+entry.getPage(pageNumber).getTitle(), i + 92, j + 20, 0x000000);
			String[] bodyContext = entry.getPage(pageNumber).getBody().getFormattedText();
			String rawText = entry.getPage(pageNumber).getBody().getRawText();
			for (int l = 0; l <= bodyContext.length - 1; l++) {
				//if (bodyContext[l].contains(VadeMecumBodyObject.craftingEntry)) continue;
				/*else */drawCenteredStringNoShadow(mc.fontRendererObj, bodyContext[l], i + 92, j + 24 + (10*(1+l)), 0x7700AA);
			}
		}
	}

	private boolean canDrawPage() {
		return playerStats != null && entry != null && entry.getPage(pageNumber) != null;
	}

	public static void drawCenteredStringNoShadow(FontRenderer render, String text, int x, int y, int color) {
		render.drawString(text, (x - render.getStringWidth(text) / 2), y, color);
	}

	@SideOnly(Side.CLIENT)
	static class TestButton extends GuiButton {
		private final boolean isForward;

		public TestButton(int id, int x, int y, boolean forward) {
			super(id, x, y, 23, 13, "");
			this.isForward = forward;
		}

		/**
		 * Draws this button to the screen.
		 */
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {
			if (this.visible) {
				boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(TEXTURES);
				int i = 0;
				int j = 192;

				if (flag) {
					i += 23;
				}

				if (!this.isForward) {
					j += 13;
				}
				this.drawTexturedModalRect(this.xPosition, this.yPosition, i, j, 23, 13);
			}
		}
	}

	private void sendPacketUpdates(VadeMecumPacketHandler.RequestType request, int objectID) {
		int pktType = ServerPacketHandler.getPacketTypeID(ServerPacketHandler.PacketType.VADEMECUM);
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(pktType);
			VadeMecumPacketHandler.ClientToServerRequest(outputStream, player, request, objectID);
			FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);
			voidCraft.channel.sendToServer(packet);
			outputStream.close();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
