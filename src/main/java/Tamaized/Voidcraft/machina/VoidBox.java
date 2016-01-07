package Tamaized.Voidcraft.machina;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;

public class VoidBox extends BlockContainer {
	
	private Random rand = new Random();
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	@SideOnly(Side.CLIENT)
	private IIcon blockSide;
	@SideOnly(Side.CLIENT)
	private IIcon blockTop;
	
	

	public VoidBox() {
		super(Material.iron);
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		if(!world.isRemote){
			FMLNetworkHandler.openGui(player, voidCraft.instance, voidCraft.guiIdBox, world, x, y, z);
		}
		return true;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block b){
		TileEntity te = world.getTileEntity(x, y, z);
		TileEntityVoidBox tileEntity;
		if(te instanceof TileEntityVoidBox) tileEntity = (TileEntityVoidBox) te;
		else return;
		
        if (!world.isRemote){
        	if(tileEntity != null){
        		if(world.isBlockIndirectlyGettingPowered(x, y, z)){
        			tileEntity.isPowered = true;
        		}else{
        			tileEntity.isPowered = false;
        		}
        	}
        }
    }

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockSide = iconRegister.registerIcon("voidCraft:voidBox_side");
		this.blockTop = iconRegister.registerIcon("voidCraft:voidBox_top");
		this.iconFront = iconRegister.registerIcon("voidCraft:voidBox_side");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata){
		return metadata == 0 && side == 3 ? this.iconFront : (side == 1 ? this.blockTop : (side == 0 ? this.blockTop : side != metadata ? this.blockSide : this.iconFront)); 
	}

	private void setDefaultDirection(World world, int x, int y, int z){
		if(!world.isRemote){
			Block l = world.getBlock(x, y, z - 1);
			Block il = world.getBlock(x, y, z + 1);
			Block jl = world.getBlock(x - 1, y, z);
			Block kl = world.getBlock(x + 1, y, z);
		
			byte b0 = 3;
			
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemstack){
		int l = MathHelper.floor_double((double)(entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		if(l == 0){
			world.setBlockMetadataWithNotify(x,  y,  z,  2, 2);
		}
		
		if(l == 1){
			world.setBlockMetadataWithNotify(x,  y,  z,  5, 2);
		}
		
		if(l == 2){
			world.setBlockMetadataWithNotify(x,  y,  z,  3, 2);
		}
		
		if(l == 3){
			world.setBlockMetadataWithNotify(x,  y,  z,  4, 2);
		}
		
		if(itemstack.hasDisplayName()){
			((TileEntityVoidBox) world.getTileEntity(x+1, y, z+1)).setGuiDisplayName(itemstack.getDisplayName());
		}
	}
	
	public void breakBlock(World world, int x, int y, int z, Block oldBlockID, int oldMetadata){
		if(!world.isRemote){
			TileEntityVoidBox tileentity = (TileEntityVoidBox) world.getTileEntity(x, y, z);
			
			if(tileentity != null){
				tileentity.StopTheSound();
				
				for(int i = 0; i < tileentity.slots.length; i++){
					ItemStack itemstack = tileentity.getStackInSlot(i);
					
					if(itemstack != null){
						float f = this.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
							
						EntityItem item = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), itemstack);
							
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
		super.breakBlock(world, x, y, z, oldBlockID, oldMetadata);
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityVoidBox();
	}
}
