package Tamaized.Voidcraft.particles.network;

import io.netty.buffer.ByteBufInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import scala.reflect.internal.Trees.If;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import Tamaized.TamModized.particles.ParticleHelper.IParticlePacketData;
import Tamaized.TamModized.particles.ParticleHelper.ParticleContructor;
import Tamaized.TamModized.particles.ParticlePacketBase;
import Tamaized.TamModized.particles.TamParticle;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.items.VoidicDrill.VoidDrillParticleData;

public class VoidicDrillLaserPacketHandler extends ParticlePacketBase {

	@Override
	public void encode(DataOutputStream packet, IParticlePacketData data) throws IOException {
		System.out.println("drillEncode");
		if (!(data instanceof VoidDrillParticleData)) throw new IOException("Incorrect IParticlePacketData type: " + data);
		VoidDrillParticleData dat = (VoidDrillParticleData) data;
		boolean flag = dat.target != null;
		packet.writeBoolean(flag);
		if (flag) {
			packet.writeDouble(dat.target.xCoord);
			packet.writeDouble(dat.target.yCoord);
			packet.writeDouble(dat.target.zCoord);
		}
		packet.writeBoolean(dat.offhand);
	}

	@Override
	public ParticleContructor decode(ByteBufInputStream packet, Class<? extends TamParticle> particleClass, WorldClient world, Vec3d pos) {
		System.out.println("drillDecode");
		try {
			ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
			classes.add(World.class);
			classes.add(Vec3d.class);
			classes.add(Vec3d.class);
			classes.add(boolean.class);
			ArrayList<Object> instances = new ArrayList<Object>();
			instances.add(world);
			instances.add(pos);
			instances.add(packet.readBoolean() ? new Vec3d(packet.readDouble(), packet.readDouble(), packet.readDouble()) : null);
			instances.add(packet.readBoolean());
			return new ParticleContructor(particleClass, classes, instances);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
