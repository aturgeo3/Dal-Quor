package Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageFruit2 implements IVadeMecumPage {

	private final String title = "";
	private final String text = "cannot be done if the seeds have already been planted. To alter the soil simply right click it with one of the following: Redstone dust, Lapis dust, Gold dust, Emerald dust, or Diamond dust. Eating these fruits will yield various benefits based on the alterations. A normal fruit grants Voidic Immunity which will prevent and decay any Voidic Infusion on the player. A redstone fruit gives a buff to strength. Gold gives Absorb and Resistance.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
