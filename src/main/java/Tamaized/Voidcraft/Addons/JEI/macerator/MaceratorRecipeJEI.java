package Tamaized.Voidcraft.Addons.JEI.macerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipesMacerator.MaceratorRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

public class MaceratorRecipeJEI extends BlankRecipeWrapper {

	private final MaceratorRecipe recipe;

	public MaceratorRecipeJEI(MaceratorRecipe r) {
		recipe = r;
	}

	@Override
	@Nonnull
	public List getInputs() {
		ArrayList<Collection> ret = new ArrayList<Collection>();
		for(ItemStack stack : recipe.getInput()){
			ret.add(Collections.singletonList(stack));
		}
		return ret;
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
