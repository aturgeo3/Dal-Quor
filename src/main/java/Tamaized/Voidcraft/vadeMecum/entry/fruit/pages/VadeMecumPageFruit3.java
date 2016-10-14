package Tamaized.Voidcraft.vadeMecum.entry.fruit.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageFruit3 implements IVadeMecumPage {

	private final String title = "";
	private final String text = "Lapis provides a speed buff. Emerald gives a jump buff. And diamond gives a health boost buff. The plants will only grow in light levels 2 and below. This means the plants need darkness.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
