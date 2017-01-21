package Tamaized.Voidcraft.handlers;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayer;

public class VadeMecumPacketHandler {

	public static enum RequestType {
		NULL, CATEGORY_ADD, CATEGORY_REMOVE, CATEGORY_CLEAR, ACTIVE_SET, ACTIVE_CLEAR, PASSIVE_ADD, PASSIVE_REMOVE, PASSIVE_CLEAR
	}

	public static int getRequestID(RequestType type) {
		return type.ordinal();
	}

	public static RequestType getRequestFromID(int id) {
		return id > RequestType.values().length - 1 ? RequestType.NULL : RequestType.values()[id];
	}

	public static void ClientToServerRequest(DataOutputStream stream, EntityPlayer player, RequestType type, int enumID) throws IOException {
		stream.writeInt(getRequestID(type));
		stream.writeInt(enumID);
	}

	public static void DecodeRequestServer(ByteBufInputStream bbis, EntityPlayer player) throws IOException {
		IVadeMecumCapability capability = player.getCapability(CapabilityList.VADEMECUM, null);
		switch (getRequestFromID(bbis.readInt())) {
			case CATEGORY_ADD:
				capability.addCategory(IVadeMecumCapability.getCategoryFromID(bbis.readInt()));
				break;
			case CATEGORY_REMOVE:
				capability.removeCategory(IVadeMecumCapability.getCategoryFromID(bbis.readInt()));
				break;
			case CATEGORY_CLEAR:
				capability.clearCategories();
				break;
			case ACTIVE_SET:
				capability.setCurrentActive(IVadeMecumCapability.getCategoryFromID(bbis.readInt()));
				break;
			case ACTIVE_CLEAR:
				capability.clearActivePower();
				break;
			case PASSIVE_ADD:
				break;
			case PASSIVE_REMOVE:
				break;
			case PASSIVE_CLEAR:
				break;
			default:
				break;
		}
	}

}
