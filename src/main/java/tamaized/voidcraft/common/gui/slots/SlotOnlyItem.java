package tamaized.voidcraft.common.gui.slots;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class SlotOnlyItem extends SlotItemHandlerBypass { // TODO TamModized
	
	private final Item item;

	public SlotOnlyItem(Item validItem, IItemHandler inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		item = validItem;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return !stack.isEmpty() && stack.getItem() == item;
	}

}
