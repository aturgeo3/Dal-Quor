package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.charger.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageCharger1 implements IVadeMecumPage {

	private final String title = "Voidic Charger";
	private final String text = "End Crystals are perhaps the most mysterious devices in the known universe. Aside from the fact that they are fragile and easily broken, all that is known of them is that they feed some sort of energy to the Ender Dragon to heal it. However, that is all you need to know to use them for your own purposes. By placing a Void Star in the center of an End Crystal, wrapping it in Void Cloth, and fortifying it with Void Infused Diamond Dust, one creates a Voidic";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
