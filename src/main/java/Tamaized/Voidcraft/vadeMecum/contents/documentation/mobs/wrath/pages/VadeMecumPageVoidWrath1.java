package Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.wrath.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoidWrath1 implements IVadeMecumPage {

	private final String title = "Void's Wrath";
	private final String text = "Only found within a Void Fortress. These entities are very sturdy and deal quite a bit of damage. They are melee based. They drop Charred bones upon death.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
