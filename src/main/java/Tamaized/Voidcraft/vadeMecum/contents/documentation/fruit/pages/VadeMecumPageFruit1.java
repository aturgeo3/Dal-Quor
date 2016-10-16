package Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageFruit1 implements IVadeMecumPage {

	private final String title = "Ethereal Fruit";
	private final String text = "Ethereal Fruits are harvested from Ethereal Plants. These plants can be found naturally in the void next to a source of Liquid Void. Harvesting via Right Click may yield fruit and seeds between 0 and 3. Yes, there is a chance to not gain any seeds or fruit at all. To plant these seeds you'll need to use a Void Hoe on Soft Bedrock and have a nearby source of liquid void as normal water will not work. Before you actually plant the seeds you can alter the soil, this";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
