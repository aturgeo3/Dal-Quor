package Tamaized.Voidcraft.capabilities;

public interface IVoidicInfusionCapability {
	
	public boolean hasLoaded();
	public void setLoaded();
	
	public float preMaxHealth();
	public void setPreMaxHealth(float f);
	
	public float checkMaxHealth();
	public void setCheckMaxHealth(float f);
	
	public int getInfusion();
	public void setInfusion(int i);
	
	public int getMaxInfusion();
	public void setMaxInfusion(int i);
	
	public float getInfusionPerc();

}
