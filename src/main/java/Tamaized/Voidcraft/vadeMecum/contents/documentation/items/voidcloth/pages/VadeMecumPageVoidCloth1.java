package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidcloth.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoidCloth1 implements IVadeMecumPage {

	private final String title = "Void Cloth";
	private final String text = "For some unknown reason the only way to obtain this cloth is from taking it from a lifeless corpse of a Void Lich. Though they were already undead to begin with. Even with the cloth in your possesion you can not figure out how exactly it is made or why it has the binding properties that it does. The material proves very useful though.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
