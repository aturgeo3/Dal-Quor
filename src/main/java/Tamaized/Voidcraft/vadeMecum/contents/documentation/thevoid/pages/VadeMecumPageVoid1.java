package Tamaized.Voidcraft.vadeMecum.contents.documentation.thevoid.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoid1 implements IVadeMecumPage {

	private final String title = "The Void";
	private final String text = "The terrain is similar to that of the Nether's. You'll mostly find Soft Bedrock here. There are large areas of rifts in space known as Holes in Reality. Touching such a hole of reality may send you back to the Overworld around the same coordinates from which you were in the Void, give or take 200 blocks. Any Item that touches such a hole will be lost forever so be careful. Void Crystal Ore and Liquid Void populate the Void as well. You may also find some";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
