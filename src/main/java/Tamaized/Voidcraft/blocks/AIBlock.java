package Tamaized.Voidcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;

public class AIBlock extends BlockContainer {
	
	@SideOnly(Side.CLIENT)
	private IIcon icon_Green;
	@SideOnly(Side.CLIENT)
	private IIcon icon_Yellow;
	@SideOnly(Side.CLIENT)
	private IIcon icon_Red;
	
	private boolean createTE = false;

	public AIBlock() {
		super(Material.cloth);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		icon_Green = iconRegister.registerIcon("wool_colored_lime");
		icon_Yellow = iconRegister.registerIcon("wool_colored_yellow");
		icon_Red = iconRegister.registerIcon("wool_colored_red");
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int meta)  {
		return meta == 2 ? icon_Red : meta == 1 ? icon_Yellow : icon_Green;
    }
	
	public Block allowTileEntityCreation(boolean b){
		createTE = b;
		return this;
	}
	
	public TileEntity getMyTileEntity(World world, int x, int y, int z){
		Block b = world.getBlock(x, y-1, z);
		if(b instanceof AIBlock){
			return ((AIBlock) b).getMyTileEntity(world, x, y-1, z);
		}
		
		return world.getTileEntity(x, y, z);
	}
	
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return createTE ? new TileEntityAIBlock() : null;
	}
	
	public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_){
		p_149660_1_.setBlockToAir(p_149660_2_, p_149660_3_, p_149660_4_);
        return p_149660_9_;
    }
	
	public void breakBlock(World world, int x, int y, int z, Block oldBlockID, int oldMetadata){
		TileEntity te = world.getTileEntity(x, y, z);
		if(te instanceof TileEntityAIBlock){
			TileEntityAIBlock teAI = (TileEntityAIBlock) te;
		}
		
		super.breakBlock(world, x, y, z, oldBlockID, oldMetadata);
	}

}
