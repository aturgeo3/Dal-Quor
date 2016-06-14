package Tamaized.Voidcraft.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.registry.IBasicVoid;

public class BasicVoidArmor extends ItemArmor implements IBasicVoid{
	
	private final String name;
	
	public BasicVoidArmor(ArmorMaterial armorMaterial, int par3, EntityEquipmentSlot par4, String type, String n) {
		super(armorMaterial, par3, par4);
		name = n;
		setUnlocalizedName(name);
		GameRegistry.register(this, new ResourceLocation(voidCraft.modid, "armors/"+n));
		this.setMaxStackSize(1);
		this.setCreativeTab(voidCraft.tabs.tabVoid);
	}
	
	@Override
	public String getName() {
		return name;
	}

}
