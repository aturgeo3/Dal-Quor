package Tamaized.Voidcraft.machina.tileentity;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;
import Tamaized.Voidcraft.machina.addons.InfuserRecipes;
import Tamaized.Voidcraft.machina.addons.VoidTank;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class TileEntityVoidInfuser extends TileEntity implements ISidedInventory, IFluidHandler{
	
	private String localizedName;
	
	public VoidTank voidTank;
	
	private static final int[] slots_top = new int[]{0};
	private static final int[] slots_bottom = new int[]{2, 1};
	private static final int[] slots_sides = new int[]{1};
	private static final int[] slots_all = new int[]{0,1,2};
	
	private ItemStack[] slots  = new ItemStack[3];//Amount of Slots
	
	public int furnaceSpeed = 801;
	public int burnTime;
	public int currentItemBurnTime;
	public int cookTime;
	
	public TileEntityVoidInfuser(){
		super();
		
		voidTank = new VoidTank(this, 3000);
		
	}
	
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		
		NBTTagList list = (NBTTagList) nbt.getTag("Items");
		this.slots = new ItemStack[this.getSizeInventory()];
		
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound nbtc = (NBTTagCompound) list.getCompoundTagAt(i);
			byte b = nbtc.getByte("Slot");
			
			if(b >= 0 && b < this.slots.length){
				this.slots[b] = ItemStack.loadItemStackFromNBT(nbtc);
			}
		}
		
		this.burnTime = nbt.getInteger("burnTime");
		if(voidTank.getFluid() != null) this.voidTank.getFluid().amount = this.burnTime;
		this.cookTime = nbt.getInteger("cookTime");
		this.currentItemBurnTime = getItemBurnTime(this.slots[1]);
		
		if(nbt.hasKey("CustomName")){
			this.localizedName = nbt.getString("CustomName");
		}
	}
	
	public int getSizeInventory(){
		return this.slots.length;
	}
	
	public String getInvName(){
		return this.isInvNameLocalized() ? this.localizedName : "container.voidInfuser";
	}
	
	public boolean isInvNameLocalized() {
		return this.localizedName != null && this.localizedName.length() > 0;
	}
	
	public void setGuiDisplayName(String displayName) {
		this.localizedName = displayName;
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
	public ItemStack getStackInSlotOnClosing(int i) {
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
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false: entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	
	
	public void updateEntity(){
		
		
		if(this.burnTime > 3000) this.currentItemBurnTime = this.burnTime = 3000;
		if(this.burnTime < 0) this.currentItemBurnTime = this.burnTime = 0;
		
		
		boolean flag = this.cookTime > 0;
		boolean flag1 = false;
		
		if(this.burnTime > 0 && this.cookTime > 0) {
			this.burnTime --;
			voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime));
		}
		
		if(!this.worldObj.isRemote){
			if(this.burnTime < 2001){
				
				if(this.burnTime > 3000) this.currentItemBurnTime = this.burnTime = 3000;
				if(getItemBurnTime(this.slots[1]) > 0){
					flag1 = true;
					
					fill(ForgeDirection.NORTH, new FluidStack(voidCraft.fluidVoid, getItemBurnTime(this.slots[1])), true);
					this.currentItemBurnTime = this.burnTime = voidTank.getFluidAmount();
					
					if(this.slots[1] != null){
						this.slots[1].stackSize--;
						this.slots[1] = new ItemStack(Items.bucket);
						
						if(this.slots[1].stackSize == 0){
							this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);
						}
					}
				}
			}
			
			if(this.isBurning() && this.canSmelt()){
				this.cookTime++;
				
				if(this.cookTime == this.furnaceSpeed){
					this.cookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
				
			}else{
				this.cookTime = 0;
			}
			
			if(flag != this.cookTime > 0){
				flag1 = true;
				//VoidMacerator.updateBlockState(this.cookTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
			
			
		}
		
		if(flag1){
			this.markDirty();
		}
		
		NBTTagCompound znbt = new NBTTagCompound();
		this.writeToNBT(znbt);
		
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
	    try {
	    	outputStream.writeInt(VoidCraftClientPacketHandler.TYPE_TE_UPDATE);
	        outputStream.writeInt(this.xCoord);
	        outputStream.writeInt(this.yCoord);
	        outputStream.writeInt(this.zCoord);
	        outputStream.writeInt(this.burnTime);
	        outputStream.writeInt(this.cookTime);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	               
	    FMLProxyPacket packet = new FMLProxyPacket(bos.buffer(), voidCraft.networkChannelName);

	    TargetPoint point = new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 10.0D);
	    
	    //if(voidCraft.channel != null && packet != null && point != null) voidCraft.channel.sendToAllAround(packet, point);
	    this.getDescriptionPacket();
		 try {
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		

	 NBTTagCompound nbt = new NBTTagCompound();
	 this.writeToNBT(nbt);
	 
	 nbt.setInteger("burnTime",  this.burnTime);
		nbt.setInteger("cookTime",  this.cookTime);
		//nbt.setShort("currentItemBurnTime", (short) this.currentItemBurnTime);
		
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
		
		if(this.isInvNameLocalized()){
			nbt.setString("CustomName", this.localizedName);
		}
		
	 return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, nbt);
	}
		
	@Override
	public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
	{
	 readFromNBT(packet.func_148857_g());
	}

	private void smeltItem() {
		if(this.canSmelt()){
			ItemStack itemstack = InfuserRecipes.smelting().getSmeltingResult(this.slots[0]);
		
			if(this.slots[2] == null){
				this.slots[2] = itemstack.copy();
				this.slots[2].stackSize=1;
			}else if(this.slots[2].isItemEqual(itemstack)){
				this.slots[2].stackSize += itemstack.stackSize + 7;
			}
			
			this.slots[0].stackSize--;
			
			if(this.slots[0].stackSize <= 0){
				this.slots[0] = null;
			}
			
		}
	}

	private boolean canSmelt() {
		if(this.slots[0] == null){
			return false;
		}else{
				ItemStack itemstack = InfuserRecipes.smelting().getSmeltingResult(this.slots[0]); //result
				if(itemstack == null) return false;
				if(this.slots[2] == null) return true;
				if(!this.slots[2].isItemEqual(itemstack)) return false;
				
				int result = this.slots[2].stackSize + itemstack.stackSize;
				
				return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
				
		}
		
	}

	public boolean isBurning() {
		return this.burnTime > 0;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i == 2 ? false : (i == 1 ? isItemFuel(itemstack) : true);
	}

	public static boolean isItemFuel(ItemStack itemstack) {
		return getItemBurnTime(itemstack) > 0;
	}

	public static int getItemBurnTime(ItemStack itemstack) {
		if(itemstack == null){
			return 0;
		}else{
			ItemStack i = itemstack;
			Item item  = itemstack.getItem();
			
			if(item == voidCraft.voidBucket) return 1000;
			
			
			return 0;
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return slots_all;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isItemValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return j != 0 || i != 1 || itemstack == new ItemStack(Items.bucket);
	}

	public int getBurnTimeRemainingScaled(int i) {
		if(this.currentItemBurnTime == 0){
			this.currentItemBurnTime = this.furnaceSpeed;
		}
		return this.burnTime * i / 3000;
	}

	public int getCookProgressScaled(int i) {
		return this.cookTime * i / this.furnaceSpeed;
	}
	
	
	
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		
		nbt.setInteger("burnTime",  this.burnTime);
		nbt.setInteger("cookTime",  this.cookTime);
		//nbt.setShort("currentItemBurnTime", (short) this.currentItemBurnTime);
		
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
		
		if(this.isInvNameLocalized()){
			nbt.setString("CustomName", this.localizedName);
		}
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(voidTank.fill(resource, false) > 0){
			this.burnTime += resource.amount;
		}
		return voidTank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		
		
		
		
		if (resource.isFluidEqual(voidTank.getFluid()))
		{
			return voidTank.drain(resource.amount, doDrain);
		}
		else
		{
			return voidTank.drain(0, doDrain);
		}
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		
		//if(this.burnTime > 0 && voidTank.getFluidAmount() > 0){
		//	this.burnTime-=maxDrain;
		//}
		
		FluidStack ret = voidTank.drain(maxDrain, doDrain);
		
		if(ret != null){
			return ret;
		}else{
			return null;
		}
	}
	
	public int doDrain(ForgeDirection from, int maxDrain, boolean doDrain){
		FluidStack retF = drain(from, maxDrain, doDrain);
		
		int retI = 0;
		if(retF != null){
			retI = retF.amount;
		}
		
		return retI;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if (voidTank.getFluid() == null)
		{
			return true;
		}

		return this.burnTime < 3000 && voidTank.getFluid().getFluid() == fluid;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if (voidTank.getFluid() == null)
		{
			return false;
		}

		return this.burnTime > 0 && voidTank.getFluid().getFluid() == fluid;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] {new FluidTankInfo(voidTank.getFluid(), voidTank.getCapacity())};
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}
}