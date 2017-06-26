package Tamaized.Voidcraft.common.blocks;

import Tamaized.TamModized.blocks.TamBlockFire;
import Tamaized.TamModized.blocks.TamBlockPortal;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.common.entity.EntityVoidMob;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class FireVoid extends TamBlockFire {

	public FireVoid(CreativeTabs tab, String n) {
		super(tab, n, SoundType.CLOTH);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (world.getBlockState(pos.add(0, -1, 0)).getBlock() != VoidCraft.blocks.blockVoidcrystal || !((TamBlockPortal) VoidCraft.blocks.blockPortalVoid).tryToCreatePortal(world, pos)) {
			if (!world.isSideSolid(pos.down(), EnumFacing.UP) && !this.canNeighborCatchFire(world, pos)) {
				world.setBlockToAir(pos);
			} else {
				world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
			}
		} else {
			world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!worldIn.isRemote && entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityVoidMob || entityIn instanceof EntityWitherSkeleton)) {
			EntityLivingBase e = ((EntityLivingBase) entityIn);
			if (!e.isPotionActive(MobEffects.NAUSEA) || !e.isPotionActive(MobEffects.WITHER) || !e.isPotionActive(MobEffects.BLINDNESS)) {
				e.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 20 * 9, 1));
				e.addPotionEffect(new PotionEffect(MobEffects.WITHER, 20 * 4, 1));
				e.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 20 * 9, 1));
			}
		}
	}

	@Override
	protected boolean canNeighborCatchFire(World worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if (world.isAirBlock(pos.down()))
			world.setBlockToAir(pos);
	}

	@Override
	protected boolean canBeOnBlock(Block block) {
		return !(block instanceof BlockVoidcrystal);
	}
}
