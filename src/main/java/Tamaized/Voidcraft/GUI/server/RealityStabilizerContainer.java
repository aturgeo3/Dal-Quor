package Tamaized.Voidcraft.GUI.server;

import Tamaized.Voidcraft.GUI.slots.SlotCantPlace;
import Tamaized.Voidcraft.machina.tileentity.TileEntityRealityStabilizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RealityStabilizerContainer extends ContainerBase {

	private TileEntityRealityStabilizer te;
	private int amount;

	public RealityStabilizerContainer(InventoryPlayer inventory, TileEntityRealityStabilizer tileEntity) {
		te = tileEntity;

		addSlotToContainer(new SlotCantPlace(tileEntity, 0, 176, 96));

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
			if (amount != te.getPowerAmount()) {
				icontainerlistener.sendProgressBarUpdate(this, 0, te.getPowerAmount());
			}
		}
		amount = te.getPowerAmount();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int par2) {
		if (slot == 0) te.setPowerAmount(par2);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int hoverSlot) {
		ItemStack itemstack = ItemStack.field_190927_a;
		Slot slot = (Slot) inventorySlots.get(hoverSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (hoverSlot == 0) {
				if (!mergeItemStack(itemstack1, 1, 37, true)) {
					return ItemStack.field_190927_a;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				if (!getSlot(0).getHasStack() && te.canInsertItem(0, itemstack1, null)) {
					if (!mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.field_190927_a;
					}
				} else if (hoverSlot >= 1 && hoverSlot < 28) {
					if (!mergeItemStack(itemstack1, 28, 37, false)) {
						return ItemStack.field_190927_a;
					}
				} else if (hoverSlot >= 28 && hoverSlot < 37) {
					if (!mergeItemStack(itemstack1, 1, 28, false)) {
						return ItemStack.field_190927_a;
					}
				}
			}

			if (itemstack1.func_190916_E() == 0) {
				slot.putStack(ItemStack.field_190927_a);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.func_190916_E() == itemstack.func_190916_E()) {
				return ItemStack.field_190927_a;
			}

			slot.func_190901_a(player, itemstack1);
		}
		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return te.isUseableByPlayer(entityplayer);
	}
}