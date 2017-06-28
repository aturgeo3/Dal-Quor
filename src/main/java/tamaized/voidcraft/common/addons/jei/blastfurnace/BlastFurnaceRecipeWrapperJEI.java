package tamaized.voidcraft.common.addons.jei.blastfurnace;

import tamaized.voidcraft.common.addons.jei.VoidCraftRecipeWrapperJEI;
import tamaized.voidcraft.common.machina.addons.TERecipesBlastFurnace.BlastFurnaceRecipe;
import mezz.jei.api.gui.IGuiIngredientGroup;

import java.util.Arrays;

public class BlastFurnaceRecipeWrapperJEI extends VoidCraftRecipeWrapperJEI<BlastFurnaceRecipe> {

	public BlastFurnaceRecipeWrapperJEI(BlastFurnaceRecipe recipe) {
		super(recipe);
	}

	@Override
	public void setupSlots(IGuiIngredientGroup g) {
		g.init(0, false, 133, 14);
		g.init(1, true, 87, 6);
		g.init(2, true, 87, 24);

		g.set(0, Arrays.asList(getOutput()));
		g.set(1, getInputs().get(0));
		g.set(2, getInputs().get(1));
	}

}
