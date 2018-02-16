package tamaized.dalquor.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerPacketHandlerLinkClear implements IMessageHandler<ServerPacketHandlerLinkClear.Packet, IMessage> {

	private static void processPacket(Packet message, EntityPlayerMP player, World world) {
		int slot = message.slot;
		ItemStack stack = ItemStack.EMPTY;
		if (slot >= 0 && slot < player.inventory.mainInventory.size())
			stack = player.inventory.mainInventory.get(slot);
		else if (slot == -1)
			stack = player.inventory.offHandInventory.get(0);
	}

	@Override
	public IMessage onMessage(Packet message, MessageContext ctx) {
		EntityPlayerMP player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> processPacket(message, player, player.world));
		return null;
	}

	public static class Packet implements IMessage {

		public int slot;

		public Packet() {

		}

		public Packet(int slot) {
			this.slot = slot;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			slot = buf.readInt();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(slot);
		}
	}

}
