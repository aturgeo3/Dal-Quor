package Tamaized.Voidcraft.capabilities;

import net.minecraft.util.ResourceLocation;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidicInfusionCapabilityHandler implements IVoidicInfusionCapability {
	
	public static final ResourceLocation ID = new ResourceLocation(voidCraft.modid, "VoidicInfusionCapabilityHandler");
	
	private float preMaxHealth = 20.0f;
	private boolean infused10 = false;
	private boolean infused90 = false;
	
	private boolean hasLoaded = false;
	
	public VoidicInfusionCapabilityHandler(){
		System.out.println("Registered");
	}

	@Override
	public float preMaxHealth() {
		return preMaxHealth;
	}
	
	@Override
	public void setPreMaxHealth(float f) {
		preMaxHealth = f;
	}
	
	@Override
	public boolean isInfused10() {
		return infused10;
	}
	
	@Override
	public void setInfused10(boolean b) {
		infused10 = b;
	}
	
	@Override
	public boolean isInfused90() {
		return infused90;
	}
	
	@Override
	public void setInfused90(boolean b) {
		infused90 = b;
	}
	
	@Override
	public boolean hasLoaded(){
		return hasLoaded;
	}
	
	@Override
	public void setLoaded(){
		hasLoaded = true;
	}
	
}
