package Tamaized.Voidcraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.IVoidicInfusionCapability;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.render.EntityAnimationsXia;
import Tamaized.Voidcraft.helper.EntityMotionHelper;
import Tamaized.Voidcraft.proxy.ClientProxy;

@SideOnly(Side.CLIENT)
public class ClientPacketHandler {

	public static enum PacketType {
		INFUSION_UPDATE, INFUSION_UPDATE_ALL, XIA_ARMSTATE, XIA_ANIMATIONS, PLAYER_MOTION
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
		World theWorld = Minecraft.getMinecraft().theWorld;
		Entity entity;
		ByteBufInputStream bbis = new ByteBufInputStream(parBB);
		int pktType = bbis.readInt();
		switch (getPacketTypeFromID(pktType)) {
			case XIA_ANIMATIONS:
				entity = theWorld.getEntityByID(bbis.readInt());
				if (entity instanceof EntityBossXia) {
					EntityBossXia xia = (EntityBossXia) entity;
					xia.addAnimation(new EntityAnimationsXia(xia, EntityAnimationsXia.getAnimationFromID(bbis.readInt())));
				}
				break;
			case XIA_ARMSTATE:
				entity = theWorld.getEntityByID(bbis.readInt());
				if (entity instanceof EntityBossXia) {
					EntityBossXia xia = (EntityBossXia) entity;
					xia.setArmRotations(bbis.readFloat(), bbis.readFloat(), bbis.readFloat(), bbis.readFloat(), false);
					xia.setAction(xia.getActionFromID(bbis.readInt()));
				}
				break;
			case INFUSION_UPDATE:
				ClientProxy.infusionHandler.amount = bbis.readInt();
				ClientProxy.infusionHandler.maxAmount = bbis.readInt();
				break;
			case INFUSION_UPDATE_ALL:
				int id = bbis.readInt();
				int amount = bbis.readInt();
				int maxAmount = bbis.readInt();
				entity = Minecraft.getMinecraft().theWorld.getEntityByID(id);
				if (entity != null && entity.hasCapability(CapabilityList.VOIDICINFUSION, null)) {
					IVoidicInfusionCapability cap = entity.getCapability(CapabilityList.VOIDICINFUSION, null);
					cap.setInfusion(amount);
					cap.setMaxInfusion(maxAmount);
				}
				break;
			case PLAYER_MOTION:
				EntityMotionHelper.updatePlayerMotion(bbis.readDouble(), bbis.readDouble(), bbis.readDouble());
				break;
			default:
				break;
		}
		bbis.close();
	}
}
