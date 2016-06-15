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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;
import Tamaized.Voidcraft.machina.VoidMacerator;
import Tamaized.Voidcraft.machina.addons.MaceratorRecipes;
import Tamaized.Voidcraft.machina.addons.VoidTank;

public class TileEntityVoidMacerator extends TileEntity implements ITickable, ISidedInventory, IFluidHandler{
	
	private String localizedName;
	
	public VoidTank voidTank;
	
	private static final int[] slots_top = new int[]{0};
	private static final int[] slots_bottom = new int[]{2, 1};
	private static final int[] slots_sides = new int[]{1};
	private static final int[] slots_all = new int[]{0, 1, 2};
	
	private ItemStack[] slots  = new ItemStack[3];//Amount of Slots
	
	public int furnaceSpeed = 101;
	public int burnTime;
	public int currentItemBurnTime;
	public int cookTime;

	
	public TileEntityVoidMacerator(){
		super();
		voidTank = new VoidTank(this, 3000);
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate){
	    return (oldState.getBlock() != newSate.getBlock());
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
		return this.isInvNameLocalized() ? this.localizedName : "container.voidMacerator";
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
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.pos) != this ? false: entityplayer.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	
	@Override
	public void update(){
		
		//voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, this.burnTime));
		//burnTime = voidTank.getFluidAmount();
		//if(burnTime > 0)System.out.println(burnTime +" : "+ voidTank.getFluidAmount());
		
		if(this.burnTime > 3000) this.currentItemBurnTime = this.burnTime = 3000;
		if(this.burnTime < 0) this.currentItemBurnTime = this.burnTime = 0;
		//if(voidTank.getFluid() != null) voidTank.getFluid().amount = this.burnTime;
		
		
		boolean flag = (this.cookTime>0 && burnTime>0);
		boolean flag1 = false;
		
		if(this.burnTime > 0 && this.cookTime > 0) {
			this.burnTime --;
			voidTank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, this.burnTime));
			//this.burnTime = doDrain(EnumFacing.NORTH, 1, true);
		}
		
		if(!this.worldObj.isRemote){
			if(this.burnTime < 2001 /*&& this.canSmelt()*/){
				
				if(this.burnTime > 3000) this.currentItemBurnTime = this.burnTime = 3000;
				if(getItemBurnTime(this.slots[1]) > 0){
					flag1 = true;
					
					fill(EnumFacing.NORTH, new FluidStack(voidCraft.fluids.voidFluid, getItemBurnTime(this.slots[1])), true);
					this.currentItemBurnTime = this.burnTime = voidTank.getFluidAmount();
					
					if(this.slots[1] != null){
						this.slots[1].stackSize--;
						this.slots[1] = new ItemStack(Items.BUCKET);
						
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
				
			}else if(slots[0] == null) this.cookTime = 0;
			
			IBlockState state = worldObj.getBlockState(pos);
			if(state.getBlock() instanceof VoidMacerator){
				VoidMacerator theMacerator = (VoidMacerator) state.getBlock();
				if(theMacerator != null){
					if(theMacerator.getIsActive(state) && !flag) theMacerator.setState(false, worldObj, pos);
					if(!theMacerator.getIsActive(state) && flag) theMacerator.setState(true, worldObj, pos);
				}
			}
			
			
		}
		
		if(flag1){
			this.markDirty();
		}
		
		if(!this.worldObj.isRemote) sendPacketToClients();
	}
	
	private void sendPacketToClients(){

		NBTTagCompound znbt = new NBTTagCompound();
		this.func_189515_b(znbt);
		
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
	    this.func_189518_D_();
		 try {
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public SPacketUpdateTileEntity func_189518_D_(){
		NBTTagCompound nbt = new NBTTagCompound();
		this.func_189515_b(nbt);
		
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

	private void smeltItem() {
		if(this.canSmelt()){
			ItemStack itemstack = MaceratorRecipes.smelting().getSmeltingResult(this.slots[0]);
		
			if(this.slots[2] == null){
				this.slots[2] = itemstack.copy();
			}else if(this.slots[2].isItemEqual(itemstack)){
				this.slots[2].stackSize += itemstack.stackSize;
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
				ItemStack itemstack = MaceratorRecipes.smelting().getSmeltingResult(this.slots[0]);
						
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
			
			if(i.getItem() == voidCraft.fluids.voidBucket.getItem()) return 1000;
			//System.out.println(i+" : "+voidCraft.fluids.voidBucket);
			
			return 0;
		}
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		//return var1 == 0 ? slots_bottom : (var1 == 1 ? slots_top : slots_sides);
		return slots_all;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, EnumFacing j) {
		return this.isItemValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, EnumFacing j) {
		return i == 2 || itemstack == new ItemStack(Items.BUCKET);
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
	
	
	
	public NBTTagCompound func_189515_b(NBTTagCompound nbt){
		super.func_189515_b(nbt);
		
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
	
	public int doDrain(EnumFacing from, int maxDrain, boolean doDrain){
		FluidStack retF = drain(from, maxDrain, doDrain);
		
		int retI = 0;
		if(retF != null){
			retI = retF.amount;
		}
		
		return retI;
	}

	@Override
	public boolean canFill(EnumFacing from, Fluid fluid) {
		if (voidTank.getFluid() == null)
		{
			return true;
		}

		return this.burnTime < 3000 && voidTank.getFluid().getFluid() == fluid;
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
