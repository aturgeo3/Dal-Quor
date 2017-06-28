package tamaized.voidcraft.common.gui.container;

import tamaized.voidcraft.common.gui.slots.SlotCantPlace;
import tamaized.voidcraft.common.gui.slots.SlotCantPlaceOrRemove;
import tamaized.voidcraft.common.gui.slots.SlotItemHandlerBypass;
import tamaized.voidcraft.common.machina.tileentity.TileEntityVoidBox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VoidBoxContainer extends Container {

	private TileEntityVoidBox te;

	public VoidBoxContainer(InventoryPlayer inventory, TileEntityVoidBox tileEntity) {
		this.te = tileEntity;

		this.addSlotToContainer(new SlotCantPlaceOrRemove(tileEntity.SLOT_CURRENT, 0, 176, 115));
		this.addSlotToContainer(new SlotItemHandlerBypass(tileEntity.SLOT_NEXT, 0, 140, 103));
		this.addSlotToContainer(new SlotCantPlace(tileEntity.SLOT_FINISH, 0, 140, 127));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 86 + j * 18, 160 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 86 + i * 18, 218));
		}

		addSlotToContainer(new Slot(inventory, inventory.getSizeInventory() - 1, 230, 137) {

			@SideOnly(Side.CLIENT)
			@Override
			public String getSlotTexture() {
				return "minecraft:items/empty_armor_slot_shield";
			}
		});
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int par2) {

	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < te.getInventorySize()) {
				if (!this.mergeItemStack(itemstack1, te.getInventorySize(), this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, te.getInventorySize(), false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Deprecated
	public ItemStack old_transferStackInSlot(EntityPlayer player, int hoverSlot) {
		ItemStack itemstack = ItemStack.EMPTY;
		/*Slot slot = (Slot) this.inventorySlots.get(hoverSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			final int maxSlots = te.getInventorySize();

			if (hoverSlot < maxSlots && te.canExtractItem(hoverSlot, itemstack, null)) {
				if (!mergeItemStack(itemstack1, maxSlots, maxSlots + 36, true)) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				ItemStack slotCheck = te.getStackInSlot(te.SLOT_NEXT);
				if ((slotCheck.isEmpty() || (slotCheck.getCount() < slotCheck.getMaxStackSize() && slotCheck.isItemEqual(itemstack))) && te.canInsertItem(te.SLOT_NEXT, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, te.SLOT_NEXT, te.SLOT_NEXT + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (hoverSlot >= maxSlots && hoverSlot < maxSlots + 27) {
					if (!mergeItemStack(itemstack1, maxSlots + 27, maxSlots + 36, false)) {
						return ItemStack.EMPTY;
					}
				} else if (hoverSlot >= maxSlots + 27 && hoverSlot < maxSlots + 36) {
					if (!mergeItemStack(itemstack1, maxSlots, maxSlots + 27, false)) {
						return ItemStack.EMPTY;
					}
				} else {
					if (!mergeItemStack(itemstack1, maxSlots, maxSlots + 36, false)) {
						return ItemStack.EMPTY;
					}
				}
			}

			if (itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemstack1);

		}
*/
		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.te.canInteractWith(entityplayer);
	}

}