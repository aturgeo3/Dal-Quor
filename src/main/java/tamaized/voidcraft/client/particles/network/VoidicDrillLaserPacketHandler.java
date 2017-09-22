package tamaized.voidcraft.client.particles.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.tammodized.common.particles.ParticleHelper.IParticlePacketData;
import tamaized.tammodized.common.particles.ParticlePacketBase;
import tamaized.tammodized.common.particles.TamParticle;
import tamaized.voidcraft.client.particles.VoidicDrillLaser;
import tamaized.voidcraft.common.items.VoidicDrill.VoidDrillParticleData;

import java.io.IOException;

public class VoidicDrillLaserPacketHandler extends ParticlePacketBase {

	@Override
	public void encode(ByteBuf packet, IParticlePacketData data) {
		try {
			if (!(data instanceof VoidDrillParticleData))
				throw new IOException("Incorrect IParticlePacketData type: " + data);
			VoidDrillParticleData dat = (VoidDrillParticleData) data;
			boolean flag = dat.target != null;
			packet.writeBoolean(flag);
			if (flag) {
				packet.writeDouble(dat.target.x);
				packet.writeDouble(dat.target.y);
				packet.writeDouble(dat.target.z);
			}
			packet.writeInt(dat.id);
			packet.writeBoolean(dat.offhand);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TamParticle decode(ByteBuf packet, WorldClient world, Vec3d pos) {
		return new VoidicDrillLaser(world, pos, packet.readBoolean() ? new Vec3d(packet.readDouble(), packet.readDouble(), packet.readDouble()) : null, packet.readInt(), packet.readBoolean());
	}
}
