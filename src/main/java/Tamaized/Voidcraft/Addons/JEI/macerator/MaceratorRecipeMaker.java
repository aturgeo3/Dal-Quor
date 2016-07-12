package Tamaized.Voidcraft.Addons.JEI.macerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import Tamaized.Voidcraft.common.voidCraft;

public class MaceratorRecipeMaker {

	@Nonnull
	public static List<MaceratorRecipeJEI> getRecipes() {
		Map<ItemStack, ItemStack> recipeList = voidCraft.teRecipes.macerator.getRawMap();
		ArrayList<MaceratorRecipeJEI> recipes = new ArrayList<MaceratorRecipeJEI>();
		for (Entry<ItemStack, ItemStack> recipe : recipeList.entrySet())
			recipes.add(new MaceratorRecipeJEI(recipe.getKey(), recipe.getValue()));
		return recipes;
	}
}
