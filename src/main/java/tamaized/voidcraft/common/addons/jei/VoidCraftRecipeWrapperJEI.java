package tamaized.voidcraft.common.addons.jei;

import mezz.jei.api.gui.IGuiIngredientGroup;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import tamaized.tammodized.common.tileentity.TamTileEntityRecipeList;

import java.util.Arrays;
import java.util.List;

public abstract class VoidCraftRecipeWrapperJEI<T extends TamTileEntityRecipeList.TamTERecipe> implements IRecipeWrapper {

	private final T recipe;

	public VoidCraftRecipeWrapperJEI(T r) {
		recipe = r;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(ItemStack.class, getInputs());
		ingredients.setOutput(ItemStack.class, getOutput());
	}

	public T getRecipe() {
		return recipe;
	}

	public List<ItemStack> getInputs() {
		return Arrays.asList(recipe.getInput());
	}

	public ItemStack getOutput() {
		return recipe.getOutput();
	}

	public boolean isValid() {
		return recipe != null && recipe.getInput().length > 0 && !recipe.getOutput().isEmpty();
	}

	public abstract void setupSlots(IGuiIngredientGroup g);

}
