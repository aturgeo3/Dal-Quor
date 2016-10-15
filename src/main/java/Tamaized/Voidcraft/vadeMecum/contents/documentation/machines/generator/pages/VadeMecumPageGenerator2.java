package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.generator.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageGenerator2 implements IVadeMecumPage {

	private final String title = "";
	private final String text = "Generator. This machine, which is constructed by surrounding a Void Infusion Altar with Redstone, converts the chemical energy found in Liquid Void back into the unique power from which it is comprised. This \"Voidic Power\" has many applications, though, details on those details are best saved for another entry.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
