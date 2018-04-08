package tamaized.dalquor.common.voidicpower;

import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import tamaized.tammodized.common.items.TamItem;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.capabilities.voidicInfusion.VoidicInfusionCapabilityHandler;
import tamaized.dalquor.common.capabilities.voidicPower.IVoidicPowerCapability;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class VoidicPowerItem extends TamItem {

	public static final int PLAYER_INV_SLOT_ARMOR_HELM = -5;
	public static final int PLAYER_INV_SLOT_ARMOR_CHEST = -4;
	public static final int PLAYER_INV_SLOT_ARMOR_LEGS = -3;
	public static final int PLAYER_INV_SLOT_ARMOR_BOOTS = -2;
	public static final int PLAYER_INV_SLOT_OFFHAND = -1;

	private static final Map<IVoidicPowerCapability, Integer> map = new HashMap<>();

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
				return capability == CapabilityList.VOIDICPOWER ? CapabilityList.VOIDICPOWER.<T>cast(inst) : null;
			}

			@Override
			public NBTTagCompound serializeNBT() {
				return (NBTTagCompound) CapabilityList.VOIDICPOWER.getStorage().writeNBT(CapabilityList.VOIDICPOWER, inst, null);
			}

			@Override
			public void deserializeNBT(NBTTagCompound nbt) {
				CapabilityList.VOIDICPOWER.getStorage().readNBT(CapabilityList.VOIDICPOWER, inst, null, nbt);
			}

		};
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged || ((oldStack.isEmpty() || newStack.isEmpty()) || oldStack.getItem() != newStack.getItem());
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		IVoidicPowerCapability cap = stack.getCapability(CapabilityList.VOIDICPOWER, null);
		if (cap != null) {
			if (!world.isRemote) {
				if (cap.isDefault()) {
					cap.setValues(getDefaultVoidicPower(), getDefaultMaxVoidicPower());
					cap.setDefault(false);
				}
				if (cap.isDirty() && entity instanceof EntityPlayerMP) {
					EntityPlayerMP player = (EntityPlayerMP) entity;
					switch (itemSlot) {
						case 0:
							if (ItemStack.areItemStacksEqual(stack, player.inventory.armorInventory.get(0))) {
								//								VoidCraft.network.sendTo(new ClientPacketHandlerPowerItem.Packet(PLAYER_INV_SLOT_ARMOR_HELM, player.inventory.armorInventory.get(0), cap), player);
								VoidicInfusionCapabilityHandler.uWotM8(player.inventory.armorInventory.get(0), cap);
								break;
							} else if (ItemStack.areItemStacksEqual(stack, player.inventory.offHandInventory.get(0))) {
								//								VoidCraft.network.sendTo(new ClientPacketHandlerPowerItem.Packet(PLAYER_INV_SLOT_OFFHAND, player.inventory.offHandInventory.get(0), cap), player);
								VoidicInfusionCapabilityHandler.uWotM8(player.inventory.offHandInventory.get(0), cap);
								break;
							}
						case 1:
							if (ItemStack.areItemStacksEqual(stack, player.inventory.armorInventory.get(1))) {
								//								VoidCraft.network.sendTo(new ClientPacketHandlerPowerItem.Packet(PLAYER_INV_SLOT_ARMOR_CHEST, player.inventory.armorInventory.get(1), cap), player);
								VoidicInfusionCapabilityHandler.uWotM8(player.inventory.armorInventory.get(1), cap);
								break;
							}
						case 2:
							if (ItemStack.areItemStacksEqual(stack, player.inventory.armorInventory.get(2))) {
								//								cap.sendUpdates(player, PLAYER_INV_SLOT_ARMOR_LEGS, stack);
								VoidicInfusionCapabilityHandler.uWotM8(player.inventory.armorInventory.get(2), cap);
								break;
							}
						case 3:
							if (ItemStack.areItemStacksEqual(stack, player.inventory.armorInventory.get(3))) {
								//								cap.sendUpdates(player, PLAYER_INV_SLOT_ARMOR_BOOTS, stack);
								VoidicInfusionCapabilityHandler.uWotM8(player.inventory.armorInventory.get(3), cap);
								break;
							}
						default:
							if (itemSlot < player.inventory.mainInventory.size() && ItemStack.areItemStacksEqual(stack, player.inventory.mainInventory.get(itemSlot)))
								VoidicInfusionCapabilityHandler.uWotM8(player.inventory.mainInventory.get(itemSlot), cap);
							//								cap.sendUpdates(player, itemSlot, stack);
							break;
					}
				}
			} else {
				NBTTagCompound nbt = stack.getOrCreateSubCompound(DalQuor.modid);
				cap.setValues(nbt.getInteger("currPower"), nbt.getInteger("maxPower"));
			}
			if (cap.isInUse()) {
				if (map.containsKey(cap)) {
					map.put(cap, map.get(cap) + useAmount());
					if (map.get(cap) >= cap.getCurrentPower()) {
						if (entity instanceof EntityLivingBase)
							onPlayerStoppedUsing(stack, world, (EntityLivingBase) entity, 0);
					}
				} else {
					map.put(cap, useAmount());
					if (map.get(cap) >= cap.getCurrentPower()) {
						if (entity instanceof EntityLivingBase)
							onPlayerStoppedUsing(stack, world, (EntityLivingBase) entity, 0);
					}
				}
			} else {
				if (map.containsKey(cap)) {
					cap.drain(map.get(cap));
					map.remove(cap);
				}
			}
			if (cap.getCurrentPower() < 0)
				cap.setCurrentPower(0);
			if (cap.getCurrentPower() > cap.getMaxPower())
				cap.setCurrentPower(cap.getMaxPower());
		}
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		IVoidicPowerCapability cap = null;
		if (stack.hasCapability(CapabilityList.VOIDICPOWER, null)) {
			cap = stack.getCapability(CapabilityList.VOIDICPOWER, null);
			NBTTagCompound nbt = stack.getOrCreateSubCompound(DalQuor.modid);
			cap.setValues(nbt.getInteger("currPower"), nbt.getInteger("maxPower"));
		}
		return cap != null && getAdjustedPerc(cap) < 1.0f;
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return 0xe900ff;
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
	public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
		IVoidicPowerCapability cap = stack.getCapability(CapabilityList.VOIDICPOWER, null);
		if (cap != null)
			tooltip.add(TextFormatting.DARK_PURPLE + "Power: " + (map.containsKey(cap) ? cap.getCurrentPower() - map.get(cap) : cap.getCurrentPower()) + "/" + cap.getMaxPower());
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		IVoidicPowerCapability cap = stack.getCapability(CapabilityList.VOIDICPOWER, null);
		if (cap != null)
			cap.setInUse(false);
		entityLiving.resetActiveHand();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!canBeUsed())
			return ActionResult.newResult(EnumActionResult.PASS, stack);
		IVoidicPowerCapability cap = stack.getCapability(CapabilityList.VOIDICPOWER, null);
		if (cap != null && cap.getCurrentPower() >= useAmount()) {
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
