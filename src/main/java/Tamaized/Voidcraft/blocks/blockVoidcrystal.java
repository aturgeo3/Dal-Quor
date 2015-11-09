package Tamaized.Voidcraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import Tamaized.Voidcraft.common.voidCraft;

public class blockVoidcrystal extends Block {

	public blockVoidcrystal(Material Material) {
		super(Material);
	}
	
	/**
	* Is this block (a) opaque and (B) a full 1m cube? This determines whether or not to render the shared face of two
	* adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	*/
	public boolean isOpaqueCube()
	{
	return false;
	}
	
	public int getRenderBlockPass()
    {
        return 1;
    }
	
	public Item getItemDropped(int par1, Random par2Random, int par3)
    {
		return voidCraft.voidcrystal;
    }
	
	public int quantityDropped(Random random){
		return 9;
	}

}
