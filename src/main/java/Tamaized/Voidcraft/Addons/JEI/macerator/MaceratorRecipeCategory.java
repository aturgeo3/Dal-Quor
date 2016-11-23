package Tamaized.Voidcraft.Addons.JEI.macerator;

import java.util.Arrays;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftJEIPlugin;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftRecipeWrapperJEI;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class MaceratorRecipeCategory implements IRecipeCategory {

	private final ResourceLocation background = new ResourceLocation(voidCraft.modid, "textures/gui/JEI/voidMacerator.png");
	private IDrawableAnimated fluidAnimation;
	private IDrawableAnimated progressAnimation;

	private static final int OUTPUT_SLOT = 0;
	private static final int INPUT_SLOT = 2;

	private final ICraftingGridHelper craftingGridHelper;

	public MaceratorRecipeCategory() {
		craftingGridHelper = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createCraftingGridHelper(INPUT_SLOT, OUTPUT_SLOT);

		IDrawableStatic powerRender = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 12, 448, 20, 48, 20 - 20, 0, 36, 0);
		fluidAnimation = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(powerRender, 200, IDrawableAnimated.StartDirection.TOP, true);

		IDrawableStatic progressRender = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 00, 434, 26, 16, 33 - 20, 0, 110, 0);
		progressAnimation = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(progressRender, 100, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public String getUid() {
		return "voidcraft_JEI_recipeCategory_Macerator";
	}

	@Override
	public String getTitle() {
		return "Void Macerator";
	}

	@Override
	public IDrawable getBackground() {
		return VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 0, 0, 180, 75, -20, 0, 0, 0);
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		fluidAnimation.draw(minecraft);
		progressAnimation.draw(minecraft);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup stackGroup = recipeLayout.getItemStacks();
		stackGroup.init(OUTPUT_SLOT, false, 146, 34 - 20);
		stackGroup.init(INPUT_SLOT, true, 89, 33 - 20);
		if (recipeWrapper instanceof VoidCraftRecipeWrapperJEI) {
			VoidCraftRecipeWrapperJEI recipe = (VoidCraftRecipeWrapperJEI) recipeWrapper;
			craftingGridHelper.setOutput(stackGroup, Arrays.asList(recipe.getOutput()));
			craftingGridHelper.setInputStacks(stackGroup, Arrays.asList(recipe.getInputs()));
		}
	}

	@Override
	public IDrawable getIcon() {
		return null;
	}

}
