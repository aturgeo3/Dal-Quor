package Tamaized.Voidcraft.tools;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;

public class moltenSword extends ItemSword {

	public moltenSword(ToolMaterial par2EnumToolMaterial) {
		super(par2EnumToolMaterial);
	
	}
	
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase){
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        par2EntityLivingBase.addPotionEffect(new PotionEffect(9, 5*20));
        par2EntityLivingBase.addPotionEffect(new PotionEffect(18, 5*20));
        par2EntityLivingBase.setFire(100);
        return true;
    }

}
