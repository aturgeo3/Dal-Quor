package Tamaized.Voidcraft.vadeMecum.entry.voidicinfusion.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoidicInfusion2 implements IVadeMecumPage {

	private final String title = "";
	private final String text = "is known as Voidic Damage. You become incorporeal in which certian physical attacks have a chance of passing right through you, dealing no damage. If the amount of Voidic Infusion becomes too great, death will occur. As Voidic Infusion decays, all of these effects begin to wear off and your health returns back to normal.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
