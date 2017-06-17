package Tamaized.Voidcraft.GUI.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class SlotCantPlaceOrRemove extends SlotItemHandlerBypass { // TODO TamModized

	public SlotCantPlaceOrRemove(IItemHandler p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}

	public boolean canTakeStack(EntityPlayer p_82869_1_) {
		return false;
	}

	public boolean isItemValid(ItemStack p_75214_1_) {
		return false;
	}

}
