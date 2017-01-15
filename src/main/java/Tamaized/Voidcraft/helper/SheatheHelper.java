package Tamaized.Voidcraft.helper;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.damageSources.DamageSourceAcid;
import Tamaized.Voidcraft.damageSources.DamageSourceFrost;
import Tamaized.Voidcraft.damageSources.DamageSourceLit;
import Tamaized.Voidcraft.damageSources.DamageSourceVoidicInfusion;
import Tamaized.Voidcraft.potion.PotionSheathe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public final class SheatheHelper {

	private SheatheHelper() {

	}

	public static void onAttack(EntityLivingBase living, EntityLivingBase attacker) {
		if (living.getActivePotionEffect(voidCraft.potions.fireSheathe) != null) {
			attacker.attackEntityFrom(DamageSource.ON_FIRE, 2.0f);
		} else if (living.getActivePotionEffect(voidCraft.potions.frostSheathe) != null) {
			attacker.attackEntityFrom(new DamageSourceFrost(), 2.0f);
		} else if (living.getActivePotionEffect(voidCraft.potions.litSheathe) != null) {
			attacker.attackEntityFrom(new DamageSourceLit(), 2.0f);
		} else if (living.getActivePotionEffect(voidCraft.potions.acidSheathe) != null) {
			attacker.attackEntityFrom(new DamageSourceAcid(), 2.0f);
		} else if (living.getActivePotionEffect(voidCraft.potions.voidSheathe) != null) {
			attacker.attackEntityFrom(new DamageSourceVoidicInfusion(), 2.0f);
			IVoidicInfusionCapability cap = attacker.getCapability(CapabilityList.VOIDICINFUSION, null);
			if (cap != null) cap.addInfusion(600);
		}
	}

	public static void castSheathe(EntityLivingBase entity, PotionSheathe.Type type, int duration) {
		entity.removePotionEffect(voidCraft.potions.fireSheathe);
		entity.removePotionEffect(voidCraft.potions.litSheathe);
		entity.removePotionEffect(voidCraft.potions.frostSheathe);
		entity.removePotionEffect(voidCraft.potions.acidSheathe);
		entity.removePotionEffect(voidCraft.potions.voidSheathe);
		switch (type) {
			case Fire:
				entity.addPotionEffect(new PotionEffect(voidCraft.potions.fireSheathe, duration));
				break;
			case Frost:
				entity.addPotionEffect(new PotionEffect(voidCraft.potions.frostSheathe, duration));
				break;
			case Lit:
				entity.addPotionEffect(new PotionEffect(voidCraft.potions.litSheathe, duration));
				break;
			case Acid:
				entity.addPotionEffect(new PotionEffect(voidCraft.potions.acidSheathe, duration));
				break;
			case Void:
				entity.addPotionEffect(new PotionEffect(voidCraft.potions.voidSheathe, duration));
				break;
			default:
				break;
		}
	}

	public static float[] getColor(EntityLivingBase entity) {
		if (entity.getActivePotionEffect(voidCraft.potions.fireSheathe) != null) {
			return new float[] { 1.0f, 0.65f, 0.0f, 1.0f };
		} else if (entity.getActivePotionEffect(voidCraft.potions.frostSheathe) != null) {
			return new float[] { 0.0f, 1.0f, 1.0f, 1.0f };
		} else if (entity.getActivePotionEffect(voidCraft.potions.litSheathe) != null) {
			return new float[] { 1.0f, 1.0f, 1.0f, 1.0f };
		} else if (entity.getActivePotionEffect(voidCraft.potions.acidSheathe) != null) {
			return new float[] { 0.0f, 1.0f, 0.0f, 1.0f };
		} else if (entity.getActivePotionEffect(voidCraft.potions.voidSheathe) != null) {
			return new float[] { 0x77 / 255F, 0.0f, 0xFF / 255F, 1.0f };
		}
		return null;
	}

}
