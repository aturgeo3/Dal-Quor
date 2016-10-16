package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.charredbone.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageCharredBone1 implements IVadeMecumPage {

	private final String title = "Charred Bone";
	private final String text = "Taken from your fallen enemies, the Void Wraths. These very sturdy creatures are nothing but bone, so it only makes sense for these bones to also be sturdy right?";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
