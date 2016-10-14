package Tamaized.Voidcraft.vadeMecum.entry.blocks.voidbrick.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoidBrick1 implements IVadeMecumPage {

	private final String title = "Void Brick";
	private final String text = "These bricks are made from cut Void Crystal Blocks. They appear to be much more sturdy than any other block found in this dimension, but the crystal that makes them up seems to have lost their conductivity for Voidic Power as a result of being cut. While their function as a material for building Void Portals has been lost, that doesn't mean that they're useless. Standing on one of these bricks will actually stall";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
