package Tamaized.Voidcraft.machina.addons;

import java.util.ArrayList;

import Tamaized.TamModized.tileentity.TamTileEntityRecipeList;
import Tamaized.Voidcraft.voidCraft;
import net.minecraft.item.ItemStack;

public class TERecipeInfuser extends TamTileEntityRecipeList<TERecipeInfuser.InfuserRecipe> {

	@Override
	public boolean registerRecipe(InfuserRecipe recipe) {
		if (recipe instanceof InfuserRecipe) {
			return super.registerRecipe(recipe);
		} else {
			voidCraft.logger.error("Tried to register a non-Infuser recipe in the Infuser Recipe List");
			return false;
		}
	}

	public ArrayList<InfuserRecipe> getList() { // TODO: move this into TamModized
		return recipes;
	}

	public boolean isInput(ItemStack[] stacks) { // TODO: move this into TamModized
		loop: for (InfuserRecipe recipe : recipes) {
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
		loop: for (InfuserRecipe recipe : recipes) {
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

	public InfuserRecipe getRecipe(ItemStack[] stacks) {// TODO: move this into TamModized
		loop: for (InfuserRecipe recipe : recipes) {
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
		return "Infuser";
	}

	@Override
	protected String getModID() {
		return voidCraft.modid;
	}

	public class InfuserRecipe extends TamTileEntityRecipeList.TamTERecipe {

		private final int fluidAmount;

		public InfuserRecipe(ItemStack[] input, ItemStack output, int requiredFluid) {
			super(input, output);
			fluidAmount = requiredFluid;
		}

		public int getRequiredFluid() {
			return fluidAmount;
		}

	}

}
