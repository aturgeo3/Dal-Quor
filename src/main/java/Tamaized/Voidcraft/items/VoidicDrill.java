package Tamaized.Voidcraft.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
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
import Tamaized.TamModized.particles.ParticleHelper.ParticleContructor;
import Tamaized.TamModized.particles.ParticleRegistry;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidicDrill extends VoidicPowerItem {

	public VoidicDrill(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player, EnumHand hand) {
		if (VoidicPowerItemHandler.getItemVoidicPower(stack) > 1) {
			if(!player.capabilities.isCreativeMode) VoidicPowerItemHandler.setItemVoidicPower(stack, VoidicPowerItemHandler.getItemVoidicPower(stack) - 1);
			RayTraceResult result = player.rayTrace(10, 1F);
			switch (result.sideHit) {
				case UP: // Y
					caseY(player, worldIn, stack, result.getBlockPos());
					break;
				case DOWN: // Y
					caseY(player, worldIn, stack, result.getBlockPos());
					break;
				case EAST: // X
					caseX(player, worldIn, stack, result.getBlockPos());
					break;
				case WEST: // X
					caseX(player, worldIn, stack, result.getBlockPos());
					break;
				case NORTH: // Z
					caseZ(player, worldIn, stack, result.getBlockPos());
					break;
				case SOUTH: // Z
					caseZ(player, worldIn, stack, result.getBlockPos());
					break;
				default:
					break;
			}
			//worldIn.spawnParticle(EnumParticleTypes.PORTAL, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), 0, 0, 0, new int[0]);
			Vec3d ray = player.rayTrace(10, 1.0f).hitVec;
			if(worldIn.isRemote){
				ParticleHelper.spawnParticle(new ParticleContructor(ParticleRegistry.getParticle(voidCraft.particles.drillRay), worldIn, new Vec3d(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()), ray == null ? player.getLook(1.0F).scale(10) : ray));
			}else{
				//ParticleHelper.sendPacketToClients(worldIn, voidCraft.particles.drillRay, new Vec3d(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()), player.rayTrace(10, 1.0f).hitVec, 64);
			}
			return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
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

}