package Tamaized.Voidcraft.blocks;

import static net.minecraftforge.common.util.ForgeDirection.UP;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.IBlockAccess;

public class blockFakeBedrock extends Block{

	public blockFakeBedrock(Material par2Material) {
		super(par2Material);
	}
	
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

}
