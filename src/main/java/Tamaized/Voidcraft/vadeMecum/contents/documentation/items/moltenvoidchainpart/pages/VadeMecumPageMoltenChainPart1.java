package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.moltenvoidchainpart.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageMoltenChainPart1 implements IVadeMecumPage {

	private final String title = "Molten Void Chain Part";
	private final String text = "When one smelts a Void Chain the result is interesting. It is a ball of molten chain in which one would think would burn as you touch it, but in fact it is cool to the touch. The only explanation for this would be the voidic properties.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
