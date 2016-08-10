package Tamaized.Voidcraft.items;

import java.util.HashSet;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fluids.IFluidBlock;
import Tamaized.TamModized.api.voidcraft.power.VoidicPowerItem;
import Tamaized.TamModized.api.voidcraft.power.VoidicPowerItemHandler;
import Tamaized.TamModized.particles.ParticleHelper;
import Tamaized.TamModized.particles.ParticleHelper.IParticlePacketData;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.damageSources.DamageSourceVoidicInfusion;

public class VoidicDrill extends VoidicPowerItem {

	public VoidicDrill(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		if (VoidicPowerItemHandler.getItemVoidicPower(stack) > 1) {
			if (!player.capabilities.isCreativeMode) VoidicPowerItemHandler.setItemVoidicPower(stack, VoidicPowerItemHandler.getItemVoidicPower(stack) - 1);
			if (!world.isRemote) {
				HashSet<Entity> exclude = new HashSet<Entity>();
				exclude.add(player);
				int maxDistance = 10;
				RayTraceResult result = tracePath(world, player, maxDistance, 1, exclude);
				Vec3d ray = getPlayerTraceVec(player, maxDistance)[1];
				if (result != null) {
					ray = result.hitVec;
					if (result.entityHit != null) {
						result.entityHit.attackEntityFrom(new DamageSourceVoidicInfusion(), 5.0f);
					} else {
						switch (result.sideHit) {
							case UP: // Y
								caseY(player, world, stack, result.getBlockPos());
								break;
							case DOWN: // Y
								caseY(player, world, stack, result.getBlockPos());
								break;
							case EAST: // X
								caseX(player, world, stack, result.getBlockPos());
								break;
							case WEST: // X
								caseX(player, world, stack, result.getBlockPos());
								break;
							case NORTH: // Z
								caseZ(player, world, stack, result.getBlockPos());
								break;
							case SOUTH: // Z
								caseZ(player, world, stack, result.getBlockPos());
								break;
							default:
								break;
						}
					}
				}
				VoidDrillParticleData data = new VoidDrillParticleData(ray == null ? player.getLook(1.0F).scale(10) : ray, hand == EnumHand.OFF_HAND);
				ParticleHelper.sendPacketToClients(world, voidCraft.particles.drillRayHandler, new Vec3d(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()), 64, new ParticleHelper.ParticlePacketHelper(voidCraft.particles.drillRayHandler, data));
			}
			return ActionResult.newResult(EnumActionResult.PASS, stack);
		}
		return ActionResult.newResult(EnumActionResult.FAIL, stack);
	}

