package Tamaized.Voidcraft.GUI.slots;

import net.minecraftforge.items.IItemHandler;

public class SlotAdjustedMaxSize extends SlotItemHandlerBypass { // TODO TamModized
	
	private final int stackSizeLimit;

	public SlotAdjustedMaxSize(IItemHandler inventoryIn, int index, int xPosition, int yPosition, int maxSize) {
		super(inventoryIn, index, xPosition, yPosition);
		stackSizeLimit = maxSize;
	}
	
	@Override
	public int getSlotStackLimit() {
		return stackSizeLimit < super.getSlotStackLimit() ? stackSizeLimit : super.getSlotStackLimit();
	}

}
