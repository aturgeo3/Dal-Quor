package Tamaized.Voidcraft.machina.tileentity;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.api.voidicpower.TileEntityVoidicPower;
import Tamaized.Voidcraft.machina.VoidMacerator;
import Tamaized.Voidcraft.machina.addons.TERecipesAlchemy.AlchemyRecipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;

public class TileEntityVoidicAlchemy extends TileEntityVoidicPower implements ISidedInventory {

	public static final int SLOT_OUTPUT = 0;
	public static final int SLOT_INPUT_1 = 1;
	public static final int SLOT_INPUT_2 = 2;
	public static final int SLOT_INPUT_3 = 3;
	public static final int SLOT_INPUT_4 = 4;
	public static final int SLOT_INPUT_5 = 5;
	public static final int SLOT_INPUT_6 = 6;
	public static final int[] SLOTS_ALL = new int[] { SLOT_OUTPUT, SLOT_INPUT_1, SLOT_INPUT_2, SLOT_INPUT_3, SLOT_INPUT_4, SLOT_INPUT_5, SLOT_INPUT_6 };
	private ItemStack[] slots = new ItemStack[SLOTS_ALL.length+1];
	private Item[] lastItem = new Item[SLOTS_ALL.length+1];

	public int cookingTick = 0;
	public int finishTick = 0;
	private AlchemyRecipe recipe;

	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return index > slots.length ? null : slots[index];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.slots[i] != null) {
			ItemStack itemstack;

			if (this.slots[i].stackSize <= j) {

				itemstack = this.slots[i];
				this.slots[i] = null;

				return itemstack;

			} else {
				itemstack = this.slots[i].splitStack(j);

				if (this.slots[i].stackSize == 0) {
					this.slots[i] = null;
				}

				return itemstack;
			}
		}

		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int i) {
		if (this.slots[i] != null) {
			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.slots[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return index == SLOT_OUTPUT ? false : true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {

	}

	@Override
	public String getName() {
		return "teVoidicAlchemy";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return SLOTS_ALL;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemstack, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemstack);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == SLOT_OUTPUT;
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
		return face != EnumFacing.UP;
	}

	@Override
	protected void readNBT(NBTTagCompound nbt) {
		NBTTagList list = (NBTTagList) nbt.getTag("Items");
		this.slots = new ItemStack[this.getSizeInventory()];
		if (list != null) {
			for (int i = 0; i < list.tagCount(); i++) {
				NBTTagCompound nbtc = (NBTTagCompound) list.getCompoundTagAt(i);
				byte b = nbtc.getByte("Slot");

				if (b >= 0 && b < this.slots.length) {
					this.slots[b] = ItemStack.loadItemStackFromNBT(nbtc);
				}
			}
		}
		this.cookingTick = nbt.getInteger("cookingTick");
	}

	@Override
	protected NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("cookingTick", this.cookingTick);

		NBTTagList list = new NBTTagList();

		for (int i = 0; i < this.slots.length; i++) {
			if (this.slots[i] != null) {
				NBTTagCompound nbtc = new NBTTagCompound();
				nbtc.setByte("Slot", (byte) i);
				this.slots[i].writeToNBT(nbtc);
				list.appendTag(nbtc);
			}
		}

		nbt.setTag("Items", list);

		return nbt;
	}

	@Override
	protected void onUpdate() {
		boolean cooking = false;

		doLastItemChecks();

		if (voidicPower > 0 && canCook()) {
			cooking = true;
			voidicPower--;
		}

		if (!worldObj.isRemote) {
			if (cooking) {
				cookingTick++;
				if (cookingTick >= (finishTick = recipe.getRequiredPower())) {
					cookingTick = 0;
					bakeItem();
					this.markDirty();
				}
			}

			IBlockState state = worldObj.getBlockState(pos);
			if (state.getBlock() instanceof VoidMacerator) {
				VoidMacerator theMacerator = (VoidMacerator) state.getBlock();
				if (theMacerator != null) {
					if (theMacerator.getIsActive(state) && !cooking) theMacerator.setState(false, worldObj, pos);
					if (!theMacerator.getIsActive(state) && cooking) theMacerator.setState(true, worldObj, pos);
				}
			}
		}
	}

	private void doLastItemChecks() {
		for (int i = SLOT_INPUT_1; i <= SLOT_INPUT_6; i++) {
			if (lastItem[i] == null || slots[i] == null || lastItem[i] != slots[i].getItem()) {
				cookingTick = 0;
				lastItem[i] = (slots[i] != null) ? slots[i].getItem() : null;
			}
		}
	}

	private boolean canCook() {
		for (int i = SLOT_INPUT_1; i <= SLOT_INPUT_6; i++) {
			if (this.slots[i] == null) {
				return false;
			}
		}
		recipe = voidCraft.teRecipes.alchemy.getRecipe(new ItemStack[] { slots[SLOT_INPUT_1], slots[SLOT_INPUT_2], slots[SLOT_INPUT_3], slots[SLOT_INPUT_4], slots[SLOT_INPUT_5], slots[SLOT_INPUT_6] });
		if (recipe == null) return false;
		if (this.slots[SLOT_OUTPUT] == null) return true;
		if (!this.slots[SLOT_OUTPUT].isItemEqual(recipe.getOutput())) return false;
		int result = this.slots[SLOT_OUTPUT].stackSize + recipe.getOutput().stackSize;
		return (result <= getInventoryStackLimit() && result <= recipe.getOutput().getMaxStackSize());
	}

	private void bakeItem() {
		if (canCook()) {
			if (this.slots[SLOT_OUTPUT] == null) {
				this.slots[SLOT_OUTPUT] = recipe.getOutput().copy();
			} else if (this.slots[SLOT_OUTPUT].isItemEqual(recipe.getOutput())) {
				this.slots[SLOT_OUTPUT].stackSize += recipe.getOutput().stackSize;
			}

			for (int i = SLOT_INPUT_1; i <= SLOT_INPUT_6; i++) {
				this.slots[i].stackSize--;
				if (this.slots[i].stackSize <= 0) {
					this.slots[i] = null;
				}
			}
		}
	}

}
