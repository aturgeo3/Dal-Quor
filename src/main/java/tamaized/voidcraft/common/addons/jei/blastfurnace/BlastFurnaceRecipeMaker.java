package tamaized.voidcraft.common.addons.jei.blastfurnace;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.machina.addons.TERecipesBlastFurnace.BlastFurnaceRecipe;

public class BlastFurnaceRecipeMaker {

	@Nonnull
	public static List<BlastFurnaceRecipeWrapperJEI> getRecipes() {
		ArrayList<BlastFurnaceRecipeWrapperJEI> recipes = new ArrayList<BlastFurnaceRecipeWrapperJEI>();
		for (BlastFurnaceRecipe recipe : VoidCraft.teRecipes.blastFurnace.getList())
			recipes.add(new BlastFurnaceRecipeWrapperJEI(recipe));
		return recipes;
	}
}
