package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.musicbox.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageMusicBox1 implements IVadeMecumPage {

	private final String title = "Void Music Box";
	private final String text = "A special jukebox which was first designed by Void Liches. It is special in the sense that it can read any fully functional music disk. This music box can take input disks and be extracted from using other machines, such as a hopper. It also has an auto-insert function and will auto-play the next pending disc. This, paired with its loop functionality, can be used to easily create a space which always has music playing in the background.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
