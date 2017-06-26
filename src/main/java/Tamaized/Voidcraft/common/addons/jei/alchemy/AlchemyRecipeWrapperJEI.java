package Tamaized.Voidcraft.common.addons.jei.alchemy;

import Tamaized.TamModized.helper.TranslateHelper;
import Tamaized.Voidcraft.common.addons.jei.VoidCraftRecipeWrapperJEI;
import Tamaized.Voidcraft.common.machina.addons.TERecipesAlchemy.AlchemyRecipe;
import Tamaized.Voidcraft.common.vademecum.progression.VadeMecumWordsOfPower;
import mezz.jei.api.gui.IGuiIngredientGroup;
import net.minecraft.client.Minecraft;

public class AlchemyRecipeWrapperJEI extends VoidCraftRecipeWrapperJEI<AlchemyRecipe> {

	public AlchemyRecipeWrapperJEI(AlchemyRecipe r) {
		super(r);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		minecraft.fontRenderer.drawString(TranslateHelper.translate("voidcraft.gui.jei.alch.research"), -40, 0, 0x000000);
		minecraft.fontRenderer.drawString(getRecipe().getCategory() == null ? TranslateHelper.translate("voidcraft.gui.misc.none") : TranslateHelper.translate(VadeMecumWordsOfPower.getCategoryData(getRecipe().getCategory()).getName()), -40, 10, 0x000000);
		super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
	}

	@Override
	public void setupSlots(IGuiIngredientGroup g) {
		final int x = 0;
		final int y = 40;

		g.init(0, false, 37 + x, 20 + y);
		g.init(1, true, 13 + x, -5 + y);
		g.init(2, true, 2 + x, 20 + y);
		g.init(3, true, 13 + x, 45 + y);
		g.init(4, true, 61 + x, -5 + y);
		g.init(5, true, 72 + x, 20 + y);
		g.init(6, true, 61 + x, 45 + y);

		g.set(0, getOutput());

		g.set(1, getInputs().get(0));
		g.set(2, getInputs().get(1));
		g.set(3, getInputs().get(2));
		g.set(4, getInputs().get(3));
		g.set(5, getInputs().get(4));
		g.set(6, getInputs().get(5));

	}

}
