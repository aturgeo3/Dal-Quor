package tamaized.voidcraft.common.addons.jei.macerator;

import mezz.jei.api.gui.IGuiIngredientGroup;
import tamaized.voidcraft.common.addons.jei.VoidCraftRecipeWrapperJEI;
import tamaized.voidcraft.common.machina.addons.TERecipesMacerator.MaceratorRecipe;

import java.util.Arrays;

public class MaceratorRecipeWrapperJEI extends VoidCraftRecipeWrapperJEI<MaceratorRecipe> {

	private static final int OUTPUT_SLOT = 0;
	private static final int INPUT_SLOT = 2;

	public MaceratorRecipeWrapperJEI(MaceratorRecipe recipe) {
		super(recipe);
	}

	@Override
	public void setupSlots(IGuiIngredientGroup g) {
		g.init(OUTPUT_SLOT, false, 146, 34 - 20);
		g.init(INPUT_SLOT, true, 89, 33 - 20);

		g.set(OUTPUT_SLOT, Arrays.asList(getOutput()));
		g.set(INPUT_SLOT, getInputs());

	}

}
