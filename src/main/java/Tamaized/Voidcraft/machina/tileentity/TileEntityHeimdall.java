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
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;
import Tamaized.Voidcraft.machina.addons.VoidTank;

public class TileEntityHeimdall extends TileEntity implements ITickable, ISidedInventory, IFluidHandler{
	
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
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(pos) != this ? false: entityplayer.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	
	@Override
	public void update(){
		//Ensure min and max are never passed
		if(this.burnTime > 10000) this.currentItemBurnTime = this.burnTime = 10000;
		if(this.burnTime < 0) this.currentItemBurnTime = this.burnTime = 0;
		
		//Generate Fluid
		if(this.burnTime < 10000){
			int rand = (int) Math.floor(Math.random()*this.pos.getY());
			if(rand == 0){
				fill(EnumFacing.NORTH, new FluidStack(voidCraft.fluids.voidFluid, 1), true);
			}
		}
		
		//Fill A Bucket
		if(!this.worldObj.isRemote){
			if(this.burnTime > 999){
				if(this.slots[0] != null && this.slots[0].getItem() == Items.BUCKET){
					this.burnTime-=1000; //Drain TileEntity Value of Fluid Amount
					voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, this.burnTime)); //Drains Fluid Amount
					this.slots[0].stackSize--;
					this.slots[0] = voidCraft.fluids.voidBucket;
				}
			}
		}
		
		//Check if Void Machina is nearby; if so give it fluid
		if(!this.worldObj.isRemote){
			if(this.burnTime > 0){
				for(int i=0;i<6;i++){
					TileEntity te;
					TileEntityVoidInfuser tevi = null;
					if(this.burnTime > 0){
						if(i==0){
							te = this.worldObj.getTileEntity(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()));
						
							if(te != null && te.getClass() == TileEntityVoidInfuser.class) tevi = (TileEntityVoidInfuser) te;
						
							if(tevi != null && tevi.burnTime < tevi.voidTank.getCapacity()){
								tevi.burnTime++;
								tevi.voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, tevi.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, this.burnTime));
							}
						}
					
						if(i==1){
							te = this.worldObj.getTileEntity(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()));
						
							if(te != null && te.getClass() == TileEntityVoidInfuser.class) tevi = (TileEntityVoidInfuser) te;
						
							if(tevi != null && tevi.burnTime < tevi.voidTank.getCapacity()){
								tevi.burnTime++;
								tevi.voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, tevi.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, this.burnTime));
							}
						}
						
						if(i==2){
							te = this.worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()));
						
							if(te != null && te.getClass() == TileEntityVoidInfuser.class) tevi = (TileEntityVoidInfuser) te;
						
							if(tevi != null && tevi.burnTime < tevi.voidTank.getCapacity()){
								tevi.burnTime++;
								tevi.voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, tevi.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, this.burnTime));
							}
						}
					
						if(i==3){
							te = this.worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY()-1, pos.getZ()));
						
							if(te != null && te.getClass() == TileEntityVoidInfuser.class) tevi = (TileEntityVoidInfuser) te;
						
							if(tevi != null && tevi.burnTime < tevi.voidTank.getCapacity()){
								tevi.burnTime++;
								tevi.voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, tevi.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, this.burnTime));
							}
						}
					
						if(i==4){
							te = this.worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1));
						
							if(te != null && te.getClass() == TileEntityVoidInfuser.class) tevi = (TileEntityVoidInfuser) te;
						
							if(tevi != null && tevi.burnTime < tevi.voidTank.getCapacity()){
								tevi.burnTime++;
								tevi.voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, tevi.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, this.burnTime));
							}
						}
					
						if(i==5){
							te = this.worldObj.getTileEntity(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1));
						
							if(te != null && te.getClass() == TileEntityVoidInfuser.class) tevi = (TileEntityVoidInfuser) te;
						
							if(tevi != null && tevi.burnTime < tevi.voidTank.getCapacity()){
								tevi.burnTime++;
								tevi.voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, tevi.burnTime));
								this.burnTime-=2;
								this.voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, this.burnTime));
							}
						}
					}
				}
			}
		}
		
		
		
		//this.markDirty();
		
		if(!this.worldObj.isRemote) sendPacketToClients();
	}
	
	private void sendPacketToClients(){

		NBTTagCompound znbt = new NBTTagCompound();
		this.writeToNBT(znbt);
		
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
	    try {
	    	outputStream.writeInt(VoidCraftClientPacketHandler.TYPE_TE_UPDATE);
	        outputStream.writeInt(this.pos.getX());
	        outputStream.writeInt(this.pos.getY());
	        outputStream.writeInt(this.pos.getZ());
	        outputStream.writeInt(this.burnTime);
	        outputStream.writeInt(this.cookTime);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	               
	    FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);

	    TargetPoint point = new TargetPoint(worldObj.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10.0D);
	    
	    if(voidCraft.channel != null && packet != null && point != null) voidCraft.channel.sendToAllAround(packet, point);
	    this.getUpdatePacket();
		 try {
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket(){
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
		return new SPacketUpdateTileEntity(pos, 2, nbt);
	}
		
	@Override
	public void onDataPacket(NetworkManager netManager, SPacketUpdateTileEntity packet){
		readFromNBT(packet.getNbtCompound());
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
			
			if(i == voidCraft.fluids.voidBucket) return 1000;
			
			return 0;
		}
	}
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return side.getIndex() == 0 ? slot : (side.getIndex() == 1 ? slot : slot);
	}
	
	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, EnumFacing j) {
		return this.isItemValidForSlot(i, itemstack);
	}
	
	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, EnumFacing j) {
		return j.getIndex() != 0 || i != 1 || itemstack == new ItemStack(Items.BUCKET);
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
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
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
		return nbt;
	}

	@Override
	public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
		if(voidTank.fill(resource, false) > 0){
			this.burnTime += resource.amount;
		}
		
		return voidTank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
		
		
		
		
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
	public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
		
		if(this.burnTime > 0 && voidTank.getFluidAmount() > 0){
			this.burnTime-=maxDrain;
		}
		
		return voidTank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(EnumFacing from, Fluid fluid) {
		if (voidTank.getFluid() == null)
		{
			return true;
		}

		return this.burnTime < 10000 && voidTank.getFluid().getFluid() == fluid;
	}

	@Override
	public boolean canDrain(EnumFacing from, Fluid fluid) {
		if (voidTank.getFluid() == null)
		{
			return false;
		}

		return this.burnTime > 0 && voidTank.getFluid().getFluid() == fluid;
	}

	@Override
	public FluidTankInfo[] getTankInfo(EnumFacing from) {
		return new FluidTankInfo[] {new FluidTankInfo(voidTank.getFluid(), voidTank.getCapacity())};
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

}
