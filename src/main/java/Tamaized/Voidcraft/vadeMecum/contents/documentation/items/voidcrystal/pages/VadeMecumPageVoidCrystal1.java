package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidcrystal.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoidCrystal1 implements IVadeMecumPage {

	private final String title = "Void Crystal";
	private final String text = "Crystallized Void, how exactly these formed and why they're found in The End is unknown. To gather these crystals, simply venture into The End and look around for Void Crystal Ore.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
