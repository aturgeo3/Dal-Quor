package Tamaized.Voidcraft.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import Tamaized.TamModized.items.TamItem;

import com.mojang.realmsclient.gui.ChatFormatting;

public abstract class VoidicPowerItem extends TamItem {
	
	protected static Map<ItemStack, Integer> powerStackInUse = new HashMap<ItemStack, Integer>();

	public VoidicPowerItem(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged ? true : oldStack.getItem() != newStack.getItem();
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (VoidicPowerItemHandler.getItemMaxVoidicPower(stack) <= 0) VoidicPowerItemHandler.setItemValues(stack, getDefaultVoidicPower(), getDefaultMaxVoidicPower());
		if (VoidicPowerItemHandler.getItemVoidicPower(stack) > VoidicPowerItemHandler.getItemMaxVoidicPower(stack)) VoidicPowerItemHandler.setItemVoidicPower(stack, VoidicPowerItemHandler.getItemMaxVoidicPower(stack));
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return getAdjustedItemVoidicPowerPerc(stack) < 1.0f;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1 - getAdjustedItemVoidicPowerPerc(stack);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(ChatFormatting.DARK_PURPLE + "Power: " + getAdjustedItemVoidicPower(stack) + "/" + VoidicPowerItemHandler.getItemMaxVoidicPower(stack));
	}

	private static float getAdjustedItemVoidicPowerPerc(ItemStack stack) {
		return ((float) getAdjustedItemVoidicPower(stack)) / ((float) VoidicPowerItemHandler.getItemMaxVoidicPower(stack));
	}
	
	private static int getAdjustedItemVoidicPower(ItemStack stack){
		return powerStackInUse.containsKey(stack) ? powerStackInUse.get(stack) : VoidicPowerItemHandler.getItemVoidicPower(stack);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(powerStackInUse.containsKey(stack)) stopUsing(stack);
	}
	
	protected void stopUsing(ItemStack stack){
		VoidicPowerItemHandler.setItemVoidicPower(stack, powerStackInUse.get(stack));
		powerStackInUse.remove(stack);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		if(!canBeUsed()) return ActionResult.newResult(EnumActionResult.PASS, stack);
		if (VoidicPowerItemHandler.getItemVoidicPower(stack) > 1) {
			player.setActiveHand(hand);
			return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
		}
		return ActionResult.newResult(EnumActionResult.FAIL, stack);
	}

	protected abstract int getDefaultVoidicPower();

	protected abstract int getDefaultMaxVoidicPower();
	
	protected abstract boolean canBeUsed();

}
