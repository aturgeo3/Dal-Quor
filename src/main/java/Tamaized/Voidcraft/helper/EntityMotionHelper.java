package Tamaized.Voidcraft.helper;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMotionHelper {

	public static void addMotion(Entity e, double x, double y, double z) {
		if (e.world.isRemote) return;
		if (e instanceof EntityPlayerMP) sendPacketToPlayer((EntityPlayerMP) e, x, y, z);
		else e.addVelocity(x, y, z);
	}

	private static void sendPacketToPlayer(EntityPlayerMP e, double x, double y, double z) {
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.PLAYER_MOTION));
			outputStream.writeDouble(x);
			outputStream.writeDouble(y);
			outputStream.writeDouble(z);
			FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);
			if (voidCraft.channel != null && packet != null) voidCraft.channel.sendTo(packet, e);
			bos.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@SideOnly(Side.CLIENT)
	public static void updatePlayerMotion(double x, double y, double z) {
		System.out.println(x + ", " + y + ", " + z);
		net.minecraft.client.Minecraft.getMinecraft().player.addVelocity(x, y, z);
	}

}
