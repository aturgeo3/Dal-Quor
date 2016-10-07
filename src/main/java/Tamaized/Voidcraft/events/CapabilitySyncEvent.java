package Tamaized.Voidcraft.events;

import java.io.DataOutputStream;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;

public class CapabilitySyncEvent {

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		if (e.phase == TickEvent.Phase.END && e.side == Side.SERVER && e.player instanceof EntityPlayerMP) {
			IVadeMecumCapability cap = e.player.getCapability(CapabilityList.VADEMECUM, null);
			if (cap != null && cap.isDirty()) {
				sendPacketUpdates((EntityPlayerMP) e.player, cap);
				cap.resetDirty();
			}
		}
	}

	private void sendPacketUpdates(EntityPlayerMP player, IVadeMecumCapability cap) {
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.VADEMECUM_UPDATE));
			cap.encodePacket(outputStream);
			FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);
			if (voidCraft.channel != null && packet != null) voidCraft.channel.sendTo(packet, player);
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
