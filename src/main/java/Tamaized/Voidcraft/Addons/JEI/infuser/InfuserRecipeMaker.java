package Tamaized.Voidcraft.Addons.JEI.infuser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipeInfuser.InfuserRecipe;

public class InfuserRecipeMaker {
	
	@Nonnull
	public static List<InfuserRecipeJEI> getRecipes(){
		ArrayList<InfuserRecipeJEI> recipes = new ArrayList<InfuserRecipeJEI>();
		for(InfuserRecipe recipe : voidCraft.teRecipes.infuser.getList()) recipes.add(new InfuserRecipeJEI(recipe));
		return recipes;
	}
}
