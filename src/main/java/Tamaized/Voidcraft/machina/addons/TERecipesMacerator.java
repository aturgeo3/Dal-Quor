package Tamaized.Voidcraft.machina.addons;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import Tamaized.TamModized.tileentity.TamTileEntityRecipeList;
import Tamaized.TamModized.tileentity.TamTileEntityRecipeList.TamTERecipe;
import Tamaized.Voidcraft.common.voidCraft;

public class TERecipesMacerator extends TamTileEntityRecipeList {
	
	@Override
	public boolean registerRecipe(ItemStack stack, TamTERecipe recipe){
		if(recipe instanceof MaceratorRecipe){
			return super.registerRecipe(stack, recipe);
		}else{
			voidCraft.logger.error("Tried to register a non-Macerator recipe in the Macerator Recipe List");
			return false;
		}
	}
	
	@Override
	public boolean registerRecipe(String item, TamTERecipe recipe) {
		if(recipe instanceof MaceratorRecipe){
			return super.registerRecipe(item, recipe);
		}else{
			voidCraft.logger.error("Tried to register a non-Macerator recipe in the Macerator Recipe List");
			return false;
		}
	}
	
	@Override
	public MaceratorRecipe getOutput(Item item){
		if(!isInput(item)) return null;
		else return (MaceratorRecipe) super.getOutput(item);
	}

	@Override
	public MaceratorRecipe getOutput(ItemStack stack){
		return stack == null ? null : getOutput(stack.getItem());
	}

	@Override
	protected String getName() {
		return "Macerator";
	}

	@Override
	protected String getModID() {
		return voidCraft.modid;
	}

	public class MaceratorRecipe extends TamTERecipe {

		private final int powerAmount;

		public MaceratorRecipe(ItemStack output, int requiredPower) {
			super(output);
			powerAmount = requiredPower;
		}

		public int getRequiredPower() {
			return powerAmount;
		}

	}

}
