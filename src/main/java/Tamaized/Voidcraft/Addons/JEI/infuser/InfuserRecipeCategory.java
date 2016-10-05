package Tamaized.Voidcraft.Addons.JEI.infuser;

import java.util.List;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftJEIPlugin;
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

public class InfuserRecipeCategory implements IRecipeCategory {

	private final ResourceLocation background = new ResourceLocation(voidCraft.modid, "textures/gui/JEI/voidInfuser.png");
	private IDrawableAnimated fluidAnimation;
	private IDrawableAnimated progressAnimation;

	private static final int OUTPUT_SLOT = 0;
	private static final int FLUID_SLOT = 1;
	private static final int INPUT_SLOT = 2;

	private final ICraftingGridHelper craftingGridHelper;

	public InfuserRecipeCategory() {
		craftingGridHelper = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createCraftingGridHelper(INPUT_SLOT, OUTPUT_SLOT);

		IDrawableStatic fluidRender = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 0, 452, 20, 46, 20, 0, 15, 0);
		fluidAnimation = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(fluidRender, 200, IDrawableAnimated.StartDirection.TOP, true);

		IDrawableStatic progressRender = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 0, 434, 80, 16, 35, 0, 110, 0);
		progressAnimation = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(progressRender, 80, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public String getUid() {
		return "voidcraft_JEI_recipeCategory_Infuser";
	}

	@Override
	public String getTitle() {
		return "Void Infuser";
	}

	@Override
	public IDrawable getBackground() {
		return VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 0, 0, 180, 70, 0, 0, 0, 0);
	}

	@Override
	public void drawExtras(Minecraft minecraft) {

	}

	@Override
	public void drawAnimations(Minecraft minecraft) {

		fluidAnimation.draw(minecraft);
		progressAnimation.draw(minecraft);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(OUTPUT_SLOT, false, 146, 34);
		guiItemStacks.init(FLUID_SLOT, true, 51, 33);
		guiItemStacks.init(INPUT_SLOT, true, 89, 33);
		if (recipeWrapper instanceof InfuserRecipeJEI) {
			InfuserRecipeJEI recipe = (InfuserRecipeJEI) recipeWrapper;
			guiItemStacks.set(FLUID_SLOT, (List) recipe.getInputs().get(1));
			craftingGridHelper.setOutput(guiItemStacks, recipe.getOutputs());
			craftingGridHelper.setInput(guiItemStacks, (List) recipe.getInputs().get(0), 2, 3);
		}
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		setRecipe(recipeLayout, recipeWrapper);
	}

}
