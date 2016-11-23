package Tamaized.Voidcraft.Addons.JEI.infuser;

import Tamaized.Voidcraft.Addons.JEI.VoidCraftRecipeWrapperJEI;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class InfuserRecipeHandler implements IRecipeHandler<VoidCraftRecipeWrapperJEI> {

	@Override
	public Class<VoidCraftRecipeWrapperJEI> getRecipeClass() {
		return VoidCraftRecipeWrapperJEI.class;
	}

	@Override
	public String getRecipeCategoryUid(VoidCraftRecipeWrapperJEI recipe) {
		return "voidcraft_JEI_recipeCategory_Infuser";
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
