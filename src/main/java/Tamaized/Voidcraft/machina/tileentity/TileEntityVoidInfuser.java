package Tamaized.Voidcraft.machina.tileentity;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import Tamaized.TamModized.tileentity.TamTileEntityInventory;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.addons.VoidTank;

public class TileEntityVoidInfuser extends TamTileEntityInventory implements IFluidHandler {

	public VoidTank tank;

	public static final int SLOT_BUCKET = 0;
	public static final int SLOT_INPUT = 1;
	public static final int SLOT_OUTPUT = 2;
	public static final int[] SLOTS_ALL = new int[] { 0, 1, 2 };

	public int finishTick = 0;
	public int cookingTick = 0;

	private Item lastCookingItem = null;

	public TileEntityVoidInfuser() {
		super(3);
		tank = new VoidTank(this, 3000);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (tank.getFluid() != null) this.tank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, nbt.getInteger("fluidAmount")));
		this.cookingTick = nbt.getInteger("cookingTick");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("fluidAmount", tank.getFluidAmount());
		nbt.setInteger("cookingTick", this.cookingTick);
		return nbt;
	}

	@Override
	public void update() {
		super.update();
		boolean cooking = false;
		if (lastCookingItem == null || slots[SLOT_INPUT] == null || lastCookingItem != slots[SLOT_INPUT].getItem()) {
			cookingTick = 0;
			lastCookingItem = (slots[SLOT_INPUT] != null) ? slots[SLOT_INPUT].getItem() : null;
		}

		if (tank.getFluidAmount() > 0 && canCook()) {
			cooking = true;
			drain(new FluidStack(voidCraft.fluids.voidFluid, 1), true);
		}

		if (getFluidAmount() <= getMaxFluidAmount() - 1000) {
			if (slots[SLOT_BUCKET] != null && slots[SLOT_BUCKET].isItemEqual(voidCraft.fluids.getBucket())) {
				fill(new FluidStack(voidCraft.fluids.voidFluid, 1000), true);
				slots[SLOT_BUCKET] = new ItemStack(Items.BUCKET);
			}
		}

		if (cooking) {
			cookingTick++;
			if (cookingTick >= (finishTick = voidCraft.teRecipes.infuser.getOutput(slots[SLOT_INPUT]).getRequiredFluid())) {
				cookingTick = 0;
				bakeItem();
				this.markDirty();
			}
		}
	}

	private void bakeItem() {
		if (canCook()) {
			ItemStack itemstack = voidCraft.teRecipes.infuser.getResultItem(this.slots[SLOT_INPUT]);
			if (this.slots[SLOT_OUTPUT] == null) {
				this.slots[SLOT_OUTPUT] = itemstack.copy();
			} else if (this.slots[SLOT_OUTPUT].isItemEqual(itemstack)) {
				this.slots[SLOT_OUTPUT].stackSize += itemstack.stackSize;
			}

			this.slots[SLOT_INPUT].stackSize--;

			if (this.slots[SLOT_INPUT].stackSize <= 0) {
				this.slots[SLOT_INPUT] = null;
			}
		}
	}

	private boolean canCook() {
		if (this.slots[SLOT_INPUT] == null) {
			return false;
		} else {
			ItemStack itemstack = voidCraft.teRecipes.infuser.getResultItem(this.slots[SLOT_INPUT]);
			if (itemstack == null) return false;
			if (this.slots[SLOT_OUTPUT] == null) return true;
			if (!this.slots[SLOT_OUTPUT].isItemEqual(itemstack)) return false;
			int result = this.slots[SLOT_OUTPUT].stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}

	public static boolean isItemFuel(ItemStack stack) {
		return stack.isItemEqual(voidCraft.fluids.getBucket());
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i != SLOT_OUTPUT ? i == SLOT_BUCKET ? isItemFuel(itemstack) : !itemstack.isItemEqual(voidCraft.fluids.getBucket()) : false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing var1) {
		return SLOTS_ALL;
	}

	@Override
	protected boolean canExtractSlot(int i) {
		return i == SLOT_BUCKET ? slots[SLOT_BUCKET] != null ? slots[SLOT_BUCKET].isItemEqual(voidCraft.fluids.getBucket()) : false : i == SLOT_OUTPUT;
	}

	@Override
	protected boolean canInsertSlot(int i) {
		return i != SLOT_OUTPUT;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public String getName() {
		return "voidInfuser";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return tank.getTankProperties();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		return tank.drain(resource, doDrain);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	public int getFluidAmount() {
		return tank.getFluidAmount();
	}

	public int getMaxFluidAmount() {
		return tank.getCapacity();
	}

	public void setFluidAmount(int amount) {
		tank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, amount > tank.getCapacity() ? tank.getCapacity() : amount));
	}
}