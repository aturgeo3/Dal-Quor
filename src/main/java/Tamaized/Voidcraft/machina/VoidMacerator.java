package Tamaized.Voidcraft.machina;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.blocks.BasicVoidBlockContainer;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;

public class VoidMacerator extends BasicVoidBlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool ACTIVE = PropertyBool.create("active");
	
	private Random rand = new Random();
	
	public VoidMacerator(String string){
		super(Material.rock, string);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, Boolean.valueOf(false)));
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state){
		this.setDefaultFacing(world, pos, state);
	}
	
	public boolean getIsActive(IBlockState state){
		return state.getValue(ACTIVE);
	}
	
	@Override
	protected BlockState createBlockState(){
        return new BlockState(this, new IProperty[]{FACING, ACTIVE});
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta){
		int val = meta > 5 ? meta-6 : meta;
		EnumFacing enumfacing = EnumFacing.getFront(val);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(ACTIVE, meta > 5);
    }
	
	@SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state){
        return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH).withProperty(ACTIVE, false);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
	@Override
    public int getMetaFromState(IBlockState state){
		int meta = ((EnumFacing)state.getValue(FACING)).getIndex();
		if(state.getValue(ACTIVE)) meta = meta + 6;
		return meta;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand){
		if(state.getValue(ACTIVE)){
			
			float x1 = (float) pos.getX() + 0.5F;
			float y1 = (float) pos.getY() + rand.nextFloat(); 
			float z1 = (float) pos.getZ() + 0.5F;
			
			float f = 0.52F;
			float f1 = rand.nextFloat() * 0.6F - 0.3F;
			
			switch(state.getValue(FACING)){
				case NORTH:
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, -1D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, -1D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, -1D);
					break;
				case SOUTH:
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 1D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 1D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 1D);
					break;
				case EAST:
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f), (double) (y1), (double) (z1 + f1), 1D, 0D, 0D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f), (double) (y1), (double) (z1 + f1), 1D, 0D, 0D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f), (double) (y1), (double) (z1 + f1), 1D, 0D, 0D);
					break;
				case WEST:
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 - f), (double) (y1), (double) (z1 + f1), -1D, 0D, 0D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 - f), (double) (y1), (double) (z1 + f1), -1D, 0D, 0D);
					world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 - f), (double) (y1), (double) (z1 + f1), -1D, 0D, 0D);
					break;
				default:
					break;
			}
		}
	}
	
	private void setDefaultFacing(World world, BlockPos pos, IBlockState state){
		if (!world.isRemote)
        {
            Block block = world.getBlockState(pos.north()).getBlock();
            Block block1 = world.getBlockState(pos.south()).getBlock();
            Block block2 = world.getBlockState(pos.west()).getBlock();
            Block block3 = world.getBlockState(pos.east()).getBlock();
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            world.setBlockState(pos, state.withProperty(FACING, enumfacing).withProperty(ACTIVE, state.getValue(ACTIVE)), 2);
        }
	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos){
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        TileEntityVoidMacerator te = (TileEntityVoidMacerator) tileentity;

        if (active)
        {
            worldIn.setBlockState(pos, voidCraft.blocks.voidMacerator.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(ACTIVE, active), 3);
            worldIn.setBlockState(pos, voidCraft.blocks.voidMacerator.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(ACTIVE, active), 3);
        }
        else
        {
            worldIn.setBlockState(pos, voidCraft.blocks.voidMacerator.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(ACTIVE, active), 3);
            worldIn.setBlockState(pos, voidCraft.blocks.voidMacerator.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(ACTIVE, active), 3);
        }


        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ){
		if(!world.isRemote){
			FMLNetworkHandler.openGui(player, voidCraft.instance, voidCraft.guiIdMacerator, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public int getRenderType(){
        return 3;
    }
		
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityVoidMacerator();
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
	
	public boolean hasComparatorInputOverride(){
		return true;
	}
	
	@Override
	public int getComparatorInputOverride(World world, BlockPos pos){
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(pos));
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player){
		return new ItemStack(voidCraft.blocks.voidMacerator);
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state){
			TileEntityVoidMacerator tileentity = (TileEntityVoidMacerator) world.getTileEntity(pos);
			
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
							
							EntityItem item = new EntityItem(world, (double)((float)pos.getX() + f), (double)((float)pos.getY() + f1), (double)((float)pos.getZ() + f2), new ItemStack(itemstack.getItem()));
							
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
		
		
		super.breakBlock(world, pos, state);
	}	
}