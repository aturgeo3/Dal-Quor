package Tamaized.Voidcraft.blocks.slab;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BasicVoidBlockSlabDouble extends BasicVoidBlockSlab.Double{
	
	private final Item item;

	public BasicVoidBlockSlabDouble(Material materialIn, String n, Item item) {
		super(materialIn, n);
		this.item = item;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return item;
	}

}
