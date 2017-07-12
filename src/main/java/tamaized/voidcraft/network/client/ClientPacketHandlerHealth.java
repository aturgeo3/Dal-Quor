package tamaized.voidcraft.network.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientPacketHandlerHealth implements IMessageHandler<ClientPacketHandlerHealth.Packet, IMessage> {

	@SideOnly(Side.CLIENT)
	private static void processPacket(Packet message, EntityPlayer player, World world) {
		Minecraft.getMinecraft().player.setHealth(message.health);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(Packet message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> processPacket(message, Minecraft.getMinecraft().player, Minecraft.getMinecraft().world));
		return null;
	}

	public static class Packet implements IMessage {

		private float health;

		public Packet() {

		}

		public Packet(float hp) {
			health = hp;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			health = buf.readFloat();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeFloat(health);
		}
	}

}
