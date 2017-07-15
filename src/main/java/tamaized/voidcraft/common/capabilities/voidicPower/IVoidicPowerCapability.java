package tamaized.voidcraft.common.capabilities.voidicPower;

public interface IVoidicPowerCapability {

	void setInUse(boolean state);

	boolean isInUse();

	void setValues(int curr, int max);

	void setCurrentPower(int curr);

	void setMaxPower(int max);

	int getCurrentPower();

	int getMaxPower();

	float getAmountPerc();

	int fill(int amount);

	int drain(int amount);

	boolean isDefault();

	void setDefault(boolean state);

	boolean hasLoaded();

	void setLoaded();

	void markDirty();

	boolean isDirty();

	void copyFrom(IVoidicPowerCapability cap);

}
