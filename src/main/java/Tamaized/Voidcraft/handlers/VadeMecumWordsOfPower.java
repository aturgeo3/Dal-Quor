package Tamaized.Voidcraft.handlers;

import java.util.HashSet;

import Tamaized.TamModized.helper.RayTraceHelper;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class VadeMecumWordsOfPower {

	public static void invoke(World world, EntityPlayer player) {
		IVadeMecumCapability cap = player.getCapability(CapabilityList.VADEMECUM, null);
		if (cap == null) return;
		IVadeMecumCapability.ActivePower power = cap.getCurrentActive();
		boolean useCharge = false;
		power = IVadeMecumCapability.ActivePower.ExplosionFire;
		if (power != null) {
			HashSet<Entity> exclude = new HashSet<Entity>();
			RayTraceResult result;
			switch (power) {
				case Flame:
					exclude.add(player);
					result = RayTraceHelper.tracePath(world, player, 32, 1, exclude);
					if (result != null) {
						if (result.entityHit != null) {
							result.entityHit.setFire(10);
						} else {
							BlockPos bp = result.getBlockPos();
							BlockPos pos = bp;
							if (bp != null) {
								switch (result.sideHit) {
									case UP:
										pos = pos.add(0, 1, 0);
										break;
									case DOWN:
										pos = pos.add(0, -1, 0);
										break;
									case NORTH:
										pos = pos.add(0, 0, -1);
										break;
									case SOUTH:
										pos = pos.add(0, 0, 1);
										break;
									case EAST:
										pos = pos.add(1, 0, 0);
										break;
									case WEST:
										pos = pos.add(-1, 0, 0);
										break;
									default:
										break;
								}
								if (world.isAirBlock(pos)) world.setBlockState(pos, Blocks.FIRE.getDefaultState());
							}
						}
						useCharge = true;
					}
					break;
				case FireSheath:
					
					break;
				case Fireball:
					Vec3d vec = player.getLookVec();
					EntityFireball entity = new EntityLargeFireball(world, player.posX, player.posY + player.eyeHeight, player.posZ, vec.xCoord, vec.yCoord, vec.zCoord);
					entity.shootingEntity = player;
					world.spawnEntityInWorld(entity);
				case FireTrap:

					break;
				case ExplosionFire:
					world.createExplosion(player, player.posX, player.posY, player.posZ, 10.0F, false);
					break;
				case CircleOfFire:

					break;
				case LitStrike:
					exclude.add(player);
					result = RayTraceHelper.tracePath(world, player, 32, 1, exclude);
					if (result != null) {
						if (result.entityHit != null) {
							EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, result.entityHit.posX, result.entityHit.posY, result.entityHit.posZ, false);
							world.addWeatherEffect(entitylightningbolt);
						} else {
							BlockPos bp = result.getBlockPos();
							if (bp != null) {
								EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, bp.getX(), bp.getY() + 1, bp.getZ(), false);
								world.addWeatherEffect(entitylightningbolt);
							}
						}
						useCharge = true;
					}
					break;
				default:
					break;
			}
		}
		if (useCharge) {

		}
	}

}
