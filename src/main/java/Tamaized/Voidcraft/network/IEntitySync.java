package Tamaized.Voidcraft.network;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.Voidcraft.voidCraft;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public interface IEntitySync {

	default void sendPacketUpdates() {
		if (getEntity().world.isRemote) return;
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.ENTITY_UPDATES));
			outputStream.writeInt(getEntity().getEntityId());
			encodePacket(outputStream);
			FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);
			if (voidCraft.channel != null && packet != null) voidCraft.channel.sendToAllAround(packet, new TargetPoint(getEntity().world.provider.getDimension(), getEntity().posX, getEntity().posY, getEntity().posZ, 64));
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void encodePacket(DataOutputStream stream) throws IOException;

	void decodePacket(ByteBufInputStream stream) throws IOException;

	/**
	 * Return the entity implementing this interface so the interface can hook into the entity to grab specific values (EntityID, World, Position)
	 */
	Entity getEntity();

}
