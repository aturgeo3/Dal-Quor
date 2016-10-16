package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidcrystalblock.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoidCrystalBlock1 implements IVadeMecumPage {
	
	private final String title = "Void Crystal Block";
	private final String text = "This block is the product of bringing together nine Void Crystals. These blocks cannot do much on their own, but holding them gives the same sensation felt when close to the bottom layer of bedrock in the Overworld. Could bringing these blocks close to the void have an effect?";
	
	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
