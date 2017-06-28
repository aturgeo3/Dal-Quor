package tamaized.voidcraft.common.gui.slots;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class SlotOnlyClass extends SlotItemHandlerBypass { // TODO TamModized
	
	private final Class clazz;

	public SlotOnlyClass(Class validClass, IItemHandler inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		clazz = validClass;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return !stack.isEmpty() && clazz.isInstance(stack.getItem());
	}

}
