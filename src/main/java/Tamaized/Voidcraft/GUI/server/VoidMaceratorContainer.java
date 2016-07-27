package Tamaized.Voidcraft.GUI.server;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;

public class VoidMaceratorContainer extends ContainerBase {
	
	private TileEntityVoidMacerator te;
	
	private int powerAmount = 0;
	private int cookAmount = 0;
	
	public VoidMaceratorContainer(InventoryPlayer inventory, TileEntityVoidMacerator tileEntity) {
		this.te = tileEntity;
		
		this.addSlotToContainer(new Slot(tileEntity, 0, 168, 100));
		this.addSlotToContainer(new SlotFurnaceOutput(inventory.player, tileEntity, 1, 225, 101));
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 9; j++){
				this.addSlotToContainer(new Slot(inventory, j + i*9 + 9, 86 + j*18, 150 + i*18));
			}
		}
		
		for(int i = 0; i < 9; i++){
			this.addSlotToContainer(new Slot(inventory, i, 86 + i*18, 208));
		}
	}
	
	@Override
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		
		for (int i = 0; i < this.listeners.size(); ++i){
			IContainerListener icontainerlistener = (IContainerListener)this.listeners.get(i);
			
			if(this.cookAmount != te.cookingTick){
				icontainerlistener.sendProgressBarUpdate(this, 0, cookAmount);
				cookAmount = te.cookingTick;
			}
			
			if(this.powerAmount != te.getPowerAmount()){
				icontainerlistener.sendProgressBarUpdate(this, 1, powerAmount);
				powerAmount = te.getPowerAmount();
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int par2){
		if(slot == 0) te.cookingTick = par2;
		if(slot == 1) te.setPowerAmount(par2);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int hoverSlot){
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(hoverSlot);
		
		if(slot != null && slot.getHasStack()){
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(hoverSlot == 0){
				if(!this.mergeItemStack(itemstack1, 2, 38, true)){
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			}else{
				if(te.canInsertItem(te.SLOT_INPUT, itemstack1, null)){
					if(!this.mergeItemStack(itemstack1, 0, 1, false)){
						return null;
					}
				}else if(hoverSlot == 1){
					if(!this.mergeItemStack(itemstack1, 2, 38, false)){
						return null;
					}
				}else if(hoverSlot >= 2 && hoverSlot < 29){
					if(!this.mergeItemStack(itemstack1, 29, 38, false)){
						return null;
					}
				}else if(hoverSlot >= 29 && hoverSlot < 38){
					if(!this.mergeItemStack(itemstack1, 2, 29, false)){
						return null;
					}
				}
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

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return te.isUseableByPlayer(entityplayer);
	}
}