package tamaized.voidcraft.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.network.client.ClientPacketHandlerEntitySync;

public interface IEntitySync {

	default void sendPacketUpdates(Entity entity) {
		if (!entity.world.isRemote) {
			NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(entity.world.provider.getDimension(), entity.posX, entity.posY, entity.posZ, 64);
			VoidCraft.network.sendToAllAround(new ClientPacketHandlerEntitySync.Packet(this, entity.getEntityId()), point);
		}
	}

	void encodePacket(ByteBuf stream);

	void decodePacket(ByteBuf stream);

}
