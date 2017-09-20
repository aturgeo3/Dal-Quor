package tamaized.voidcraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.CapabilityList;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.VadeMecumEntry;
import tamaized.voidcraft.network.server.ServerPacketHandlerVadeMecum;
import tamaized.voidcraft.network.server.ServerPacketHandlerVadeMecumLastEntry;
import tamaized.voidcraft.proxy.ClientProxy;

import java.io.IOException;

public class VadeMecumGUI extends GuiScreen {

	private static final ResourceLocation TEXTURES = new ResourceLocation(VoidCraft.modid, "textures/gui/VadeMecum/VadeMecum.png");
	private final EntityPlayer player;
	private int vadeW;
	private int vadeH;
	private int vadeX;
	private int vadeY;
	private IVadeMecumCapability playerStats;
	private VadeMecumEntry entry;
	private VadeMecumEntry nextEntry;
	private int pageNumber = 0;
	private ItemStack renderStackHover = ItemStack.EMPTY;

	private VadeMecumGUI.ArrowButton button_back;
	private VadeMecumGUI.ArrowButton button_forward;
	private VadeMecumGUI.LargeArrowButton button_largeBack;
	private VadeMecumGUI.OverlayButton button_entryBack;
	private VadeMecumGUI.OverlayButton button_credits;
	private VadeMecumGUI.FullButton button_spells;
	private VadeMecumGUI.FullButton button_infusion;
	private boolean displayIntro = true;

	public VadeMecumGUI(EntityPlayer p) {
		player = p;
		initPosSize();
	}

	private static int getButtonID(Button b) {
		return b.ordinal();
	}

	private static Button getButtonFromID(int id) {
		return id > Button.values().length ? Button.NULL : Button.values()[id];
	}

	public static void drawCenteredStringNoShadow(FontRenderer render, String text, int x, int y, int color) {
		render.drawString(text, (x - render.getStringWidth(text) / 2), y, color);
	}

	private void initPosSize() {
		vadeW = 256 + 140;
		vadeH = 192 + 25;
		vadeX = (width - vadeW) / 2;
		vadeY = (height - vadeH) / 2;
	}

	public int getX() {
		return vadeX;
	}

	public int getY() {
		return vadeY;
	}

	public int getW() {
		return vadeW;
	}

