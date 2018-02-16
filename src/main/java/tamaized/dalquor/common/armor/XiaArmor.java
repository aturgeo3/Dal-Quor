package tamaized.dalquor.common.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import tamaized.tammodized.common.armors.TamArmor;

public class XiaArmor extends TamArmor {

	public XiaArmor(CreativeTabs tab, ArmorMaterial armorMaterial, int par3, EntityEquipmentSlot par4, String type, String n) {
		super(tab, armorMaterial, par3, par4, type, n);
		setHasSubtypes(true);
	}

}
