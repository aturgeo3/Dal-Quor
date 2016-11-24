package Tamaized.Voidcraft.machina.addons;

import java.util.ArrayList;

import Tamaized.TamModized.tileentity.TamTileEntityRecipeList;
import Tamaized.Voidcraft.voidCraft;
import net.minecraft.item.ItemStack;

public class TERecipesAlchemy extends TamTileEntityRecipeList<TERecipesAlchemy.AlchemyRecipe> {

	@Override
	public boolean registerRecipe(AlchemyRecipe recipe) {
		if (recipe instanceof AlchemyRecipe) {
			return super.registerRecipe(recipe);
		} else {
			voidCraft.logger.error("Tried to register a non-Alchemy recipe in the Alchemy Recipe List");
			return false;
		}
	}

	public ArrayList<AlchemyRecipe> getList() { // TODO: move this into TamModized
		return recipes;
	}

	public boolean isInput(ItemStack[] stacks) { // TODO: move this into TamModized
		loop: for (AlchemyRecipe recipe : recipes) {
			if (recipe.getInput().length != stacks.length) continue;
			for (ItemStack stack : stacks) {
				boolean flag2 = false;
				for (ItemStack checkStack : recipe.getInput()) {
					if (stack.getItem() == checkStack.getItem()) {
						flag2 = true;
						break;
					}
				}
				if (!flag2) continue loop;
			}
			return true;
		}
		return false;
	}

	public ItemStack getOutput(ItemStack[] stacks) {// TODO: move this into TamModized
		loop: for (AlchemyRecipe recipe : recipes) {
			if (recipe.getInput().length != stacks.length) continue;
			for (ItemStack stack : stacks) {
				boolean flag2 = false;
				for (ItemStack checkStack : recipe.getInput()) {
					if (stack.getItem() == checkStack.getItem()) {
						flag2 = true;
						break;
					}
				}
				if (!flag2) continue loop;
			}
			return recipe.getOutput();
		}
		return ItemStack.EMPTY;
	}

	public AlchemyRecipe getRecipe(ItemStack[] stacks) {// TODO: move this into TamModized
		loop: for (AlchemyRecipe recipe : recipes) {
			if (recipe.getInput().length != stacks.length) continue;
			for (ItemStack stack : stacks) {
				boolean flag2 = false;
				for (ItemStack checkStack : recipe.getInput()) {
					if (!stack.isEmpty() && !checkStack.isEmpty() && stack.getItem() == checkStack.getItem()) {
						flag2 = true;
						break;
					}
				}
				if (!flag2) continue loop;
			}
			return recipe;
		}
		return null;
	}

	protected String getName() {
		return "Alchemy";
	}

	@Override
	protected String getModID() {
		return voidCraft.modid;
	}

	public class AlchemyRecipe extends TamTileEntityRecipeList.TamTERecipe {

		private final int powerAmount;

		public AlchemyRecipe(ItemStack[] input, ItemStack output, int requiredPower) {
			super(input, output);
			powerAmount = requiredPower;
		}

		public int getRequiredPower() {
			return powerAmount;
		}

	}

}
