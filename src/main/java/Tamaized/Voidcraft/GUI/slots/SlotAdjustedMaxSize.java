package Tamaized.Voidcraft.GUI.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotAdjustedMaxSize extends Slot {
	
	private final int stackSizeLimit;

	public SlotAdjustedMaxSize(IInventory inventoryIn, int index, int xPosition, int yPosition, int maxSize) {
		super(inventoryIn, index, xPosition, yPosition);
		stackSizeLimit = maxSize;
	}
	
	@Override
	public int getSlotStackLimit() {
		return stackSizeLimit < super.getSlotStackLimit() ? stackSizeLimit : super.getSlotStackLimit();
	}

}
