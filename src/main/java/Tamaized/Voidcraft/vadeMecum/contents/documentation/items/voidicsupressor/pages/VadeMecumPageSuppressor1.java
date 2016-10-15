package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidicsupressor.pages;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageSuppressor1 implements IVadeMecumPage {

	private final String title = "Voidic Suppressor";
	private final String text = "One of the few items that requires Voidic Power to function. To charge this device place it in a Voidic Charger. If this device contains power, it'll use that power to anchor the holder to the material plane. This means while in your hand or offhand the Voidic Suppressor will remove any Voidic Infusion on you and prevent more from being gained.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
