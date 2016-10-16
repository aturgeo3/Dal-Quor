package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.charger.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageCharger2 implements IVadeMecumPage {

	private final String title = "";
	private final String text = "Charger. A device capable of charging certain items with Voidic Power. However, it should be noted that, due to its shape, it can only take in power from the Bottom. So plan accordingly when making your network of Voidic Generators and Void Cables.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
