package Tamaized.Voidcraft.Addons.JEI.infuser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.common.voidCraft;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InfuserRecipeJEI extends BlankRecipeWrapper {
	
	private final Item input;
	private final ItemStack output;

	public InfuserRecipeJEI(Item i, ItemStack o) {
		input = i;
		output = o;
	}
	
	@Override
	@Nonnull
	public List getInputs() {
		ArrayList<Collection> ret = new ArrayList<Collection>();
		ret.add(Collections.singletonList(new ItemStack(input)));
		ret.add(Collections.singletonList(voidCraft.fluids.getBucket()));
		return ret;
	}
	
	@Override
	@Nonnull
	public List<ItemStack> getOutputs(){
		return Collections.singletonList(output);
	}
	
}
