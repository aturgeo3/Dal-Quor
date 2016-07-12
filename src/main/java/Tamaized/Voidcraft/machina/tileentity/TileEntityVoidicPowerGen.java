package Tamaized.Voidcraft.machina.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import Tamaized.TamModized.api.voidcraft.power.TileEntityVoidicPower;
import Tamaized.TamModized.api.voidcraft.power.VoidicPowerHandler;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.addons.VoidTank;

public class TileEntityVoidicPowerGen extends TileEntityVoidicPower implements ISidedInventory, IFluidHandler{
	
	public static final int SLOT_DEFAULT = 0;
	private ItemStack[] slots = new ItemStack[1];
	private int[] slots_all = {SLOT_DEFAULT};
	
	private VoidTank tank;
	private int useAmount = 1;
	private int genAmount = 2;
	private int rate = 1;
	
	public TileEntityVoidicPowerGen(){
		tank = new VoidTank(this, 5000);
	}

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
		
		tank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, nbt.getInteger("fluidAmount")));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		
		nbt.setInteger("fluidAmount",  tank.getFluidAmount());
		
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
	public void update() {
		super.update();
		rate = 10;
		int gen = genAmount*rate;
		int use = useAmount*rate;
		if(getFluidAmount() <= getMaxFluidAmount() - 1000){
			if(slots[SLOT_DEFAULT] != null && slots[SLOT_DEFAULT].isItemEqual(voidCraft.fluids.getBucket())){
				fill(new FluidStack(voidCraft.fluids.voidFluid, 1000), true);
				slots[SLOT_DEFAULT] = new ItemStack(Items.BUCKET);
			}
		}
		if(getFluidAmount() >= use && voidicPower <= getMaxPower()-gen){
			drain(new FluidStack(voidCraft.fluids.voidFluid, use), true);
			voidicPower+=gen;
		}
		VoidicPowerHandler.sendToSurrounding(this, worldObj, pos);
	}

	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return slots[i];
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
		return i == SLOT_DEFAULT ?  stack.getItem() == voidCraft.fluids.getBucket().getItem() : false;
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
		return "voidicPowerGen";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString("voidicPowerGen");
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return slots_all;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		switch(index){
			case SLOT_DEFAULT:
				return stack.getItem() == Items.BUCKET;
			default:
				return true;
		}
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
	
	public int getFluidAmount(){
		return tank.getFluidAmount();
	}
	
	public int getMaxFluidAmount(){
		return tank.getCapacity();
	}
	
	public void setFluidAmount(int amount){
		tank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, amount > tank.getCapacity() ? tank.getCapacity() : amount));
	}

	@Override
	public int getMaxPower() {
		return 100000;
	}

	@Override
	public int maxPowerTransfer() {
		return 160;
	}

	@Override
	public boolean canOutputPower(EnumFacing face) {
		return true;
	}

	@Override
	public boolean canInputPower(EnumFacing face) {
		return false;
	}

}
