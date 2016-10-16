package Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.specter.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageSpecterChain1 implements IVadeMecumPage {

	private final String title = "Chained Specter";
	private final String text = "A common entity found within the Void. Its attacks are ranged based. Upon death it'll drop Void Chains.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
