package tamaized.voidcraft.common.vademecum;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import tamaized.voidcraft.client.gui.VadeMecumGUI;

public class VadeMecumPage implements IVadeMecumPage {

	protected final String title;
	protected final String text;

	public VadeMecumPage(String title, String text) {
		this.title = I18n.format(title);
		this.text = I18n.format(text);
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		VadeMecumGUI.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
