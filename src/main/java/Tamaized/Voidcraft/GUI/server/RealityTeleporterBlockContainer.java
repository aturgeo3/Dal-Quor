package Tamaized.Voidcraft.GUI.server;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.slots.SlotOnlyItem;
import Tamaized.Voidcraft.machina.tileentity.TileEntityRealityTeleporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RealityTeleporterBlockContainer extends ContainerBase {

	private final TileEntityRealityTeleporter te;
	private int amount = 0;

	public RealityTeleporterBlockContainer(InventoryPlayer inventory, TileEntityRealityTeleporter host) {
		te = host;
		addSlotToContainer(new SlotOnlyItem(Item.getItemFromBlock(voidCraft.blocks.realityHole), te, 0, 176, 96));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				int index = j + i * 9 + 9;
				addSlotToContainer(new Slot(inventory, index, 86 + j * 18, 150 + i * 18));
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
			if (te != null && amount != te.getPowerAmount()) {
				icontainerlistener.sendProgressBarUpdate(this, 0, te.getPowerAmount());
				amount = te.getPowerAmount();
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int i, int v) {
		if (i == 0 && te != null) te.setPowerAmount(v);
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
				if (!getSlot(0).getHasStack() && te != null && te.isItemValidForSlot(0, itemstack1)) {
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