package Tamaized.Voidcraft.vadeMecum.entry.blocks.realityhole.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageRealityHole1 implements IVadeMecumPage {

	private final String title = "Hole in Reality";
	private final String text = "This Rift in Space links back to the Overworld. Stepping into one will result in being teleported to around the same coordinates from where you were in the void. Being around these rifts reminds you of the sensation you feel when you're being watched by... him...";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
