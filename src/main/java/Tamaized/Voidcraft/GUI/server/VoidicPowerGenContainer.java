package Tamaized.Voidcraft.GUI.server;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicPowerGen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VoidicPowerGenContainer extends ContainerBase {

	private final TileEntityVoidicPowerGen te;

	private int fluidAmount = 0;
	private int powerAmount = 0;

	public VoidicPowerGenContainer(InventoryPlayer inventory, TileEntityVoidicPowerGen tileEntity) {
		te = tileEntity;

		addSlotToContainer(new Slot(tileEntity, 0, 130, 100));

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

			if (fluidAmount != te.getFluidAmount()) {
				fluidAmount = te.getFluidAmount();
				icontainerlistener.sendProgressBarUpdate(this, 0, fluidAmount);
			}

			if (powerAmount != te.getPowerAmount()) {
				powerAmount = te.getPowerAmount();
				icontainerlistener.sendProgressBarUpdate(this, 1, powerAmount);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int value) {
		switch (id) {
			case 0:
				te.setFluidAmount(value);
				break;
			case 1:
				te.setPowerAmount(value);
				break;
			default:
				break;
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int hoverSlot) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) inventorySlots.get(hoverSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (hoverSlot == 0) {
				if (!mergeItemStack(itemstack1, 1, 37, true)) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				if (!getSlot(0).getHasStack() && itemstack1.getItem() == voidCraft.fluids.getBucket().getItem()) {
					if (!mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (hoverSlot >= 1 && hoverSlot < 28) {
					if (!mergeItemStack(itemstack1, 28, 37, false)) {
						return ItemStack.EMPTY;
					}
				} else if (hoverSlot >= 28 && hoverSlot < 37) {
					if (!mergeItemStack(itemstack1, 1, 28, false)) {
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
				return null;
			}

			slot.onTake(player, itemstack1);
		}
		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.isUsableByPlayer(playerIn);
	}

}
