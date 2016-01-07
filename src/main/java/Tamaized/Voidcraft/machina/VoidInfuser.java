package Tamaized.Voidcraft.machina;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;

public class VoidInfuser extends BlockContainer{
	private Random rand = new Random();
	
	private IIcon daIcon;

public VoidInfuser()
{
    super(Material.iron);
}

/**
 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
 */
public boolean isOpaqueCube()
{
    return false;
}

/**
 * The type of render function that is called for this block
 *
public int getRenderType()
{
    return 25;
}


/**
 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
 */
public boolean renderAsNormalBlock()
{
    return false;
}

/**
 * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
 * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
 */
public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
{
    this.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 0.875F, 0.5625F);
    super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    this.setBlockBoundsForItemRender();
    super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
}

/**
 * Sets the block's bounds for rendering it as an item
 */
public void setBlockBoundsForItemRender()
{
    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
}

/**
 * Called upon block activation (right click on the block.)
 */
public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
{
    if (par1World.isRemote)
    {
        return true;
    }
    else
    {
    	FMLNetworkHandler.openGui(par5EntityPlayer, voidCraft.instance, voidCraft.guiIdInfuser, par1World, par2, par3, par4);
		
        return true;
    }
}

/**
 * Called when the block is placed in the world.
 */
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
		((TileEntityVoidInfuser) world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
	}
}

/**
 * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
 * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
 * metadata
 */
public void breakBlock(World world, int x, int y, int z, Block oldBlockID, int oldMetadata){
	
	TileEntityVoidInfuser tileentity = (TileEntityVoidInfuser) world.getTileEntity(x, y, z);
	
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
		}
		
		//world.func_96440_m(x, y, z, oldBlockID);
	}


super.breakBlock(world, x, y, z, oldBlockID, oldMetadata);
}



@SideOnly(Side.CLIENT)

/**
 * A randomly called display update to be able to add particles or other items for display
 */
public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
{
    double d0 = (double)((float)par2 + 0.4F + par5Random.nextFloat() * 0.2F);
    double d1 = (double)((float)par3 + 0.0F + par5Random.nextFloat() * 0.3F);
    double d2 = (double)((float)par4 + 0.4F + par5Random.nextFloat() * 0.2F);
    par1World.spawnParticle("portal", d0, d1, d2, 0.0D, 0.5D, 0.0D);
    par1World.spawnParticle("portal", d0, d1, d2, 1.0D, 0.0D, 0.0D);
    par1World.spawnParticle("portal", d0, d1, d2, -1.0D, 0.0D, 0.0D);
    par1World.spawnParticle("portal", d0, d1, d2, 0.0D, 0.0D, 1.0D);
    par1World.spawnParticle("portal", d0, d1, d2, 0.0D, 0.0D, -1.0D);
    
     d0 = (double)((float)par2 + 0.4F + par5Random.nextFloat() * 0.2F);
     d1 = (double)((float)par3 + 0.0F + par5Random.nextFloat() * 0.3F);
     d2 = (double)((float)par4 + 0.4F + par5Random.nextFloat() * 0.2F);
    par1World.spawnParticle("portal", d0, d1, d2, 0.0D, 0.5D, 0.0D);
    par1World.spawnParticle("portal", d0, d1, d2, 1.0D, 0.0D, 0.0D);
    par1World.spawnParticle("portal", d0, d1, d2, -1.0D, 0.0D, 0.0D);
    par1World.spawnParticle("portal", d0, d1, d2, 0.0D, 0.0D, 1.0D);
    par1World.spawnParticle("portal", d0, d1, d2, 0.0D, 0.0D, -1.0D);
}



/**
 * If this returns true, then comparators facing away from this block will use the value from
 * getComparatorInputOverride instead of the actual redstone signal strength.
 */
public boolean hasComparatorInputOverride()
{
    return true;
}

/**
 * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
 * strength when this block inputs to a comparator.
 */
public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
{
    return Container.calcRedstoneFromInventory((IInventory)par1World.getTileEntity(par2, par3, par4));
}

@SideOnly(Side.CLIENT)

/**
 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
 * is the only chance you get to register icons.
 */
public void registerBlockIcons(IIconRegister par1IconRegister)
{
    //super.registerBlockIcons(par1IconRegister);
    this.daIcon = par1IconRegister.registerIcon("voidCraft:voidInfuser");
}

@SideOnly(Side.CLIENT)
public IIcon getIcon(int side, int metadata){
	return this.daIcon; 
}


@Override
public TileEntity createNewTileEntity(World arg0, int arg1) {
	TileEntityVoidInfuser te = new TileEntityVoidInfuser();
	return te;
}
}
