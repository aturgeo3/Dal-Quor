package tamaized.dalquor.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;

public class ServerPacketHandlerVadeMecumLastEntry implements IMessageHandler<ServerPacketHandlerVadeMecumLastEntry.Packet, IMessage> {

	private static void processPacket(Packet message, EntityPlayerMP player, World world) {
		IVadeMecumCapability cap = player.getCapability(CapabilityList.VADEMECUM, null);
		if (cap != null)
			cap.setLastEntry(message.lastEntry);
	}

	@Override
	public IMessage onMessage(Packet message, MessageContext ctx) {
		EntityPlayerMP player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> processPacket(message, player, player.world));
		return null;
	}

	public static class Packet implements IMessage {

		private String lastEntry;

		public Packet() {

		}

		public Packet(String entry) {
			lastEntry = entry;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			lastEntry = ByteBufUtils.readUTF8String(buf);
		}

		@Override
		public void toBytes(ByteBuf buf) {
			ByteBufUtils.writeUTF8String(buf, lastEntry);
		}
	}

}
