package Tamaized.Voidcraft.registry;

import Tamaized.Voidcraft.common.entity.mob.EntityMobWraith;
import Tamaized.Voidcraft.common.world.dim.thevoid.BiomeGenVoid;
import Tamaized.Voidcraft.common.world.dim.xia.BiomeGenXia;
import Tamaized.Voidcraft.common.world.dim.dalquor.BiomeGenDream;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraft.world.biome.Biome.*;

@Mod.EventBusSubscriber
public class VoidCraftBiomes {
	
	public static Biome biomeVoid;
	public static Biome biomeXia;
	
	public static Biome biomeDreamOverworld;
	public static Biome biomeDreamNether;
	public static Biome biomeDreamEnd;
	public static Biome biomeDreamVoid;

	@SubscribeEvent
	public static void register(RegistryEvent.Register<Biome> event){
		float baseHeight = -1F;
		float heightVariation = 0.1F;
		BiomeProperties biomeVoidProp = new BiomeProperties("The Void").setBaseBiome("voidcraft_biome_void").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeXiaProp = new BiomeProperties("???").setBaseBiome("voidcraft_biome_xia").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();

		BiomeProperties biomeDreamOverworldProp = new BiomeProperties("Dream Overworld").setBaseBiome("voidcraft_biome_dream_overworld").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeDreamNetherProp = new BiomeProperties("Dream Nether").setBaseBiome("voidcraft_biome_dream_nether").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeDreamEndProp = new BiomeProperties("Dream End").setBaseBiome("voidcraft_biome_dream_end").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeDreamVoidProp = new BiomeProperties("Dream Void").setBaseBiome("voidcraft_biome_dream_void").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();

		biomeVoid = new BiomeGenVoid(biomeVoidProp);
		biomeXia = new BiomeGenXia(biomeXiaProp);

		biomeDreamOverworld = new BiomeGenDream(biomeDreamOverworldProp, new SpawnListEntry(EntityZombie.class, 100, 0, 1));
		biomeDreamNether = new BiomeGenDream(biomeDreamNetherProp, new SpawnListEntry(EntityPigZombie.class, 100, 0, 1));
		biomeDreamEnd = new BiomeGenDream(biomeDreamEndProp, new SpawnListEntry(EntityEnderman.class, 100, 0, 1));
		biomeDreamVoid = new BiomeGenDream(biomeDreamVoidProp, new SpawnListEntry(EntityMobWraith.class, 100, 0, 1));

		biomeVoid.setRegistryName("voidcraft_biome_void");
		biomeXia.setRegistryName("voidcraft_biome_xia");

		biomeDreamOverworld.setRegistryName("voidcraft_biome_dream_overworld");
		biomeDreamNether.setRegistryName("voidcraft_biome_dream_nether");
		biomeDreamEnd.setRegistryName("voidcraft_biome_dream_end");
		biomeDreamVoid.setRegistryName("voidcraft_biome_dream_void");

		event.getRegistry().register(biomeVoid);
		event.getRegistry().register(biomeXia);

		event.getRegistry().register(biomeDreamOverworld);
		event.getRegistry().register(biomeDreamNether);
		event.getRegistry().register(biomeDreamEnd);
		event.getRegistry().register(biomeDreamVoid);
	}

	public static void init() {
		BiomeDictionary.addTypes(biomeVoid, BiomeDictionary.Type.VOID);
		BiomeDictionary.addTypes(biomeXia, BiomeDictionary.Type.VOID);

		BiomeDictionary.addTypes(biomeDreamOverworld, BiomeDictionary.Type.VOID);
		BiomeDictionary.addTypes(biomeDreamNether, BiomeDictionary.Type.VOID);
		BiomeDictionary.addTypes(biomeDreamEnd, BiomeDictionary.Type.VOID);
		BiomeDictionary.addTypes(biomeDreamVoid, BiomeDictionary.Type.VOID);
	}

}
