package Tamaized.Voidcraft.vadeMecum.entry.machines.macerator.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageMacerator1 implements IVadeMecumPage {

	private final String title = "Void Infused Macerator";
	private final String text = "This Machine is the product of infusing a standard Furnace with Liquid Void. It takes Voidic Power to grind an input ore into four Void Infused dusts. You can process these dusts into a regular ingot using mundane machines.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
