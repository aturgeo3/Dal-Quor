package tamaized.voidcraft.common.addons.jei.macerator;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.machina.addons.TERecipesMacerator.MaceratorRecipe;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class MaceratorRecipeMaker {

	@Nonnull
	public static List<MaceratorRecipeWrapperJEI> getRecipes() {
		ArrayList<MaceratorRecipeWrapperJEI> recipes = new ArrayList<MaceratorRecipeWrapperJEI>();
		for (MaceratorRecipe recipe : VoidCraft.teRecipes.macerator.getList())
			recipes.add(new MaceratorRecipeWrapperJEI(recipe));
		return recipes;
	}
}
