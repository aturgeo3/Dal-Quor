package tamaized.voidcraft.common.capabilities.voidicInfusion;

import net.minecraft.entity.EntityLivingBase;

public interface IVoidicInfusionCapability {

	boolean hasLoaded();

	void load(EntityLivingBase living);

	void update(EntityLivingBase player);
	
	int getInfusion();

	void addInfusion(int amount);

	void setInfusion(int i);

	int getMaxInfusion();

	void setMaxInfusion(int i);

	float getInfusionPerc();

	boolean canFly(EntityLivingBase entity);
	
	int getXiaDefeats();
	
	void setXiaDefeats(int amount);

	void copyFrom(IVoidicInfusionCapability cap);

}
