package Tamaized.Voidcraft.GUI.server;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class HeimdallContainer extends Container {
	
	private TileEntityHeimdall voidtilemacerator;
	
	public int lastBurnTime;
	public int lastItemBurnTime;
	public int lastCookTime;

	public HeimdallContainer(InventoryPlayer inventory, TileEntityHeimdall tileEntity) {
		this.voidtilemacerator = tileEntity;
		
		this.addSlotToContainer(new Slot(tileEntity, 0, 158, 100));
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 9; j++){
				this.addSlotToContainer(new Slot(inventory, j + i*9 + 9, 86 + j*18, 150 + i*18));
			}
		}
		
		for(int i = 0; i < 9; i++){
			this.addSlotToContainer(new Slot(inventory, i, 86 + i*18, 208));
		}
	}
	
	public void addCraftingToCrafters(ICrafting icrafting){
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.voidtilemacerator.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.voidtilemacerator.burnTime);
		icrafting.sendProgressBarUpdate(this, 2, this.voidtilemacerator.currentItemBurnTime);
	}

	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.crafters.size(); i++){
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
			
			if(this.lastCookTime != this.voidtilemacerator.cookTime){
				icrafting.sendProgressBarUpdate(this, 0, this.voidtilemacerator.cookTime);
			}
			
			if(this.lastBurnTime != this.voidtilemacerator.burnTime){
				icrafting.sendProgressBarUpdate(this, 1, this.voidtilemacerator.burnTime);
			}
			
			if(this.lastItemBurnTime != this.voidtilemacerator.currentItemBurnTime){
				icrafting.sendProgressBarUpdate(this, 2, this.voidtilemacerator.currentItemBurnTime);
			}
		}
		
		this.lastCookTime = this.voidtilemacerator.cookTime;
		this.lastBurnTime = this.voidtilemacerator.burnTime;
		this.lastItemBurnTime = this.voidtilemacerator.currentItemBurnTime;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int par2){
		if(slot == 0) this.voidtilemacerator.cookTime = par2;
		if(slot == 1) this.voidtilemacerator.burnTime = par2;
		if(slot == 2) this.voidtilemacerator.currentItemBurnTime = par2;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int hoverSlot){
		/*ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(hoverSlot);
		
		if(slot != null && slot.getHasStack()){
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(hoverSlot == 2){
				if(!this.mergeItemStack(itemstack1, 3, 39, true)){
					return null;
				}
				
				slot.onSlotChange(itemstack1, itemstack);
			}else if(hoverSlot != 1 && hoverSlot != 0){
				if(FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null){
					if(!this.mergeItemStack(itemstack1, 0, 1, false)){
						return null;
					}
				}else if(TileEntityVoidInfuser.isItemFuel(itemstack1)){
					if(!this.mergeItemStack(itemstack1, 1, 2, false)){
						return null;
					}
				}else if(hoverSlot >= 3 && hoverSlot < 30){
					if(!this.mergeItemStack(itemstack1, 30, 39, false)){
						return null;
					}
				}else if(hoverSlot >= 30 && hoverSlot <= 38){
					if(!this.mergeItemStack(itemstack1, 3, 30, false)){
						return null;
					}
				}
			}else if(!this.mergeItemStack(itemstack1, 3, 39, false)){
				return null;
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
		*/
		return null;//itemstack;
	}

	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.voidtilemacerator.isUseableByPlayer(entityplayer);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}