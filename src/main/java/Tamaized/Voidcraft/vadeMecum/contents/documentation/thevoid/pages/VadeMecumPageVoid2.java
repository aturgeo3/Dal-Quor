package Tamaized.Voidcraft.vadeMecum.contents.documentation.thevoid.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoid2 implements IVadeMecumPage {

	private final String title = "";
	private final String text = "plant life growing near a source of Liquid Void. Only two entities can be found anywhere within the Void, these are the Void Wraiths and Chained Specters. On occasion you can find a structure known as a Void Fortress. These Void Fortresses resemble the Nether Fortress in design. Void Wraths, Herobrine Creepers and Shulkers populate these Fortresses.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
