package Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageFruit4 implements IVadeMecumPage {

	private final String title = "Potion of Plenum";
	private final String text = "One can make a Voidic Alchemy Table and combine all the types of Ethereal Fruit into a potion. What this potion does is it gives the user immunity to void damage allowing one to venture down into the void below bedrock in the Overworld.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
