package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.moltensword.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumPageMoltenSword1 implements IVadeMecumPage {

	private final String title = "Molten Sword";
	private final String text = "Foes struck by this sword are lit on fire and are inflicted with a weakness and nausea debuff for 5 seconds.";

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int offset) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65 + offset, y, 0x000000);
		render.drawSplitString(text, x + offset, y + 20, 140, 0x000000);
	}

}
