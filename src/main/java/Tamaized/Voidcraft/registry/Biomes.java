package Tamaized.Voidcraft.registry;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.world.dim.TheVoid.BiomeGenVoid;
import Tamaized.Voidcraft.world.dim.Xia.BiomeGenXia;

public class Biomes extends RegistryBase {

	public static Biome biomeVoid;
	public static Biome biomeXia;

	@Override
	public void preInit() {
		float baseHeight = -1F;
		float heightVariation = 0.1F;
		BiomeProperties biomeVoidProp = new BiomeProperties("The Void").setBaseBiome("voidcraft_biome_void").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeXiaProp = new BiomeProperties("???").setBaseBiome("voidcraft_biome_xia").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		
		biomeVoid = new BiomeGenVoid(biomeVoidProp);
		biomeXia = new BiomeGenXia(biomeXiaProp);
		
		biomeVoid.setRegistryName("voidcraft_biome_void");
		biomeXia.setRegistryName("voidcraft_biome_xia");
		
		GameRegistry.register(biomeVoid);
		GameRegistry.register(biomeXia);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {

	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void setupRender() {
		
	}

}
