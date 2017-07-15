package tamaized.voidcraft.common.addons.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.addons.jei.alchemy.AlchemyRecipeCategory;
import tamaized.voidcraft.common.addons.jei.alchemy.AlchemyRecipeHandler;
import tamaized.voidcraft.common.addons.jei.alchemy.AlchemyRecipeMaker;
import tamaized.voidcraft.common.addons.jei.blastfurnace.BlastFurnaceRecipeCategory;
import tamaized.voidcraft.common.addons.jei.blastfurnace.BlastFurnaceRecipeHandler;
import tamaized.voidcraft.common.addons.jei.blastfurnace.BlastFurnaceRecipeMaker;
import tamaized.voidcraft.common.addons.jei.infuser.InfuserRecipeCategory;
import tamaized.voidcraft.common.addons.jei.infuser.InfuserRecipeHandler;
import tamaized.voidcraft.common.addons.jei.infuser.InfuserRecipeMaker;
import tamaized.voidcraft.common.addons.jei.macerator.MaceratorRecipeCategory;
import tamaized.voidcraft.common.addons.jei.macerator.MaceratorRecipeHandler;
import tamaized.voidcraft.common.addons.jei.macerator.MaceratorRecipeMaker;
import tamaized.voidcraft.registry.VoidCraftBlocks;
import tamaized.voidcraft.registry.VoidCraftItems;

import javax.annotation.Nonnull;

@JEIPlugin
public class VoidCraftJEIPlugin extends BlankModPlugin {

	public static IJeiHelpers jeiHelpers;

	@Override
	public void register(@Nonnull IModRegistry registry) {
		VoidCraft.instance.logger.info("jei detected, loading VoidCraft jei Plugin");
		IIngredientRegistry itemRegistry = registry.getIngredientRegistry();
		jeiHelpers = registry.getJeiHelpers();

		registry.addIngredientInfo(new ItemStack(VoidCraftItems.vadeMecum), ItemStack.class, "Toss a book into Void Fire created from an Obsidian Flask");
		registry.addIngredientInfo(new ItemStack(VoidCraftItems.obsidianFlask), ItemStack.class, "While at Y level 5 and below, Right Click with an Empty Obsidian Flask in hand");

		registry.addRecipeCategories(new InfuserRecipeCategory(), new MaceratorRecipeCategory(), new AlchemyRecipeCategory(), new BlastFurnaceRecipeCategory());

		registry.addRecipes(InfuserRecipeMaker.getRecipes());
		registry.addRecipes(MaceratorRecipeMaker.getRecipes());
		registry.addRecipes(AlchemyRecipeMaker.getRecipes());
		registry.addRecipes(BlastFurnaceRecipeMaker.getRecipes());

		registry.addRecipeHandlers(new InfuserRecipeHandler(), new MaceratorRecipeHandler(), new AlchemyRecipeHandler(), new BlastFurnaceRecipeHandler());

		registry.addRecipeCategoryCraftingItem(new ItemStack(VoidCraftBlocks.voidInfuser), "voidcraft_JEI_recipeCategory_Infuser");
		registry.addRecipeCategoryCraftingItem(new ItemStack(VoidCraftBlocks.voidMacerator), "voidcraft_JEI_recipeCategory_Macerator");
		registry.addRecipeCategoryCraftingItem(new ItemStack(VoidCraftBlocks.voidicAlchemyTable), "voidcraft_JEI_recipeCategory_Alchemy");
		registry.addRecipeCategoryCraftingItem(new ItemStack(VoidCraftBlocks.voidBlastFurnace), "voidcraft_JEI_recipeCategory_BlastFurnace");

		registry.addAdvancedGuiHandlers(new VadeMecumSpellAdvancedGuiHandler());

	}

}
