package tamaized.voidcraft.common.machina.tileentity;

import tamaized.tammodized.common.tileentity.TamTileEntityInventory;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.voidicpower.TileEntityVoidicPowerInventory;
import tamaized.voidcraft.common.machina.VoidMacerator;
import tamaized.voidcraft.common.machina.addons.TERecipesBlastFurnace.BlastFurnaceRecipe;
import tamaized.voidcraft.registry.VoidCraftBlocks;
import tamaized.voidcraft.registry.VoidCraftItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import javax.annotation.Nullable;

public class TileEntityVoidBlastFurnace extends TileEntityVoidicPowerInventory {

	public TamTileEntityInventory.ItemStackFilterHandler SLOT_INPUT_IRON;
	public TamTileEntityInventory.ItemStackFilterHandler SLOT_INPUT_COAL;
	public TamTileEntityInventory.ItemStackFilterHandler SLOT_OUTPUT;
	public int cookingTick = 0;
	public int finishTick = 0;
	private boolean cooking = false;
	private BlastFurnaceRecipe recipe;

	private Item[] lastItem = new Item[2];

	public TileEntityVoidBlastFurnace() {
		super();
	}

	@Override
	protected ItemStackHandler[] register() {
		return new ItemStackHandler[]{SLOT_INPUT_IRON = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[]{new ItemStack(VoidCraftItems.ironDust)}, true, new ItemStack[0], false), SLOT_INPUT_COAL = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[]{new ItemStack(VoidCraftItems.coalDust)}, true, new ItemStack[0], false), SLOT_OUTPUT = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[0], false, new ItemStack[0], true)};
	}

	@Nullable
	@Override
	protected IItemHandler getCap(EnumFacing face) {
		return new CombinedInvWrapper(SLOT_INPUT_COAL, SLOT_INPUT_IRON, SLOT_OUTPUT);
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

		doLastItemChecks();

		if (voidicPower > 0 && canCook()) {
			cooking = true;
			voidicPower--;
		} else {
			cooking = false;
		}

		if (!world.isRemote) {
			if (cooking) {
				cookingTick++;
				if (cookingTick >= (finishTick = recipe.getRequiredPower())) {
					cookingTick = 0;
					bakeItem();
					markDirty();
				}
			}

			IBlockState state = world.getBlockState(pos);
			if (state.getBlock() instanceof VoidMacerator) {
				VoidMacerator theMacerator = (VoidMacerator) state.getBlock();
				if (theMacerator.getIsActive(state) && !cooking)
					VoidMacerator.setState(false, world, pos);
				if (!theMacerator.getIsActive(state) && cooking)
					VoidMacerator.setState(true, world, pos);
			}
		}
	}

	private void doLastItemChecks() {
		TamTileEntityInventory.ItemStackFilterHandler[] slots = new TamTileEntityInventory.ItemStackFilterHandler[]{SLOT_INPUT_IRON, SLOT_INPUT_COAL};
		for (int i = 0; i < 2; i++) {
			if (lastItem[i] == null || slots[i].getStackInSlot(0).isEmpty() || lastItem[i] != slots[i].getStackInSlot(0).getItem()) {
				cookingTick = 0;
				lastItem[i] = (!slots[i].getStackInSlot(0).isEmpty()) ? slots[i].getStackInSlot(0).getItem() : null;
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

			SLOT_INPUT_COAL.getStackInSlot(0).shrink(1);
			SLOT_INPUT_IRON.getStackInSlot(0).shrink(1);

			if (SLOT_INPUT_COAL.getStackInSlot(0).getCount() <= 0) {
				SLOT_INPUT_COAL.setStackInSlot(0, ItemStack.EMPTY);
			}

			if (SLOT_INPUT_IRON.getStackInSlot(0).getCount() <= 0) {
				SLOT_INPUT_IRON.setStackInSlot(0, ItemStack.EMPTY);
			}
		}
	}

	private boolean canCook() {
		if (SLOT_INPUT_IRON.getStackInSlot(0).isEmpty() || SLOT_INPUT_COAL.getStackInSlot(0).isEmpty() || world.getBlockState(getPos().down()) == null || world.getBlockState(getPos().down()).getBlock() != VoidCraftBlocks.blockVoidFire)
			return false;
		recipe = VoidCraft.teRecipes.blastFurnace.getRecipe(new ItemStack[]{SLOT_INPUT_IRON.getStackInSlot(0), SLOT_INPUT_COAL.getStackInSlot(0)});
		if (recipe == null)
			return false;
		if (SLOT_OUTPUT.getStackInSlot(0).isEmpty())
			return true;
		if (!SLOT_OUTPUT.getStackInSlot(0).isItemEqual(recipe.getOutput()))
			return false;
		int result = SLOT_OUTPUT.getStackInSlot(0).getCount() + recipe.getOutput().getCount();
		return (result <= 64 && result <= recipe.getOutput().getMaxStackSize());
	}

	public boolean isCooking() {
		return cooking;
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
