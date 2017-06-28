package tamaized.voidcraft.common.addons.jei.infuser;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.machina.addons.TERecipeInfuser.InfuserRecipe;

public class InfuserRecipeMaker {

	@Nonnull
	public static List<InfuserRecipeWrapperJEI> getRecipes() {
		ArrayList<InfuserRecipeWrapperJEI> recipes = new ArrayList<InfuserRecipeWrapperJEI>();
		for (InfuserRecipe recipe : VoidCraft.teRecipes.infuser.getList())
			recipes.add(new InfuserRecipeWrapperJEI(recipe));
		return recipes;
	}
}
