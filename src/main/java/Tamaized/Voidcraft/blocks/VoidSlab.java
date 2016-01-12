package Tamaized.Voidcraft.blocks;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.ItemStack;

public class VoidSlab extends BasicVoidBlockSlab{

	public VoidSlab(Material mat, String string) {
		super(mat, string);
	}

	@Override
	public boolean isDouble() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IProperty getVariantProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getVariant(ItemStack stack) {
		// TODO Auto-generated method stub
		return null;
	}

}
