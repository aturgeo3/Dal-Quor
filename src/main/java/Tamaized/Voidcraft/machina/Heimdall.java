package Tamaized.Voidcraft.machina;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.blocks.BasicVoidBlockContainer;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;

public class Heimdall extends BasicVoidBlockContainer{

	public Heimdall(Material p_i45386_1_, String string) {
		super(p_i45386_1_, string);
		//this.setBlockBounds(0.125F, 0F, 0.125F, 0.875F, 0.6875F, .875F);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		if(!worldIn.isRemote){
			FMLNetworkHandler.openGui(playerIn, voidCraft.instance, GuiHandler.guiIdHeimdall, worldIn, pos.getX(), pos.getY(), pos.getZ());	
		}
		return true;
	}
		
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityHeimdall();
	}
	
	/**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube(){
    	return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock(){
    	return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType(){
    	return -1;
    }

}
