package tamaized.voidcraft.common.vademecum;

import net.minecraft.client.gui.FontRenderer;
import tamaized.voidcraft.client.gui.VadeMecumGUI;

public interface IVadeMecumPage {

	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset);

}
