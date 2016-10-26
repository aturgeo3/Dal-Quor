package Tamaized.Voidcraft.handlers;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayer;

public class VadeMecumPacketHandler {

	public static enum RequestType {
		NULL, CATEGORY_ADD, CATEGORY_REMOVE, CATEGORY_CLEAR, ACTIVE_ADD, ACTIVE_REMOVE, ACTIVE_CLEAR, ACTIVE_CURRENT_SET, PASSIVE_ADD, PASSIVE_REMOVE, PASSIVE_CLEAR
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
			case ACTIVE_ADD:
				capability.addActivePower(IVadeMecumCapability.getActivePowerFromID(bbis.readInt()));
			case ACTIVE_REMOVE:
				capability.removeActivePower(IVadeMecumCapability.getActivePowerFromID(bbis.readInt()));
				break;
			case ACTIVE_CLEAR:
				capability.clearActivePowers();
				break;
			case ACTIVE_CURRENT_SET:
				capability.setCurrentActive(IVadeMecumCapability.getActivePowerFromID(bbis.readInt()));
				break;
			case PASSIVE_ADD:
				capability.addPassivePower(IVadeMecumCapability.getPassivePowerFromID(bbis.readInt()));
			case PASSIVE_REMOVE:
				capability.removePassivePower(IVadeMecumCapability.getPassivePowerFromID(bbis.readInt()));
				break;
			case PASSIVE_CLEAR:
				capability.clearPassivePowers();
				break;
			default:
				break;
		}
	}

}
