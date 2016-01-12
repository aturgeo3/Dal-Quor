package Tamaized.Voidcraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.common.voidCraft;

public class BlockPortalVoid extends BlockVoidTeleporter {

	public BlockPortalVoid() {
		super();
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		float f;
		float f1;
		if(worldIn.getBlockState(pos.add(-1, 0, 0)).getBlock() != this && worldIn.getBlockState(pos.add(1, 0, 0)).getBlock() != this){
			f = 0.125F;
			f1 = 0.5F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
		}else{
			f = 0.5F;
			f1 = 0.125F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
		}
	}
	
	@Override
	public boolean tryToCreatePortal(World par1World, BlockPos pos) {
		byte b0 = 0;
    	byte b1 = 0;
    
    	int x = pos.getX();
    	int y = pos.getY();
    	int z = pos.getZ();

    	if (par1World.getBlockState(pos.add(-1, 0, 0)).getBlock() == voidCraft.blocks.blockVoidcrystal || par1World.getBlockState(pos.add(1, 0, 0)).getBlock() == voidCraft.blocks.blockVoidcrystal){
    		b0 = 1;
    	}

    	if (par1World.getBlockState(pos.add(0, 0, -1)).getBlock() == voidCraft.blocks.blockVoidcrystal || par1World.getBlockState(pos.add(0, 0, 1)).getBlock() == voidCraft.blocks.blockVoidcrystal){
    		b1 = 1;
    	}

    	if (b0 == b1){
    		return false;
    	}else{
    		if (par1World.isAirBlock(new BlockPos(x - b0, y, z - b1))){
    			x -= b0;
    			y -= b1;
    		}

    		int l;
    		int i1;

    		for (l = -1; l <= 2; ++l){
    			for (i1 = -1; i1 <= 3; ++i1){
    				boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

    				if (l != -1 && l != 2 || i1 != -1 && i1 != 3){
    					Block j1 = par1World.getBlockState(new BlockPos(x + b0 * l, y + i1, z + b1 * l)).getBlock();
    					boolean isAirBlock = par1World.isAirBlock(new BlockPos(x + b0 * l, y + i1, z + b1 * l));
    					
    					if (flag){
    						if (j1 != voidCraft.blocks.blockVoidcrystal){
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
    				par1World.setBlockState(new BlockPos(x + b0 * l, y + i1, z + b1 * l), voidCraft.blocks.blockPortalXia.getDefaultState(), 2);
    			}
    		}

    		return true;
    	}
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock){
        byte b0 = 0;
        byte b1 = 1;

        if (world.getBlockState(pos.add(-1, 0, 0)).getBlock() == this || world.getBlockState(pos.add(1, 0, 0)).getBlock() == this)
        {
            b0 = 1;
            b1 = 0;
        }

        int i1;

        for (i1 = pos.getY(); world.getBlockState(pos.add(0, i1 - 1, 0)).getBlock() == this; --i1)
        {
            ;
        }

        if (world.getBlockState(pos.add(0, i1 - 1, 0)).getBlock() != voidCraft.blocks.blockVoidcrystal)
        {
            world.setBlockToAir(pos);
        }
        else
        {
            int j1;

            for (j1 = 1; j1 < 4 && world.getBlockState(pos.add(0, i1 + j1, 0)).getBlock() == this; ++j1)
            {
                ;
            }

            if (j1 == 3 && world.getBlockState(pos.add(0, i1 + j1, 0)).getBlock() == voidCraft.blocks.blockVoidcrystal)
            {
                boolean flag = world.getBlockState(pos.add(-1, 0, 0)).getBlock() == this || world.getBlockState(pos.add(1, 0, 0)).getBlock() == this;
                boolean flag1 = world.getBlockState(pos.add(0, 0, -1)).getBlock() == this || world.getBlockState(pos.add(0, 0, 1)).getBlock() == this;

                if (flag && flag1)
                {
                    world.setBlockToAir(pos);
                }
                else
                {
                    if ((world.getBlockState(pos.add(b0, 0, b1)).getBlock() != voidCraft.blocks.blockVoidcrystal || world.getBlockState(pos.add(-b0, 0, -b1)).getBlock() != this) && (world.getBlockState(pos.add(-b0, 0, -b1)).getBlock() != voidCraft.blocks.blockVoidcrystal || world.getBlockState(pos.add(b0, 0, b1)).getBlock() != this))
                    {
                        world.setBlockToAir(pos);
                    }
                }
            }
            else
            {
                world.setBlockToAir(pos);
            }
        }
    }

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand){
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
        for (int l = 0; l < 4; ++l){
            double d0 = (double)((float)x + rand.nextFloat());
            double d1 = (double)((float)y + rand.nextFloat());
            double d2 = (double)((float)z + rand.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = rand.nextInt(2) * 2 - 1;
            d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;

            if (world.getBlockState(pos.add(-1, 0, 0)).getBlock() != this && world.getBlockState(pos.add(1, 0, 0)).getBlock() != this)
            {
                d0 = (double)x + 0.5D + 0.25D * (double)i1;
                d3 = (double)(rand.nextFloat() * 2.0F * (float)i1);
            }
            else
            {
                d2 = (double)z + 0.5D + 0.25D * (double)i1;
                d5 = (double)(rand.nextFloat() * 2.0F * (float)i1);
            }

			// par1World.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
			// Minecraft.getMinecraft().effectRenderer.addEffect(new
			// TestFX(par1World, d0, d1, d2));
		}
	}

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates. Args: blockAccess, x, y, z, side
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		if (worldIn.getBlockState(pos).getBlock() == this) {
			return false;
		} else {
			boolean flag = worldIn.getBlockState(pos.add(-1, 0, 0)).getBlock() == this && worldIn.getBlockState(pos.add(-2, 0, 0)).getBlock() != this;
			boolean flag1 = worldIn.getBlockState(pos.add(1, 0, 0)).getBlock() == this && worldIn.getBlockState(pos.add(2, 0, 0)).getBlock() != this;
			boolean flag2 = worldIn.getBlockState(pos.add(0, 0, -1)).getBlock() == this && worldIn.getBlockState(pos.add(0, 0, -2)).getBlock() != this;
			boolean flag3 = worldIn.getBlockState(pos.add(0, 0, 1)).getBlock() == this && worldIn.getBlockState(pos.add(0, 0, 2)).getBlock() != this;
			boolean flag4 = flag || flag1;
			boolean flag5 = flag2 || flag3;
			return flag4 && side == EnumFacing.WEST ? true : (flag4 && side == EnumFacing.EAST ? true : (flag5 && side == EnumFacing.NORTH ? true : flag5 && side == EnumFacing.SOUTH));
		}
	}

}
