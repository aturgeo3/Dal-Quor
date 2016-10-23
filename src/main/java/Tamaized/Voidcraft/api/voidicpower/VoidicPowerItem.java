package Tamaized.Voidcraft.api.voidicpower;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mojang.realmsclient.gui.ChatFormatting;

import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public abstract class VoidicPowerItem extends TamItem {

	private static final Map<IVoidicPowerCapability, Integer> map = new HashMap<IVoidicPowerCapability, Integer>();

	public VoidicPowerItem(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new ICapabilitySerializable<NBTTagCompound>() {

			IVoidicPowerCapability inst = CapabilityList.VOIDICPOWER.getDefaultInstance();

			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
				return capability == CapabilityList.VOIDICPOWER;
			}

			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
				return capability == CapabilityList.VOIDICPOWER ? CapabilityList.VOIDICPOWER.<T> cast(inst) : null;
			}

			@Override
			public NBTTagCompound serializeNBT() {
				return (NBTTagCompound) CapabilityList.VOIDICPOWER.getStorage().writeNBT(CapabilityList.VOIDICPOWER, inst, null);
			}

			@Override
			public void deserializeNBT(NBTTagCompound nbt) {
				inst.setValues(getDefaultVoidicPower(), getDefaultMaxVoidicPower());
				CapabilityList.VOIDICPOWER.getStorage().readNBT(CapabilityList.VOIDICPOWER, inst, null, nbt);
			}

		};
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged ? true : (oldStack == null || newStack == null) ? true : oldStack.getItem() != newStack.getItem();
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		IVoidicPowerCapability cap = stack.getCapability(CapabilityList.VOIDICPOWER, null);
		if (cap != null) {
			if (cap.isDefault()) {
				cap.setValues(getDefaultVoidicPower(), getDefaultMaxVoidicPower());
				cap.setDefault(false);
			}
			//if (cap.isDirty()) {
			//	if (entity instanceof EntityPlayerMP) ((EntityPlayerMP) entity).sendContainerToPlayer(((EntityPlayerMP) entity).inventoryContainer);
			//}
			if (cap.isInUse()) {
				if (map.containsKey(cap)) {
					map.put(cap, map.get(cap) + useAmount());
					if (map.get(cap) >= cap.getCurrentPower()) {
						if (entity instanceof EntityLivingBase) onPlayerStoppedUsing(stack, world, (EntityLivingBase) entity, 0);
					}
				} else {
					map.put(cap, useAmount());
					if (map.get(cap) >= cap.getCurrentPower()) {
						if (entity instanceof EntityLivingBase) onPlayerStoppedUsing(stack, world, (EntityLivingBase) entity, 0);
					}
				}
			} else {
				if (map.containsKey(cap)) {
					cap.drain(map.get(cap));
					map.remove(cap);
				}
			}
			if (cap.getCurrentPower() < 0) cap.setCurrentPower(0);
			if (cap.getCurrentPower() > cap.getMaxPower()) cap.setCurrentPower(cap.getMaxPower());
		}
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		IVoidicPowerCapability cap = stack.getCapability(CapabilityList.VOIDICPOWER, null);
		return cap == null ? false : getAdjustedPerc(cap) < 1.0f;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		IVoidicPowerCapability cap = stack.getCapability(CapabilityList.VOIDICPOWER, null);
		return cap == null ? 0 : 1 - getAdjustedPerc(cap);
	}

	private double getAdjustedPerc(IVoidicPowerCapability cap) {
		return cap != null ? map.containsKey(cap) ? (float) (cap.getCurrentPower() - map.get(cap)) / (float) cap.getMaxPower() : cap.getAmountPerc() : 1.0f;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		IVoidicPowerCapability cap = stack.getCapability(CapabilityList.VOIDICPOWER, null);
		if (cap != null) tooltip.add(ChatFormatting.DARK_PURPLE + "Power: " + (map.containsKey(cap) ? cap.getCurrentPower() - map.get(cap) : cap.getCurrentPower()) + "/" + cap.getMaxPower());
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		IVoidicPowerCapability cap = stack.getCapability(CapabilityList.VOIDICPOWER, null);
		if (cap != null) cap.setInUse(false);
		entityLiving.resetActiveHand();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		if (!canBeUsed()) return ActionResult.newResult(EnumActionResult.PASS, stack);
		IVoidicPowerCapability cap = stack.getCapability(CapabilityList.VOIDICPOWER, null);
		if (cap != null && cap.getCurrentPower() > useAmount()) {
			player.setActiveHand(hand);
			cap.setInUse(true);
			return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
		}
		return ActionResult.newResult(EnumActionResult.FAIL, stack);
	}

	protected abstract int getDefaultVoidicPower();

	protected abstract int getDefaultMaxVoidicPower();

	protected abstract int useAmount();

	protected abstract boolean canBeUsed();

}
