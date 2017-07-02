package tamaized.voidcraft.common.machina.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import tamaized.tammodized.common.particles.network.ParticleFluffPacketHandler;
import tamaized.tammodized.common.tileentity.TamTileEntityInventory;
import tamaized.voidcraft.common.voidicpower.TileEntityVoidicPowerInventory;
import tamaized.voidcraft.registry.VoidCraftBlocks;

import javax.annotation.Nullable;

public class TileEntityRealityStabilizer extends TileEntityVoidicPowerInventory {

	public TamTileEntityInventory.ItemStackFilterHandler SLOT_OUTPUT;

	public TileEntityRealityStabilizer() {
		super();
	}

	@Override
	protected ItemStackHandler[] register() {
		return new ItemStackHandler[]{SLOT_OUTPUT = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[0], false, new ItemStack[0], true)};
	}

	@Nullable
	@Override
	protected IItemHandler getCap(EnumFacing face) {
		return SLOT_OUTPUT;
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {

	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		return nbt;
	}

	@Override
	public void onUpdate() {
		if (!world.isRemote) {
			if (SLOT_OUTPUT.getStackInSlot(0).isEmpty() || SLOT_OUTPUT.getStackInSlot(0).getCount() < SLOT_OUTPUT.getStackInSlot(0).getMaxStackSize())
				pickupNextBlock();
		}
	}

	public int requiredPower() {
		return 2000;
	}

	private void usePower() {
		if (hasEnoughPower())
			voidicPower -= requiredPower();
	}

	private boolean hasEnoughPower() {
		return voidicPower >= requiredPower();
	}

	private void pickupNextBlock() {
		if (hasEnoughPower() && (SLOT_OUTPUT.getStackInSlot(0).isEmpty() || (SLOT_OUTPUT.getStackInSlot(0).getItem() == Item.getItemFromBlock(VoidCraftBlocks.realityHole) && SLOT_OUTPUT.getStackInSlot(0).getCount() < SLOT_OUTPUT.getStackInSlot(0).getMaxStackSize()))) {
			BlockPosWrapper wrapper = searchState(VoidCraftBlocks.realityHole, getPos(), 4);
			if (wrapper.state == null)
				return;
			world.setBlockToAir(wrapper.pos);
			for (float xf = 0.0F; xf <= 1.0F; xf += 0.25F)
				for (float zf = 0.0F; zf <= 1.0F; zf += 0.25F)
					for (float yf = 0.0F; yf <= 1.0F; yf += 0.25F)
						ParticleFluffPacketHandler.spawnOnServer(world, new Vec3d(wrapper.pos.getX() + xf, wrapper.pos.getY() + yf, wrapper.pos.getZ() + zf), new Vec3d(0, 0, 0), world.rand.nextInt(20 * 2) + 20, -((world.rand.nextFloat() * 0.02F) + 0.01F), (world.rand.nextFloat() * 0.50F) + 0.25F, 0xFFFFFFFF);
			usePower();
			if (SLOT_OUTPUT.getStackInSlot(0).isEmpty()) {
				SLOT_OUTPUT.setStackInSlot(0, new ItemStack(VoidCraftBlocks.realityHole));
			} else {
				SLOT_OUTPUT.getStackInSlot(0).grow(1);
			}
		}
	}

	private BlockPosWrapper searchState(Block type, BlockPos origin, int radius) {
		for (int x = -radius; x <= radius; x++) {
			for (int z = -radius; z <= radius; z++) {
				for (int y = radius; y >= -radius; y--) {
					IBlockState state = world.getBlockState(origin.add(x, y, z));
					if (state != null && state.getBlock() == VoidCraftBlocks.realityHole) {
						return new BlockPosWrapper(origin.add(x, y, z), state);
					}
				}
			}
		}
		return new BlockPosWrapper(origin, null);
	}

	@Override
	public int getMaxPower() {
		return 50000;
	}

	@Override
	public int maxPowerTransfer() {
		return 320;
	}

	@Override
	public boolean canOutputPower(EnumFacing face) {
		return false;
	}

	@Override
	public boolean canInputPower(EnumFacing face) {
		return true;
	}

	private class BlockPosWrapper {

		public final BlockPos pos;
		public final IBlockState state;

		public BlockPosWrapper(BlockPos p, IBlockState s) {
			pos = p;
			state = s;
		}
	}

}