	private void caseY(EntityPlayer player, World world, ItemStack tool, BlockPos pos) {
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				removeBlockWithDrops(player, world, tool, pos.add(x, 0, z), true);
			}
		}
	}

	private void caseX(EntityPlayer player, World world, ItemStack tool, BlockPos pos) {
		for (int y = -1; y <= 1; y++) {
			for (int z = -1; z <= 1; z++) {
				removeBlockWithDrops(player, world, tool, pos.add(0, y, z), true);
			}
		}
	}

	private void caseZ(EntityPlayer player, World world, ItemStack tool, BlockPos pos) {
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				removeBlockWithDrops(player, world, tool, pos.add(x, y, 0), true);
			}
		}
	}

	private static void removeBlockWithDrops(EntityPlayer player, World world, ItemStack tool, BlockPos pos, boolean particles) {
		int harvestLevel = 8;
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		if (!world.isRemote && block != null && !block.isAir(state, world, pos) && !(block instanceof BlockLiquid) && !(block instanceof IFluidBlock) && block.getPlayerRelativeBlockHardness(state, player, world, pos) > 0) {
			int neededHarvestLevel = block.getHarvestLevel(state);
			if (neededHarvestLevel > harvestLevel && (tool != null && !tool.canHarvestBlock(state))) return;

			BreakEvent event = new BreakEvent(world, pos, state, player);
			MinecraftForge.EVENT_BUS.post(event);
			if (!event.isCanceled()) {
				if (!player.capabilities.isCreativeMode) {
					if (block.removedByPlayer(state, world, pos, player, true)) {
						block.onBlockDestroyedByPlayer(world, pos, state);
						block.harvestBlock(world, player, pos, state, world.getTileEntity(pos), tool);
					}
				} else world.setBlockToAir(pos);
			}

			if (particles) world.playEvent(2001, pos, Block.getStateId(state));
		}
	}

	@Override
	protected int getDefaultVoidicPower() {
		return 0;
	}

	@Override
	protected int getDefaultMaxVoidicPower() {
		return 5000;
	}

	public class VoidDrillParticleData implements IParticlePacketData {

		public final Vec3d target;
		public final boolean offhand;

		public VoidDrillParticleData(Vec3d t, boolean o) {
			target = t;
			offhand = o;
		}

	}

	public static Vec3d[] getPlayerTraceVec(EntityPlayer player, int distance) {
		Vec3d vec3d = player.getPositionEyes(1.0f);
		Vec3d vec3d1 = player.getLook(1.0f);
		Vec3d vec3d2 = vec3d.addVector(vec3d1.xCoord * distance, vec3d1.yCoord * distance, vec3d1.zCoord * distance);
		return new Vec3d[] { vec3d, vec3d2 };
	}

	public static RayTraceResult tracePath(World world, EntityPlayer player, int distance, float borderSize, HashSet<Entity> excluded) {
		Vec3d[] vecs = getPlayerTraceVec(player, distance);
		return tracePath(world, vecs[0], vecs[1], borderSize, excluded);
	}

	public static RayTraceResult tracePath(World world, Vec3d vec1, Vec3d vec2, float borderSize, HashSet<Entity> excluded) {
		return tracePath(world, (float) vec1.xCoord, (float) vec1.yCoord, (float) vec1.zCoord, (float) vec2.xCoord, (float) vec2.yCoord, (float) vec2.zCoord, borderSize, excluded);
	}

	private static RayTraceResult tracePath(World world, float x, float y, float z, float tx, float ty, float tz, float borderSize, HashSet<Entity> excluded) {
		Vec3d startVec = new Vec3d(x, y, z);
		Vec3d lookVec = new Vec3d(tx - x, ty - y, tz - z);
		Vec3d endVec = new Vec3d(tx, ty, tz);
		float minX = x < tx ? x : tx;
		float minY = y < ty ? y : ty;
		float minZ = z < tz ? z : tz;
		float maxX = x > tx ? x : tx;
		float maxY = y > ty ? y : ty;
		float maxZ = z > tz ? z : tz;
		AxisAlignedBB bb = new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ).expand(borderSize, borderSize, borderSize);
		List<Entity> allEntities = world.getEntitiesWithinAABBExcludingEntity(null, bb);
		RayTraceResult blockHit = world.rayTraceBlocks(startVec, endVec);
		startVec = new Vec3d(x, y, z);
		endVec = new Vec3d(tx, ty, tz);
		float maxDistance = (float) endVec.distanceTo(startVec);
		if (blockHit != null) {
			maxDistance = (float) blockHit.hitVec.distanceTo(startVec);
		}
		Entity closestHitEntity = null;
		float closestHit = Float.POSITIVE_INFINITY;
		float currentHit = 0.f;
		AxisAlignedBB entityBb;// = ent.getBoundingBox();
		RayTraceResult intercept;
		for (Entity ent : allEntities) {
			if (ent.canBeCollidedWith() && !excluded.contains(ent)) {
				float entBorder = ent.getCollisionBorderSize();
				entityBb = ent.getEntityBoundingBox();
				if (entityBb != null) {
					entityBb = entityBb.expand(entBorder, entBorder, entBorder);
					intercept = entityBb.calculateIntercept(startVec, endVec);
					if (intercept != null) {
						currentHit = (float) intercept.hitVec.distanceTo(startVec);
						if (currentHit < closestHit || currentHit == 0) {
							closestHit = currentHit;
							closestHitEntity = ent;
						}
					}
				}
			}
		}
		if (closestHitEntity != null) {
			blockHit = new RayTraceResult(closestHitEntity);
		}
		return blockHit;
	}

}