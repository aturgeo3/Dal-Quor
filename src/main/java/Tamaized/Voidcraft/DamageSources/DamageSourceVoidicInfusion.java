package Tamaized.Voidcraft.DamageSources;

import net.minecraft.util.DamageSource;

public class DamageSourceVoidicInfusion extends DamageSource {

	public DamageSourceVoidicInfusion() {
		super("VoidicInfusion");
		this.setDamageBypassesArmor();
		this.setDamageIsAbsolute();
		this.setDamageAllowedInCreativeMode();
	}

}
