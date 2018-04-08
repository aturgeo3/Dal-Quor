package tamaized.dalquor.registry;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tamaized.dalquor.common.entity.mob.EntityMobWraith;
import tamaized.dalquor.common.world.dim.dalquor.BiomeGenDream;
import tamaized.dalquor.common.world.dim.thevoid.BiomeGenVoid;
import tamaized.dalquor.common.world.dim.xia.BiomeGenXia;

import static net.minecraft.world.biome.Biome.BiomeProperties;
import static net.minecraft.world.biome.Biome.SpawnListEntry;

@Mod.EventBusSubscriber
public class ModBiomes {

	public static Biome biomeVoid;
	public static Biome biomeXia;

	public static Biome biomeDreamOverworld;
	public static Biome biomeDreamNether;
	public static Biome biomeDreamEnd;
	public static Biome biomeDreamVoid;

	@SubscribeEvent
	public static void register(RegistryEvent.Register<Biome> event) {
		float baseHeight = -1F;
		float heightVariation = 0.1F;
		BiomeProperties biomeVoidProp = new BiomeProperties("The Void").setBaseBiome("dalquor.biome_void").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeXiaProp = new BiomeProperties("???").setBaseBiome("dalquor.biome_xia").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();

		BiomeProperties biomeDreamOverworldProp = new BiomeProperties("Dream Overworld").setBaseBiome("dalquor.biome_dream_overworld").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeDreamNetherProp = new BiomeProperties("Dream Nether").setBaseBiome("dalquor.biome_dream_nether").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeDreamEndProp = new BiomeProperties("Dream End").setBaseBiome("dalquor.biome_dream_end").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeDreamVoidProp = new BiomeProperties("Dream Void").setBaseBiome("dalquor.biome_dream_void").setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();

		biomeVoid = new BiomeGenVoid(biomeVoidProp);
		biomeXia = new BiomeGenXia(biomeXiaProp);

		biomeDreamOverworld = new BiomeGenDream(biomeDreamOverworldProp, new SpawnListEntry(EntityZombie.class, 100, 0, 1));
		biomeDreamNether = new BiomeGenDream(biomeDreamNetherProp, new SpawnListEntry(EntityPigZombie.class, 100, 0, 1));
		biomeDreamEnd = new BiomeGenDream(biomeDreamEndProp, new SpawnListEntry(EntityEnderman.class, 100, 0, 1));
		biomeDreamVoid = new BiomeGenDream(biomeDreamVoidProp, new SpawnListEntry(EntityMobWraith.class, 100, 0, 1));

		biomeVoid.setRegistryName("dalquor.biome_void");
		biomeXia.setRegistryName("dalquor.biome_xia");

		biomeDreamOverworld.setRegistryName("dalquor.biome_dream_overworld");
		biomeDreamNether.setRegistryName("dalquor.biome_dream_nether");
		biomeDreamEnd.setRegistryName("dalquor.biome_dream_end");
		biomeDreamVoid.setRegistryName("dalquor.biome_dream_void");

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
