package Tamaized.Voidcraft.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class BlockNoBreak extends BasicVoidBlockContainer{

	public BlockNoBreak(Material arg0, String string) {
		super(arg0, string, true);
	}

	@Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_){
        return new TileEntityNoBreak();
    }
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.INVISIBLE;
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
    public boolean isFullCube(IBlockState state) {
    	return false;
    }
    
    @Override
    public boolean isVisuallyOpaque() {
    	return false;
    }
    
}
