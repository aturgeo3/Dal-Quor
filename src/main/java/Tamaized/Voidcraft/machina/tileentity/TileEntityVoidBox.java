package Tamaized.Voidcraft.machina.tileentity;

import java.io.File;

import Tamaized.TamModized.tileentity.TamTileEntityInventory;
import Tamaized.Voidcraft.voidCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityVoidBox extends TamTileEntityInventory {

	public static final int SLOT_CURRENT = 0;
	public static final int SLOT_NEXT = 1;
	public static final int SLOT_FINISH = 2;
	public static final int[] SLOTS_ACCESSABLE = new int[] { 1, 2 };
	public static final int[] SLOTS_ALL = new int[] { 0, 1, 2 };

	private boolean loop;
	private boolean autoFill;
	private ItemStack oldRecord;
	private boolean isPowered = false;
	private boolean pulsed = false;

	// These should only be handled on the client
	@SideOnly(Side.CLIENT)
	public boolean isPlaying;
	@SideOnly(Side.CLIENT)
	private int loopTime;
	@SideOnly(Side.CLIENT)
	private int maxLoopTime;

	public TileEntityVoidBox() {
		super(3);
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		loop = nbt.getBoolean("loop");
		autoFill = nbt.getBoolean("autoFill");
		int temp = nbt.getInteger("oldRecord");
		oldRecord = temp > -1 ? new ItemStack(Item.getItemById(temp)) : null;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("loop", loop);
		nbt.setBoolean("autoFill", autoFill);
		nbt.setInteger("oldRecord", oldRecord != null ? Item.getIdFromItem(oldRecord.getItem()) : -1);
		return nbt;
	}

	/**
	 * Play the Record
	 */
	@SideOnly(Side.CLIENT)
	private void PlayTheSound(ItemStack itemStack) {
		if (itemStack != null && itemStack.getItem() instanceof ItemRecord) {
			ItemRecord theRecord = (ItemRecord) itemStack.getItem();
			worldObj.playEvent((EntityPlayer) null, 1010, getPos(), Item.getIdFromItem(theRecord));
		} else {
			voidCraft.logger.warn("NULL/NON-ITEMRECORD IN SLOT DETECTED");
		}
	}

	public void PlayCurrentRecord() { // TODO: Send a packet to all around, only clients will be able to play the actual record
		ItemStack record = slots[SLOT_CURRENT];
		if (record != null) {
			// Send the packet
		}
	}

	/**
	 * Stop the Record
	 */
	public void StopTheSound() {
		worldObj.playEvent((EntityPlayer) null, 1010, getPos(), 0);
	}

	public void setHasRedstoneSignal(boolean b) {
		isPowered = b;
	}

	@Override
	public void update() {
		super.update();
		if (!worldObj.isRemote) {

		} else {

		}
	}

	public void updatez(){
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
					if(slots[SLOT_CURRENT].getItem() instanceof ItemRecord){
						ItemRecord theRecord = ((ItemRecord) slots[SLOT_CURRENT].getItem());
						ResourceLocation theResource = theRecord.getSound().getRegistryName();
						
						File soundFile = new File("resourcepacks/moreMusic/assets/minecraft/" + theResource.getResourcePath());
						thytime = 
						
					}
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
		return i == SLOT_NEXT ? stack.getItem() instanceof ItemRecord : false;
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
