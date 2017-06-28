package tamaized.voidcraft.registry;

import Tamaized.TamModized.particles.ParticlePacketHandlerRegistry;
import tamaized.voidcraft.client.particles.network.VoidicDrillLaserPacketHandler;
import tamaized.voidcraft.client.particles.network.XiaLaserPacketHandler;

public class VoidCraftParticles {

	public static int drillRayHandler;
	public static int xiaTeleportHandler;

	static {
		drillRayHandler = ParticlePacketHandlerRegistry.register(new VoidicDrillLaserPacketHandler());
		xiaTeleportHandler = ParticlePacketHandlerRegistry.register(new XiaLaserPacketHandler());
	}

}
