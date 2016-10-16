package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.cable.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageCable1 implements IVadeMecumPage {

	private final String title = "Voidic Cable";
	private final String text = "The Voidic Generator is certainly able to supply power, however, it may not be able to reach all the machines you would like it to. However, by combining Redstone, which naturally transmits power, with a Void Crystal Block, which in and of itself is a conduit for the Void's energies, one creates Void Cable. These cables will automatically connect to machines and each other when placed in the world. When connected to a something storing Voidic Power, they will relay";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
