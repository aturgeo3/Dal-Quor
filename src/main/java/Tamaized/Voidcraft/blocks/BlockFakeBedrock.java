package Tamaized.Voidcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.IBlockAccess;

public class BlockFakeBedrock extends BasicVoidBlock{

	public BlockFakeBedrock(Material par2Material, String s) {
		super(par2Material, s);
	}
	
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

}
