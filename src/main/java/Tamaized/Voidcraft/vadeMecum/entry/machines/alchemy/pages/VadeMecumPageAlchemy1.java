package Tamaized.Voidcraft.vadeMecum.entry.machines.alchemy.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageAlchemy1 implements IVadeMecumPage {

	private final String title = "Voidic Alchemy Table";
	private final String text = "Consuming Ethereal Fruit and their fertilized variants provide many great benefits. However, if brewing and farming in the Overworld has taught you anything, it would be that some of the most potent potions are created from enhanced crops. This holds true for the Voidic Plants as well. By encasing a Brewing Stand in Void Bricks and creating an interface of Void Cables and an Inert Void Infusion Altar, one creates a special workspace";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
