package Tamaized.Voidcraft.vadeMecum.contents.documentation.thevoid.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoid3 implements IVadeMecumPage {

	private final String title = "";
	private final String text = "One final thing to note is that while in the Void without proper items and/or effects, you will take Voidic Infusion which both slowly kills and buffs you.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);//One final thing to note is that while in the Void without proper items and/or effects, you will take Voidic Infusion which both slowly kills and buffs you.
	}

}
