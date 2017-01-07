package Tamaized.Voidcraft.Addons.JEI.alchemy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipesAlchemy.AlchemyRecipe;

public class AlchemyRecipeMaker {

	@Nonnull
	public static List<AlchemyRecipeWrapperJEI> getRecipes() {
		ArrayList<AlchemyRecipeWrapperJEI> recipes = new ArrayList<AlchemyRecipeWrapperJEI>();
		if (voidCraft.teRecipes.alchemy.getList() != null) {
			for (AlchemyRecipe recipe : voidCraft.teRecipes.alchemy.getList()) {
				recipes.add(new AlchemyRecipeWrapperJEI(recipe));
			}
		} else {
			voidCraft.instance.logger.warn("Issue loading AlchemyRecipe JEI list");
		}
		return recipes;
	}
}
