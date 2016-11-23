package Tamaized.Voidcraft.Addons.JEI;

import java.util.List;

import Tamaized.TamModized.tileentity.TamTileEntityRecipeList;
import Tamaized.Voidcraft.machina.addons.TERecipeInfuser.InfuserRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

public class VoidCraftRecipeWrapperJEI<T extends TamTileEntityRecipeList.TamTERecipe> extends BlankRecipeWrapper {

	private final T recipe;

	public VoidCraftRecipeWrapperJEI(T r) {
		recipe = r;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(ItemStack.class, getInputs());
		ingredients.setOutput(ItemStack.class, getOutput());
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

}
