package Tamaized.Voidcraft.machina.tileentity;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;
import Tamaized.Voidcraft.machina.VoidMacerator;
import Tamaized.Voidcraft.machina.addons.MaceratorRecipes;
import Tamaized.Voidcraft.power.IVoidicPower;
import Tamaized.Voidcraft.power.TileEntityVoidicPower;

public class TileEntityVoidMacerator extends TileEntityVoidicPower implements ITickable, ISidedInventory, IVoidicPower{
	
	public static final int SLOT_INPUT = 0;
	public static final int SLOT_OUTPUT = 1;
	public static final int[] SLOTS_ALL = new int[]{0, 1};
	private ItemStack[] slots  = new ItemStack[2];
	
	public int finishTick = 101;
	public int cookingTick = 0;
	
	private Item lastCookingItem = null;
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate){
	    return (oldState.getBlock() != newSate.getBlock());
	}
	
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
		this.cookingTick = nbt.getInteger("cookingTick");
	}

	@Override
	public NBTTagCompound func_189515_b(NBTTagCompound nbt){
		super.func_189515_b(nbt);
		
		nbt.setInteger("cookingTick",  this.cookingTick);
		
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
	public int getSizeInventory(){
		return this.slots.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		return this.slots[i];
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
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.slots[i] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()){
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.pos) != this ? false: entityplayer.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	@Override
	public void update(){
		super.update();
		boolean cooking = false;
		if(lastCookingItem == null || slots[SLOT_INPUT] == null || lastCookingItem != slots[SLOT_INPUT].getItem()){
			cookingTick = 0;
			lastCookingItem = (slots[SLOT_INPUT] != null) ? slots[SLOT_INPUT].getItem() : null;
		}
		
		if(voidicPower > 0 && canCook()) {
			cooking = true;
			voidicPower--;
		}
		
		if(!worldObj.isRemote){
			if(cooking){
				cookingTick++;
				if(cookingTick >= finishTick){
					cookingTick = 0;
					bakeItem();
					this.markDirty();
				}
			}
			
			IBlockState state = worldObj.getBlockState(pos);
			if(state.getBlock() instanceof VoidMacerator){
				VoidMacerator theMacerator = (VoidMacerator) state.getBlock();
				if(theMacerator != null){
					if(theMacerator.getIsActive(state) && !cooking) theMacerator.setState(false, worldObj, pos);
					if(!theMacerator.getIsActive(state) && cooking) theMacerator.setState(true, worldObj, pos);
				}
			}
		}
	}
	
	private void bakeItem() {
		if(canCook()){
			ItemStack itemstack = MaceratorRecipes.smelting().getSmeltingResult(this.slots[SLOT_INPUT]);
			if(this.slots[SLOT_OUTPUT] == null){
				this.slots[SLOT_OUTPUT] = itemstack.copy();
			}else if(this.slots[SLOT_OUTPUT].isItemEqual(itemstack)){
				this.slots[SLOT_OUTPUT].stackSize += itemstack.stackSize;
			}
			
			this.slots[SLOT_INPUT].stackSize--;
			
			if(this.slots[SLOT_INPUT].stackSize <= 0){
				this.slots[SLOT_INPUT] = null;
			}
		}
	}

	private boolean canCook() {
		if(this.slots[SLOT_INPUT] == null){
			return false;
		}else{
			ItemStack itemstack = MaceratorRecipes.smelting().getSmeltingResult(this.slots[SLOT_INPUT]);
			if(itemstack == null) return false;
			if(this.slots[SLOT_OUTPUT] == null) return true;
			if(!this.slots[SLOT_OUTPUT].isItemEqual(itemstack)) return false;
			int result = this.slots[SLOT_OUTPUT].stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i == SLOT_OUTPUT ? false : true;
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
		return "teVoidMacerator";
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
