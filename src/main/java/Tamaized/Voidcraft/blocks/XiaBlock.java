package Tamaized.Voidcraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityXiaCastle;

public class XiaBlock extends BasicVoidBlockContainer {

	public XiaBlock(String string) {
		super(Material.CLOTH, string);
	}
	
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityXiaCastle();
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return this.getStateFromMeta(meta);
    }

}
