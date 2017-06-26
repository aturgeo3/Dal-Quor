package Tamaized.Voidcraft.network;

import Tamaized.TamModized.helper.MotionHelper;
import Tamaized.Voidcraft.common.voidicpower.VoidicPowerItem;
import Tamaized.Voidcraft.common.capabilities.CapabilityList;
import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.common.capabilities.voidicPower.IVoidicPowerCapability;
import Tamaized.Voidcraft.common.entity.EntityVoidNPC;
import Tamaized.Voidcraft.client.entity.animation.IAnimation;
import Tamaized.Voidcraft.common.entity.ghost.EntityGhostPlayerBase;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

public class ClientPacketHandler {

	public static int getPacketTypeID(PacketType type) {
		return type.ordinal();
	}

	public static PacketType getPacketTypeFromID(int id) {
		return PacketType.values()[id];
	}

	@SideOnly(Side.CLIENT)
	public static void processPacket(ByteBuf parBB) throws IOException {
		World world = Minecraft.getMinecraft().world;
		if (world == null)
			return;
		ByteBufInputStream bbis = new ByteBufInputStream(parBB);
		int pktType = bbis.readInt();
		switch (getPacketTypeFromID(pktType)) {
			case CLIENT_HEALTH: {
				Minecraft.getMinecraft().player.setHealth(bbis.readFloat());
			}
			break;
			case GHOSTPLAYER_UPDATES: {
				Entity entity = world.getEntityByID(bbis.readInt());
				if (entity instanceof EntityGhostPlayerBase) {
					((EntityGhostPlayerBase) entity).decodePacket(bbis);
				}
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
				if (checkStack.isEmpty() || stack.isEmpty() || !ItemStack.areItemStacksEqual(checkStack, stack))
					break;
				IVoidicPowerCapability cap = checkStack.getCapability(CapabilityList.VOIDICPOWER, null);
				if (cap != null) {
					cap.decodePacket(bbis);
				}
			}
			break;
			case VADEMECUM_UPDATE: {
				IVadeMecumCapability vadeMecumCap = Minecraft.getMinecraft().player.getCapability(CapabilityList.VADEMECUM, null);
				if (vadeMecumCap != null)
					vadeMecumCap.decodePacket(parBB, bbis);
			}
			break;
			case ANIMATIONS: {
				Entity entity = world.getEntityByID(bbis.readInt());
				if (entity instanceof EntityVoidNPC) {
					EntityVoidNPC npc = (EntityVoidNPC) entity;
					IAnimation animation = npc.constructAnimation(bbis.readInt());
					if (animation != null)
						animation.decodePacket(bbis);
				}
			}
			break;
			case ENTITY_UPDATES: {
				Entity entity = world.getEntityByID(bbis.readInt());
				if (entity instanceof IEntitySync)
					((IEntitySync) entity).decodePacket(bbis);
			}
			break;
			case SHEATHE: {
				Entity entity = world.getEntityByID(bbis.readInt());
				if (entity instanceof EntityLivingBase) {
					EntityLivingBase living = (EntityLivingBase) entity;
					living.addPotionEffect(new PotionEffect(Potion.getPotionById(bbis.readInt()), bbis.readInt()));
				}
			}
			break;
			case INFUSION_UPDATE: {
				Entity entity = world.getEntityByID(bbis.readInt());
				if (entity != null && entity instanceof EntityLivingBase) {
					IVoidicInfusionCapability cap = entity.getCapability(CapabilityList.VOIDICINFUSION, null);
					if (cap != null)
						cap.decodePacket(bbis);
				}
			}
			break;
			case PLAYER_MOTION: {
				MotionHelper.updatePlayerMotion(bbis.readDouble(), bbis.readDouble(), bbis.readDouble());
			}
			break;
			default: {

			}
			break;
		}
		bbis.close();
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

	public static enum PacketType {
		INFUSION_UPDATE, INFUSION_UPDATE_ALL, ENTITY_UPDATES, ANIMATIONS, SHEATHE, PLAYER_MOTION, VADEMECUM_UPDATE, VOIDICPOWERITEM, GHOSTPLAYER_UPDATES, CLIENT_HEALTH
	}
}
