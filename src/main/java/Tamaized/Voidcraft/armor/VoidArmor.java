package Tamaized.Voidcraft.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import Tamaized.Voidcraft.common.voidCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class VoidArmor extends ItemArmor {

	private String texturePath = "voidcraft:textures/models/armor/";
	
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
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register){
		this.itemIcon = register.registerIcon(voidCraft.modid + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")+1));
	}
	
	@Override
	public String getArmorTexture(ItemStack armor, Entity entity, int slot, String type) {
		return this.texturePath;
	}

}
