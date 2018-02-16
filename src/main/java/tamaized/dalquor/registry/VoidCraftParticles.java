package tamaized.dalquor.registry;

import tamaized.tammodized.common.particles.ParticlePacketHandlerRegistry;
import tamaized.dalquor.client.particles.network.XiaLaserPacketHandler;

public class VoidCraftParticles {

	public static int xiaTeleportHandler;

	static {
		xiaTeleportHandler = ParticlePacketHandlerRegistry.register(new XiaLaserPacketHandler());
	}

}
