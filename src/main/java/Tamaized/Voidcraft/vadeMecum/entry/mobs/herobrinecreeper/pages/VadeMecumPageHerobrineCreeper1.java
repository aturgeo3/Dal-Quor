package Tamaized.Voidcraft.vadeMecum.entry.mobs.herobrinecreeper.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageHerobrineCreeper1 implements IVadeMecumPage {

	private final String title = "Herobrine Creeper";
	private final String text = "Only found within a Void Fortress. These are enhanced Creepers with different visuals. The explosion radius and damage are a bit larger. Though they do not harm the world.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