	public int getH() {
		return vadeH;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	public IVadeMecumCapability getPlayerStats() {
		return playerStats;
	}

	@Override
	public void initGui() {
		initPosSize();
		VoidCraft.reloadRitualList();
		ClientProxy.vadeMecum = this;
		playerStats = player.getCapability(CapabilityList.VADEMECUM, null);
		if (playerStats != null && playerStats.getLastEntry() != null && !playerStats.getLastEntry().equals("null") && playerStats.getLastEntry().contains(":"))
			setEntry(VadeMecumEntry.getEntry(playerStats.getLastEntry().split(":")[0]), Integer.parseInt(playerStats.getLastEntry().split(":")[1]));
			// else setEntry(ClientProxy.vadeMecumEntryList, 0);
		else
			setEntry(ClientProxy.vadeMecumEntryList, 0);
		button_forward = addButton(new ArrowButton(getButtonID(Button.Forward), vadeX + vadeW - 70, vadeY + 185, true));
		button_back = addButton(new ArrowButton(getButtonID(Button.Back), vadeX + 45, vadeY + 185, false));
		button_entryBack = addButton(new OverlayButton(this, getButtonID(Button.EntryBack), vadeX + 18, vadeY + 8, true));
		button_credits = addButton(new OverlayButton(this, getButtonID(Button.Credits), vadeX + 358, vadeY + 8, false));
		button_largeBack = addButton(new LargeArrowButton(getButtonID(Button.LargeBack), vadeX + 17, vadeY + vadeH - 2/* vadeY + 10 */));
		button_spells = addButton(new FullButton(this, I18n.format("voidcraft.gui.misc.spells"), getButtonID(Button.WordsOfPower), vadeX + 42, vadeY + vadeH - 3));
		button_infusion = addButton(new FullButton(this, I18n.format("voidcraft.gui.misc.infusion"), getButtonID(Button.Infusion), vadeX + 82, vadeY + vadeH - 3));
		updateButtons();
	}

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		int dwheel = Mouse.getEventDWheel();
		if (dwheel > 0)
			prevPage();
		if (dwheel < 0)
			nextPage();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == 1 || mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode)) {
			mc.player.closeScreen();
		}
	}

	private void setEntry(VadeMecumEntry e, int page) {
		entry = e == null ? ClientProxy.vadeMecumEntryList : e;
		pageNumber = page > entry.getPageLength(this) ? entry.getPageLength(this) : page;
		entry.init(this);
		updateButtons();
	}

	public void changeEntry(VadeMecumEntry e) {
		nextEntry = e;
		String eName = VadeMecumEntry.getEntry(e);
		if (eName != null)
			sendLastEntryPacket(eName + ":" + pageNumber);
	}

	private void nextPage() {
		if (pageNumber + 2 < entry.getPageLength(this)) {
			pageNumber += 2;
			if (pageNumber >= entry.getPageLength(this))
				pageNumber = entry.getPageLength(this);
			updateButtons();
		}
	}

	private void prevPage() {
		if (pageNumber > 0) {
			pageNumber -= 2;
			if (pageNumber < 0)
				pageNumber = 0;
			updateButtons();
		}
	}

	@Override
	public void onGuiClosed() {
		ClientProxy.vadeMecum = null;
		String e = VadeMecumEntry.getEntry(entry);
		if (e != null)
			sendLastEntryPacket(e + ":" + pageNumber);
	}

	@Override
	public void updateScreen() {
		if (nextEntry != null) {
			setEntry(nextEntry, 0);
			nextEntry = null;
		}
	}

	private void updateButtons() {
		if (button_forward != null)
			button_forward.visible = canDrawPage() && pageNumber + 2 < entry.getPageLength(this);
		if (button_back != null)
			button_back.visible = canDrawPage() && pageNumber > 0;
		if (button_entryBack != null)
			button_entryBack.visible = (entry != ClientProxy.vadeMecumEntryList);
		if (button_credits != null)
			button_credits.visible = false;// entry == ClientProxy.vadeMecumEntryList.MAIN;
		if (button_largeBack != null)
			button_largeBack.visible = (entry != ClientProxy.vadeMecumEntryList);
		if (button_spells != null)
			button_spells.visible = (playerStats != null && playerStats.hasCategory(IVadeMecumCapability.Category.TOME));
		if (button_infusion != null)
			button_infusion.visible = (playerStats != null && playerStats.hasCategory(IVadeMecumCapability.Category.VoidicControl));
	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
	 */
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.enabled) {
			switch (getButtonFromID(button.id)) {
				case Forward:
					nextPage();
					break;
				case Back:
					prevPage();
					break;
				case LargeBack:
					entry.goBack(this);
					break;
				case EntryBack:
					setEntry(ClientProxy.vadeMecumEntryList, 0);
					// setEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN, 0);
					break;
				case WordsOfPower:
					VoidCraft.network.sendToServer(new ServerPacketHandlerVadeMecum.Packet(ServerPacketHandlerVadeMecum.RequestType.SPELLS_PAGE, 0));
					break;
				case Infusion:
					mc.displayGuiScreen(new VadeMecumInfusionGUI(playerStats));
					break;
				case Credits:
					break;
				default:
					break;
			}
			// sendPacketUpdates(VadeMecumPacketHandler.RequestType.CATEGORY_ADD, IVadeMecumCapability.getCategoryID(IVadeMecumCapability.Category.TEST));
			// sendPacketUpdates(VadeMecumPacketHandler.RequestType.CATEGORY_CLEAR, 0);
			updateButtons();
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		if (canDrawPage()) {
			entry.mouseClicked(this, pageNumber, mouseX, mouseY, mouseButton);
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(TEXTURES);
		drawTexturedModalRect(vadeX, vadeY, vadeW, vadeH, 0, 0, 256, 192);
		super.drawScreen(mouseX, mouseY, partialTicks);
		if (canDrawPage()) {
			entry.render(this, fontRenderer, mouseX, mouseY, vadeX, vadeY, pageNumber);
		}
		if (button_entryBack != null && button_entryBack.visible)
			drawCenteredString(fontRenderer, I18n.format("voidcraft.gui.misc.main"), vadeX + 30, vadeY + vadeH - 24, 0xFFFF00);
		if (button_credits != null && button_credits.visible)
			drawCenteredString(fontRenderer, I18n.format("voidcraft.gui.misc.credits"), vadeX + 360, vadeY + 12, 0xFFFF00);
		if (!renderStackHover.isEmpty()) {
			renderToolTip(renderStackHover, mouseX, mouseY);
			renderStackHover = ItemStack.EMPTY;
		}
	}

	private boolean canDrawPage() {
		return playerStats != null && entry != null;
	}

	public void renderItemStack(ItemStack stack, int x, int y, int mx, int my) {
		renderItemStack(stack, x, y, mx, my, 0xFFFFFF);
	}

	public void renderItemStack(ItemStack stack, int x, int y, int mx, int my, int color) {
		if (itemRender != null) {
			RenderHelper.enableGUIStandardItemLighting();
			GlStateManager.enableDepth();
			itemRender.renderItemIntoGUI(stack, x, y);
			// drawCenteredString(fontRenderer, ""+stack, x, y, 0xFFFFFF);
			GlStateManager.disableDepth();
			if (stack.getCount() > 1)
				drawString(fontRenderer, "" + stack.getCount(), x + 11, y + 9, color);
			GlStateManager.enableDepth();
			if (mx >= x && mx <= x + 16 && my >= y && my <= y + 16)
				renderStackHover = stack;
			RenderHelper.disableStandardItemLighting();
		}
	}

	public void drawTexturedModalRect(int x, int y, int width, int height, int textureX, int textureY, int textureW, int textureH) {
		GlStateManager.enableBlend();
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos((double) (x), (double) (y + height), (double) zLevel).tex((double) ((float) (textureX) * 0.00390625F), (double) ((float) (textureY + textureH) * 0.00390625F)).endVertex();
		vertexbuffer.pos((double) (x + width), (double) (y + height), (double) zLevel).tex((double) ((float) (textureX + textureW) * 0.00390625F), (double) ((float) (textureY + textureH) * 0.00390625F)).endVertex();
		vertexbuffer.pos((double) (x + width), (double) (y), (double) zLevel).tex((double) ((float) (textureX + textureW) * 0.00390625F), (double) ((float) (textureY) * 0.00390625F)).endVertex();
		vertexbuffer.pos((double) (x), (double) (y), (double) zLevel).tex((double) ((float) (textureX) * 0.00390625F), (double) ((float) (textureY) * 0.00390625F)).endVertex();
		tessellator.draw();
		GlStateManager.disableBlend();
	}

	private void sendLastEntryPacket(String entry) {
		if (!entry.equals(""))
			VoidCraft.network.sendToServer(new ServerPacketHandlerVadeMecumLastEntry.Packet(entry));
	}

	private enum Button {
		NULL, Back, Forward, LargeBack, EntryBack, Credits, WordsOfPower, Infusion
	}

	@SideOnly(Side.CLIENT)
	static class ArrowButton extends GuiButton {
		private final boolean isForward;

		ArrowButton(int id, int x, int y, boolean forward) {
			super(id, x, y, 23, 13, "");
			isForward = forward;
		}

		/**
		 * Draws this button to the screen.
		 */
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float p_191745_4_) {
			if (visible) {
				boolean flag = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(TEXTURES);
				int i = 0;
				int j = 192;

				if (flag) {
					i += 23;
				}

				if (!isForward) {
					j += 13;
				}
				drawTexturedModalRect(x, y, i, j, 23, 13);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	static class LargeArrowButton extends GuiButton {

		LargeArrowButton(int id, int x, int y) {
			super(id, x, y, 23, 13, "");
		}

		/**
		 * Draws this button to the screen.
		 */
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float p_191745_4_) {
			if (visible) {
				boolean flag = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(TEXTURES);
				int i = 50;
				int j = 195;

				if (flag) {
					j += 11;
				}

				drawTexturedModalRect(x, y, i, j, 23, 11);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	static class FullButton extends GuiButton {
		public static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/VadeMecum/button_full.png");
		private final VadeMecumGUI parent;

		FullButton(VadeMecumGUI gui, String text, int id, int x, int y) {
			super(id, x, y, 40, 14, text);
			parent = gui;
		}

		/**
		 * Draws this button to the screen.
		 */
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float p_191745_4_) {
			if (visible) {
				boolean flag = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(TEXTURE);
				int i = 0;
				int j = 0;

				if (flag) {
					j += 124;
				}
				parent.drawTexturedModalRect(x, y, 40, 14, i, j, 255, 124);
				parent.drawCenteredString(parent.fontRenderer, displayString, x + (width / 2), y + (height / 2) - (parent.fontRenderer.FONT_HEIGHT / 2), 0xFFFF00);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	static class OverlayButton extends GuiButton {
		public static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/VadeMecum/edgeButton.png");
		private final boolean isForward;
		private final VadeMecumGUI parent;

		OverlayButton(VadeMecumGUI gui, int id, int x, int y, boolean forward) {
			super(id, x, y, 20, 198, "");
			parent = gui;
			isForward = forward;
		}

		/**
		 * Draws this button to the screen.
		 */
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float p_191745_4_) {
			if (visible) {
				boolean flag = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(TEXTURE);
				int i = 0;
				int j = 0;

				if (flag) {
					j += 128;
				}

				if (!isForward) {
					i += 128;
				}
				parent.drawTexturedModalRect(x, y, width, height, i, j, 128, 128);
			}
		}
	}

}
