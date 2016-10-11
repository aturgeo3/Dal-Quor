package Tamaized.Voidcraft.Addons.JEI.macerator;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class MaceratorRecipeHandler implements IRecipeHandler<MaceratorRecipeJEI> {

	@Override
	public Class<MaceratorRecipeJEI> getRecipeClass() {
		return MaceratorRecipeJEI.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return "voidcraft_JEI_recipeCategory_Macerator";
	}

	@Override
	public String getRecipeCategoryUid(MaceratorRecipeJEI recipe) {
		return "voidcraft_JEI_recipeCategory_Macerator";
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(MaceratorRecipeJEI recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(MaceratorRecipeJEI recipe) {
		return recipe.getOutputs().size() > 0 && recipe.getInputs().size() > 0;
	}

}
