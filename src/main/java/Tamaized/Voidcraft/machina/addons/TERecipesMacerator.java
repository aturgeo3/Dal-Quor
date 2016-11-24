package Tamaized.Voidcraft.machina.addons;

import java.util.ArrayList;

import Tamaized.TamModized.tileentity.TamTileEntityRecipeList;
import Tamaized.Voidcraft.voidCraft;
import net.minecraft.item.ItemStack;

public class TERecipesMacerator extends TamTileEntityRecipeList<TERecipesMacerator.MaceratorRecipe> {

	@Override
	public boolean registerRecipe(MaceratorRecipe recipe) {
		if (recipe instanceof MaceratorRecipe) {
			return super.registerRecipe(recipe);
		} else {
			voidCraft.logger.error("Tried to register a non-Macerator recipe in the Macerator Recipe List");
			return false;
		}
	}

	public ArrayList<MaceratorRecipe> getList() { // TODO: move this into TamModized
		return recipes;
	}

	public boolean isInput(ItemStack[] stacks) { // TODO: move this into TamModized
		loop: for (MaceratorRecipe recipe : recipes) {
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
		loop: for (MaceratorRecipe recipe : recipes) {
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

	public MaceratorRecipe getRecipe(ItemStack[] stacks) {// TODO: move this into TamModized
		loop: for (MaceratorRecipe recipe : recipes) {
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
			return recipe;
		}
		return null;
	}

	@Override
	protected String getName() {
		return "Macerator";
	}

	@Override
	protected String getModID() {
		return voidCraft.modid;
	}

	public class MaceratorRecipe extends TamTileEntityRecipeList.TamTERecipe {

		private final int powerAmount;

		public MaceratorRecipe(ItemStack[] input, ItemStack output, int requiredPower) {
			super(input, output);
			powerAmount = requiredPower;
		}

		public int getRequiredPower() {
			return powerAmount;
		}

	}

}
