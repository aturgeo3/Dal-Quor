package Tamaized.Voidcraft.machina.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import Tamaized.TamModized.api.voidcraft.power.TileEntityVoidicPower;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.VoidMacerator;

public class TileEntityRealityStabilizer extends TileEntityVoidicPower implements ISidedInventory {

	public static final int SLOT_OUTPUT = 0;
	public static final int[] SLOTS_ALL = new int[] { 0 };
	private ItemStack[] slots = new ItemStack[1];

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

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
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

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
	public int getSizeInventory() {
		return this.slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.slots[i];
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
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.pos) != this ? false : entityplayer.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void update() {
		super.update();
		if (!worldObj.isRemote) {
			if (slots[SLOT_OUTPUT] == null || slots[SLOT_OUTPUT].stackSize < slots[SLOT_OUTPUT].getMaxStackSize()) pickupNextBlock();
		}
	}

	public int requiredPower() {
		return 2000;
	}

	private void usePower() {
		if (hasEnoughPower()) voidicPower -= requiredPower();
	}

	private boolean hasEnoughPower() {
		return voidicPower >= requiredPower();
	}

	private void pickupNextBlock() {
		if (hasEnoughPower() && (slots[SLOT_OUTPUT] == null || (slots[SLOT_OUTPUT].getItem() == Item.getItemFromBlock(voidCraft.blocks.realityHole) && slots[SLOT_OUTPUT].stackSize < slots[SLOT_OUTPUT].getMaxStackSize()))) {
			BlockPosWrapper wrapper = searchState(voidCraft.blocks.realityHole, getPos(), 4);
			if(wrapper.state == null) return;
			worldObj.setBlockToAir(wrapper.pos);
			usePower();
			if (slots[SLOT_OUTPUT] == null) {
				slots[SLOT_OUTPUT] = new ItemStack(voidCraft.blocks.realityHole);
			} else {
				slots[SLOT_OUTPUT].stackSize++;
			}
		}
	}
	
	private BlockPosWrapper searchState(Block type, BlockPos origin, int radius){
		for (int x = -radius; x <= radius; x++) {
			for (int z = -radius; z <= radius; z++) {
				for (int y = radius; y >= -radius; y--) {
					IBlockState state = worldObj.getBlockState(origin.add(x, y, z));
					if (state != null && state.getBlock() == voidCraft.blocks.realityHole) {
						return new BlockPosWrapper(origin.add(x, y, z), state);
					}
				}
			}
		}
		return new BlockPosWrapper(origin, null);
	}
	
	private class BlockPosWrapper {
		
		public final BlockPos pos;
		public final IBlockState state;
		
		public BlockPosWrapper(BlockPos p, IBlockState s){
			pos = p;
			state = s;
		}
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return SLOTS_ALL;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, EnumFacing j) {
		return this.isItemValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, EnumFacing j) {
		return i == SLOT_OUTPUT;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {

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
		return "teRealityStabilizer";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
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

}
