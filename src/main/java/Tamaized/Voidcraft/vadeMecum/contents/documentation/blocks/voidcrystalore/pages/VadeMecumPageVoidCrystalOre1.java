package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidcrystalore.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoidCrystalOre1 implements IVadeMecumPage {

	private final String title = "Void Crystal Ore";
	private final String text = "An ore found only in The End and Void. Its look depends on the dimension as well. If the ore is found in the Void, it will have a Soft Bedrock look. If it is found elsewhere, it will have an End Stone look. This ore will always drop one to three Void Crystals regardless of one's fortune.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
