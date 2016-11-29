package Tamaized.Voidcraft.blocks;

import Tamaized.TamModized.blocks.TamBlockContainer;
import Tamaized.Voidcraft.xiaCastle.logic.TileEntityXiaCastle;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class XiaBlock extends TamBlockContainer {

	public XiaBlock(String string) {
		super(null, Material.CLOTH, string, -1);
		setBlockUnbreakable();
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityXiaCastle();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof TileEntityXiaCastle) ((TileEntityXiaCastle) te).start();
		}
		return true;
	}

}
