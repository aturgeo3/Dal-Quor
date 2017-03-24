package Tamaized.Voidcraft.Addons.JEI.alchemy;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.Addons.JEI.VoidCraftRecipeWrapperJEI;
import Tamaized.Voidcraft.machina.addons.TERecipesAlchemy.AlchemyRecipe;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicAlchemy;
import Tamaized.Voidcraft.vadeMecum.progression.VadeMecumWordsOfPower;
import mezz.jei.api.gui.IGuiIngredientGroup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class AlchemyRecipeWrapperJEI extends VoidCraftRecipeWrapperJEI<AlchemyRecipe> {

	public AlchemyRecipeWrapperJEI(AlchemyRecipe r) {
		super(r);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		minecraft.fontRendererObj.drawString(I18n.format("voidcraft.gui.jei.alch.research", new Object[0]).trim(), -40, 0, 0x000000);
		minecraft.fontRendererObj.drawString(getRecipe().getCategory() == null ? I18n.format("voidcraft.gui.misc.none", new Object[0]).trim() :  I18n.format(VadeMecumWordsOfPower.getCategoryData(getRecipe().getCategory()).getName(), new Object[0]).trim(), -40, 10, 0x000000);
		super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
	}

	@Override
	public void setupSlots(IGuiIngredientGroup g) {
		final int x = 0;
		final int y = 40;
		
		g.init(TileEntityVoidicAlchemy.SLOT_OUTPUT, false, 37 + x, 20 + y);
		g.init(TileEntityVoidicAlchemy.SLOT_INPUT_1, true, 13 + x, -5 + y);
		g.init(TileEntityVoidicAlchemy.SLOT_INPUT_2, true, 2 + x, 20 + y);
		g.init(TileEntityVoidicAlchemy.SLOT_INPUT_3, true, 13 + x, 45 + y);
		g.init(TileEntityVoidicAlchemy.SLOT_INPUT_4, true, 61 + x, -5 + y);
		g.init(TileEntityVoidicAlchemy.SLOT_INPUT_5, true, 72 + x, 20 + y);
		g.init(TileEntityVoidicAlchemy.SLOT_INPUT_6, true, 61 + x, 45 + y);

		g.set(TileEntityVoidicAlchemy.SLOT_OUTPUT, getOutput());

		g.set(TileEntityVoidicAlchemy.SLOT_INPUT_1, getInputs().get(0));
		g.set(TileEntityVoidicAlchemy.SLOT_INPUT_2, getInputs().get(1));
		g.set(TileEntityVoidicAlchemy.SLOT_INPUT_3, getInputs().get(2));
		g.set(TileEntityVoidicAlchemy.SLOT_INPUT_4, getInputs().get(3));
		g.set(TileEntityVoidicAlchemy.SLOT_INPUT_5, getInputs().get(4));
		g.set(TileEntityVoidicAlchemy.SLOT_INPUT_6, getInputs().get(5));

	}

}
