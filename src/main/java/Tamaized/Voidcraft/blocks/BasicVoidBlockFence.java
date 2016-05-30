package Tamaized.Voidcraft.blocks;

import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.registry.IBasicVoid;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BasicVoidBlockFence extends BlockFence implements IBasicVoid{
	
	private final String name;

	public BasicVoidBlockFence(Material materialIn, MapColor mapColor, String n) {
		super(materialIn, mapColor);
		name = n;
		setUnlocalizedName(name);
		GameRegistry.registerBlock(this, "blocks/"+n);
	}
	
	@Override
	public String getName() {
		return name;
	}

}
