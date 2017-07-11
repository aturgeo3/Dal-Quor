package tamaized.voidcraft.common.machina.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import tamaized.tammodized.common.tileentity.TamTileEntityInventory;
import tamaized.voidcraft.common.capabilities.CapabilityList;
import tamaized.voidcraft.common.capabilities.voidicInfusion.VoidicInfusionCapabilityHandler;
import tamaized.voidcraft.common.capabilities.voidicPower.IVoidicPowerCapability;
import tamaized.voidcraft.common.voidicpower.TileEntityVoidicPowerInventory;
import tamaized.voidcraft.common.voidicpower.VoidicPowerItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityVoidicCharger extends TileEntityVoidicPowerInventory {

	public TamTileEntityInventory.ItemStackFilterHandler SLOT_DEFAULT;

	public TileEntityVoidicCharger() {
		super();
	}

	@Override
	protected ItemStackHandler[] register() {
		SLOT_DEFAULT = new TamTileEntityInventory.ItemStackFilterHandler(new Class[]{VoidicPowerItem.class}, true, new Class[0], true) {
			@Nonnull
			@Override
			public ItemStack extractItem(int slot, int amount, boolean simulate) {
				IVoidicPowerCapability cap = getStackInSlot(0).getCapability(CapabilityList.VOIDICPOWER, null);
				if (cap != null && cap.getCurrentPower() == cap.getMaxPower())
					return super.extractItem(slot, amount, simulate);
				return ItemStack.EMPTY;
			}
		};
		SLOT_DEFAULT.setStackLimit(1);
		return new ItemStackHandler[]{SLOT_DEFAULT};
	}

	@Nullable
	@Override
	protected IItemHandler getCap(EnumFacing face) {
		return SLOT_DEFAULT;
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {

	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		return nbt;
	}

	@Override
	public int getMaxPower() {
		return 200000;
	}

	@Override
	public int maxPowerTransfer() {
		return 600;
	}

	@Override
	public boolean canOutputPower(EnumFacing face) {
		return false;
	}

	@Override
	public boolean canInputPower(EnumFacing face) {
		return face == EnumFacing.DOWN;
	}

	@Override
	public void onUpdate() {
		if (voidicPower > 0 && !SLOT_DEFAULT.getStackInSlot(0).isEmpty() && SLOT_DEFAULT.getStackInSlot(0).getItem() instanceof VoidicPowerItem && SLOT_DEFAULT.getStackInSlot(0).hasCapability(CapabilityList.VOIDICPOWER, null)) {
			IVoidicPowerCapability cap = SLOT_DEFAULT.getStackInSlot(0).getCapability(CapabilityList.VOIDICPOWER, null);
			if (cap.getAmountPerc() < 1.0f) {
				int amount = voidicPower >= maxPowerTransfer() ? maxPowerTransfer() : voidicPower;
				int overflow = cap.fill(amount);
				voidicPower -= (amount - overflow);
				//				cap.sendUpdates(null, 0, SLOT_DEFAULT.getStackInSlot(0));
				VoidicInfusionCapabilityHandler.uWotM8(SLOT_DEFAULT.getStackInSlot(0), cap);
			}
		}
	}

}
