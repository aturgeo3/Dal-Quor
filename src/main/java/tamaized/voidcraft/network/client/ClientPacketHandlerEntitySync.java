package tamaized.voidcraft.network.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.voidcraft.network.IEntitySync;

public class ClientPacketHandlerEntitySync implements IMessageHandler<ClientPacketHandlerEntitySync.Packet, IMessage> {

	@SideOnly(Side.CLIENT)
	private static void processPacket(Packet message, EntityPlayer player, World world) {
		Entity entity = world.getEntityByID(message.entityID);
		if (entity instanceof IEntitySync)
			((IEntitySync) entity).decodePacket(message.stream);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(Packet message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> processPacket(message, Minecraft.getMinecraft().player, Minecraft.getMinecraft().world));
		return null;
	}

	public static class Packet implements IMessage {

		private IEntitySync entity;
		private int entityID;
		private ByteBuf stream;

		public Packet() {

		}

		public Packet(IEntitySync e, int id) {
			entity = e;
			entityID = id;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			entityID = buf.readInt();
			stream = buf;
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(entityID);
			entity.encodePacket(buf);
		}
	}

}
