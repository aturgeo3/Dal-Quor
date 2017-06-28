package tamaized.voidcraft.common.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public final class RecipeHelper {

	private RecipeHelper() {

	}

	public static IRecipe getRecipe(ItemStack output) {
		for (IRecipe recipe : CraftingManager.REGISTRY) {
			if ((recipe instanceof ShapedRecipes || recipe instanceof ShapelessOreRecipe) && ItemStack.areItemsEqual(recipe.getRecipeOutput(), output)) {
				return recipe;
			}
		}
		return null;
	}

	public static List<ItemStack> getFurnaceRecipe(ItemStack output) {
		List<ItemStack> list = new ArrayList<>();
		for (Entry<ItemStack, ItemStack> entry : FurnaceRecipes.instance().getSmeltingList().entrySet()) {
			if(ItemStack.areItemsEqual(entry.getValue(), output)){
				list.add(new ItemStack(entry.getKey().getItem()));
			}
		}
		return list;
	}

}
