package Tamaized.Voidcraft.helper;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class TempParticleHelper { // TODO: move this into TamModized

	public static void spawnVanillaParticleOnServer(World world, EnumParticleTypes particle, double x, double y, double z, double xS, double yS, double zS) {
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.PARTICLE));
			outputStream.writeInt(particle.getParticleID());
			outputStream.writeDouble(x);
			outputStream.writeDouble(y);
			outputStream.writeDouble(z);
			outputStream.writeDouble(xS);
			outputStream.writeDouble(yS);
			outputStream.writeDouble(zS);
			FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);
			if (voidCraft.channel != null && packet != null) voidCraft.channel.sendToAllAround(packet, new TargetPoint(world.provider.getDimension(), x, y, z, 64));
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void decodePacket(ByteBufInputStream stream) throws IOException {
		spawnVanillaParticleOnClient(net.minecraft.client.Minecraft.getMinecraft().world, EnumParticleTypes.getParticleFromId(stream.readInt()), stream.readDouble(), stream.readDouble(), stream.readDouble(), stream.readDouble(), stream.readDouble(), stream.readDouble());
	}

	public static void spawnVanillaParticleOnClient(World world, EnumParticleTypes particle, double x, double y, double z, double xS, double yS, double zS) {
		world.spawnParticle(particle, x, y, z, xS, yS, zS);
	}

}
