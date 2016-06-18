package Tamaized.Voidcraft.capabilities;

public interface IVoidicInfusionCapability {
	
	public boolean hasLoaded();
	public void setLoaded();
	
	public float preMaxHealth();
	public void setPreMaxHealth(float f);
	
	public boolean isInfused10();
	public void setInfused10(boolean b);
	
	public boolean isInfused90();
	public void setInfused90(boolean b);

}
