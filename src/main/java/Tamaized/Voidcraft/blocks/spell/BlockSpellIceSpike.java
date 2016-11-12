package Tamaized.Voidcraft.blocks.spell;

import Tamaized.Voidcraft.blocks.spell.tileentity.TileEntitySpell;
import Tamaized.Voidcraft.blocks.spell.tileentity.TileEntitySpellIceSpike;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSpellIceSpike extends BlockSpell {

	public BlockSpellIceSpike(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness);
	}

	@Override
	protected TileEntitySpell createNewTileEntitySpell(World world, int meta) {
		return new TileEntitySpellIceSpike();
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

}
