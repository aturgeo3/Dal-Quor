package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.particles.ParticlePacketHandlerRegistry;
import Tamaized.TamModized.particles.ParticleRegistry;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.particles.VoidicDrillLaser;
import Tamaized.Voidcraft.particles.network.VoidicDrillLaserPacketHandler;

public class VoidCraftParticles implements ITamRegistry {

	public static int drillRay;
	
	public static int drillRayHandler;

	@Override
	public void preInit() {
		drillRay = ParticleRegistry.registerParticle(VoidicDrillLaser.class);
		
		drillRayHandler = ParticlePacketHandlerRegistry.register(new VoidicDrillLaserPacketHandler());
	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {

	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return new ArrayList<ITamModel>();
	}

	@Override
	public String getModID() {
		return voidCraft.modid;
	}

}
