package Tamaized.Voidcraft.power;

public interface IVoidicPower {
	
	public int getPowerAmount();
	
	public int getMaxPower();
	
	public int maxPowerTransfer();
	
	public int recievePower(int a);
	
	public int sendPower(int limit);
	
	public void setPowerAmount(int amount);
}
