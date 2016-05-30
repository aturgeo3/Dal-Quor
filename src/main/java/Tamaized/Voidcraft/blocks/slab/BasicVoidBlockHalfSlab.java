package Tamaized.Voidcraft.blocks.slab;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.ItemStack;

public class BasicVoidBlockHalfSlab extends BasicVoidBlockSlab {

	public BasicVoidBlockHalfSlab(Material materialIn, String n) {
		super(materialIn, n);
	}

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		// TODO Auto-generated method stub
		return null;
	}

}
