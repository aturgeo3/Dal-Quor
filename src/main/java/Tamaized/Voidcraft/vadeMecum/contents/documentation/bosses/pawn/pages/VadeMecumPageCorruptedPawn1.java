package Tamaized.Voidcraft.vadeMecum.contents.documentation.bosses.pawn.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageCorruptedPawn1 implements IVadeMecumPage {

	private final String title = "Corrupted Pawn";
	private final String text = "Spawned into the world with a Chained Skull followed by an explosion. These deadly entities are very quick, deal massive damage and have a very large health pool. They attack most other creatures in sight. One must be very prepared before taking one on. Upon death, they drop their inner core, the Void Star.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
