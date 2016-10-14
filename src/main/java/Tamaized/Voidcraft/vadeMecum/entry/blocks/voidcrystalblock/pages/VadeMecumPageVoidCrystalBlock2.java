package Tamaized.Voidcraft.vadeMecum.entry.blocks.voidcrystalblock.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoidCrystalBlock2 implements IVadeMecumPage {

	private final String title = "";
	private final String text = "This block is used to construct a portal to the Void. Create a formation in the same way as you would to construct a nether portal frame. After doing so, collect an Obsidian Flask and toss it onto the top face of the bottom layer frame. If done correctly a portal will be open, leading to the Void.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
