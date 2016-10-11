package Tamaized.Voidcraft.Addons.JEI.alchemy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipesAlchemy.AlchemyRecipe;

public class AlchemyRecipeMaker {

	@Nonnull
	public static List<AlchemyRecipeJEI> getRecipes() {
		ArrayList<AlchemyRecipeJEI> recipes = new ArrayList<AlchemyRecipeJEI>();
		if (voidCraft.teRecipes.alchemy.getList() != null) {
			for (AlchemyRecipe recipe : voidCraft.teRecipes.alchemy.getList()) {
				recipes.add(new AlchemyRecipeJEI(recipe));
			}
		}else{
			voidCraft.logger.warn("Issue loading AlchemyRecipeJEI list");
		}
		return recipes;
	}
}
