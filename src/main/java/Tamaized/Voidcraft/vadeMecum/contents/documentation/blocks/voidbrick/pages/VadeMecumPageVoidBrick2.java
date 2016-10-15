package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidbrick.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoidBrick2 implements IVadeMecumPage {

	private final String title = "";
	private final String text = "Voidic Infusion from being gained as if the bricks are trying to consume it from you at the same rate you gain it. Their firm frame could be used for making other objects, or simply for building and decoration.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
