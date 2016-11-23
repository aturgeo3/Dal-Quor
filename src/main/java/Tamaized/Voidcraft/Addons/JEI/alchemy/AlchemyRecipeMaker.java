package Tamaized.Voidcraft.Addons.JEI.alchemy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftRecipeWrapperJEI;
import Tamaized.Voidcraft.machina.addons.TERecipesAlchemy.AlchemyRecipe;

public class AlchemyRecipeMaker {

	@Nonnull
	public static List<VoidCraftRecipeWrapperJEI<AlchemyRecipe>> getRecipes() {
		ArrayList<VoidCraftRecipeWrapperJEI<AlchemyRecipe>> recipes = new ArrayList<VoidCraftRecipeWrapperJEI<AlchemyRecipe>>();
		if (voidCraft.teRecipes.alchemy.getList() != null) {
			for (AlchemyRecipe recipe : voidCraft.teRecipes.alchemy.getList()) {
				recipes.add(new VoidCraftRecipeWrapperJEI<AlchemyRecipe>(recipe));
			}
		} else {
			voidCraft.logger.warn("Issue loading AlchemyRecipe JEI list");
		}
		return recipes;
	}
}
