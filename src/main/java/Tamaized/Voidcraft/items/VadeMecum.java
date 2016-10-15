package Tamaized.Voidcraft.items;

import java.util.HashSet;

import Tamaized.TamModized.helper.RayTraceHelper;
import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.events.client.DebugEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class VadeMecum extends TamItem {

	public VadeMecum(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		if (player.isSneaking()) {
			player.getCapability(CapabilityList.VADEMECUM, null).toggleBookState();
			return EnumActionResult.FAIL;
		} else {
			IVadeMecumCapability cap = player.getCapability(CapabilityList.VADEMECUM, null);
			if (cap != null && cap.getBookState()) {
				RayTraceResult result = RayTraceHelper.tracePath(world, player, 32, 1, new HashSet<Entity>());
				if (result != null) {
					BlockPos bp = result.getBlockPos();
					EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, bp.getX(), bp.getY(), bp.getZ(), false);
					world.addWeatherEffect(entitylightningbolt);
					return EnumActionResult.FAIL;
				}
			}
		}
		return EnumActionResult.PASS;
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) openBook(playerIn, worldIn, playerIn.getPosition());
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!worldIn.isRemote) openBook(playerIn, worldIn, playerIn.getPosition());
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}

	private void openBook(EntityPlayer player, World world, BlockPos pos) {
		player.openGui(voidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.VadeMecum), world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (world.isRemote && entity instanceof EntityLivingBase && isSelected) {
			EntityLivingBase living = (EntityLivingBase) entity;
			IVadeMecumCapability cap = living.getCapability(CapabilityList.VADEMECUM, null);
			if (cap != null && cap.getBookState()) {
				boolean flag = false;
				if (entity == Minecraft.getMinecraft().thePlayer) {
					flag = Minecraft.getMinecraft().gameSettings.thirdPersonView == 0;
				}
				if (flag) {

					double pitch = living.rotationPitch < 0 ? 0 : (90 - living.rotationPitch);
					double yaw = Math.toRadians(living.rotationYaw + 10 + (pitch / 2));

					double range = 0.15D + ((pitch / 90) / 2);
					DebugEvent.debugMode = true;
					DebugEvent.textL = "pitch: " + pitch + "; range: " + range;

					double xOffset = (range * -Math.cos(yaw));
					double yOffset = 0.5D;
					double zOffset = (range * -Math.sin(yaw));

					world.spawnParticle(EnumParticleTypes.PORTAL, living.posX + xOffset, living.posY + yOffset, living.posZ + zOffset, 0, 0, 0);
				} else {
					double yaw = Math.toRadians(living.renderYawOffset - 50);

					double range = 0.63D;

					double xOffset = (range * -Math.cos(yaw));
					double yOffset = 0.25D;
					double zOffset = (range * -Math.sin(yaw));

					world.spawnParticle(EnumParticleTypes.PORTAL, living.posX + xOffset, living.posY + yOffset, living.posZ + zOffset, 0, 0, 0);
				}
			}
		}
	}

}
