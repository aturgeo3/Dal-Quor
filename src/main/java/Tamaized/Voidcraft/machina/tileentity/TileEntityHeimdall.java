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

public class TileEntityHeimdall extends TileEntity implements ISidedInventory, IFluidHandler{
	
	private String localizedName;
	
	public VoidTank voidTank;
	
	public boolean isDraining = false;
	
	private static final int[] slot = new int[]{0,1,2};
	
	private ItemStack[] slots  = new ItemStack[1];//Amount of Slots
	
	public int furnaceSpeed = 801;
	public int burnTime;
	public int currentItemBurnTime;
	public int cookTime;
	
	public TileEntityHeimdall(){
		super();
		
		voidTank = new VoidTank(this, 10000);
		
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
		this.currentItemBurnTime = getItemBurnTime(this.slots[0]);
		
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
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false: entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	
	
	public void updateEntity(){
		//Ensure min and max are never passed
		if(this.burnTime > 10000) this.currentItemBurnTime = this.burnTime = 10000;
		if(this.burnTime < 0) this.currentItemBurnTime = this.burnTime = 0;
		
		//Generate Fluid
		if(this.burnTime < 10000){
			int rand = (int) Math.floor(Math.random()*this.yCoord);
			if(rand == 0){
				fill(ForgeDirection.NORTH, new FluidStack(voidCraft.fluidVoid, 1), true);
			}
		}
		
		//Fill A Bucket
		if(!this.worldObj.isRemote){
			if(this.burnTime > 999){
				if(this.slots[0] != null && this.slots[0].getItem() == Items.bucket){
					this.burnTime-=1000; //Drain TileEntity Value of Fluid Amount
					voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime)); //Drains Fluid Amount
					this.slots[0].stackSize--;
					this.slots[0] = new ItemStack(voidCraft.voidBucket);
				}
			}
		}
		
		//Check if Void Machina is nearby; if so give it fluid
		if(!this.worldObj.isRemote){
			if(this.burnTime > 0){
				for(int i=0;i<6;i++){
					TileEntity te;
					TileEntityVoidMacerator tevm = null;
					TileEntityVoidInfuser tevi = null;
					if(this.burnTime > 0){
						if(i==0){
							te = this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord);
						
							if(te != null && te.getClass() == TileEntityVoidMacerator.class) tevm = (TileEntityVoidMacerator) te;
							if(te != null && te.getClass() == TileEntityVoidInfuser.class) tevi = (TileEntityVoidInfuser) te;
						
							if(tevm != null && tevm.burnTime < tevm.voidTank.getCapacity()){
								tevm.burnTime++;
								tevm.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, tevm.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime));
							}
						
							if(tevi != null && tevi.burnTime < tevi.voidTank.getCapacity()){
								tevi.burnTime++;
								tevi.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, tevi.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime));
							}
						}
					
						if(i==1){
							te = this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord);
						
							if(te != null && te.getClass() == TileEntityVoidMacerator.class) tevm = (TileEntityVoidMacerator) te;
							if(te != null && te.getClass() == TileEntityVoidInfuser.class) tevi = (TileEntityVoidInfuser) te;
						
							if(tevm != null && tevm.burnTime < tevm.voidTank.getCapacity()){
								tevm.burnTime++;
								tevm.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, tevm.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime));
							}
						
						
							if(tevi != null && tevi.burnTime < tevi.voidTank.getCapacity()){
								tevi.burnTime++;
								tevi.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, tevi.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime));
							}
						}
						
						if(i==2){
							te = this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
						
							if(te != null && te.getClass() == TileEntityVoidMacerator.class) tevm = (TileEntityVoidMacerator) te;
							if(te != null && te.getClass() == TileEntityVoidInfuser.class) tevi = (TileEntityVoidInfuser) te;
						
							if(tevm != null && tevm.burnTime < tevm.voidTank.getCapacity()){
								tevm.burnTime++;
								tevm.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, tevm.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime));
							}
							
							if(tevi != null && tevi.burnTime < tevi.voidTank.getCapacity()){
								tevi.burnTime++;
								tevi.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, tevi.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime));
							}
						}
					
						if(i==3){
							te = this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
						
							if(te != null && te.getClass() == TileEntityVoidMacerator.class) tevm = (TileEntityVoidMacerator) te;
							if(te != null && te.getClass() == TileEntityVoidInfuser.class) tevi = (TileEntityVoidInfuser) te;
						
							if(tevm != null && tevm.burnTime < tevm.voidTank.getCapacity()){
								tevm.burnTime++;
								tevm.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, tevm.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime));
							}
						
							if(tevi != null && tevi.burnTime < tevi.voidTank.getCapacity()){
								tevi.burnTime++;
								tevi.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, tevi.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime));
							}
						}
					
						if(i==4){
							te = this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1);
						
							if(te != null && te.getClass() == TileEntityVoidMacerator.class) tevm = (TileEntityVoidMacerator) te;
							if(te != null && te.getClass() == TileEntityVoidInfuser.class) tevi = (TileEntityVoidInfuser) te;
						
							if(tevm != null && tevm.burnTime < tevm.voidTank.getCapacity()){
								tevm.burnTime++;
								tevm.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, tevm.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime));
							}
						
						
							if(tevi != null && tevi.burnTime < tevi.voidTank.getCapacity()){
								tevi.burnTime++;
								tevi.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, tevi.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime));
							}
						}
					
						if(i==5){
							te = this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1);
						
							if(te != null && te.getClass() == TileEntityVoidMacerator.class) tevm = (TileEntityVoidMacerator) te;
							if(te != null && te.getClass() == TileEntityVoidInfuser.class) tevi = (TileEntityVoidInfuser) te;
						
							if(tevm != null && tevm.burnTime < tevm.voidTank.getCapacity()){
								tevm.burnTime++;
								tevm.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, tevm.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime));
							}
						
							if(tevi != null && tevi.burnTime < tevi.voidTank.getCapacity()){
								tevi.burnTime++;
								tevi.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, tevi.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluidVoid, this.burnTime));
							}
						}
					}
				}
			}
		}
		
		
		
		//this.markDirty();
		
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
		return var1 == 0 ? slot : (var1 == 1 ? slot : slot);
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
		return this.burnTime * i / 10000;
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
		
		if(this.burnTime > 0 && voidTank.getFluidAmount() > 0){
			this.burnTime-=maxDrain;
		}
		
		return voidTank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if (voidTank.getFluid() == null)
		{
			return true;
		}

		return this.burnTime < 10000 && voidTank.getFluid().getFluid() == fluid;
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
