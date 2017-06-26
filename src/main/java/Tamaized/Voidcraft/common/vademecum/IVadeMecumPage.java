package Tamaized.Voidcraft.common.vademecum;

import Tamaized.Voidcraft.client.gui.VadeMecumGUI;
import net.minecraft.client.gui.FontRenderer;

public interface IVadeMecumPage {

	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset);

}
