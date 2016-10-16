package Tamaized.Voidcraft.items;

import java.util.HashSet;

import Tamaized.TamModized.helper.RayTraceHelper;
import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.capabilities.vadeMecumItem.IVadeMecumItemCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class VadeMecum extends TamItem {

	public VadeMecum(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new ICapabilitySerializable<NBTTagCompound>() {

			IVadeMecumItemCapability inst = CapabilityList.VADEMECUMITEM.getDefaultInstance();

			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
				return capability == CapabilityList.VADEMECUMITEM;
			}

			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
				return capability == CapabilityList.VADEMECUMITEM ? CapabilityList.VADEMECUMITEM.<T> cast(inst) : null;
			}

			@Override
			public NBTTagCompound serializeNBT() {
				return (NBTTagCompound) CapabilityList.VADEMECUMITEM.getStorage().writeNBT(CapabilityList.VADEMECUMITEM, inst, null);
			}

			@Override
			public void deserializeNBT(NBTTagCompound nbt) {
				CapabilityList.VADEMECUMITEM.getStorage().readNBT(CapabilityList.VADEMECUMITEM, inst, null, nbt);
			}

		};
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		dorightClick(world, player, stack);
		return super.onItemUse(stack, player, world, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		dorightClick(world, player, stack);
		return super.onItemRightClick(stack, world, player, hand);
	}

	private void dorightClick(World world, EntityPlayer player, ItemStack stack) {
		IVadeMecumItemCapability cap = stack.getCapability(CapabilityList.VADEMECUMITEM, null);
		if (cap == null || world.isRemote) return;
		if (player.isSneaking()) {
			cap.toggleBookState();
			return;
		} else {
			if (cap.getBookState()) {
				HashSet<Entity> exclude = new HashSet<Entity>();
				exclude.add(player);
				RayTraceResult result = RayTraceHelper.tracePath(world, player, 32, 1, exclude);
				if (result != null) {
					if (result.entityHit != null) {
						EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, result.entityHit.posX, result.entityHit.posY, result.entityHit.posZ, false);
						world.addWeatherEffect(entitylightningbolt);
					} else {
						BlockPos bp = result.getBlockPos();
						if (bp != null) {
							EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, bp.getX(), bp.getY(), bp.getZ(), false);
							world.addWeatherEffect(entitylightningbolt);
						}
					}
				}
			} else {
				openBook(player, world, player.getPosition());
			}
		}
	}

	private void openBook(EntityPlayer player, World world, BlockPos pos) {
		player.openGui(voidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.VadeMecum), world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (world.isRemote && entity instanceof EntityLivingBase && isSelected) {
			EntityLivingBase living = (EntityLivingBase) entity;
			IVadeMecumCapability playerCap = living.getCapability(CapabilityList.VADEMECUM, null);
			IVadeMecumItemCapability itemCap = stack.getCapability(CapabilityList.VADEMECUMITEM, null);
			if (itemCap != null && itemCap.getBookState()) {
				boolean flag = false;
				if (entity == Minecraft.getMinecraft().thePlayer) {
					flag = Minecraft.getMinecraft().gameSettings.thirdPersonView == 0;
				}
				if (flag) {
					if (voidCraft.config.getRenderFirstPersonParticles()) {
						double pitch180 = (180 - (living.rotationPitch + 90));
						double pitch90 = ((living.rotationPitch));
						double pitch1802 = ((living.rotationPitch + 90));
						double pitch = (1 + Math.cos(Math.toRadians(living.rotationPitch + 90))) / 2;
						double yaw = Math.toRadians(living.rotationYaw - 90);
						double range = ((pitch1802 < 90 ? pitch1802 : 180 - pitch1802) / 90) * 2.0D;
						double xOffset = range * -Math.cos(yaw);
						double yOffset = pitch * 2.85D;
						double zOffset = range * -Math.sin(yaw);
						double yaw2 = Math.toRadians(living.rotationYaw);
						double range2 = 0.25D;
						double xOffset2 = range2 * -Math.cos(yaw2);
						double zOffset2 = range2 * -Math.sin(yaw2);
						world.spawnParticle(EnumParticleTypes.PORTAL, living.posX + xOffset, living.posY + yOffset, living.posZ + zOffset, -xOffset + xOffset2, 1.15D - yOffset, -zOffset + zOffset2);
					}
				} else {
					if (voidCraft.config.getRenderThirdPersonParticles()) {
						double yaw = Math.toRadians(living.renderYawOffset - 50);
						double range = 0.63D;
						double sneakRange = 0.43D;
						double xOffset = entity.isSneaking() ? (sneakRange * -Math.cos(yaw)) : (range * -Math.cos(yaw));
						double yOffset = entity.isSneaking() ? -0.5D : 0.0D;
						double zOffset = entity.isSneaking() ? (sneakRange * -Math.sin(yaw)) : (range * -Math.sin(yaw));
						world.spawnParticle(EnumParticleTypes.PORTAL, living.posX + xOffset, living.posY + yOffset, living.posZ + zOffset, 0, 0, 0);
					}
				}
			}
		}
	}

}
