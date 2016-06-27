package Tamaized.Voidcraft.machina.tileentity;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

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
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;
import Tamaized.Voidcraft.items.VoidRecord;
import Tamaized.Voidcraft.power.VoidicPowerItem;

public class TileEntityVoidBox extends TileEntityInventoryBase {
	
	public static final int SLOT_CURRENT = 0;
	public static final int SLOT_NEXT = 1;
	public static final int SLOT_FINISH = 2;
	public static final int[] SLOTS_ACCESSABLE = new int[]{1, 2};
	public static final int[] SLOTS_ALL = new int[]{0, 1, 2};
	
	public boolean isPlaying;
	public boolean doPlay;
	public boolean loop;
	public boolean autoFill;
	public ItemStack oldRecord;
	public int loopTime;
	public int maxLoopTime;
	private boolean isPowered = false;
	private boolean pulsed = false;
	
	public TileEntityVoidBox() {
		super(3);
	}
	
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		loop = nbt.getBoolean("loop");
		autoFill = nbt.getBoolean("autoFill");
		isPlaying = nbt.getBoolean("isPlaying");
		doPlay = nbt.getBoolean("doPlay");
		int temp = nbt.getInteger("oldRecord");
		oldRecord = temp > -1 ? new ItemStack(Item.getItemById(temp)) : null;
		loopTime = nbt.getInteger("loopTime");
		maxLoopTime = nbt.getInteger("maxLoopTime");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setBoolean("loop", loop);
		nbt.setBoolean("autoFill", autoFill);
		nbt.setBoolean("isPlaying", isPlaying);
		nbt.setBoolean("doPlay", doPlay);
		nbt.setInteger("oldRecord", oldRecord != null ? Item.getIdFromItem(oldRecord.getItem()) : -1);
		nbt.setInteger("loopTime", loopTime);
		nbt.setInteger("maxLoopTime", maxLoopTime);
		return nbt;
	}

	/**
	 *  Play the Record
	 *   
     */
	private void PlayTheSound(ItemStack itemStack){
		if(itemStack != null && itemStack.getItem() instanceof VoidRecord){
			VoidRecord theRecord = (VoidRecord) itemStack.getItem();
                worldObj.playEvent((EntityPlayer)null, 1010, getPos(), Item.getIdFromItem(theRecord));
		}else{
			voidCraft.logger.warn("NULL/NON-VOIDRECORD SLOT DETECTED");
		}
	}
	
	public void PlayCurrentRecord(){
		ItemStack record = slots[SLOT_CURRENT];
		if(record != null) PlayTheSound(record);
	}
	
	/**
	 *  Stop the Record
	 *   
     */
	public void StopTheSound(){
		worldObj.playEvent((EntityPlayer)null, 1010, getPos(), 0);
	}
	
	public void setHasRedstoneSignal(boolean b){
		isPowered = b;
	}

	@Override
	public void update(){
		super.update();
		if(!worldObj.isRemote){
			if(!pulsed && isPowered){
				pulsed = true;
				doPlay = isPlaying = !isPlaying;
			}else if(pulsed && !isPowered){
				pulsed = false;
			}
			
			if(isPlaying){
				if(doPlay){
					if(slots[SLOT_CURRENT] == null){
						if(slots[SLOT_NEXT] == null){
							isPlaying = doPlay = false;
						}else{
							slots[SLOT_CURRENT] = slots[SLOT_NEXT];
							slots[SLOT_NEXT] = null;
						}
					}
					PlayCurrentRecord();
					doPlay = false;
					int thytime=0;
					oldRecord = slots[SLOT_CURRENT];
					if(slots[SLOT_CURRENT].getItem() instanceof VoidRecord) thytime = ((VoidRecord) slots[SLOT_CURRENT].getItem()).getTime();
					else{
						StopTheSound();
						isPlaying = doPlay = false;
						return;
					}
					maxLoopTime = loopTime = (thytime*20); //Seconds*ticks (20 ticks per 1 second)
				}else{
					loopTime--;
				}
				
				if(loopTime <= 0){
					if(loop){
			 			oldRecord = slots[SLOT_CURRENT];
			 			isPlaying = true;
			 			doPlay = true;
					}else{
						StopTheSound();
						isPlaying = false;
					}
				}
				
				if(slots[SLOT_CURRENT] != oldRecord) {
					StopTheSound();
					isPlaying = false;
				}
			}else{
				StopTheSound();
				if(!loop && slots[SLOT_CURRENT] != null && slots[SLOT_FINISH] == null){
					slots[SLOT_FINISH] = slots[SLOT_CURRENT];
					slots[SLOT_CURRENT] = null;
				}
				if(autoFill && slots[SLOT_CURRENT] == null && slots[SLOT_NEXT] != null){
					slots[SLOT_CURRENT] = slots[SLOT_NEXT];
					slots[SLOT_NEXT] = null;
					doPlay = isPlaying = true;
				}
			}
		}
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return i == SLOT_NEXT ? stack.getItem() instanceof VoidRecord : false;
	}

	@Override
	public String getName() {
		return "voidicMusicBox";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return SLOTS_ACCESSABLE;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	protected boolean canExtractSlot(int i) {
		return i == SLOT_FINISH;
	}

	@Override
	protected boolean canInsertSlot(int i) {
		return i == SLOT_NEXT;
	}
}
