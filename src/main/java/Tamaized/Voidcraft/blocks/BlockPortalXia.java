package Tamaized.Voidcraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.common.voidCraft;


public class BlockPortalXia extends BlockVoidTeleporter {

	public BlockPortalXia() {
		super();
	}
	/**
	* Updates the blocks bounds based on its current state. Args: world, x, y, z
	*/
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4){
		float f;
		float f1;
		if(par1IBlockAccess.getBlockState(new BlockPos(par2 - 1, par3, par4)).getBlock() != this && par1IBlockAccess.getBlockState(new BlockPos(par2 + 1, par3, par4)).getBlock() != this){
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

	
	public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4){
        byte b0 = 0;
        byte b1 = 0;

        if (par1World.getBlockState(new BlockPos(par2 - 1, par3, par4)).getBlock() == voidCraft.blocks.blockFakeBedrock || par1World.getBlockState(new BlockPos(par2 + 1, par3, par4)).getBlock() == voidCraft.blocks.blockFakeBedrock){
            b0 = 1;
        }

        if (par1World.getBlockState(new BlockPos(par2, par3, par4 - 1)).getBlock() == voidCraft.blocks.blockFakeBedrock || par1World.getBlockState(new BlockPos(par2, par3, par4 + 1)).getBlock() == voidCraft.blocks.blockFakeBedrock){
            b1 = 1;
        }

        if (b0 == b1){
            return false;
        }else{
        	if (par1World.isAirBlock(new BlockPos(par2 - b0, par3, par4 - b1))){
                par2 -= b0;
                par4 -= b1;
            }

            int l;
            int i1;

            for (l = -1; l <= 2; ++l){
                for (i1 = -1; i1 <= 3; ++i1){
                    boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

                    if (l != -1 && l != 2 || i1 != -1 && i1 != 3){
                        Block j1 = par1World.getBlockState(new BlockPos(par2 + b0 * l, par3 + i1, par4 + b1 * l)).getBlock();
                        boolean isAirBlock = par1World.isAirBlock(new BlockPos(par2 + b0 * l, par3 + i1, par4 + b1 * l));

                        if (flag){
                            if (j1 != voidCraft.blocks.blockFakeBedrock){
                                return false;
                            }
                        }
                        else if (!isAirBlock && j1 != voidCraft.blocks.fireVoid){
                            return false;
                        }
                    }
                }
            }

            for (l = 0; l < 2; ++l){
                for (i1 = 0; i1 < 3; ++i1){
                    par1World.setBlockState(new BlockPos(par2 + b0 * l, par3 + i1, par4 + b1 * l), voidCraft.blocks.blockPortalXia.getDefaultState(), 2);
                }
            }

            return true;
        }
    }
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5){
        byte b0 = 0;
        byte b1 = 1;

        if (par1World.getBlockState(new BlockPos(par2 - 1, par3, par4)).getBlock() == this || par1World.getBlockState(new BlockPos(par2 + 1, par3, par4)).getBlock() == this)
        {
            b0 = 1;
            b1 = 0;
        }

        int i1;

        for (i1 = par3; par1World.getBlockState(new BlockPos(par2, i1 - 1, par4)).getBlock() == this; --i1)
        {
            ;
        }

        if (par1World.getBlockState(new BlockPos(par2, i1 - 1, par4)).getBlock() != voidCraft.blocks.blockFakeBedrock)
        {
            par1World.setBlockToAir(new BlockPos(par2, par3, par4));
        }
        else
        {
            int j1;

            for (j1 = 1; j1 < 4 && par1World.getBlockState(new BlockPos(par2, i1 + j1, par4)).getBlock() == this; ++j1)
            {
                ;
            }

            if (j1 == 3 && par1World.getBlockState(new BlockPos(par2, i1 + j1, par4)).getBlock() == voidCraft.blocks.blockFakeBedrock)
            {
                boolean flag = par1World.getBlockState(new BlockPos(par2 - 1, par3, par4)).getBlock() == this || par1World.getBlockState(new BlockPos(par2 + 1, par3, par4)).getBlock() == this;
                boolean flag1 = par1World.getBlockState(new BlockPos(par2, par3, par4 - 1)).getBlock() == this || par1World.getBlockState(new BlockPos(par2, par3, par4 + 1)).getBlock() == this;

                if (flag && flag1)
                {
                    par1World.setBlockToAir(new BlockPos(par2, par3, par4));
                }
                else
                {
                    if ((par1World.getBlockState(new BlockPos(par2 + b0, par3, par4 + b1)).getBlock() != voidCraft.blocks.blockFakeBedrock || par1World.getBlockState(new BlockPos(par2 - b0, par3, par4 - b1)).getBlock() != this) && (par1World.getBlockState(new BlockPos(par2 - b0, par3, par4 - b1)).getBlock() != voidCraft.blocks.blockFakeBedrock || par1World.getBlockState(new BlockPos(par2 + b0, par3, par4 + b1)).getBlock() != this))
                    {
                        par1World.setBlockToAir(new BlockPos(par2, par3, par4));
                    }
                }
            }
            else
            {
                par1World.setBlockToAir(new BlockPos(par2, par3, par4));
            }
        }
    }
	
	
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random){
        for (int l = 0; l < 4; ++l){
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

            if (par1World.getBlockState(new BlockPos(par2 - 1, par3, par4)).getBlock() != this && par1World.getBlockState(new BlockPos(par2 + 1, par3, par4)).getBlock() != this)
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

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
		return true;
	}
	
}
