package tamaized.voidcraft.common.gui.slots;

import tamaized.tammodized.common.tileentity.TamTileEntityInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotItemHandlerBypass extends SlotItemHandler { // TODO TamModized

	private final int i;

	public SlotItemHandlerBypass(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		i = index;
	}

	@Override
	public boolean canTakeStack(EntityPlayer playerIn) {
		IItemHandler handler = getItemHandler();
		return handler instanceof TamTileEntityInventory.ItemStackFilterHandler ? !((TamTileEntityInventory.ItemStackFilterHandler)handler).extractBypass(i, 1, true).isEmpty() : !handler.extractItem(i, 1, true).isEmpty();
	}

	@Override
	@Nonnull
	public ItemStack decrStackSize(int amount) {
		IItemHandler handler = getItemHandler();
		return handler instanceof TamTileEntityInventory.ItemStackFilterHandler ? ((TamTileEntityInventory.ItemStackFilterHandler)handler).extractBypass(i, amount, false) : handler.extractItem(i, amount, false);
	}
}
