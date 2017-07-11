package tamaized.voidcraft.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.CapabilityList;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.network.client.ClientPacketHandlerVadeMecumUpdate;

public class ServerPacketHandlerVadeMecum implements IMessageHandler<ServerPacketHandlerVadeMecum.Packet, IMessage> {

	private static int getRequestID(RequestType type) {
		return type.ordinal();
	}

	private static RequestType getRequestFromID(int id) {
		return id > RequestType.values().length - 1 ? RequestType.NULL : RequestType.values()[id];
	}

	private static void processPacket(Packet message, EntityPlayerMP player, World world) {
		IVadeMecumCapability capability = player.getCapability(CapabilityList.VADEMECUM, null);
		switch (getRequestFromID(message.request)) {
			case OPEN:
				VoidCraft.network.sendTo(new ClientPacketHandlerVadeMecumUpdate.Packet(capability, ClientPacketHandlerVadeMecumUpdate.Type.Normal), player);
				break;
			case SPELLS_PAGE:
				capability.setPage(message.object);
				VoidCraft.network.sendTo(new ClientPacketHandlerVadeMecumUpdate.Packet(capability, ClientPacketHandlerVadeMecumUpdate.Type.Spells), player);
				break;
			case CATEGORY_ADD:
				capability.addCategory(player, IVadeMecumCapability.getCategoryFromID(message.object));
				break;
			case CATEGORY_REMOVE:
				capability.removeCategory(IVadeMecumCapability.getCategoryFromID(message.object));
				break;
			case CATEGORY_CLEAR:
				capability.clearCategories();
				break;
			case ACTIVE_SET:
				IVadeMecumCapability.Category category = IVadeMecumCapability.getCategoryFromID(message.object);
				if (category != null && IVadeMecumCapability.isActivePower(category) && capability.hasCategory(category))
					capability.setCurrentActive(category);
				break;
			case ACTIVE_CLEAR:
				capability.clearActivePower();
				break;
			case PASSIVE:
				IVadeMecumCapability.Passive passive = IVadeMecumCapability.getPassiveFromID(message.object);
				if (capability.hasPassive(passive))
					capability.removePassive(passive);
				else if (capability.canHavePassive(passive))
					capability.addPassive(passive);
				break;
			default:
				break;
		}
	}

	@Override
	public IMessage onMessage(Packet message, MessageContext ctx) {
		EntityPlayerMP player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> processPacket(message, player, player.world));
		return null;
	}

	public enum RequestType {
		NULL, OPEN, CATEGORY_ADD, CATEGORY_REMOVE, CATEGORY_CLEAR, ACTIVE_SET, ACTIVE_CLEAR, PASSIVE, SPELLS_PAGE
	}

	public static class Packet implements IMessage {

		private int request;
		private int object;

		public Packet() {

		}

		public Packet(RequestType type, int objectID) {
			request = getRequestID(type);
			object = objectID;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			request = buf.readInt();
			object = buf.readInt();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(request);
			buf.writeInt(object);
		}
	}

}
