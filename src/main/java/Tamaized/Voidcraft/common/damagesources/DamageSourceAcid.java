package Tamaized.Voidcraft.common.damagesources;

import net.minecraft.util.DamageSource;

public class DamageSourceAcid extends DamageSource {

	public DamageSourceAcid() {
		super("Acid");
		this.setDamageBypassesArmor();
		this.setMagicDamage();
	}

}
