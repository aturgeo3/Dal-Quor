package Tamaized.Voidcraft.Addons.JEI.macerator;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipesMacerator.MaceratorRecipe;

public class MaceratorRecipeMaker {

	@Nonnull
	public static List<MaceratorRecipeJEI> getRecipes() {
		ArrayList<MaceratorRecipeJEI> recipes = new ArrayList<MaceratorRecipeJEI>();
		for (MaceratorRecipe recipe : voidCraft.teRecipes.macerator.getList())
			recipes.add(new MaceratorRecipeJEI(recipe));
		return recipes;
	}
}
