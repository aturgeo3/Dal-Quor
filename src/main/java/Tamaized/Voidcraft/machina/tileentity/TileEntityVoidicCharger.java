package Tamaized.Voidcraft.machina.tileentity;

import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import Tamaized.TamModized.api.voidcraft.power.TileEntityVoidicPower;
import Tamaized.TamModized.api.voidcraft.power.VoidicPowerItem;
import Tamaized.TamModized.api.voidcraft.power.VoidicPowerItemHandler;

public class TileEntityVoidicCharger extends TileEntityVoidicPower implements ISidedInventory{
	
	public static final int SLOT_DEFAULT = 0;
	private ItemStack[] slots = new ItemStack[1];
	private int[] slots_all = {SLOT_DEFAULT};
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		NBTTagList list = (NBTTagList) nbt.getTag("Items");
		this.slots = new ItemStack[this.getSizeInventory()];
		if(list != null){
			for(int i = 0; i < list.tagCount(); i++){
				NBTTagCompound nbtc = (NBTTagCompound) list.getCompoundTagAt(i);
				byte b = nbtc.getByte("Slot");
				if(b >= 0 && b < this.slots.length){
					this.slots[b] = ItemStack.loadItemStackFromNBT(nbtc);
				}
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < this.slots.length; i++){
			if(this.slots[i] != null){
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
	public int getMaxPower() {
		return 200000;
	}

	@Override
	public int maxPowerTransfer() {
		return 600;
	}

	@Override
	public boolean canOutputPower(EnumFacing face) {
		return false;
	}

	@Override
	public boolean canInputPower(EnumFacing face) {
		return face == EnumFacing.DOWN;
	}

	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return slots[index];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(this.slots[i] != null){
			ItemStack itemstack;
			if(this.slots[i].stackSize <= j){
				itemstack = this.slots[i];
				this.slots[i] = null;
				return itemstack;
			}else{
				itemstack = this.slots[i].splitStack(j);
				if(this.slots[i].stackSize == 0) {
					this.slots[i] = null;
				}
				return itemstack;
			}
		}
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int i) {
		if(this.slots[i] != null){
			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		this.slots[i] = stack;
		if(stack != null && stack.stackSize > this.getInventoryStackLimit()){
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(pos) != this ? false: player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return i == SLOT_DEFAULT ?  stack.getItem() instanceof VoidicPowerItem : false;
	}

	@Override
	public int getField(int id) {
		switch(id){
			default:
				return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch(id){
			default:
				break;
		}
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for(int i=0; i<slots.length; i++) slots[i] = null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return slots_all;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return Arrays.asList(getSlotsForFace(direction)).contains(index) ? isItemValidForSlot(index, itemStackIn) : false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if(Arrays.asList(getSlotsForFace(direction)).contains(index)){
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		super.update();
		if(voidicPower > 0 && slots[SLOT_DEFAULT] != null && slots[SLOT_DEFAULT].getItem() instanceof VoidicPowerItem && VoidicPowerItemHandler.getItemVoidicPowerPerc(slots[SLOT_DEFAULT]) < 1.0f){
			int amount = voidicPower >= maxPowerTransfer() ? maxPowerTransfer() : voidicPower;
			int overflow = VoidicPowerItemHandler.fillItemVoidicPower(slots[SLOT_DEFAULT], amount);
			voidicPower-=(amount-overflow);
		}
	}

}
