package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.heimdall.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageHeimdall1 implements IVadeMecumPage {

	private final String title = "Heimdall";
	private final String text = "Beacons have the curious effect of seemingly generating a powerful beam of concentrated light. Though, such power cannot possibly be generated on its own. The more logical explanation is that it creates a small gate or rift to the Nether from which it can retrieve this energy. By modifying a Beacon via Void Infusion Altar, one can attune this gate to gather energy from the Void instead. Though, unlike a Beacon, which works effectively in";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
