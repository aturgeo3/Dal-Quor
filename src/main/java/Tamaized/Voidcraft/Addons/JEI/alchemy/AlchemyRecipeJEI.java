package Tamaized.Voidcraft.Addons.JEI.alchemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.machina.addons.TERecipesAlchemy.AlchemyRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

public class AlchemyRecipeJEI extends BlankRecipeWrapper {

	private final AlchemyRecipe recipe;

	public AlchemyRecipeJEI(AlchemyRecipe r) {
		recipe = r;
	}

	@Override
	@Nonnull
	public List<List<ItemStack>> getInputs() {
		List<List<ItemStack>> list = new ArrayList<List<ItemStack>>();
		for(ItemStack stack : recipe.getInput()){
			list.add(Arrays.asList(stack));
		}
		return list;
	}

	@Override
	@Nonnull
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(recipe.getOutput());
	}

	@Override
	public void getIngredients(IIngredients ingredients) {

	}

}
