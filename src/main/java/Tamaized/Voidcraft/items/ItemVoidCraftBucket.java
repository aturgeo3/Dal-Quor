package Tamaized.Voidcraft.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBucket;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ItemVoidCraftBucket extends ItemBucket {
	
	private Block liquid;

	public ItemVoidCraftBucket(Block liquid) {
		super(liquid);
		
		this.liquid = liquid;
	}
	
	/**
     * Attempts to place the liquid contained inside the bucket.
     */
	@Override
    public boolean tryPlaceContainedLiquid(World world, BlockPos pos)
    {
        if (this.liquid == Blocks.air)
        {
            return false;
        }
        else
        {
            Material material = world.getBlockState(pos).getBlock().getMaterial();
            boolean flag = !material.isSolid();

            if (!world.isAirBlock(pos) && !flag)
            {
                return false;
            }
            else
            {
                if (world.provider.getDimensionId() == -1 && this.liquid == Blocks.flowing_water)
                {
                    world.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l)
                    {
                        world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)pos.getX() + Math.random(), (double)pos.getY() + Math.random(), (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
                else
                {
                    if (!world.isRemote && flag && !material.isLiquid())
                    {
                        world.destroyBlock(pos, true);
                    }

                    world.setBlockState(pos, this.liquid.getDefaultState(), 3);
                }

                return true;
            }
        }
    }

}
