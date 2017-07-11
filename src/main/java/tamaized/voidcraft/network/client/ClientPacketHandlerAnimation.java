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
import tamaized.voidcraft.client.entity.animation.IAnimation;
import tamaized.voidcraft.common.entity.EntityVoidNPC;

public class ClientPacketHandlerAnimation implements IMessageHandler<ClientPacketHandlerAnimation.Packet, IMessage> {

	private static void processPacket(Packet message, EntityPlayer player, World world) {
		Entity entity = world.getEntityByID(message.entityID);
		if (entity instanceof EntityVoidNPC) {
			EntityVoidNPC npc = (EntityVoidNPC) entity;
			IAnimation animation = npc.constructAnimation(message.animation);
			if (animation != null)
				animation.decodePacket(message.stream);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(Packet message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> processPacket(message, Minecraft.getMinecraft().player, Minecraft.getMinecraft().world));
		return null;
	}

	public static class Packet implements IMessage {

		private int entityID;
		private int animation;
		private ByteBuf stream;
		private IAnimation ani;

		public Packet() {

		}

		public Packet(int entity, int animationID, IAnimation instance) {
			entityID = entity;
			animation = animationID;
			ani = instance;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			entityID = buf.readInt();
			animation = buf.readInt();
			stream = buf;
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(entityID);
			buf.writeInt(animation);
			ani.encodePacket(buf);
		}
	}

}
