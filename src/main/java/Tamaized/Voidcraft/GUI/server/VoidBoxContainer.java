package Tamaized.Voidcraft.GUI.server;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import Tamaized.Voidcraft.GUI.SlotCantRemove;
import Tamaized.Voidcraft.GUI.SlotCantPlace;
import Tamaized.Voidcraft.GUI.SlotCantPlaceOrRemove;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class VoidBoxContainer extends Container {
	
	private TileEntityVoidBox te;

	public VoidBoxContainer(InventoryPlayer inventory, TileEntityVoidBox tileEntity) {
		this.te = tileEntity;
		
		this.addSlotToContainer(new SlotCantPlaceOrRemove(tileEntity, 0, 176, 115));
		this.addSlotToContainer(new Slot(tileEntity, 1, 140, 103));
		this.addSlotToContainer(new SlotCantPlace(tileEntity, 2, 140, 127));
		
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 9; j++){
				this.addSlotToContainer(new Slot(inventory, j + i*9 + 9, 86 + j*18, 150 + i*18));
			}
		}
		
		for(int i = 0; i < 9; i++){
			this.addSlotToContainer(new Slot(inventory, i, 86 + i*18, 208));
		}
	}

	public void detectAndSendChanges(){
		super.detectAndSendChanges();
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int par2){
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int hoverSlot){
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(hoverSlot);
		
		if(slot != null && slot.getHasStack()){
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(hoverSlot != 0){
				if(hoverSlot >= 3 && hoverSlot < 28){
					if(te.isItemValidForSlot(1, itemstack1)){
						if(!this.mergeItemStack(itemstack1, 1, 2, true)){
							return null;
						}
					}else
					if(!this.mergeItemStack(itemstack1, 30, 39, false)){
						return null;
					}
				}else if(hoverSlot >= 28 && hoverSlot <= 38){
					if(te.isItemValidForSlot(1, itemstack1)){
						if(!this.mergeItemStack(itemstack1, 1, 2, true)){
							return null;
						}
					}else
					if(!this.mergeItemStack(itemstack1, 3, 27, false)){
						return null;
					}
				}else if(hoverSlot > 0 && hoverSlot < 3){
					if(!this.mergeItemStack(itemstack1, 3, 27, false)){
						return null;
					}
				}
			}else{
				return itemstack;
			}
			
			if(itemstack1.stackSize == 0){
				slot.putStack((ItemStack) null);
			}else{
				slot.onSlotChanged();
			}
			
			if(itemstack1.stackSize == itemstack.stackSize){
				return null;
			}
			
			slot.onPickupFromSlot(player, itemstack1);
		
		}
		
		return itemstack;
	}

	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.te.isUseableByPlayer(entityplayer);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}