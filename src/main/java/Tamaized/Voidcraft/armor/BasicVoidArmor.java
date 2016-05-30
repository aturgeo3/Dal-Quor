package Tamaized.Voidcraft.armor;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.registry.IBasicVoid;

public class BasicVoidArmor extends ItemArmor implements IBasicVoid{
	
	private final String name;
	private String texturePath = voidCraft.modid+":textures/models/armor/";
	
	public BasicVoidArmor(ArmorMaterial armorMaterial, int par3, EntityEquipmentSlot par4, String type, String n) {
		super(armorMaterial, par3, par4);
		name = n;
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, "armors/"+n);
		this.setMaxStackSize(1);
		this.setCreativeTab(voidCraft.tabs.tabVoid);
	}
	
	@Override
	public String getName() {
		return name;
	}

}
