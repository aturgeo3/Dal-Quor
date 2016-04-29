package Tamaized.Voidcraft.tools.molten;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import Tamaized.Voidcraft.tools.BasicVoidItemSword;

public class MoltenSword extends BasicVoidItemSword {

	public MoltenSword(ToolMaterial material, String n) {
		super(material, n);
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase){
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        par2EntityLivingBase.addPotionEffect(new PotionEffect(9, 5*20));
        par2EntityLivingBase.addPotionEffect(new PotionEffect(18, 5*20));
        par2EntityLivingBase.setFire(100);
        return true;
    }

}
