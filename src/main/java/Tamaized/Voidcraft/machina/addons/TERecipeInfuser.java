package Tamaized.Voidcraft.machina.addons;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import Tamaized.TamModized.tileentity.TamTileEntityRecipeList;
import Tamaized.TamModized.tileentity.TamTileEntityRecipeList.TamTERecipe;
import Tamaized.Voidcraft.common.voidCraft;

public class TERecipeInfuser extends TamTileEntityRecipeList {

	@Override
	public boolean registerRecipe(ItemStack stack, TamTERecipe recipe) {
		if (recipe instanceof InfuserRecipe) {
			return super.registerRecipe(stack, recipe);
		} else {
			voidCraft.logger.error("Tried to register a non-Infuser recipe in the Infuser Recipe List");
			return false;
		}
	}

	@Override
	public boolean registerRecipe(String item, TamTERecipe recipe) {
		if (recipe instanceof InfuserRecipe) {
			return super.registerRecipe(item, recipe);
		} else {
			voidCraft.logger.error("Tried to register a non-Infuser recipe in the Infuser Recipe List");
			return false;
		}
	}

	@Override
	public InfuserRecipe getOutput(Item item) {
		if (!isInput(item)) return null;
		else return (InfuserRecipe) super.getOutput(item);
	}

	@Override
	public InfuserRecipe getOutput(ItemStack stack){
		return stack == null ? null : getOutput(stack.getItem());
	}

	@Override
	protected String getName() {
		return "Infuser";
	}

	@Override
	protected String getModID() {
		return voidCraft.modid;
	}

	public class InfuserRecipe extends TamTERecipe {

		private final int fluidAmount;

		public InfuserRecipe(ItemStack output, int requiredFluid) {
			super(output);
			fluidAmount = requiredFluid;
		}

		public int getRequiredFluid() {
			return fluidAmount;
		}

	}

}
