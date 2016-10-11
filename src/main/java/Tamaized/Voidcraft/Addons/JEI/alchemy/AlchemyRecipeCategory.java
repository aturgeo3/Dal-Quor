package Tamaized.Voidcraft.Addons.JEI.alchemy;

import java.util.List;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftJEIPlugin;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicAlchemy;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AlchemyRecipeCategory implements IRecipeCategory {

	private final ResourceLocation background = new ResourceLocation(voidCraft.modid, "textures/gui/JEI/voidicAlchemy.png");
	private IDrawableAnimated powerAnimation;
	private IDrawableAnimated progressAnimation;

	private ICraftingGridHelper craftingGridHelper;

	public AlchemyRecipeCategory() {
		craftingGridHelper = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createCraftingGridHelper(TileEntityVoidicAlchemy.SLOT_INPUT_1, TileEntityVoidicAlchemy.SLOT_OUTPUT);

		IDrawableStatic powerRender = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 12, 419, 12, 47, 5, 0, -14, 0);
		powerAnimation = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(powerRender, 500, IDrawableAnimated.StartDirection.TOP, true);

		IDrawableStatic progressRender = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 24, 420, 4, 11, 0, 0, 44, 0);
		progressAnimation = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(progressRender, 100, IDrawableAnimated.StartDirection.BOTTOM, false);
	}

	@Override
	public String getUid() {
		return "voidcraft_JEI_recipeCategory_Alchemy";
	}

	@Override
	public String getTitle() {
		return "Void Alchemy Table";
	}

	@Override
	public IDrawable getBackground() {
		return VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 0, 0, 150, 75, -10, 0, -60, 0);
	}

	@Override
	public void drawExtras(Minecraft minecraft) {

	}

	@Override
	public void drawAnimations(Minecraft minecraft) {
		powerAnimation.draw(minecraft);
		progressAnimation.draw(minecraft);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper) {

		IDrawableStatic progressRender = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createDrawable(background, 24, 420, 4, 12, 0, 0, 44, 0);
		progressAnimation = VoidCraftJEIPlugin.jeiHelpers.getGuiHelper().createAnimatedDrawable(progressRender, 100, IDrawableAnimated.StartDirection.BOTTOM, false);
		
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(TileEntityVoidicAlchemy.SLOT_OUTPUT, false, 37, 20);
		guiItemStacks.init(TileEntityVoidicAlchemy.SLOT_INPUT_1, true, 13, -5);
		guiItemStacks.init(TileEntityVoidicAlchemy.SLOT_INPUT_2, true, 2, 20);
		guiItemStacks.init(TileEntityVoidicAlchemy.SLOT_INPUT_3, true, 13, 45);
		guiItemStacks.init(TileEntityVoidicAlchemy.SLOT_INPUT_4, true, 61, -5);
		guiItemStacks.init(TileEntityVoidicAlchemy.SLOT_INPUT_5, true, 72, 20);
		guiItemStacks.init(TileEntityVoidicAlchemy.SLOT_INPUT_6, true, 61, 45);
		if (recipeWrapper instanceof AlchemyRecipeJEI) {
			AlchemyRecipeJEI recipe = (AlchemyRecipeJEI) recipeWrapper;
			craftingGridHelper.setOutput(guiItemStacks, recipe.getOutputs());
			craftingGridHelper.setInputStacks(guiItemStacks, recipe.getInputs(), TileEntityVoidicAlchemy.SLOT_INPUT_6, TileEntityVoidicAlchemy.SLOT_INPUT_6);
		}
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		setRecipe(recipeLayout, recipeWrapper);
	}

}
