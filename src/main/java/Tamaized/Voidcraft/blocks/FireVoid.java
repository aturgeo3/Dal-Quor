package Tamaized.Voidcraft.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.mobs.EntityVoidMob;

public class FireVoid extends BasicVoidBlockFire{

	public FireVoid(String string) {
		super(string);
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state){
		if(world.getBlockState(pos.add(0, -1, 0)).getBlock() != voidCraft.blocks.blockVoidcrystal || !((BlockVoidTeleporter) voidCraft.blocks.blockPortalVoid).tryToCreatePortal(world, pos)){
			if(!world.isSideSolid(pos.down(), EnumFacing.UP) && !this.canNeighborCatchFire(world, pos)){
				world.setBlockToAir(pos);
			}else{
				world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
			}
		}else{
			world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if(entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityVoidMob)){
			if(entityIn instanceof EntitySkeleton){
				EntitySkeleton skelly = (EntitySkeleton) entityIn;
				if(skelly.getSkeletonType() == 1) return;
			}
			EntityLivingBase e = ((EntityLivingBase) entityIn);
			e.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 60, 1)); //nausea
			e.addPotionEffect(new PotionEffect(Potion.getPotionById(20), 60, 1)); //wither
			e.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 60, 1)); //blind
		}
	}

	@Override
	protected boolean canNeighborCatchFire(World worldIn, BlockPos pos){
		return false;
	}
}
