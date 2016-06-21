package Tamaized.Voidcraft.power;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;


public class VoidicPowerItemHandler {
	
	public static void setItemValues(ItemStack stack, int power, int maxPower){
		NBTTagCompound ct = stack.getSubCompound(voidCraft.modid, true);
		ct.setInteger("voidicPower", power);
		ct.setInteger("maxVoidicPower", maxPower);
	}
	
	public static void setItemVoidicPower(ItemStack stack, int power){
		stack.getSubCompound(voidCraft.modid, true).setInteger("voidicPower", power);
	}
	
	public static int getItemVoidicPower(ItemStack stack){
		return stack.getSubCompound(voidCraft.modid, true).getInteger("voidicPower");
	}

	
	public static int getItemMaxVoidicPower(ItemStack stack){
		return stack.getSubCompound(voidCraft.modid, true).getInteger("maxVoidicPower");
	}
	
	public static float getItemVoidicPowerPerc(ItemStack stack){
		return ((float)getItemVoidicPower(stack))/((float)getItemMaxVoidicPower(stack));
	}
}
