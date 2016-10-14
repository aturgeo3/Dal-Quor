package Tamaized.Voidcraft.vadeMecum.entry.machines.infusionAltar.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageInfusionAltar1 implements IVadeMecumPage {

	private final String title = "Void Infusion Altar";
	private final String text = "The special properties of Liquid Void are quite interesting indeed. While one can ponder what exactly makes it behave the way it does, this entry will focus more on harnessing its power. By combining Void Bricks, Void Cloth, a Cauldron, and a Void Star, one can create a machine that can change the physical properties of certain items in much the same way that you change via prolonged exposure to the Void's environment.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
