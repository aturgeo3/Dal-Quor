package tamaized.dalquor.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import tamaized.dalquor.network.client.*;
import tamaized.dalquor.network.server.*;

public class NetworkMessages {

	private static int index = 0;

	public static void register(SimpleNetworkWrapper network) {
		// Server
		registerMessage(network, ServerPacketHandlerCustomElytra.class, ServerPacketHandlerCustomElytra.Packet.class, Side.SERVER);
		registerMessage(network, ServerPacketHandlerLinkClear.class, ServerPacketHandlerLinkClear.Packet.class, Side.SERVER);
		registerMessage(network, ServerPacketHandlerStarforgeCraft.class, ServerPacketHandlerStarforgeCraft.Packet.class, Side.SERVER);
		registerMessage(network, ServerPacketHandlerVadeMecum.class, ServerPacketHandlerVadeMecum.Packet.class, Side.SERVER);
		registerMessage(network, ServerPacketHandlerVadeMecumLastEntry.class, ServerPacketHandlerVadeMecumLastEntry.Packet.class, Side.SERVER);

		// Client
		registerMessage(network, ClientPacketHandlerHealth.class, ClientPacketHandlerHealth.Packet.class, Side.CLIENT);
		registerMessage(network, ClientPacketHandlerInfusion.class, ClientPacketHandlerInfusion.Packet.class, Side.CLIENT);
		registerMessage(network, ClientPacketHandlerPowerItem.class, ClientPacketHandlerPowerItem.Packet.class, Side.CLIENT);
		registerMessage(network, ClientPacketHandlerSheathe.class, ClientPacketHandlerSheathe.Packet.class, Side.CLIENT);
		registerMessage(network, ClientPacketHandlerVadeMecumUpdate.class, ClientPacketHandlerVadeMecumUpdate.Packet.class, Side.CLIENT);
	}

	private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(SimpleNetworkWrapper network, Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
		network.registerMessage(messageHandler, requestMessageType, index, side);
		index++;
	}

}
