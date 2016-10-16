package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.stabilizer.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageStabilizer1 implements IVadeMecumPage {

	private final String title = "Reality Stabilizer";
	private final String text = "Throughout the Void, there are tears in space and time. It is unknown if these Holes in Reality occur naturally, or if they are the product some other powerful force. In either case, one may wish to remove them from the terrain for one reason or another. To do this, one must construct a powerful machine capable of containing these rifts. That is where this machine, the Reality Stabilizer, comes into play.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
