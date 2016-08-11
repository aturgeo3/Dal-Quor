package Tamaized.Voidcraft.GUI.server;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCharger;

public class VoidicChargerContainer extends ContainerBase {

	private final TileEntityVoidicCharger te;

	private int powerAmount = 0;

	public VoidicChargerContainer(InventoryPlayer inventory, TileEntityVoidicCharger tileEntity) {
		te = tileEntity;

		this.addSlotToContainer(new Slot(tileEntity, 0, 176, 96));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 86 + j * 18, 150 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 86 + i * 18, 208));
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

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icontainerlistener = (IContainerListener) this.listeners.get(i);

			if (this.powerAmount != te.getPowerAmount()) {
				powerAmount = te.getPowerAmount();
				icontainerlistener.sendProgressBarUpdate(this, 0, powerAmount);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int value) {
		switch (id) {
			case 0:
				te.setPowerAmount(value);
				break;
			default:
				break;
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int hoverSlot) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(hoverSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (hoverSlot == 0) {
				if (!this.mergeItemStack(itemstack1, 1, 37, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				if (!this.getSlot(0).getHasStack()) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (hoverSlot >= 1 && hoverSlot < 28) {
					if (!this.mergeItemStack(itemstack1, 28, 37, false)) {
						return null;
					}
				} else if (hoverSlot >= 28 && hoverSlot < 37) {
					if (!this.mergeItemStack(itemstack1, 1, 28, false)) {
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
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.isUseableByPlayer(playerIn);
	}

}
