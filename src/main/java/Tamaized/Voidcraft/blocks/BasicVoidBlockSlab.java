package Tamaized.Voidcraft.blocks;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class BasicVoidBlockSlab extends BlockSlab implements IBasicVoidBlock{
	
	private final String name;

	public BasicVoidBlockSlab(Material materialIn, String n) {
		super(materialIn);
		name = n;
		setUnlocalizedName(name);
		GameRegistry.registerBlock(this, n);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getUnlocalizedName(int meta) {
		return super.getUnlocalizedName();
	}

}
