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

public class FireVoid extends BlockFire{

	public FireVoid() {
		super();
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state){
		if(world.getBlockState(pos).getBlock() != voidCraft.blocks.blockVoidcrystal || !((BlockVoidTeleporter) voidCraft.blocks.blockPortalVoid).tryToCreatePortal(world, pos)){
			if(!world.doesBlockHaveSolidTopSurface(world, pos.add(0, -1, 0)) && !this.canNeighborBurn(world, pos.getX(), pos.getY(), pos.getZ())){
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
			e.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 20, 1));
			e.addPotionEffect(new PotionEffect(Potion.wither.getId(), 20, 1));
			e.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 20, 1));
		}
	}
	
	private boolean canNeighborBurn(World par1World, int par2, int par3, int par4){
		return false;
	}
	 
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
		if (world.getGameRules().getGameRuleBooleanValue("doFireTick")){
			boolean flag = world.getBlockState(pos.add(0, -1, 0)).getBlock().isFireSource(world, pos.add(0, -1, 0), EnumFacing.UP);
			
			if (!this.canPlaceBlockAt(world, pos)){
				world.setBlockToAir(pos);
			}

			if (!flag && world.isRaining() && (
				world.canLightningStrike(pos) ||
				world.canLightningStrike(pos.add(-1, 0, 0)) ||
				world.canLightningStrike(pos.add(1, 0, 0)) ||
				world.canLightningStrike(pos.add(0, 0, -1)) ||
				world.canLightningStrike(pos.add(0, 0, 1)))
			){
				world.setBlockToAir(pos);
			}else{
				IBlockState bState = world.getBlockState(pos);
				int l = bState.getBlock().getMetaFromState(bState);
				
				if (l < 15){
					world.setBlockState(pos, this.getStateFromMeta(l + rand.nextInt(3) / 2), 4);
				}

				world.scheduleUpdate(pos, this, this.tickRate(world) + rand.nextInt(10));

				if (!flag && !this.canNeighborBurn(world, pos.getX(), pos.getY(), pos.getZ())){
					if (!World.doesBlockHaveSolidTopSurface(world, pos.add(0, -1, 0)) || l > 3){
						if(!(world.getBlockState(pos.add(0, -1, 0)).getBlock() instanceof BlockVoidcrystal)) world.setBlockToAir(pos);
					}
				}else if (!flag && !this.canCatchFire(world, pos.add(0, -1, 0), EnumFacing.UP) && l == 15 && rand.nextInt(4) == 0){
					world.setBlockToAir(pos);
				}else{
					boolean flag1 = world.isBlockinHighHumidity(pos);
					byte b0 = 0;
					
					if (flag1){
						b0 = -50;
					}
					
					this.tryCatchFire(world, pos.add(1, 0, 0), 300 + b0, rand, l, EnumFacing.WEST);
					this.tryCatchFire(world, pos.add(-1, 0, 0), 300 + b0, rand, l, EnumFacing.EAST);
					this.tryCatchFire(world, pos.add(0, -1, 0), 250 + b0, rand, l, EnumFacing.UP);
					this.tryCatchFire(world, pos.add(0, 1, 0), 250 + b0, rand, l, EnumFacing.DOWN);
					this.tryCatchFire(world, pos.add(0, 0, -1), 300 + b0, rand, l, EnumFacing.SOUTH);
					this.tryCatchFire(world, pos.add(0, 0, 1), 300 + b0, rand, l, EnumFacing.NORTH);
					
					for (int i1 = pos.getX() - 1; i1 <= pos.getX() + 1; ++i1){
						for (int j1 = pos.getZ() - 1; j1 <= pos.getZ() + 1; ++j1){
							for (int k1 = pos.getY() - 1; k1 <= pos.getY() + 4; ++k1){
								if (i1 != pos.getX() || k1 != pos.getY() || j1 != pos.getZ()){
									int l1 = 100;
									
									if (k1 > pos.getY() + 1){
										l1 += (k1 - (pos.getY() + 1)) * 100;
									}
										
									int i2 = this.getChanceOfNeighborsEncouragingFire(world, new BlockPos(i1, k1, j1));
									
									if (i2 > 0){
										int j2 = (i2 + 40 + world.getDifficulty().getDifficultyId() * 7) / (l + 30);
										
										if (flag1){
											j2 /= 2;
										}

										if (
												j2 > 0 &&
												rand.nextInt(l1) <= j2 &&
												(!world.isRaining() ||
														!world.canLightningStrike(new BlockPos(i1, k1, j1))) &&
														!world.canLightningStrike(new BlockPos(i1 - 1, k1, pos.getZ())) &&
														!world.canLightningStrike(new BlockPos(i1 + 1, k1, j1)) &&
														!world.canLightningStrike(new BlockPos(i1, k1, j1 - 1)) &&
														!world.canLightningStrike(new BlockPos(i1, k1, j1 + 1)))
										{
											int k2 = l + rand.nextInt(5) / 4;
											
											if (k2 > 15){
												k2 = 15;
											}
											
											world.setBlockState(new BlockPos(i1, k1, j1), this.getStateFromMeta(k2), 3);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	private void tryCatchFire(World world, BlockPos pos, int chance, Random rand, int age, EnumFacing face){
		int j1 = world.getBlockState(pos).getBlock().getFlammability(world, pos, face);
		
		if (rand.nextInt(chance) < j1){
			boolean flag = world.getBlockState(pos).getBlock() == Blocks.tnt;
			
			if (rand.nextInt(age + 10) < 5 && !world.canLightningStrike(pos)){
				int k1 = age + rand.nextInt(5) / 4;
				
				if (k1 > 15){
					k1 = 15;
				}
					
				world.setBlockState(pos, this.getStateFromMeta(k1),  3);
			}else{
				world.setBlockToAir(pos);
			}	

			if (flag){
				Blocks.tnt.onBlockDestroyedByPlayer(world, pos, Blocks.tnt.getStateFromMeta(1));
			}
		}
	}
	 
	 /**
	  * Gets the highest chance of a neighbor block encouraging this block to catch fire
	  */
	private int getChanceOfNeighborsEncouragingFire(World world, BlockPos pos){
		byte b0 = 0;
		
		if (!world.isAirBlock(pos)){
			return 0;
		}else{
			int l = b0;
			l = this.getFlammability(world, pos.add(1, 0, 0), EnumFacing.WEST);
			l = this.getFlammability(world, pos.add(-1, 0, 0), EnumFacing.EAST);
			l = this.getFlammability(world, pos.add(0, -1, 0), EnumFacing.UP);
			l = this.getFlammability(world, pos.add(0, 1, 0), EnumFacing.DOWN);
			l = this.getFlammability(world, pos.add(0, 0, -1), EnumFacing.SOUTH);
			l = this.getFlammability(world, pos.add(0, 0, 1), EnumFacing.NORTH);
			return l;
		}
	}
}
