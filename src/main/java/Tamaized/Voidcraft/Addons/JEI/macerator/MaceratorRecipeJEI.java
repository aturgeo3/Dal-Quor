package Tamaized.Voidcraft.Addons.JEI.macerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

public class MaceratorRecipeJEI extends BlankRecipeWrapper {

	private final ItemStack input;
	private final ItemStack output;

	public MaceratorRecipeJEI(ItemStack i, ItemStack o) {
		input = i;
		output = o;
	}

	@Override
	@Nonnull
	public List getInputs() {
		ArrayList<Collection> ret = new ArrayList<Collection>();
		ret.add(Collections.singletonList(input));
		return ret;
	}

	@Override
	@Nonnull
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(output);
	}

	@Override
	public void getIngredients(IIngredients ingredients) {

	}

}
