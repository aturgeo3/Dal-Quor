package Tamaized.Voidcraft.power;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import Tamaized.Voidcraft.items.BasicVoidItems;

public abstract class VoidicPowerItem extends BasicVoidItems {

	public VoidicPowerItem(String n) {
		super(n);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(VoidicPowerItemHandler.getItemMaxVoidicPower(stack) <= 0){
			VoidicPowerItemHandler.setItemValues(stack, getDefaultVoidicPower(), getDefaultMaxVoidicPower());
		}
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return VoidicPowerItemHandler.getItemVoidicPowerPerc(stack) < 1.0f;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1 - VoidicPowerItemHandler.getItemVoidicPowerPerc(stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(ChatFormatting.DARK_RED+"Power: "+VoidicPowerItemHandler.getItemVoidicPower(stack)+"/"+VoidicPowerItemHandler.getItemMaxVoidicPower(stack));
	}
	
	protected abstract int getDefaultVoidicPower();
	
	protected abstract int getDefaultMaxVoidicPower();

}
