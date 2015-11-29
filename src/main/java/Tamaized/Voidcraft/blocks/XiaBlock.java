package Tamaized.Voidcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityXiaCastle;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class XiaBlock extends BlockContainer {
	
	@SideOnly(Side.CLIENT)
	private IIcon icon;

	public XiaBlock() {
		super(Material.cloth);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		icon = iconRegister.registerIcon("wool_colored_purple");
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int meta)  {
		return icon;
    }
	
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityXiaCastle();
	}
	
	public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_){
		p_149660_1_.setBlockToAir(p_149660_2_, p_149660_3_, p_149660_4_);
        return p_149660_9_;
    }

}
