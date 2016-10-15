package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.chainedskull.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageChainedSkull1 implements IVadeMecumPage {

	private final String title = "Chained Skull";
	private final String text = "Oddly, covering a Wither's skull in Charred Bones followed by Molten Chains transforms the skull. You do not know if it is your mind playing tricks on you or if this skull is alive... breathing... speaking to you. It wants you to place it down. It tells you that you can end the world with it, that you should.                                                 Placing down the skull will result in explosions and a boss fight, be prepared.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
