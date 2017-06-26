package Tamaized.Voidcraft.client.particles.network;

import Tamaized.TamModized.particles.ParticleHelper.IParticlePacketData;
import Tamaized.TamModized.particles.ParticlePacketBase;
import Tamaized.TamModized.particles.TamParticle;
import Tamaized.Voidcraft.common.items.VoidicDrill.VoidDrillParticleData;
import Tamaized.Voidcraft.client.particles.VoidicDrillLaser;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.DataOutputStream;
import java.io.IOException;

public class VoidicDrillLaserPacketHandler extends ParticlePacketBase {

	@Override
	public void encode(DataOutputStream packet, IParticlePacketData data) throws IOException {
		if (!(data instanceof VoidDrillParticleData)) throw new IOException("Incorrect IParticlePacketData type: " + data);
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
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TamParticle decode(ByteBufInputStream packet, WorldClient world, Vec3d pos) {
		try {
			return new VoidicDrillLaser(world, pos, packet.readBoolean() ? new Vec3d(packet.readDouble(), packet.readDouble(), packet.readDouble()) : null, packet.readInt(), packet.readBoolean());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
