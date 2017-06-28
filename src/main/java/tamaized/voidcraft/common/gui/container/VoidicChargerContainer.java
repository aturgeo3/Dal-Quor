package tamaized.voidcraft.common.gui.container;

import tamaized.voidcraft.common.gui.slots.SlotItemHandlerBypass;
import tamaized.voidcraft.common.machina.tileentity.TileEntityVoidicCharger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VoidicChargerContainer extends Container {

	private final TileEntityVoidicCharger te;

	private int powerAmount = 0;

	public VoidicChargerContainer(InventoryPlayer inventory, TileEntityVoidicCharger tileEntity) {
		te = tileEntity;

		this.addSlotToContainer(new SlotItemHandlerBypass(tileEntity.SLOT_DEFAULT, 0, 176, 96));

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

		for (IContainerListener icontainerlistener : this.listeners) {
			if (this.powerAmount != te.getPowerAmount()) {
				powerAmount = te.getPowerAmount();
				icontainerlistener.sendWindowProperty(this, 0, powerAmount);
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

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.te.canInteractWith(entityplayer);
	}

}
