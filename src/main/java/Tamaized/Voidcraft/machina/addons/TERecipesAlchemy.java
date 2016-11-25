package Tamaized.Voidcraft.machina.addons;

import Tamaized.TamModized.tileentity.TamTileEntityRecipeList;
import Tamaized.Voidcraft.voidCraft;
import net.minecraft.item.ItemStack;

public class TERecipesAlchemy extends TamTileEntityRecipeList<TERecipesAlchemy.AlchemyRecipe> {

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
