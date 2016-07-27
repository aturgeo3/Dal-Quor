package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.TamModized.particles.ParticleRegistry;
import Tamaized.TamModized.particles.TamParticle;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.particles.AcidFX;
import Tamaized.Voidcraft.particles.TestParticle;

public class VoidParticles implements ITamRegistry {

	public static int drillRay;

	@Override
	public void preInit() {
		drillRay = ParticleRegistry.registerParticle(TestParticle.class);
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
