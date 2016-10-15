package Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.lich.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageLich1 implements IVadeMecumPage {

	private final String title = "Void Lich";
	private final String text = "Only found within a Swampland and quite rare. Liches will do a variety of attacks. These attacks are a Lightning Bolt, a Fireball, the summoning of undead aid which consists of Void Wraiths, Chained Specters, Void Wraths, and Wither Skeletons. Liches can cast a spreading ring of Void Fire. And they can incase their target in stone. Liches do not like each other and will attack one another on sight. Upon death they'll drop Void Cloth.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x+65+offset, y, 0x000000);
		render.drawSplitString(text, x+offset, y + 20, 140, 0x000000);
	}

}
