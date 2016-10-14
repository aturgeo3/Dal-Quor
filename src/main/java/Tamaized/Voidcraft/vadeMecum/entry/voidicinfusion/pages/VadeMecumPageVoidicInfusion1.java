package Tamaized.Voidcraft.vadeMecum.entry.voidicinfusion.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageVoidicInfusion1 implements IVadeMecumPage {

	private final String title = "Voidic Infusion";
	private final String text = "While in the void, without a Voidic Suppressor in hand or the effects of Voidic Immunity, you will slowly gain Voidic Infusion. While under the effects of Voidic Infusion your vision becomes slightly obscured. Your maximum health will decrease. Void Crystal like spikes will begin to form and stick out of your arms and back. At a certian point you will gain the ability to fly. Your attacks will deal an addition 1 damage which goes through all forms of protection, this damage";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
