package Tamaized.Voidcraft.damageSources;

import net.minecraft.util.DamageSource;

public class DamageSourceFrost extends DamageSource {

	public DamageSourceFrost() {
		super("Frost");
		this.setDamageBypassesArmor();
		this.setMagicDamage();
	}

}
