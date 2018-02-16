package tamaized.dalquor.common.vademecum;

import net.minecraft.client.gui.FontRenderer;
import tamaized.dalquor.client.gui.VadeMecumGUI;

public class VadeMecumPageCrafting implements IVadeMecumPage {

	private final IVadeMecumCrafting crafting;

	public VadeMecumPageCrafting(IVadeMecumCrafting craft) {
		crafting = craft;
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		crafting.render(gui, render, x + offset, y, mx, my);
	}

}
