package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.obsidianflask.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageObsidianFlask1 implements IVadeMecumPage {

	private final String title = "Obsidian Flask";
	private final String text = "It is actually possible to collect 'Void' from within the Overworld. You need a special type of flask to do so. Simply get yourself some Empty Obsidian Flasks, head down near bedrock, and Right Click. What do these Flasks do you may ask? Well if you toss one (via right click) they explode on contact. They do not seem to damage the world at all but they cast a purple flame that does not seem to burn. It is a cold flame. Though coming in contact with such a flame";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
