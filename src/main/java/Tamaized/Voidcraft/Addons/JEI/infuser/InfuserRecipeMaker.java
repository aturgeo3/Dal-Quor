package Tamaized.Voidcraft.Addons.JEI.infuser;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftRecipeWrapperJEI;
import Tamaized.Voidcraft.machina.addons.TERecipeInfuser.InfuserRecipe;

public class InfuserRecipeMaker {

	@Nonnull
	public static List<VoidCraftRecipeWrapperJEI<InfuserRecipe>> getRecipes() {
		ArrayList<VoidCraftRecipeWrapperJEI<InfuserRecipe>> recipes = new ArrayList<VoidCraftRecipeWrapperJEI<InfuserRecipe>>();
		for (InfuserRecipe recipe : voidCraft.teRecipes.infuser.getList())
			recipes.add(new VoidCraftRecipeWrapperJEI<InfuserRecipe>(recipe));
		return recipes;
	}
}
