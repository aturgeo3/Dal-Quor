package tamaized.voidcraft.common.vademecum.contents.documentation.starforge;

import tamaized.voidcraft.client.gui.VadeMecumGUI;
import tamaized.voidcraft.common.starforge.StarForgeEffectRecipeList;
import tamaized.voidcraft.common.starforge.effects.IStarForgeEffect;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VadeMecumPageStarForgeEffect extends VadeMecumPage {

	private StarForgeEffectRecipeList.Recipe recipe;

	public VadeMecumPageStarForgeEffect(String title, String text, IStarForgeEffect effect) {
		super(title, text);
		recipe = StarForgeEffectRecipeList.instance.getRecipe(effect);
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my, int offset) {
		super.render(gui, render, x, y, mx, my, offset);
		int index = 0;
		for (ItemStack stack : recipe.getInputs()) {
			gui.renderItemStack(stack, (x + offset) + (24 * index), y + 130, mx, my, 0xCCCCCC);
			index++;
		}
	}

}
