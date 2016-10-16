package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.heimdall.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageHeimdall2 implements IVadeMecumPage {

	private final String title = "";
	private final String text = "most places with an open ceiling, the Heimdall requires fuel to keep its gate open. Since it is a modified rift to the nether recalibrated to go to the Void, it requires energy from both realms to function properly. Void Infused Quartz Dust will the job quite nicely.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
