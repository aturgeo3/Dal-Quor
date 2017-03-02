package Tamaized.Voidcraft.Addons.JEI.blastfurnace;

import java.util.Arrays;

import Tamaized.Voidcraft.Addons.JEI.VoidCraftRecipeWrapperJEI;
import Tamaized.Voidcraft.machina.addons.TERecipesBlastFurnace.BlastFurnaceRecipe;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBlastFurnace;
import mezz.jei.api.gui.IGuiIngredientGroup;

public class BlastFurnaceRecipeWrapperJEI extends VoidCraftRecipeWrapperJEI<BlastFurnaceRecipe> {

	public BlastFurnaceRecipeWrapperJEI(BlastFurnaceRecipe recipe) {
		super(recipe);
	}

	@Override
	public void setupSlots(IGuiIngredientGroup g) {
		g.init(TileEntityVoidBlastFurnace.SLOT_OUTPUT, false, 133, 14);
		g.init(TileEntityVoidBlastFurnace.SLOT_INPUT_IRON, true, 87, 6);
		g.init(TileEntityVoidBlastFurnace.SLOT_INPUT_COAL, true, 87, 24);

		g.set(TileEntityVoidBlastFurnace.SLOT_OUTPUT, Arrays.asList(getOutput()));
		g.set(TileEntityVoidBlastFurnace.SLOT_INPUT_IRON, getInputs().get(0));
		g.set(TileEntityVoidBlastFurnace.SLOT_INPUT_COAL, getInputs().get(1));
	}

}
