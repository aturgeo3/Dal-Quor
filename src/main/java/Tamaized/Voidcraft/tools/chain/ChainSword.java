package Tamaized.Voidcraft.tools.chain;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import Tamaized.Voidcraft.tools.BasicVoidItemSword;

public class ChainSword extends BasicVoidItemSword {

	public ChainSword(ToolMaterial material, String n) {
		super(material, n);
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase){
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("slowness"), 5*20)); //slow
        return true;
    }

}
