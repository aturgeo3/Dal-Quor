package Tamaized.Voidcraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.world.dim.Xia.TeleporterXia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockTeleporterXia extends BlockBreakable {
	
	public static int timer = 0;
	public static boolean rdyTime = false;

	public BlockTeleporterXia() {
		super("VoidCraft:blockTeleporterXia", Material.portal, false);
		this.setTickRandomly(true);
		this.setLightLevel(0.75F);
		}
	
	/**
	* Ticks the block if it's been scheduled
	*/
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random){
		super.updateTick(par1World, par2, par3, par4, par5Random);
	
		if (par1World.provider.isSurfaceWorld()){
			int l;
			for (l = par3; !par1World.doesBlockHaveSolidTopSurface(par1World, par2, l, par4) && l > 0; --l){
				;
			}
			if (l > 0 && !par1World.isBlockNormalCubeDefault(par2, l + 1, par4, false)){
			//	Entity entity = ItemMonsterPlacer.spawnCreature(par1World, 57, (double)par2 + 0.5D, (double)l + 1.1D, (double)par4 + 0.5D);
			//	if (entity != null){
			//		entity.timeUntilPortal = entity.getPortalCooldown();
			//	}
			}
		}
	}
	
	/**
	* Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	* cleared to be reused)
	*/
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4){
		return null;
	}
	
	/**
	* Updates the blocks bounds based on its current state. Args: world, x, y, z
	*/
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4){
		float f;
		float f1;
		if(par1IBlockAccess.getBlock(par2 - 1, par3, par4) != this && par1IBlockAccess.getBlock(par2 + 1, par3, par4) != this){
			f = 0.125F;
			f1 = 0.5F;
			//this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
			this.setBlockBounds(0.0F, 0.3F, 0.0F, 1.0F, 0.6F, 1.0F);
		}else{
			f = 0.5F;
			f1 = 0.125F;
			//this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
			this.setBlockBounds(0.0F, 0.3F, 0.0F, 1.0F, 0.6F, 1.0F);
		}
	}
	
	/**
	* Is this block (a) opaque and (B) a full 1m cube? This determines whether or not to render the shared face of two
	* adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	*/
	public boolean isOpaqueCube(){
		return false;
	}
	
	/**
	* If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	*/
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
	
