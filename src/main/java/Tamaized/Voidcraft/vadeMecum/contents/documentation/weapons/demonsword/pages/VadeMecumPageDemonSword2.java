package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.demonsword.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingInfuser;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VadeMecumPageDemonSword2 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingInfuser("Infusion Recipe", new ItemStack(voidCraft.tools.archSword), new ItemStack(voidCraft.tools.demonSword));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
