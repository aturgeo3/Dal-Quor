package tamaized.dalquor.common.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.dalquor.common.blocks.tileentity.TileEntityStarForge;
import tamaized.dalquor.common.events.client.TextureStitch;
import tamaized.dalquor.common.gui.slots.SlotOnlyClass;
import tamaized.dalquor.common.gui.slots.SlotOnlyItem;
import tamaized.dalquor.common.starforge.IStarForgeTool;
import tamaized.dalquor.registry.VoidCraftBlocks;
import tamaized.dalquor.registry.VoidCraftItems;

public class StarForgeContainer extends Container {

	private TileEntityStarForge te;

	public StarForgeContainer(InventoryPlayer inventory, TileEntityStarForge tileEntity) {
		te = tileEntity;
		{
			addSlotToContainer(new SlotOnlyClass(IStarForgeTool.class, te.SLOT_INPUT_TOOL, 0, 81, 84) {
				@SideOnly(Side.CLIENT)
				@Override
				public String getSlotTexture() {
					return TextureStitch.guiSlot_Tool.getIconName();
				}
			});

			addSlotToContainer(new SlotOnlyItem(Item.getItemFromBlock(VoidCraftBlocks.cosmicMaterial), te.SLOT_INPUT_COSMICMATERIAL, 0, 37, 105) {
				@SideOnly(Side.CLIENT)
				@Override
				public String getSlotTexture() {
					return TextureStitch.guiSlot_Block.getIconName();
				}
			});

			addSlotToContainer(new SlotOnlyItem(VoidCraftItems.voidicDragonScale, te.SLOT_INPUT_DRAGONSCALE, 0, 59, 105) {
				@SideOnly(Side.CLIENT)
				@Override
				public String getSlotTexture() {
					return TextureStitch.guiSlot_Dragonscale.getIconName();
				}
			});

			addSlotToContainer(new SlotOnlyItem(VoidCraftItems.quoriFragment, te.SLOT_INPUT_QUORIFRAGMENT, 0, 81, 105) {
				@SideOnly(Side.CLIENT)
				@Override
				public String getSlotTexture() {
					return TextureStitch.guiSlot_QuoriFragment.getIconName();
				}
			});

			addSlotToContainer(new SlotOnlyItem(VoidCraftItems.astralEssence, te.SLOT_INPUT_ASTRALESSENCE, 0, 103, 105) {
				@SideOnly(Side.CLIENT)
				@Override
				public String getSlotTexture() {
					return TextureStitch.guiSlot_AstralEsssence.getIconName();
				}
			});

			addSlotToContainer(new SlotOnlyItem(VoidCraftItems.voidicPhlogiston, te.SLOT_INPUT_VOIDICPHLOG, 0, 125, 105) {
				@SideOnly(Side.CLIENT)
				@Override
				public String getSlotTexture() {
					return TextureStitch.guiSlot_VoidicPhlog.getIconName();
				}
			});
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 18 + j * 18, 127 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventory, i, 18 + i * 18, 185));
		}

		addSlotToContainer(new Slot(inventory, inventory.getSizeInventory() - 1, 162, 105) {

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

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.te.canInteractWith(entityplayer);
	}
}