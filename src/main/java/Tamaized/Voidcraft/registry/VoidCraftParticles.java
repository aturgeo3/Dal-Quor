package Tamaized.Voidcraft.registry;

import Tamaized.TamModized.particles.ParticlePacketHandlerRegistry;
import Tamaized.Voidcraft.client.particles.network.VoidicDrillLaserPacketHandler;
import Tamaized.Voidcraft.client.particles.network.XiaLaserPacketHandler;

public class VoidCraftParticles {

	public static int drillRayHandler;
	public static int xiaTeleportHandler;

	static {
		drillRayHandler = ParticlePacketHandlerRegistry.register(new VoidicDrillLaserPacketHandler());
		xiaTeleportHandler = ParticlePacketHandlerRegistry.register(new XiaLaserPacketHandler());
	}

}
