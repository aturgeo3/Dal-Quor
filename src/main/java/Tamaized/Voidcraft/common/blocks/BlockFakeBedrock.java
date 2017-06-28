package tamaized.voidcraft.common.blocks;

import Tamaized.TamModized.blocks.TamBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockFakeBedrock extends TamBlock {

	public BlockFakeBedrock(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness, SoundType.STONE);
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
		return true;
	}

}
