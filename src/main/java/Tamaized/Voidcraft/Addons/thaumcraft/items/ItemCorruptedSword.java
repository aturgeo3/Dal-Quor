package Tamaized.Voidcraft.Addons.thaumcraft.items;

import Tamaized.Voidcraft.mobs.EntityVoidMob;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobVoidBoss;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;

public class ItemCorruptedSword extends ItemSword {

	public ItemCorruptedSword(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
	}
	
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase player){
        par1ItemStack.damageItem(1, player);
        player.heal(2);
        return true;
    }

}
