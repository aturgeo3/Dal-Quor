package tamaized.dalquor.registry;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tamaized.dalquor.DalQuor;
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
		final String idVoid = "biome_void";
		final String idXia = "biome_xia";
		final String idDreamOverworld = "biome_dream_overworld";
		final String idDreamNether = "biome_dream_nether";
		final String idDreamEnd = "biome_dream_end";
		final String idDreamVoid = "biome_dream_void";

		float baseHeight = -1F;
		float heightVariation = 0.1F;

		BiomeProperties biomeVoidProp = makeProperties("The Void", idVoid).setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeXiaProp = makeProperties("???", idXia).setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeDreamOverworldProp = makeProperties("Dream Overworld", idDreamOverworld).setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeDreamNetherProp = makeProperties("Dream Nether", idDreamNether).setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeDreamEndProp = makeProperties("Dream End", idDreamEnd).setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();
		BiomeProperties biomeDreamVoidProp = makeProperties("Dream Void", idVoid).setBaseHeight(baseHeight).setHeightVariation(heightVariation).setTemperature(0.21F).setRainfall(0.0F).setRainDisabled();

		biomeVoid = new BiomeGenVoid(biomeVoidProp);
		biomeXia = new BiomeGenXia(biomeXiaProp);
		biomeDreamOverworld = new BiomeGenDream(biomeDreamOverworldProp, new SpawnListEntry(EntityZombie.class, 100, 0, 1));
		biomeDreamNether = new BiomeGenDream(biomeDreamNetherProp, new SpawnListEntry(EntityPigZombie.class, 100, 0, 1));
		biomeDreamEnd = new BiomeGenDream(biomeDreamEndProp, new SpawnListEntry(EntityEnderman.class, 100, 0, 1));
		biomeDreamVoid = new BiomeGenDream(biomeDreamVoidProp, new SpawnListEntry(EntityMobWraith.class, 100, 0, 1));

		event.getRegistry().register(biomeVoid.setRegistryName(DalQuor.modid, idVoid));
		event.getRegistry().register(biomeXia.setRegistryName(DalQuor.modid, idXia));
		event.getRegistry().register(biomeDreamOverworld.setRegistryName(DalQuor.modid, idDreamOverworld));
		event.getRegistry().register(biomeDreamNether.setRegistryName(DalQuor.modid, idDreamNether));
		event.getRegistry().register(biomeDreamEnd.setRegistryName(DalQuor.modid, idDreamEnd));
		event.getRegistry().register(biomeDreamVoid.setRegistryName(DalQuor.modid, idDreamVoid));
	}

	public static void init() {
		BiomeDictionary.addTypes(biomeVoid, BiomeDictionary.Type.VOID);
		BiomeDictionary.addTypes(biomeXia, BiomeDictionary.Type.VOID);
		BiomeDictionary.addTypes(biomeDreamOverworld, BiomeDictionary.Type.VOID);
		BiomeDictionary.addTypes(biomeDreamNether, BiomeDictionary.Type.VOID);
		BiomeDictionary.addTypes(biomeDreamEnd, BiomeDictionary.Type.VOID);
		BiomeDictionary.addTypes(biomeDreamVoid, BiomeDictionary.Type.VOID);
	}

	private static BiomeProperties makeProperties(String name, String id){
		return new BiomeProperties(name).setBaseBiome(DalQuor.modid + ":" + id);
	}

}
