package Tamaized.Voidcraft.GUI.server;

import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VoidMaceratorContainer extends ContainerBase {

	private TileEntityVoidMacerator te;

	private int powerAmount = 0;
	private int cookAmount = 0;
	private int finishTick = 0;

	public VoidMaceratorContainer(InventoryPlayer inventory, TileEntityVoidMacerator tileEntity) {
		te = tileEntity;

		addSlotToContainer(new Slot(tileEntity, 0, 168, 100));
		addSlotToContainer(new SlotFurnaceOutput(inventory.player, tileEntity, 1, 225, 101));

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

			if (cookAmount != te.cookingTick) {
				cookAmount = te.cookingTick;
				icontainerlistener.sendProgressBarUpdate(this, 0, cookAmount);
			}

			if (finishTick != te.finishTick) {
				finishTick = te.finishTick;
				icontainerlistener.sendProgressBarUpdate(this, 1, finishTick);
			}

			if (powerAmount != te.getPowerAmount()) {
				powerAmount = te.getPowerAmount();
				icontainerlistener.sendProgressBarUpdate(this, 2, powerAmount);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int par2) {
		if (slot == 0) te.cookingTick = par2;
		if (slot == 1) te.finishTick = par2;
		if (slot == 2) te.setPowerAmount(par2);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int hoverSlot) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) inventorySlots.get(hoverSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (hoverSlot == 0) {
				if (!mergeItemStack(itemstack1, 2, 38, true)) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				if (te.canInsertItem(te.SLOT_INPUT, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (hoverSlot == 1) {
					if (!mergeItemStack(itemstack1, 2, 38, false)) {
						return ItemStack.EMPTY;
					}
				} else if (hoverSlot >= 2 && hoverSlot < 29) {
					if (!mergeItemStack(itemstack1, 29, 38, false)) {
						return ItemStack.EMPTY;
					}
				} else if (hoverSlot >= 29 && hoverSlot < 38) {
					if (!mergeItemStack(itemstack1, 2, 29, false)) {
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
		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return te.isUsableByPlayer(entityplayer);
	}
}