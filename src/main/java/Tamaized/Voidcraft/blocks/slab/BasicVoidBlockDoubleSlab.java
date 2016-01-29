package Tamaized.Voidcraft.blocks.slab;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.ItemStack;

public class BasicVoidBlockDoubleSlab extends BasicVoidBlockSlab {

	public BasicVoidBlockDoubleSlab(Material materialIn, String n) {
		super(materialIn, n);
	}

	@Override
	public boolean isDouble() {
		return true;
	}

}
