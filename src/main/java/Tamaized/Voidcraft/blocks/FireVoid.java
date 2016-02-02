package Tamaized.Voidcraft.blocks;

import java.util.Random;

import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.mobs.EntityVoidMob;

public class FireVoid extends BasicVoidBlockFire{

	public FireVoid(String string) {
		super(string);
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state){
		if(world.getBlockState(pos.add(0, -1, 0)).getBlock() != voidCraft.blocks.blockVoidcrystal || !((BlockVoidTeleporter) voidCraft.blocks.blockPortalVoid).tryToCreatePortal(world, pos.add(0, -1, 0))){
			if(!world.doesBlockHaveSolidTopSurface(world, pos.add(0, -1, 0)) && !this.canNeighborBurn(world, pos)){
				world.setBlockToAir(pos);
			}else{
				world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
			}
		}else{
			world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
	}
	
	public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity entity) {
		if(entity instanceof EntityLivingBase && !(entity instanceof EntityVoidMob)){
			if(entity instanceof EntitySkeleton){
				EntitySkeleton skelly = (EntitySkeleton) entity;
				if(Integer.valueOf(skelly.getDataWatcher().getWatchableObjectByte(13))==1) return;
			}
			EntityLivingBase e = ((EntityLivingBase) entity);
			e.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 60, 1));
			e.addPotionEffect(new PotionEffect(Potion.wither.getId(), 60, 1));
			e.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 60, 1));
		}
	}

	@Override
	protected boolean canNeighborBurn(World par1World, BlockPos pos) {
		return false;
	}
}
