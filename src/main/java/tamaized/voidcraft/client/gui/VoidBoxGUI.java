package tamaized.voidcraft.client.gui;

import Tamaized.TamModized.helper.PacketHelper;
import Tamaized.TamModized.helper.PacketHelper.PacketWrapper;
import Tamaized.TamModized.helper.TranslateHelper;
import tamaized.voidcraft.common.gui.container.VoidBoxContainer;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.machina.tileentity.TileEntityVoidBox;
import tamaized.voidcraft.network.ServerPacketHandler;
import com.sun.javafx.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;

import java.io.DataOutputStream;

public class VoidBoxGUI extends GuiContainer {

	private static final int BUTTON_PLAY = 0;
	private static final int BUTTON_STOP = 1;
	private static final int BUTTON_LOOP = 2;
	private static final int BUTTON_AUTO = 3;
	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/gui/voidbox.png");
	private TileEntityVoidBox te;
	private GuiButton btnPlay;
	private GuiButton btnStop;

	public VoidBoxGUI(InventoryPlayer inventoryPlayer, TileEntityVoidBox tileEntity) {
		super(new VoidBoxContainer(inventoryPlayer, tileEntity));

		this.te = tileEntity;

		this.xSize = 347;
		this.ySize = 320;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void initGui() {
		super.initGui();

		buttonList.clear();
		buttonList.add(btnPlay = new GuiButton(BUTTON_PLAY, guiLeft + 200, guiTop + 113, 33, 20, TranslateHelper.translate("voidcraft.gui.misc.play")));
		buttonList.add(btnStop = new GuiButton(BUTTON_STOP, guiLeft + 100, guiTop + 113, 33, 20, TranslateHelper.translate("voidcraft.gui.misc.stop")));
		buttonList.add(new GuiButton(BUTTON_LOOP, guiLeft + 240, guiTop + 113, 33, 20, TranslateHelper.translate("voidcraft.gui.misc.loop")));
		buttonList.add(new GuiButton(BUTTON_AUTO, guiLeft + 25, guiTop + 113, 66, 20, TranslateHelper.translate("voidcraft.gui.misc.auto")));
	}

	@Override
	public void updateScreen() {
		super.updateScreen();

		btnPlay.enabled = !te.SLOT_NEXT.getStackInSlot(0).isEmpty() && ((te.SLOT_NEXT.getStackInSlot(0).getItem() instanceof ItemRecord) && te.SLOT_CURRENT.getStackInSlot(0).isEmpty());
		btnStop.enabled = te.isPlaying();

	}

	@Override
	public void actionPerformed(GuiButton button) {
		switch (button.id) {
			case BUTTON_PLAY:
				sendPacket(ServerPacketHandler.PacketType.VOIDBOX_PLAY);
				break;
			case BUTTON_STOP:
				sendPacket(ServerPacketHandler.PacketType.VOIDBOX_STOP);
				break;
			case BUTTON_LOOP:
				sendPacket(ServerPacketHandler.PacketType.VOIDBOX_LOOP);
				break;
			case BUTTON_AUTO:
				sendPacket(ServerPacketHandler.PacketType.VOIDBOX_AUTO);
				break;
			default:
				break;
		}
	}

	private void sendPacket(ServerPacketHandler.PacketType type) {
		int xcoord = te.getPos().getX();
		int ycoord = te.getPos().getY();
		int zcoord = te.getPos().getZ();
		try {
			PacketWrapper packet = PacketHelper.createPacket(VoidCraft.channel, VoidCraft.networkChannelName, ServerPacketHandler.getPacketTypeID(type));
			DataOutputStream stream = packet.getStream();
			stream.writeInt(xcoord);
			stream.writeInt(ycoord);
			stream.writeInt(zcoord);
			packet.sendPacketToServer();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String name = TranslateHelper.translate("voidcraft.gui.musicbox.title");
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, this.ySize - 260, 0x7700FF);
		name = TranslateHelper.translate("voidcraft.gui.musicbox.curr") + ":";
		this.fontRenderer.drawString(name, (this.xSize / 12 - this.fontRenderer.getStringWidth(name) / 12) - 5, this.ySize - 240, 0xFF0000);
		if (te.isPlaying() && !te.SLOT_CURRENT.getStackInSlot(0).isEmpty()) {
			fontRenderer.drawString(((ItemRecord) te.SLOT_CURRENT.getStackInSlot(0).getItem()).getRecordNameLocal(), (xSize / 12) - 13 - (fontRenderer.getStringWidth(name) / 12) + 102, (ySize / 12) + 54, getColor(System.currentTimeMillis() / 10));
		}
		fontRenderer.drawString(TranslateHelper.translate("voidcraft.gui.misc.loop") + ": " + TranslateHelper.translate("voidcraft.gui.misc." + (te.getLoopState() ? "on" : "off")), (xSize / 12) - (fontRenderer.getStringWidth(name) / 12) + 220, ySize - 220, te.getLoopState() ? 0x00FF00 : 0xFF0000);
		fontRenderer.drawString(TranslateHelper.translate("voidcraft.gui.misc.auto") + ": " + TranslateHelper.translate("voidcraft.gui.misc." + (te.getAutoState() ? "on" : "off")), (xSize / 12) - (fontRenderer.getStringWidth(name) / 12) + 7, ySize - 180, te.getAutoState() ? 0x00FF00 : 0xFF0000);
		if (te.isPlaying())
			fontRenderer.drawString(getTimeInMinutes(te.getSongLength() - te.getSongTimeLeft()) + "/" + getTimeInMinutes(te.getSongLength()), (xSize / 12) + (fontRenderer.getStringWidth(name) / 12), ySize - 220, 0xFFFF00);
	}

	private int getColor(double hue) {
		double[] c = Utils.HSBtoRGB(hue, 1, 1);
		return (int) (c[0] * 255) << 16 | ((int) (c[1] * 255) << 8) | (((int) c[2] * 255));
	}

	private String getTimeInMinutes(int t) {
		t = t / 20;
		int s = t % 60;
		int m = (t / 60) % 60;
		return m + ":" + (String.valueOf(s).length() == 1 ? "0" + s : s);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		drawTexturedModalRect(guiLeft + 78, guiTop + 99, 0, 0, xSize / 2, ySize / 2);
		this.updateScreen();
	}

}
