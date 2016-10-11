package Tamaized.Voidcraft.Addons.JEI.alchemy;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class AlchemyRecipeHandler implements IRecipeHandler<AlchemyRecipeJEI> {

	@Override
	public Class<AlchemyRecipeJEI> getRecipeClass() {
		return AlchemyRecipeJEI.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return "voidcraft_JEI_recipeCategory_Alchemy";
	}

	@Override
	public String getRecipeCategoryUid(AlchemyRecipeJEI recipe) {
		return "voidcraft_JEI_recipeCategory_Alchemy";
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(AlchemyRecipeJEI recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(AlchemyRecipeJEI recipe) {
		return recipe.getOutputs().size() > 0 && recipe.getInputs().size() > 0;
	}

}
