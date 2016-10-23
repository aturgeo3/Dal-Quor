package Tamaized.Voidcraft.capabilities.voidicPower;

import Tamaized.Voidcraft.voidCraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class VoidicPowerCapabilityHandler implements IVoidicPowerCapability {

	public static final ResourceLocation ID = new ResourceLocation(voidCraft.modid, "VoidicPowerCapabilityHandler");

	private int currPower = 0;
	private int maxPower = 0;
	private boolean inUse = false;

	private boolean hasLoaded = false;
	private boolean isDefault = true;
	private boolean dirty = false;

	@Override
	public boolean hasLoaded() {
		return hasLoaded;
	}

	@Override
	public void setLoaded() {
		hasLoaded = true;
	}

	@Override
	public boolean isDefault() {
		return isDefault;
	}

	@Override
	public void setDefault(boolean state) {
		isDefault = state;
		markDirty();
	}

	@Override
	public void setInUse(boolean state) {
		inUse = state;
		markDirty();
	}

	@Override
	public boolean isInUse() {
		return inUse;
	}

	@Override
	public void setValues(int curr, int max) {
		currPower = curr;
		maxPower = max;
		markDirty();
	}

	@Override
	public void setCurrentPower(int curr) {
		currPower = curr;
		markDirty();
	}

	@Override
	public void setMaxPower(int max) {
		maxPower = max;
		markDirty();
	}

	@Override
	public int getCurrentPower() {
		return currPower;
	}

	@Override
	public int getMaxPower() {
		return maxPower;
	}

	@Override
	public float getAmountPerc() {
		return (float) currPower / (float) maxPower;
	}

	@Override
	public int fill(int amount) {
		if (amount < 0) return amount;
		markDirty();
		if (getCurrentPower() + amount > getMaxPower()) {
			setCurrentPower(getMaxPower());
			return (getCurrentPower() + amount) - getMaxPower();
		} else {
			setCurrentPower(getCurrentPower() + amount);
			return 0;
		}
	}

	@Override
	public int drain(int amount) {
		if (amount < 0) return amount;
		markDirty();
		if (getCurrentPower() - amount < 0) {
			setCurrentPower(0);
			return (getCurrentPower());
		} else {
			setCurrentPower(getCurrentPower() - amount);
			return amount;
		}
	}

	@Override
	public void markDirty() {
		dirty = true;
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public void copyFrom(IVoidicPowerCapability cap) {
		setValues(cap.getCurrentPower(), cap.getMaxPower());
		setDefault(cap.isDefault());
		setLoaded();
	}

	@Override
	public void sendUpdates(ItemStack stack) {
		//stack.serializeNBT();
		dirty = false;
	}

}
