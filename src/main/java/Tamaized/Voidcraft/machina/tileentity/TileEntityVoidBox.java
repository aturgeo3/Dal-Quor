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
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;
import Tamaized.Voidcraft.items.VoidRecord;

public class TileEntityVoidBox extends TileEntity implements ITickable, ISidedInventory{
	
	public static NBTTagCompound znbtc = new NBTTagCompound();
	
	
	private String localizedName;
	
	public static final int[] slots_curr = new int[]{0};
	public static final int[] slots_next = new int[]{1};
	public static final int[] slots_done = new int[]{2};
	public static final int[] slots_accessable = new int[]{1, 2};
	public static final int[] slots_all = new int[]{0, 1, 2};

	public boolean isPlaying;
	public boolean doPlay;
	public boolean loop;
	public boolean autoFill;
	public Item oldRecord;
	public int loopTime;
	public int maxLoopTime;
	public boolean isPowered = false;
	private boolean pulsed = false;
	
	public ItemStack[] slots  = new ItemStack[3]; //Amount of Slots cuz length is a thing in arrays duh
	
	public int getSizeInventory(){
		return this.slots.length;
	}
	
	public String getInvName(){
		return this.isInvNameLocalized() ? this.localizedName : "container.voidBox";
	}
	
	public boolean isInvNameLocalized() {
		return this.localizedName != null && this.localizedName.length() > 0;
	}
	
