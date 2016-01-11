package Tamaized.Voidcraft.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidArmor extends ItemArmor {

	private String texturePath = voidCraft.modid+":textures/models/armor/";
	
	public VoidArmor(ArmorMaterial armorMaterial, int par3, int par4, String type) {
		super(armorMaterial, par3, par4);
		
		this.setMaxStackSize(1);
		this.setCreativeTab(voidCraft.tabs.tabVoid);
		this.setTextureName(type, par4);
	}
	
	private void setTextureName(String type, int armorPart){
		
		if(armorType == 0 || armorType == 1 || armorType == 3){
			this.texturePath += type + "_layer_1.png";
		
		}else{
			this.texturePath += type + "_layer_2.png";
			}
		
	}
	
	@Override
	public String getArmorTexture(ItemStack armor, Entity entity, int slot, String type) {
		return this.texturePath;
	}

}
