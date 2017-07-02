package tamaized.voidcraft.common.machina.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import tamaized.tammodized.common.particles.network.ParticleFluffPacketHandler;
import tamaized.tammodized.common.tileentity.TamTileEntityInventory;
import tamaized.voidcraft.common.machina.VoidMacerator;
import tamaized.voidcraft.common.machina.addons.TERecipesMacerator.MaceratorRecipe;
import tamaized.voidcraft.common.voidicpower.TileEntityVoidicPowerInventory;
import tamaized.voidcraft.registry.VoidCraftTERecipes;

import javax.annotation.Nullable;

public class TileEntityVoidMacerator extends TileEntityVoidicPowerInventory {

	public TamTileEntityInventory.ItemStackFilterHandler SLOT_INPUT;
	public TamTileEntityInventory.ItemStackFilterHandler SLOT_OUTPUT;

	public int cookingTick = 0;
	public int finishTick = 0;
	private MaceratorRecipe recipe;

	private Item lastCookingItem = null;

	public TileEntityVoidMacerator() {
		super();
	}

	@Override
	protected ItemStackHandler[] register() {
		return new ItemStackHandler[]{SLOT_INPUT = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[0], true, new ItemStack[0], false), SLOT_OUTPUT = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[0], false, new ItemStack[0], true)};
	}

	@Nullable
	@Override
	protected IItemHandler getCap(EnumFacing face) {
		return new CombinedInvWrapper(SLOT_INPUT, SLOT_OUTPUT);
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return (oldState.getBlock() != newSate.getBlock());
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
		cookingTick = nbt.getInteger("cookingTick");
	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("cookingTick", cookingTick);
		return nbt;
	}

	@Override
	public void onUpdate() {
		boolean cooking = false;
		if (lastCookingItem == null || SLOT_INPUT.getStackInSlot(0).isEmpty() || lastCookingItem != SLOT_INPUT.getStackInSlot(0).getItem()) {
			cookingTick = 0;
			lastCookingItem = (!SLOT_INPUT.getStackInSlot(0).isEmpty()) ? SLOT_INPUT.getStackInSlot(0).getItem() : null;
		}

		if (voidicPower > 0 && canCook()) {
			cooking = true;
			voidicPower--;
		}

		if (!world.isRemote) {
			if (cooking) {
				cookingTick++;
				if (cookingTick % 10 == 0)
					ParticleFluffPacketHandler.spawnOnServer(world, new Vec3d(pos.getX() + 0.25F + (world.rand.nextFloat() * 0.5F), pos.getY() + 0.5F, pos.getZ() + 0.25F + (world.rand.nextFloat() * 0.5F)), new Vec3d(0, 0, 0), world.rand.nextInt(20 * 4) + 20, -((world.rand.nextFloat() * 0.02F) + 0.01F), (world.rand.nextFloat() * 0.75F) + 0.25F, 0x7700FFFF);
				if (cookingTick >= (finishTick = recipe.getRequiredPower())) {
					cookingTick = 0;
					bakeItem();
					markDirty();
				}
			}

			IBlockState state = world.getBlockState(pos);
			if (state.getBlock() instanceof VoidMacerator) {
				VoidMacerator theMacerator = (VoidMacerator) state.getBlock();
				if (theMacerator != null) {
					if (theMacerator.getIsActive(state) && !cooking)
						VoidMacerator.setState(false, world, pos);
					if (!theMacerator.getIsActive(state) && cooking)
						VoidMacerator.setState(true, world, pos);
				}
			}
		}
	}

	private void bakeItem() {
		if (canCook()) {
			if (SLOT_OUTPUT.getStackInSlot(0).isEmpty()) {
				SLOT_OUTPUT.setStackInSlot(0, recipe.getOutput().copy());
			} else if (SLOT_OUTPUT.getStackInSlot(0).isItemEqual(recipe.getOutput())) {
				SLOT_OUTPUT.getStackInSlot(0).grow(recipe.getOutput().getCount());
			}

			SLOT_INPUT.getStackInSlot(0).shrink(1);

			if (SLOT_INPUT.getStackInSlot(0).getCount() <= 0) {
				SLOT_INPUT.setStackInSlot(0, ItemStack.EMPTY);
			}
		}
	}

	private boolean canCook() {
		if (SLOT_INPUT.getStackInSlot(0).isEmpty())
			return false;
		recipe = VoidCraftTERecipes.macerator.getRecipe(new ItemStack[]{SLOT_INPUT.getStackInSlot(0)});
		if (recipe == null)
			return false;
		if (SLOT_OUTPUT.getStackInSlot(0).isEmpty())
			return true;
		if (!SLOT_OUTPUT.getStackInSlot(0).isItemEqual(recipe.getOutput()))
			return false;
		int result = SLOT_OUTPUT.getStackInSlot(0).getCount() + recipe.getOutput().getCount();
		return (result <= 64 && result <= recipe.getOutput().getMaxStackSize());
	}

	@Override
	public int getMaxPower() {
		return 50000;
	}

	@Override
	public int maxPowerTransfer() {
		return 160;
	}

	@Override
	public boolean canOutputPower(EnumFacing face) {
		return false;
	}

	@Override
	public boolean canInputPower(EnumFacing face) {
		return true;
	}

}
