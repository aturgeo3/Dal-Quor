package Tamaized.Voidcraft.helper;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.voidCraft;

public class EntityMotionHelper {

	public static void addMotion(Entity e, double x, double y, double z) {
		if (e.worldObj.isRemote) return;
		e.addVelocity(x, y, z);
		if (e instanceof EntityPlayerMP) sendPacketToPlayer((EntityPlayerMP) e, x, y, z);
	}

	private static void sendPacketToPlayer(EntityPlayerMP e, double x, double y, double z) {
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
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
		net.minecraft.client.Minecraft.getMinecraft().thePlayer.addVelocity(x, y, z);
	}

}
