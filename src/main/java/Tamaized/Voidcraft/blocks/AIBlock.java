package Tamaized.Voidcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;

public class AIBlock extends BasicVoidBlockContainer {
	
	private boolean createTE = false;

	public AIBlock(String string) {
		super(Material.cloth, string);
	}
	
	public Block allowTileEntityCreation(boolean b){
		createTE = b;
		return this;
	}
	
	public TileEntity getMyTileEntity(World world, BlockPos pos){
		Block b = world.getBlockState(pos.add(0, -1, 0)).getBlock();
		if(b instanceof AIBlock){
			return ((AIBlock) b).getMyTileEntity(world, pos.add(0, -1, 0));
		}
		
		return world.getTileEntity(pos);
	}
	
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return createTE ? new TileEntityAIBlock() : null;
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
		world.setBlockToAir(pos);
        return this.getStateFromMeta(meta);
    }
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state){
		TileEntity te = world.getTileEntity(pos);
		if(te instanceof TileEntityAIBlock){
			TileEntityAIBlock teAI = (TileEntityAIBlock) te;
		}
		
		super.breakBlock(world, pos, state);
	}

}
