package Tamaized.Voidcraft.Addons.JEI.infuser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import Tamaized.Voidcraft.machina.addons.InfuserRecipes;

public class InfuserRecipeMaker {
	
	@Nonnull
	public static List<InfuserRecipeJEI> getRecipes(){
		Map<Item, ItemStack> recipeList = InfuserRecipes.smelting().getSmeltingList();
		ArrayList<InfuserRecipeJEI> recipes = new ArrayList<InfuserRecipeJEI>();
		for(Entry<Item, ItemStack> recipe : recipeList.entrySet()) recipes.add(new InfuserRecipeJEI(recipe.getKey(), recipe.getValue()));
		return recipes;
	}
}
