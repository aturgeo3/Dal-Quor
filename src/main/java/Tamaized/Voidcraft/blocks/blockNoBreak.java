package Tamaized.Voidcraft.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class blockNoBreak extends BlockContainer{

	public blockNoBreak(Material arg0) {
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
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        super.registerBlockIcons(p_149651_1_);
    }

}
