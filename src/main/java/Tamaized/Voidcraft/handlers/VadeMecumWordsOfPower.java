package Tamaized.Voidcraft.handlers;

import java.util.HashSet;

import Tamaized.TamModized.helper.RayTraceHelper;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.blocks.spell.tileentity.TileEntitySpellIceSpike;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.entity.nonliving.EntityCasterLightningBolt;
import Tamaized.Voidcraft.entity.nonliving.EntitySpellRune;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class VadeMecumWordsOfPower {

	public static void invoke(World world, EntityPlayer player) {
		IVadeMecumCapability cap = player.getCapability(CapabilityList.VADEMECUM, null);
		if (cap == null || world.isRemote) return;
		IVadeMecumCapability.ActivePower power = cap.getCurrentActive();
		boolean useCharge = false;
		power = IVadeMecumCapability.ActivePower.RingOfFrost;
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
				case FireSheathe:
					player.removePotionEffect(voidCraft.potions.frostSheath);
					player.removePotionEffect(voidCraft.potions.acidSheath);
					player.removePotionEffect(voidCraft.potions.litSheath);
					player.addPotionEffect(new PotionEffect(voidCraft.potions.fireSheath, 20 * 90));
					useCharge = true;
					break;
				case Fireball:
					Vec3d vec = player.getLookVec();
					EntityFireball entity = new EntityLargeFireball(world, player.posX, player.posY + player.eyeHeight, player.posZ, vec.xCoord, vec.yCoord, vec.zCoord);
					entity.shootingEntity = player;
					world.spawnEntity(entity);
					useCharge = true;
				case FireTrap:
					useCharge = castRune(world, player, new EntitySpellRune(world, EntitySpellRune.DamageType.FIRE, 10, 5, 0xFF5733));
					break;
				case ExplosionFire:
					world.createExplosion(player, player.posX, player.posY, player.posZ, 10.0F, false);
					useCharge = true;
					break;
				case RingOfFire:
					for (BlockPos pos : createCircle(player.getPosition()))
						if (world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) world.setBlockState(pos, Blocks.FIRE.getDefaultState());
					useCharge = true;
					break;
				case LitStrike:
					exclude.add(player);
					result = RayTraceHelper.tracePath(world, player, 32, 1, exclude);
					if (result != null) {
						if (result.entityHit != null) {
							EntityCasterLightningBolt entitylightningbolt = new EntityCasterLightningBolt(world, player, result.entityHit.posX, result.entityHit.posY, result.entityHit.posZ, false);
							world.addWeatherEffect(entitylightningbolt);
						} else {
							BlockPos bp = result.getBlockPos();
							if (bp != null) {
								EntityCasterLightningBolt entitylightningbolt = new EntityCasterLightningBolt(world, player, bp.getX(), bp.getY() + 1, bp.getZ(), false);
								world.addWeatherEffect(entitylightningbolt);
							}
						}
						useCharge = true;
					}
					break;
				case LitTrap:
					useCharge = castRune(world, player, new EntitySpellRune(world, EntitySpellRune.DamageType.SHOCK, 10, 5, 0xFFFFFF));
					break;
				case RingOfLit:
					for (BlockPos pos : createCircle(player.getPosition())) {
						EntityCasterLightningBolt entitylightningbolt = new EntityCasterLightningBolt(world, player, pos.getX(), pos.getY(), pos.getZ(), false);
						world.addWeatherEffect(entitylightningbolt);
					}
					useCharge = true;
					break;
				case FrostTrap:
					useCharge = castRune(world, player, new EntitySpellRune(world, EntitySpellRune.DamageType.FROST, 10, 5, 0x00FFFF));
					break;
				case RingOfFrost:
					for (BlockPos pos : createCircle(player.getPosition()))
						if (world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) {
							world.setBlockState(pos, voidCraft.blocks.iceSpike.getDefaultState());
							TileEntity te = world.getTileEntity(pos);
							if (te instanceof TileEntitySpellIceSpike) ((TileEntitySpellIceSpike) te).setCaster(player);
						}
					useCharge = true;
					break;
				case AcidTrap:
					useCharge = castRune(world, player, new EntitySpellRune(world, EntitySpellRune.DamageType.ACID, 15, 5, 0x00FF00));
					break;
				default:
					break;
			}
		}
		if (useCharge) {

		}
	}

	private static BlockPos[] createCircle(BlockPos pos) {
		return new BlockPos[] {

				pos.add(3, 0, 1), pos.add(3, 0, 0), pos.add(3, 0, -1),

				pos.add(-3, 0, 1), pos.add(-3, 0, 0), pos.add(-3, 0, -1),

				pos.add(1, 0, -3), pos.add(0, 0, -3), pos.add(-1, 0, -3),

				pos.add(1, 0, 3), pos.add(0, 0, 3), pos.add(-1, 0, 3),

				pos.add(2, 0, 2), pos.add(-2, 0, 2), pos.add(2, 0, -2), pos.add(-2, 0, -2)

		};
	}

	private static boolean castRune(World world, EntityPlayer player, EntitySpellRune rune) {
		if (!world.isRemote) {
			HashSet<Entity> exclude = new HashSet<Entity>();
			exclude.add(player);
			RayTraceResult result = RayTraceHelper.tracePath(world, player, 32, 1, exclude);
			if (result != null) {
				if (result.entityHit == null) {
					BlockPos bp = result.getBlockPos();
					BlockPos pos = bp;
					if (bp != null) {
						switch (result.sideHit) {
							case UP:
								pos = pos.add(0, 1, 0);
								rune.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
								world.spawnEntity(rune);
								return true;
							default:
								return false;
						}
					}
				}
			}
		}
		return false;
	}

}
