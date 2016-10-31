package Tamaized.Voidcraft.damageSources;

import net.minecraft.util.DamageSource;

public class DamageSourceLit extends DamageSource {

	public DamageSourceLit() {
		super("Lit");
		this.setDamageBypassesArmor();
		this.setMagicDamage();
	}

}
