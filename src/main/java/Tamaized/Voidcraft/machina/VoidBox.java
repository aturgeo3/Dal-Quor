package Tamaized.Voidcraft.machina;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.blocks.BasicVoidBlockContainer;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;

public class VoidBox extends BasicVoidBlockContainer {
	
	private Random rand = new Random();

	public VoidBox(String string) {
		super(Material.IRON, string);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		if(!worldIn.isRemote){
			FMLNetworkHandler.openGui(playerIn, voidCraft.instance, GuiHandler.guiIdBox, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	private void setDefaultDirection(World world, BlockPos pos){
		if(!world.isRemote){
			Block l = world.getBlockState(pos.add(0, 0, -1)).getBlock();
			Block il = world.getBlockState(pos.add(0, 0, 1)).getBlock();
			Block jl = world.getBlockState(pos.add(-1, 0, 0)).getBlock();
			Block kl = world.getBlockState(pos.add(1, 0, 0)).getBlock();
		
			byte b0 = 3;
			
			world.setBlockState(pos, this.getStateFromMeta(b0), 2);
		}
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
		int l = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		if(l == 0){
			world.setBlockState(pos, this.getStateFromMeta(2), 2);
		}
		
		if(l == 1){
			world.setBlockState(pos, this.getStateFromMeta(5), 2);
		}
		
		if(l == 2){
			world.setBlockState(pos, this.getStateFromMeta(3), 2);
		}
		
		if(l == 3){
			world.setBlockState(pos, this.getStateFromMeta(4), 2);
		}
		
		if(stack.hasDisplayName()){
			((TileEntityVoidMacerator) world.getTileEntity(pos)).setGuiDisplayName(stack.getDisplayName());
		}
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state){
		if(!world.isRemote){
			TileEntityVoidBox tileentity = (TileEntityVoidBox) world.getTileEntity(pos);
			
			if(tileentity != null){
				tileentity.StopTheSound();
				
				for(int i = 0; i < tileentity.slots.length; i++){
					ItemStack itemstack = tileentity.getStackInSlot(i);
					
					if(itemstack != null){
						float f = this.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
							
						EntityItem item = new EntityItem(world, (double)((float)pos.getX() + f), (double)((float)pos.getY() + f1), (double)((float)pos.getZ() + f2), itemstack);
							
						if(itemstack.hasTagCompound()){
							item.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}
							
						float f3 = 0.05F;
						item.motionX = (double)((float)this.rand.nextGaussian() * f3);
						item.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
						item.motionZ = (double)((float)this.rand.nextGaussian() * f3);
						world.spawnEntityInWorld(item);
					}
				}
			}else{
				System.out.println("Issue");
			}
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityVoidBox();
	}
}
