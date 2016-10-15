package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.moltenvoidchain.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageMoltenChain1 implements IVadeMecumPage {

	private final String title = "Molten Void Chain";
	private final String text = "Molten Chain Parts are far too pliable for any real purpose. That is where the fully formed Molten Chains come into play. Combine these parts with Charred Bones and you get a sturdy malleable chain capable of permanently changing shape as needed unlike the parts which revert back into a ball form.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
