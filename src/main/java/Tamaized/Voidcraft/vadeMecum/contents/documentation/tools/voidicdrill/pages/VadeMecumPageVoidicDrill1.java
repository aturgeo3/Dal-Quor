package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidicdrill.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoidicDrill1 implements IVadeMecumPage {

	private final String title = "Voidic Drill";
	private final String text = "One of the few items that requires Voidic Power to function. Charge this device using a Voidic Charger. If this device contains power and it is being used via holding down right click, it will emit a beacon like laser that mines in a 3x3 area and has a range of up to 10 blocks. This laser will also deal damage to entites.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
