package Tamaized.Voidcraft.common.gui.container;

import Tamaized.Voidcraft.common.gui.slots.SlotAdjustedMaxSize;
import Tamaized.Voidcraft.common.gui.slots.SlotItemHandlerBypass;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityHeimdall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HeimdallContainer extends Container {

	private TileEntityHeimdall te;
	private int fluidAmount;
	private int powerAmount;

	public HeimdallContainer(InventoryPlayer inventory, TileEntityHeimdall tileEntity) {
		te = tileEntity;

		addSlotToContainer(new SlotItemHandlerBypass(tileEntity.SLOT_INPUT, 0, 158, 87));
		addSlotToContainer(new SlotAdjustedMaxSize(tileEntity.SLOT_BUCKET, 0, 158, 114, 1));

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
			if (fluidAmount != te.getFluidAmount())
				icontainerlistener.sendWindowProperty(this, 0, te.getFluidAmount());
			if (powerAmount != te.getEnergyStored())
				icontainerlistener.sendWindowProperty(this, 1, te.getEnergyStored());
		}
		fluidAmount = te.getFluidAmount();
		powerAmount = te.getEnergyStored();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int index, int value) {
		switch (index) {
			case 0:
				te.setFluidAmount(value);
				break;
			case 1:
				te.setEnergyAmount(value);
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