/*	public void onEntityCollidedWithBlock(World world, int x,int y, int z, Entity entity){
		if(entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP) entity;
			ModLoader.getMinecraftServerInstance();
			MinecraftServer server = MinecraftServer.getServer();
			
			
			if(player.timeUntilPortal > 0){
				player.timeUntilPortal = 10;
			}else if(player.dimension != voidCraft.dimensionIdXia){
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, voidCraft.dimensionIdXia, new TeleporterVoid(server.worldServerForDimension(voidCraft.dimensionIdXia)));
				
			}else{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterVoid(server.worldServerForDimension(0)));
				
				
			}
			
			
			}
		
		
	}
	*/
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity){
		if((par5Entity.ridingEntity == null) && (par5Entity.riddenByEntity == null) && ((par5Entity instanceof EntityPlayerMP))){
			EntityPlayerMP thePlayer = (EntityPlayerMP)par5Entity;
			if (thePlayer.timeUntilPortal > 10){
				thePlayer.timeUntilPortal = 10;
			}else if (thePlayer.dimension != voidCraft.dimensionIdXia){
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, voidCraft.dimensionIdXia, new TeleporterXia(thePlayer.mcServer.worldServerForDimension(voidCraft.dimensionIdXia)));
			}else{
				thePlayer.timeUntilPortal = 10;
				//thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterVoid(thePlayer.mcServer.worldServerForDimension(0)));
			}
		}
	}

	
	public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4)
    {
        byte b0 = 0;
        byte b1 = 0;

        if (par1World.getBlock(par2 - 1, par3, par4) == voidCraft.blockFakeBedrock || par1World.getBlock(par2 + 1, par3, par4) == voidCraft.blockFakeBedrock)
        {
            b0 = 1;
        }

        if (par1World.getBlock(par2, par3, par4 - 1) == voidCraft.blockFakeBedrock || par1World.getBlock(par2, par3, par4 + 1) == voidCraft.blockFakeBedrock)
        {
            b1 = 1;
        }

        if (b0 == b1)
        {
            return false;
        }
        else
        {
            if (par1World.isAirBlock(par2 - b0, par3, par4 - b1))
            {
                par2 -= b0;
                par4 -= b1;
            }

            int l;
            int i1;

            for (l = -1; l <= 2; ++l)
            {
                for (i1 = -1; i1 <= 3; ++i1)
                {
                    boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

                    if (l != -1 && l != 2 || i1 != -1 && i1 != 3)
                    {
                        Block j1 = par1World.getBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);
                        boolean isAirBlock = par1World.isAirBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);

                        if (flag)
                        {
                            if (j1 != voidCraft.blockFakeBedrock)
                            {
                                return false;
                            }
                        }
                        else if (!isAirBlock && j1 != voidCraft.fireVoid)
                        {
                            return false;
                        }
                    }
                }
            }

            for (l = 0; l < 2; ++l)
            {
                for (i1 = 0; i1 < 3; ++i1)
                {
                    par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l, voidCraft.blockTeleporterXia, 0, 2);
                }
            }

            return true;
        }
    }
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        byte b0 = 0;
        byte b1 = 1;

        if (par1World.getBlock(par2 - 1, par3, par4) == this || par1World.getBlock(par2 + 1, par3, par4) == this)
        {
            b0 = 1;
            b1 = 0;
        }

        int i1;

        for (i1 = par3; par1World.getBlock(par2, i1 - 1, par4) == this; --i1)
        {
            ;
        }

        if (par1World.getBlock(par2, i1 - 1, par4) != voidCraft.blockFakeBedrock)
        {
            par1World.setBlockToAir(par2, par3, par4);
        }
        else
        {
            int j1;

            for (j1 = 1; j1 < 4 && par1World.getBlock(par2, i1 + j1, par4) == this; ++j1)
            {
                ;
            }

            if (j1 == 3 && par1World.getBlock(par2, i1 + j1, par4) == voidCraft.blockFakeBedrock)
            {
                boolean flag = par1World.getBlock(par2 - 1, par3, par4) == this || par1World.getBlock(par2 + 1, par3, par4) == this;
                boolean flag1 = par1World.getBlock(par2, par3, par4 - 1) == this || par1World.getBlock(par2, par3, par4 + 1) == this;

                if (flag && flag1)
                {
                    par1World.setBlockToAir(par2, par3, par4);
                }
                else
                {
                    if ((par1World.getBlock(par2 + b0, par3, par4 + b1) != voidCraft.blockFakeBedrock || par1World.getBlock(par2 - b0, par3, par4 - b1) != this) && (par1World.getBlock(par2 - b0, par3, par4 - b1) != voidCraft.blockFakeBedrock || par1World.getBlock(par2 + b0, par3, par4 + b1) != this))
                    {
                        par1World.setBlockToAir(par2, par3, par4);
                    }
                }
            }
            else
            {
                par1World.setBlockToAir(par2, par3, par4);
            }
        }
    }
	@SideOnly(Side.CLIENT)
	/**
	* Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
	*/
	public int getRenderBlockPass()
	{
	return 1;
	}
	
	
	
	@SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
		
		//System.out.println(rdyTime+" "+timer);
		
		if(rdyTime == false){
        if (par5Random.nextInt(100) == 0)
        {
        	try{
            par1World.playSound((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "VoidCraft:portal.Xia", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F, false);
            rdyTime = true;
            timer = (204) * 4;
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
		}else{
			timer--;
		}
		
		if(timer <= 0){
			rdyTime = false;
		}

        for (int l = 0; l < 4; ++l)
        {
            double d0 = (double)((float)par2 + par5Random.nextFloat());
            double d1 = (double)((float)par3 + par5Random.nextFloat());
            double d2 = (double)((float)par4 + par5Random.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = par5Random.nextInt(2) * 2 - 1;
            d3 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;

            if (par1World.getBlock(par2 - 1, par3, par4) != this && par1World.getBlock(par2 + 1, par3, par4) != this)
            {
                d0 = (double)par2 + 0.5D + 0.25D * (double)i1;
                d3 = (double)(par5Random.nextFloat() * 2.0F * (float)i1);
            }
            else
            {
                d2 = (double)par4 + 0.5D + 0.25D * (double)i1;
                d5 = (double)(par5Random.nextFloat() * 2.0F * (float)i1);
            }

            //par1World.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
            //Minecraft.getMinecraft().effectRenderer.addEffect(new TestFX(par1World, d0, d1, d2));
        }
    }
	
	/**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    
	
	//@SideOnly(Side.CLIENT)
	/**
	* Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	* coordinates. Args: blockAccess, x, y, z, side
	
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
	if (par1IBlockAccess.getBlockId(par2, par3, par4) == this.blockID)
	{
	return false;
	}
	else
	{
	boolean flag = par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.blockID && par1IBlockAccess.getBlockId(par2 - 2, par3, par4) != this.blockID;
	boolean flag1 = par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.blockID && par1IBlockAccess.getBlockId(par2 + 2, par3, par4) != this.blockID;
	boolean flag2 = par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.blockID && par1IBlockAccess.getBlockId(par2, par3, par4 - 2) != this.blockID;
	boolean flag3 = par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.blockID && par1IBlockAccess.getBlockId(par2, par3, par4 + 2) != this.blockID;
	boolean flag4 = flag || flag1;
	boolean flag5 = flag2 || flag3;
	return flag4 && par5 == 4 ? true : (flag4 && par5 == 5 ? true : (flag5 && par5 == 2 ? true : flag5 && par5 == 3));
	}
	}*/
	
}
