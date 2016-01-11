package Tamaized.Voidcraft.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockNoBreak extends BlockContainer{

	public BlockNoBreak(Material arg0) {
		super(arg0);
	}
	
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_){
        return new TileEntityNoBreak();
    }
    
    public int getRenderType(){
        return -1;
    }
    
    public boolean isOpaqueCube(){
        return false;
    }
    
    public boolean renderAsNormalBlock(){
        return false;
    }

}
