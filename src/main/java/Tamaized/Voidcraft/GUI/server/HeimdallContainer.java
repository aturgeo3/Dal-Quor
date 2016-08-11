package Tamaized.Voidcraft.GUI.server;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;

public class HeimdallContainer extends ContainerBase {

	private TileEntityHeimdall te;
	private int amount;

	public HeimdallContainer(InventoryPlayer inventory, TileEntityHeimdall tileEntity) {
		te = tileEntity;

		addSlotToContainer(new Slot(tileEntity, 0, 158, 100));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 86 + j * 18, 150 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventory, i, 86 + i * 18, 208));
		}

		addSlotToContainer(new Slot(inventory, inventory.getSizeInventory() - 1, 230, 127) {

			@SideOnly(Side.CLIENT)
			@Override
			public String getSlotTexture() {
				return "minecraft:items/empty_armor_slot_shield";
			}
		});
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < listeners.size(); ++i) {
			IContainerListener icontainerlistener = (IContainerListener) listeners.get(i);
			if (amount != te.getFluidAmount()) {
				icontainerlistener.sendProgressBarUpdate(this, 0, te.getFluidAmount());
			}
		}
		amount = te.getFluidAmount();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int par2) {
		if (slot == 0) te.setFluidAmount(par2);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int hoverSlot) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(hoverSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (hoverSlot == 0) {
				if (!mergeItemStack(itemstack1, 1, 37, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				if (!getSlot(0).getHasStack() && te.canInsertItem(0, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (hoverSlot >= 1 && hoverSlot < 28) {
					if (!mergeItemStack(itemstack1, 28, 37, false)) {
						return null;
					}
				} else if (hoverSlot >= 28 && hoverSlot < 37) {
					if (!mergeItemStack(itemstack1, 1, 28, false)) {
						return null;
					}
				}
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
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