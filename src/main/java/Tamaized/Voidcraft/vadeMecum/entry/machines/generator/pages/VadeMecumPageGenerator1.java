package Tamaized.Voidcraft.vadeMecum.entry.machines.generator.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageGenerator1 implements IVadeMecumPage {

	private final String title = "Voidic Generator";
	private final String text = "They say there is no such thing as nothingness, for even empty space is filled with energy. But given the vacuous nature of The Void, there are plenty of opportunities to exploit this fundamental part of quantum physics. This energy manifests itself as pools of Liquid Void, whose strange physical properties make it behave in even stranger ways. This leaves one to wonder how it can be used; one potential answer can be found in the Voidic";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
