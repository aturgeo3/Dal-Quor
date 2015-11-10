package Tamaized.Voidcraft.registery;

import Tamaized.Voidcraft.world.dim.TheVoid.BiomeGenVoid;
import Tamaized.Voidcraft.world.dim.Xia.BiomeGenXia;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;

public class Biomes extends RegistryBase {

	public static BiomeGenBase biomeVoid;
	public static BiomeGenBase biomeXia;

	@Override
	public void preInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		Height bvoidmm = new Height(-1F, 0.1F);
		biomeVoid = new BiomeGenVoid(251).setBiomeName("The Void").setHeight(bvoidmm).setTemperatureRainfall(0.10F, 0.0F).setDisableRain();
		biomeXia = new BiomeGenXia(252).setBiomeName("???").setHeight(bvoidmm).setTemperatureRainfall(0.10F, 0.0F).setDisableRain();
		//BiomeManager.coolBiomes.add(new BiomeEntry(biomeVoid, 100));
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub

	}

}
