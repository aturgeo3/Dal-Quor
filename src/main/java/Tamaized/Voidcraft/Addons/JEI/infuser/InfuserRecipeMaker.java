package Tamaized.Voidcraft.Addons.JEI.infuser;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipeInfuser.InfuserRecipe;

public class InfuserRecipeMaker {

	@Nonnull
	public static List<InfuserRecipeWrapperJEI> getRecipes() {
		ArrayList<InfuserRecipeWrapperJEI> recipes = new ArrayList<InfuserRecipeWrapperJEI>();
		for (InfuserRecipe recipe : voidCraft.teRecipes.infuser.getList())
			recipes.add(new InfuserRecipeWrapperJEI(recipe));
		return recipes;
	}
}
