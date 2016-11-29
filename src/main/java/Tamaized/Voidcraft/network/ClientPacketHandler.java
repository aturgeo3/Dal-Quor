package Tamaized.Voidcraft.network;

import java.io.IOException;

import Tamaized.Voidcraft.api.voidicpower.VoidicPowerItem;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia2;
import Tamaized.Voidcraft.entity.boss.xia.render.EntityAnimationsXia;
import Tamaized.Voidcraft.helper.EntityMotionHelper;
import Tamaized.Voidcraft.helper.TempParticleHelper;
import Tamaized.Voidcraft.proxy.ClientProxy;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientPacketHandler {

	public static enum PacketType {
		INFUSION_UPDATE, INFUSION_UPDATE_ALL, XIA_ARMSTATE, XIA_ANIMATIONS, XIA_SHEATHE, PLAYER_MOTION, PARTICLE, VADEMECUM_UPDATE, VOIDICPOWERITEM
	}

	public static int getPacketTypeID(PacketType type) {
		return type.ordinal();
	}

	public static PacketType getPacketTypeFromID(int id) {
		return PacketType.values()[id];
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onClientPacket(ClientCustomPacketEvent event) {
		Minecraft.getMinecraft().addScheduledTask(new Runnable() {
			public void run() {
				try {
					processPacket(event.getPacket().payload());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SideOnly(Side.CLIENT)
	public static void processPacket(ByteBuf parBB) throws IOException {
		World theWorld = Minecraft.getMinecraft().world;
		Entity entity;
		ByteBufInputStream bbis = new ByteBufInputStream(parBB);
		int pktType = bbis.readInt();
		switch (getPacketTypeFromID(pktType)) {
			case PARTICLE: {
				TempParticleHelper.decodePacket(bbis);
			}
				break;
			case VOIDICPOWERITEM: {
				int slot = bbis.readInt();
				ItemStack stack = ItemStackNetworkHelper.decodeStack(parBB, bbis);
				ItemStack checkStack = ItemStack.EMPTY;
				switch (slot) {
					case VoidicPowerItem.PLAYER_INV_SLOT_OFFHAND:
						checkStack = Minecraft.getMinecraft().player.inventory.offHandInventory.get(0);
						break;
					case VoidicPowerItem.PLAYER_INV_SLOT_ARMOR_HELM:
						checkStack = Minecraft.getMinecraft().player.inventory.armorInventory.get(0);
						break;
					case VoidicPowerItem.PLAYER_INV_SLOT_ARMOR_CHEST:
						checkStack = Minecraft.getMinecraft().player.inventory.armorInventory.get(1);
						break;
					case VoidicPowerItem.PLAYER_INV_SLOT_ARMOR_LEGS:
						checkStack = Minecraft.getMinecraft().player.inventory.armorInventory.get(2);
						break;
					case VoidicPowerItem.PLAYER_INV_SLOT_ARMOR_BOOTS:
						checkStack = Minecraft.getMinecraft().player.inventory.armorInventory.get(3);
						break;
					default:
						checkStack = Minecraft.getMinecraft().player.inventory.mainInventory.get(slot);
						break;
				}
				if (checkStack.isEmpty() || stack.isEmpty() || !ItemStack.areItemStacksEqual(checkStack, stack)) break;
				IVoidicPowerCapability cap = checkStack.getCapability(CapabilityList.VOIDICPOWER, null);
				if (cap != null) {
					cap.decodePacket(bbis);
				}
			}
				break;
			case VADEMECUM_UPDATE: {
				IVadeMecumCapability vadeMecumCap = Minecraft.getMinecraft().player.getCapability(CapabilityList.VADEMECUM, null);
				if (vadeMecumCap != null) vadeMecumCap.decodePacket(bbis);
			}
				break;
			case XIA_ANIMATIONS: {
				entity = theWorld.getEntityByID(bbis.readInt());
				if (entity instanceof EntityBossXia) {
					EntityBossXia xia = (EntityBossXia) entity;
					xia.addAnimation(new EntityAnimationsXia(xia, EntityAnimationsXia.getAnimationFromID(bbis.readInt())));
				}
			}
				break;
			case XIA_ARMSTATE: {
				entity = theWorld.getEntityByID(bbis.readInt());
				if (entity instanceof EntityBossXia) {
					EntityBossXia xia = (EntityBossXia) entity;
					xia.setArmRotations(bbis.readFloat(), bbis.readFloat(), bbis.readFloat(), bbis.readFloat(), false);
				} else if (entity instanceof EntityBossXia2) {
					EntityBossXia2 xia = (EntityBossXia2) entity;
					xia.setArmRotations(bbis.readFloat(), bbis.readFloat(), bbis.readFloat(), bbis.readFloat(), false);
				}
			}
				break;
			case XIA_SHEATHE: {
				entity = theWorld.getEntityByID(bbis.readInt());
				if (entity instanceof EntityBossXia) {
					EntityBossXia xia = (EntityBossXia) entity;
					xia.addPotionEffect(new PotionEffect(Potion.getPotionById(bbis.readInt()), bbis.readInt()));
				}
			}
				break;
			case INFUSION_UPDATE: {
				ClientProxy.infusionHandler.amount = bbis.readInt();
				ClientProxy.infusionHandler.maxAmount = bbis.readInt();
			}
				break;
			case INFUSION_UPDATE_ALL: {
				int id = bbis.readInt();
				int amount = bbis.readInt();
				int maxAmount = bbis.readInt();
				entity = Minecraft.getMinecraft().world.getEntityByID(id);
				if (entity != null && entity.hasCapability(CapabilityList.VOIDICINFUSION, null)) {
					IVoidicInfusionCapability cap = entity.getCapability(CapabilityList.VOIDICINFUSION, null);
					cap.setInfusion(amount);
					cap.setMaxInfusion(maxAmount);
				}
			}
				break;
			case PLAYER_MOTION: {
				EntityMotionHelper.updatePlayerMotion(bbis.readDouble(), bbis.readDouble(), bbis.readDouble());
			}
				break;
			default: {

			}
				break;
		}
		bbis.close();
	}
}
