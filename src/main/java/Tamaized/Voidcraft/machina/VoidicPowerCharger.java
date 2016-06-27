package Tamaized.Voidcraft.machina;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.blocks.BasicVoidBlockContainer;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCharger;

public class VoidicPowerCharger extends BasicVoidBlockContainer {

	public VoidicPowerCharger(Material materialIn, String n) {
		super(materialIn, n, true);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityVoidicCharger();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		if(!worldIn.isRemote) FMLNetworkHandler.openGui(playerIn, voidCraft.instance, GuiHandler.guiIdCharger, worldIn, pos.getX(), pos.getY(), pos.getZ());	
		return true;
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
