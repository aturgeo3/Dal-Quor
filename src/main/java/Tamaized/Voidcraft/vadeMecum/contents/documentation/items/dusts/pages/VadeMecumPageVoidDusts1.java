package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.dusts.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoidDusts1 implements IVadeMecumPage {

	private final String title = "Void Dusts";
	private final String text = "Void Dusts are the result of using a Void Infused Macerator. Using the device with ores provides four dust. Using it with Ingots or Gems results in one. The types of dusts that can be obtained are Iron, Gold, Copper, Tin, Lead, Quartz, Emerald, Lapis, and Diamond. The non-gem based dusts can then be smelted into ingot form. This effectively allows, to an extent, quadruple ore processing.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
