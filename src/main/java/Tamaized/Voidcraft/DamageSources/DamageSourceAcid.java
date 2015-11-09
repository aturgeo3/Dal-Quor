package Tamaized.Voidcraft.DamageSources;

import net.minecraft.util.DamageSource;

public class DamageSourceAcid extends DamageSource{

	public DamageSourceAcid() {
		super("Acid");
		this.setDamageBypassesArmor();
		this.setMagicDamage();
	}

}