	public void setGuiDisplayName(String displayName) {
		this.localizedName = displayName;
	}
	
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		
		if(nbt.getTag("loop") != null){
			NBTTagString nbtLoop = (NBTTagString) nbt.getTag("loop");
			loop = nbtLoop.getString().equals("true");
		}
		
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
		
		
		if(nbt.hasKey("CustomName")){
			this.localizedName = nbt.getString("CustomName");
		}
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.slots[i];
	}
	
	public Item getItemInSlot(int i){
		if(this.slots[i] != null){
			return  this.slots[i].getItem();
		}else{
			return Items.SNOWBALL;
		}
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
	
	/**
	 *  Play the Record
	 *   
     */
	public void PlayTheSound(ItemStack itemStack){
		if(itemStack != null && itemStack.getItem() instanceof VoidRecord){
			VoidRecord theRecord = (VoidRecord) itemStack.getItem();
                this.worldObj.playEvent((EntityPlayer)null, 1010, getPos(), Item.getIdFromItem(theRecord));
		}else{
			System.out.println("NULL/NON-VOID RECORD SLOT DETECTED");
		}
	}
	
	/**
	 *  Stop the Record
	 *   
     */
	public void StopTheSound(){
		this.worldObj.playEvent((EntityPlayer)null, 1010, getPos(), 0);
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
		if(!this.worldObj.isRemote){
			if(!pulsed && isPowered){
				pulsed = true;
				doPlay = isPlaying = !isPlaying;
			}else if(pulsed && !isPowered){
				pulsed = false;
			}
			
			if(this.isPlaying){
				if(this.doPlay){
					if(slots[slots_curr[0]] == null){
						if(slots[slots_next[0]] == null){
							isPlaying = doPlay = false;
						}else{
							Item item = slots[slots_next[0]].getItem();
							slots[slots_next[0]] = null;
							slots[slots_curr[0]] = new ItemStack(item);
						}
					}
					this.PlayTheSound(this.getStackInSlot(0));
					this.doPlay = false;
					int thytime=0;
					this.oldRecord = this.getItemInSlot(0);
					if(getItemInSlot(0) instanceof VoidRecord) thytime = ((VoidRecord) getItemInSlot(0)).getTime();
					else{
						isPlaying = doPlay = false;
						return;
					}
					maxLoopTime = loopTime = (thytime*20); //Seconds*ticks (20 ticks per 1 second)
				}else{
					loopTime--;
				}
				
				if(this.loopTime < 0){
					if(loop){
			 			this.oldRecord = this.getItemInSlot(0);
			 			this.isPlaying = true;
			 			this.doPlay = true;
					}else{
						this.StopTheSound();
						this.isPlaying = false;
					}
				}
				
				if(this.getItemInSlot(0) != this.oldRecord) {
					this.StopTheSound();
					this.isPlaying = false;
				}
			}else{
				this.StopTheSound();
				if(!loop && slots[slots_curr[0]] != null && slots[slots_done[0]] == null){
					Item item = slots[slots_curr[0]].getItem();
					slots[slots_curr[0]] = null;
					slots[slots_done[0]] = new ItemStack(item);
				}
				if(autoFill && slots[slots_curr[0]] == null && slots[slots_next[0]] != null){
					Item item = slots[slots_next[0]].getItem();
					slots[slots_next[0]] = null;
					slots[slots_curr[0]] = new ItemStack(item);
					doPlay = isPlaying = true;
				}
			}
		}
		
		if(!this.worldObj.isRemote) sendPacketToClients();
		
	}
	
	private void sendPacketToClients(){
		NBTTagCompound znbt = new NBTTagCompound();
		this.func_189515_b(znbt);
		
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
	    try {
	    	outputStream.writeInt(VoidCraftClientPacketHandler.TYPE_VOIDBOX_UPDATE);
	        outputStream.writeInt(pos.getX());
	        outputStream.writeInt(pos.getY());
	        outputStream.writeInt(pos.getZ());
	        outputStream.writeBoolean(this.isPlaying);
	        if(this.oldRecord == null) outputStream.writeBoolean(false);
	        else{
	        	outputStream.writeBoolean(true);
	        	outputStream.writeInt(Item.getIdFromItem(this.oldRecord));
	        }
	        outputStream.writeInt(this.loopTime);
	        outputStream.writeInt(this.maxLoopTime);
	        outputStream.writeBoolean(this.loop);
	        outputStream.writeBoolean(this.autoFill);
	        FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);
		    TargetPoint point = new TargetPoint(worldObj.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10.0D);
			if(voidCraft.channel != null && packet != null && point != null) voidCraft.channel.sendToAllAround(packet, point);
		    this.func_189518_D_();
		    this.markDirty();
		    bos.close();
	    }catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public SPacketUpdateTileEntity func_189518_D_(){
		NBTTagCompound nbt = new NBTTagCompound();
		this.func_189515_b(nbt);
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
		
		NBTTagString nbtLoop = new NBTTagString(loop ? "true" : "false");
		
		nbt.setTag("Items", list);
		nbt.setTag("loop", nbtLoop);
		
		if(this.isInvNameLocalized()){
			nbt.setString("CustomName", this.localizedName);
		}
		
		return new SPacketUpdateTileEntity(pos, 2, nbt);
	}
		
	@Override
	public void onDataPacket(NetworkManager netManager, SPacketUpdateTileEntity packet){
	 readFromNBT(packet.getNbtCompound());
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i == 1 ? (slots[slots_next[0]] == null && (itemstack.getItem() instanceof VoidRecord)) : false;
	}

	/**
	 * 0 = down
	 * 1 = up
	 * rest = sides
	 */
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return slots_accessable;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemstack, EnumFacing i) {
		return isItemValidForSlot(i.getIndex(), itemstack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, EnumFacing j) {
		return i==2 ? true : false;
	}

	@Override
	public NBTTagCompound func_189515_b(NBTTagCompound nbt){
		super.func_189515_b(nbt);
		
		NBTTagList list = new NBTTagList();
		
		for(int i = 0; i < this.slots.length; i++){
			if(this.slots[i] != null){
				NBTTagCompound nbtc = new NBTTagCompound();
				nbtc.setByte("Slot", (byte) i);
				this.slots[i].writeToNBT(nbtc);
				list.appendTag(nbtc);
			}
		}
		
		NBTTagString nbtLoop = new NBTTagString(loop ? "true" : "false");
		
		nbt.setTag("Items", list);
		nbt.setTag("loop", nbtLoop);
		
		if(this.isInvNameLocalized()){
			nbt.setString("VoidBox", this.localizedName);
		}
		return nbt;
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		
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
	public void openInventory(EntityPlayer player) {
		
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
	public ITextComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}
}
