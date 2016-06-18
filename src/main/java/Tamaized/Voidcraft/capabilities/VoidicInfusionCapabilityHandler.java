package Tamaized.Voidcraft.capabilities;

import net.minecraft.util.ResourceLocation;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidicInfusionCapabilityHandler implements IVoidicInfusionCapability {
	
	public static final ResourceLocation ID = new ResourceLocation(voidCraft.modid, "VoidicInfusionCapabilityHandler");
	
	private float preMaxHealth = 20.0f;
	private float checkMaxHealth = 0.0f;
	private int infusion = 0;
	private int maxInfusion = 300;
	
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
	public float checkMaxHealth() {
		return checkMaxHealth;
	}

	@Override
	public void setCheckMaxHealth(float f) {
		checkMaxHealth = f;
	}

	@Override
	public int getInfusion() {
		return infusion;
	}

	@Override
	public void setInfusion(int i) {
		infusion = i;
	}

	@Override
	public int getMaxInfusion() {
		return maxInfusion;
	}

	@Override
	public void setMaxInfusion(int i) {
		maxInfusion = i;
	}
	
	@Override
	public float getInfusionPerc(){
		return (float)infusion/(float)maxInfusion;
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
