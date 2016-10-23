package Tamaized.Voidcraft.capabilities.voidicPower;

import net.minecraft.item.ItemStack;

public interface IVoidicPowerCapability {

	public void setInUse(boolean state);

	public boolean isInUse();

	public void setValues(int curr, int max);

	public void setCurrentPower(int curr);

	public void setMaxPower(int max);

	public int getCurrentPower();

	public int getMaxPower();

	public float getAmountPerc();

	public int fill(int amount);

	public int drain(int amount);

	public boolean isDefault();

	public void setDefault(boolean state);

	public boolean hasLoaded();

	public void setLoaded();
	
	public void markDirty();
	
	public boolean isDirty();

	public void copyFrom(IVoidicPowerCapability cap);

	public void sendUpdates(ItemStack stack);

}
