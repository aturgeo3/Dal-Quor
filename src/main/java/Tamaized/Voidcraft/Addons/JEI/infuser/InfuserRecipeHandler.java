package Tamaized.Voidcraft.Addons.JEI.infuser;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class InfuserRecipeHandler implements IRecipeHandler<InfuserRecipeJEI> {

	@Override
	public Class<InfuserRecipeJEI> getRecipeClass() {
		return InfuserRecipeJEI.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return "voidcraft_JEI_recipeCategory_Infuser";
	}

	@Override
	public String getRecipeCategoryUid(InfuserRecipeJEI recipe) {
		return "voidcraft_JEI_recipeCategory_Infuser";
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(InfuserRecipeJEI recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(InfuserRecipeJEI recipe) {
		return recipe.getOutputs().size() > 0 && recipe.getInputs().size() > 0;
	}

}
