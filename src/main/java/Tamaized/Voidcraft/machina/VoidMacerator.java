package Tamaized.Voidcraft.machina;

import java.util.Random;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class VoidMacerator extends BlockContainer {
	
	private Random rand = new Random();
	
	public boolean isActive;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFront_Acitve;
	@SideOnly(Side.CLIENT)
	private IIcon iconFront_Inacitve;
	@SideOnly(Side.CLIENT)
	private IIcon blockSide;
	@SideOnly(Side.CLIENT)
	private IIcon blockTop;
	
	public VoidMacerator(){
		super(Material.rock);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		blockSide = iconRegister.registerIcon("voidCraft:voidgrind_side");
		blockTop = iconRegister.registerIcon("voidCraft:voidgrind_top");
		iconFront_Acitve =  iconRegister.registerIcon("voidCraft:voidgrind_front_on");
		iconFront_Inacitve =  iconRegister.registerIcon("voidCraft:voidgrind_front_off");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata){
		return metadata == 0 && side == 3 ? (isActive ? iconFront_Acitve : iconFront_Inacitve) : (side == 1 ? this.blockTop : (side == 0 ? this.blockTop : side != metadata ? this.blockSide : (isActive ? iconFront_Acitve : iconFront_Inacitve))); 
	}
	
	public void onBlockAdded(World world, int x, int y, int z){
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random){
		if(this.isActive){
			int dir = world.getBlockMetadata(x, y, z);
			
			float x1 = (float) x + 0.5F;
			float y1 = (float) y + random.nextFloat(); 
			float z1 = (float) z + 0.5F;
			
			float f = 0.52F;
			float f1 = random.nextFloat() * 0.6F - 0.3F;
			
			if(dir == 4){
				world.spawnParticle("portal", (double) (x1 - f), (double) (y1), (double) (z1 + f1), -1D, 0D, 0D);
				world.spawnParticle("portal", (double) (x1 - f), (double) (y1), (double) (z1 + f1), -1D, 0D, 0D);
				world.spawnParticle("portal", (double) (x1 - f), (double) (y1), (double) (z1 + f1), -1D, 0D, 0D);
			}else if(dir == 5){
				world.spawnParticle("portal", (double) (x1 + f), (double) (y1), (double) (z1 + f1), 1D, 0D, 0D);
				world.spawnParticle("portal", (double) (x1 + f), (double) (y1), (double) (z1 + f1), 1D, 0D, 0D);
				world.spawnParticle("portal", (double) (x1 + f), (double) (y1), (double) (z1 + f1), 1D, 0D, 0D);
			}else if(dir == 2){
				world.spawnParticle("portal", (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, -1D);
				world.spawnParticle("portal", (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, -1D);
				world.spawnParticle("portal", (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, -1D);
			}else if(dir == 3){
				world.spawnParticle("portal", (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 1D);
				world.spawnParticle("portal", (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 1D);
				world.spawnParticle("portal", (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 1D);
			}
		}
	}
	
	private void setDefaultDirection(World world, int x, int y, int z){
		if(!world.isRemote){
			Block l = world.getBlock(x, y, z - 1);
			Block il = world.getBlock(x, y, z + 1);
			Block jl = world.getBlock(x - 1, y, z);
			Block kl = world.getBlock(x + 1, y, z);
		
			byte b0 = 3;
			
			/*if(Blocks.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[il]){
				b0 = 3;
			}
			
			if(Block.opaqueCubeLookup[il] && !Block.opaqueCubeLookup[l]){
				b0 = 2;
			}
			
			if(Block.opaqueCubeLookup[kl] && !Block.opaqueCubeLookup[jl]){
				b0 = 5;
			}
			
			if(Block.opaqueCubeLookup[jl] && !Block.opaqueCubeLookup[kl]){
				b0 = 4;
			}
		*/
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		if(!world.isRemote){
			FMLNetworkHandler.openGui(player, voidCraft.instance, voidCraft.guiIdMacerator, world, x, y, z);
		}
		return true;
	}
		
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityVoidMacerator();
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
			((TileEntityVoidMacerator) world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
		}
	}
	
	public boolean hasComparatorInputOverride(){
		return true;
	}
	
	public int getComparatorInputOverride(World world, int x, int y, int z, int i){
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
	}
	
	public Block idPicked(World world, int x, int y, int z){
		return voidCraft.voidMacerator;
	}
	
	public void breakBlock(World world, int x, int y, int z, Block oldBlockID, int oldMetadata){
			TileEntityVoidMacerator tileentity = (TileEntityVoidMacerator) world.getTileEntity(x, y, z);
			
			if(tileentity != null){
				for(int i = 0; i < tileentity.getSizeInventory(); i++){
					ItemStack itemstack = tileentity.getStackInSlot(i);
					
					if(itemstack != null){
						float f = this.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
						
						while(itemstack.stackSize > 0){
							int j = this.rand.nextInt(21);
							
							if(j > itemstack.stackSize){
								j = itemstack.stackSize;
							}
							
							itemstack.stackSize-=j;
							
							EntityItem item = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem()));
							
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
				}
				
				//world.func_96440_m(x, y, z, oldBlockID);
			}
		
		
		super.breakBlock(world, x, y, z, oldBlockID, oldMetadata);
	}

	
	
	
	
	}