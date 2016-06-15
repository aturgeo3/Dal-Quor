package Tamaized.Voidcraft.Addons.JEI;

import javax.annotation.Nonnull;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IItemRegistry;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;
import Tamaized.Voidcraft.Addons.JEI.infuser.InfuserRecipeCategory;
import Tamaized.Voidcraft.Addons.JEI.infuser.InfuserRecipeHandler;
import Tamaized.Voidcraft.Addons.JEI.infuser.InfuserRecipeMaker;
import Tamaized.Voidcraft.Addons.JEI.macerator.MaceratorRecipeCategory;
import Tamaized.Voidcraft.Addons.JEI.macerator.MaceratorRecipeHandler;
import Tamaized.Voidcraft.Addons.JEI.macerator.MaceratorRecipeMaker;
import Tamaized.Voidcraft.common.voidCraft;

@JEIPlugin
public class VoidCraftJEIPlugin extends BlankModPlugin{
	
	public static IJeiHelpers jeiHelpers;
	
	@Override
	public void register(@Nonnull IModRegistry registry) {
		voidCraft.logger.info("JEI detected, loading VoidCraft JEI Plugin");
		IItemRegistry itemRegistry = registry.getItemRegistry();
		jeiHelpers = registry.getJeiHelpers();
		
		registry.addRecipeCategories(new InfuserRecipeCategory(), new MaceratorRecipeCategory());
		
		registry.addRecipes(InfuserRecipeMaker.getRecipes());
		registry.addRecipes(MaceratorRecipeMaker.getRecipes());
		
		registry.addRecipeHandlers(new InfuserRecipeHandler(), new MaceratorRecipeHandler());
		
		registry.addRecipeCategoryCraftingItem(new ItemStack(voidCraft.blocks.voidInfuser), "voidcraft_JEI_recipeCategory_Infuser");
		registry.addRecipeCategoryCraftingItem(new ItemStack(voidCraft.blocks.voidMacerator), "voidcraft_JEI_recipeCategory_Macerator");
		
	}
	
}
