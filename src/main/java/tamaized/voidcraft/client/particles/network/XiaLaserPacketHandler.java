package tamaized.voidcraft.client.particles.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.Vec3d;
import tamaized.tammodized.common.particles.ParticleHelper.IParticlePacketData;
import tamaized.tammodized.common.particles.ParticlePacketBase;
import tamaized.tammodized.common.particles.TamParticle;
import tamaized.voidcraft.client.particles.XiaLaser;

import java.io.IOException;

public class XiaLaserPacketHandler extends ParticlePacketBase {

	@Override
	public void encode(ByteBuf packet, IParticlePacketData data) {
		try {
			if (!(data instanceof XiaLaserParticleData))
				throw new IOException("Incorrect IParticlePacketData type: " + data);
			XiaLaserParticleData dat = (XiaLaserParticleData) data;
			packet.writeInt(dat.entityID);
			packet.writeInt(dat.yaw);
			packet.writeInt(dat.pitch);
			packet.writeFloat(dat.colors[0]);
			packet.writeFloat(dat.colors[1]);
			packet.writeFloat(dat.colors[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TamParticle decode(ByteBuf packet, WorldClient world, Vec3d pos) {
		return new XiaLaser(world, pos, packet.readInt(), packet.readInt(), packet.readInt(), new float[]{packet.readFloat(), packet.readFloat(), packet.readFloat()});
	}

	public class XiaLaserParticleData implements IParticlePacketData {

		public final int yaw;
		final int entityID;
		final int pitch;
		final float[] colors;

		public XiaLaserParticleData(int entityID, int yaw, int pitch, float[] colors) {
			this.entityID = entityID;
			this.yaw = yaw;
			this.pitch = pitch;
			this.colors = colors;
		}

	}

}
