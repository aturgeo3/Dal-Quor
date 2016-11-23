package Tamaized.Voidcraft.Addons.JEI.macerator;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftRecipeWrapperJEI;
import Tamaized.Voidcraft.machina.addons.TERecipesMacerator.MaceratorRecipe;

public class MaceratorRecipeMaker {

	@Nonnull
	public static List<VoidCraftRecipeWrapperJEI<MaceratorRecipe>> getRecipes() {
		ArrayList<VoidCraftRecipeWrapperJEI<MaceratorRecipe>> recipes = new ArrayList<VoidCraftRecipeWrapperJEI<MaceratorRecipe>>();
		for (MaceratorRecipe recipe : voidCraft.teRecipes.macerator.getList())
			recipes.add(new VoidCraftRecipeWrapperJEI<MaceratorRecipe>(recipe));
		return recipes;
	}
}
