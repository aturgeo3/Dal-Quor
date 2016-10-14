package Tamaized.Voidcraft.vadeMecum.entry.blocks.softbedrock.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageSoftBedrock1 implements IVadeMecumPage {

	private final String title = "Soft Bedrock";
	private final String text = "Upon entering the Void, you notice that the vast majority of your surroundings is comprised of Bedrock. But unlike the flat, rigid, and unyielding layer found in the overworld, this \"Soft\" Bedrock makes a clearly defined landscape and feels plush beneath your feet. It has the firmness of stone, so it could be used as a solid building block. Yet it also has the consistency of fertile soil and can be tilled with a strong enough tool.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
