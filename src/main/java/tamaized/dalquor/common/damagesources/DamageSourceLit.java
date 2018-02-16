package tamaized.dalquor.common.damagesources;

import net.minecraft.util.DamageSource;

public class DamageSourceLit extends DamageSource {

	public DamageSourceLit() {
		super("Lit");
		this.setDamageBypassesArmor();
		this.setMagicDamage();
	}

}
