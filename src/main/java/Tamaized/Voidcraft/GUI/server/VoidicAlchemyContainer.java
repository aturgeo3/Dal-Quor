package Tamaized.Voidcraft.GUI.server;

import Tamaized.Voidcraft.GUI.slots.SlotCantPlace;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicAlchemy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VoidicAlchemyContainer extends ContainerBase {

	private TileEntityVoidicAlchemy te;

	private int powerAmount = 0;
	private int cookTick = 0;
	private int finishTick = 0;

	public VoidicAlchemyContainer(InventoryPlayer inventory, TileEntityVoidicAlchemy tileEntity) {
		this.te = tileEntity;

		this.addSlotToContainer(new SlotCantPlace(tileEntity, te.SLOT_OUTPUT, 176, 97));
		this.addSlotToContainer(new Slot(tileEntity, te.SLOT_INPUT_1, 152, 72));
		this.addSlotToContainer(new Slot(tileEntity, te.SLOT_INPUT_2, 141, 97));
		this.addSlotToContainer(new Slot(tileEntity, te.SLOT_INPUT_3, 152, 122));
		this.addSlotToContainer(new Slot(tileEntity, te.SLOT_INPUT_4, 200, 72));
		this.addSlotToContainer(new Slot(tileEntity, te.SLOT_INPUT_5, 211, 97));
		this.addSlotToContainer(new Slot(tileEntity, te.SLOT_INPUT_6, 200, 122));

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

			if (this.cookTick != te.cookingTick) {
				cookTick = te.cookingTick;
				icontainerlistener.sendProgressBarUpdate(this, 0, cookTick);
			}

			if (this.finishTick != te.finishTick) {
				finishTick = te.finishTick;
				icontainerlistener.sendProgressBarUpdate(this, 1, finishTick);
			}

			if (this.powerAmount != te.getPowerAmount()) {
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
		Slot slot = (Slot) this.inventorySlots.get(hoverSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (hoverSlot <= te.SLOTS_ALL.length) {
				if (!this.mergeItemStack(itemstack1, te.SLOTS_ALL.length + 1, te.SLOTS_ALL.length + 37, true)) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				if (te.canInsertItem(te.SLOT_INPUT_1, itemstack1, null)) {
					if (!this.mergeItemStack(itemstack1, te.SLOT_INPUT_1, te.SLOT_INPUT_6+1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (hoverSlot >= te.SLOTS_ALL.length + 1 && hoverSlot < te.SLOTS_ALL.length + 28) {
					if (!this.mergeItemStack(itemstack1, te.SLOTS_ALL.length + 28, te.SLOTS_ALL.length + 37, false)) {
						return ItemStack.EMPTY;
					}
				} else if (hoverSlot >= te.SLOTS_ALL.length + 28 && hoverSlot < te.SLOTS_ALL.length + 37) {
					if (!this.mergeItemStack(itemstack1, te.SLOTS_ALL.length + 1, te.SLOTS_ALL.length + 28, false)) {
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
