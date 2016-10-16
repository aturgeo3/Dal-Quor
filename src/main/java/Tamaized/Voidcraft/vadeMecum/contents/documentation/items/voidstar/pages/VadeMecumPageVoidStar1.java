package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidstar.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoidStar1 implements IVadeMecumPage {

	private final String title = "Void Star";
	private final String text = "Very similar to the Nether Star. These are dropped from the Corrupted Pawn, in fact they are the cores of such beings. These stars are capable of opening rifts to the void but only when contained correctly.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
