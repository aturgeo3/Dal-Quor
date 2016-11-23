package Tamaized.Voidcraft.Addons.JEI.macerator;

import Tamaized.Voidcraft.Addons.JEI.VoidCraftRecipeWrapperJEI;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class MaceratorRecipeHandler implements IRecipeHandler<VoidCraftRecipeWrapperJEI> {

	@Override
	public Class<VoidCraftRecipeWrapperJEI> getRecipeClass() {
		return VoidCraftRecipeWrapperJEI.class;
	}

	@Override
	public String getRecipeCategoryUid(VoidCraftRecipeWrapperJEI recipe) {
		return "voidcraft_JEI_recipeCategory_Macerator";
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(VoidCraftRecipeWrapperJEI recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(VoidCraftRecipeWrapperJEI recipe) {
		return recipe.isValid();
	}

}
