package Tamaized.Voidcraft;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.apache.logging.log4j.LogManager;

import Tamaized.TamModized.TamModBase;
import Tamaized.TamModized.TamModized;
import Tamaized.TamModized.proxy.AbstractProxy;
import Tamaized.Voidcraft.Addons.thaumcraft.VoidCraftThaum;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.blocks.TileEntityNoBreak;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityXiaCastle;
import Tamaized.Voidcraft.capabilities.IVoidicInfusionCapability;
import Tamaized.Voidcraft.capabilities.VoidicInfusionCapabilityHandler;
import Tamaized.Voidcraft.capabilities.VoidicInfusionCapabilityStorage;
import Tamaized.Voidcraft.entity.boss.EntityBossCorruptedPawn;
import Tamaized.Voidcraft.entity.boss.herobrine.EntityBossHerobrine;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineFireball;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineShadow;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineTNTPrimed;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineWitherSkull;
import Tamaized.Voidcraft.entity.boss.twins.EntityBossDol;
import Tamaized.Voidcraft.entity.boss.twins.EntityBossZol;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia2;
import Tamaized.Voidcraft.entity.ghost.EntityGhostBiped;
import Tamaized.Voidcraft.entity.ghost.EntityGhostPlayer;
import Tamaized.Voidcraft.entity.mob.EntityMobLich;
import Tamaized.Voidcraft.entity.mob.EntityMobSpectreChain;
import Tamaized.Voidcraft.entity.mob.EntityMobVoidWrath;
import Tamaized.Voidcraft.entity.mob.EntityMobWraith;
import Tamaized.Voidcraft.entity.mob.lich.EntityLichInferno;
import Tamaized.Voidcraft.entity.nonliving.AcidBall;
import Tamaized.Voidcraft.entity.nonliving.EntityHookShot;
import Tamaized.Voidcraft.entity.nonliving.EntityObsidianFlask;
import Tamaized.Voidcraft.entity.nonliving.VoidChain;
import Tamaized.Voidcraft.events.BlockBreakPlaceEvent;
import Tamaized.Voidcraft.events.DamageEvent;
import Tamaized.Voidcraft.events.PickUpEvent;
import Tamaized.Voidcraft.events.SpawnEvent;
import Tamaized.Voidcraft.events.VoidTickEvent;
import Tamaized.Voidcraft.handlers.CraftingHandler;
import Tamaized.Voidcraft.handlers.SkinHandler;
import Tamaized.Voidcraft.handlers.XiaFlightHandler;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.machina.tileentity.TileEntityRealityStabilizer;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCharger;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicPowerCable;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicPowerGen;
import Tamaized.Voidcraft.network.ServerPacketHandler;
import Tamaized.Voidcraft.registry.VoidCraftAchievements;
import Tamaized.Voidcraft.registry.VoidCraftArmors;
import Tamaized.Voidcraft.registry.VoidCraftBiomes;
import Tamaized.Voidcraft.registry.VoidCraftBlocks;
import Tamaized.Voidcraft.registry.VoidCraftCreativeTabs;
import Tamaized.Voidcraft.registry.VoidCraftFluids;
import Tamaized.Voidcraft.registry.VoidCraftItems;
import Tamaized.Voidcraft.registry.VoidCraftLootTables;
import Tamaized.Voidcraft.registry.VoidCraftMaterials;
import Tamaized.Voidcraft.registry.VoidCraftParticles;
import Tamaized.Voidcraft.registry.VoidCraftTERecipes;
import Tamaized.Voidcraft.registry.VoidCraftTools;
import Tamaized.Voidcraft.sound.BossMusicManager;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import Tamaized.Voidcraft.structures.voidFortress.MapGenVoidFortress;
import Tamaized.Voidcraft.structures.voidFortress.StructureVoidFortressPieces;
import Tamaized.Voidcraft.voidicInfusion.VoidicInfusionHandler;
import Tamaized.Voidcraft.world.WorldGeneratorVoid;
import Tamaized.Voidcraft.world.dim.TheVoid.WorldProviderVoid;
import Tamaized.Voidcraft.world.dim.Xia.WorldProviderXia;

@Mod(modid = voidCraft.modid, name = "VoidCraft", version = voidCraft.version, dependencies = "required-before:" + TamModized.modid + "@[" + TamModized.version + ",)")
public class voidCraft extends TamModBase {

	public static final String version = "0.9.0_DEV04";
	public static final String modid = "voidcraft";

	public static String getVersion() {
		return version;
	}

	@Instance(modid)
	public static voidCraft instance = new voidCraft();

	public static FMLEventChannel channel;
	public static final String networkChannelName = "VoidCraft";

	@SidedProxy(clientSide = "Tamaized.Voidcraft.proxy.ClientProxy", serverSide = "Tamaized.Voidcraft.proxy.ServerProxy")
	public static AbstractProxy proxy;

	public VoidTickEvent VoidTickEvent;

	public static final SkinHandler skinHandler = new SkinHandler();
	public static VoidicInfusionHandler infusionHandler = new VoidicInfusionHandler();

	// Public API Integrations
	public static VoidCraftThaum thaumcraftIntegration;

	public static VoidCraftMaterials materials;
	public static VoidCraftCreativeTabs tabs;
	public static VoidCraftTools tools;
	public static VoidCraftItems items;
	public static VoidCraftArmors armors;
	public static VoidCraftFluids fluids;
	public static VoidCraftBlocks blocks;
	public static VoidCraftBiomes biomes;
	public static VoidCraftAchievements achievements;
	public static VoidCraftLootTables lootTables;
	public static VoidCraftTERecipes teRecipes;
	public static VoidCraftParticles particles;

	public static final int dimensionIdVoid = -2;
	public static final int dimensionIdXia = -3;

	@Override
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = LogManager.getLogger("VoidCraft");

		logger.info("Uh oh, I guess we need to open a portal to the Void");
		logger.info("Starting VoidCraft PreInit");

		// Initialize Network
		channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(networkChannelName);

		// yo dawg, Register the Registers... i'm sorry
		register(materials = new VoidCraftMaterials());
		register(tabs = new VoidCraftCreativeTabs());
		register(tools = new VoidCraftTools());
		register(items = new VoidCraftItems());
		register(armors = new VoidCraftArmors());
		register(fluids = new VoidCraftFluids());
		register(blocks = new VoidCraftBlocks());
		register(biomes = new VoidCraftBiomes());
		register(achievements = new VoidCraftAchievements());
		register(lootTables = new VoidCraftLootTables());
		register(teRecipes = new VoidCraftTERecipes());
		register(particles = new VoidCraftParticles());

		// Register Sounds Events
		VoidSoundEvents.register();

		// Super here to start register stuff
		super.preInit(event);

		// API Loader
		if (Loader.isModLoaded("Thaumcraft")) {
			// logger.info("Thaumcraft Detected. Attempting to load API");
			try {
				// thaumcraftIntegration = new VoidCraftThaum();
				// logger.info("Loaded ThaumcraftAPI into VoidCraft");
			} catch (Exception e1) {
				logger.info("Error while adding ThaumcraftAPI into VoidCraft");
				e1.printStackTrace(System.err);
			}
		}

		// if(thaumcraftIntegration != null) thaumcraftIntegration.preInit();

		// Register Capabilities
		CapabilityManager.INSTANCE.register(IVoidicInfusionCapability.class, new VoidicInfusionCapabilityStorage(), VoidicInfusionCapabilityHandler.class);
		MinecraftForge.EVENT_BUS.register(new Tamaized.Voidcraft.capabilities.EventHandler());

		// Proxy Stuff
		proxy.preInit();
	}

	@Override
	@EventHandler
	public void init(FMLInitializationEvent event) {
		logger.info("Starting VoidCraft Init");

		super.init(event);

		// Run Skin Handler
		skinHandler.run();

		// Tile Entities
		GameRegistry.registerTileEntity(TileEntityVoidMacerator.class, "tileEntityVoidMacerator");
		GameRegistry.registerTileEntity(TileEntityVoidBox.class, "tileEntityVoidBox");
		GameRegistry.registerTileEntity(TileEntityVoidInfuser.class, "tileEntityVoidInfuser");
		GameRegistry.registerTileEntity(TileEntityHeimdall.class, blocks.Heimdall.getName());
		GameRegistry.registerTileEntity(TileEntityNoBreak.class, blocks.blockNoBreak.getName());
		GameRegistry.registerTileEntity(TileEntityAIBlock.class, "tileEntityAIBlock");
		GameRegistry.registerTileEntity(TileEntityXiaCastle.class, "tileEntityXiaCastle");
		GameRegistry.registerTileEntity(TileEntityVoidicPowerGen.class, "tileEntityVoidicPowerGen");
		GameRegistry.registerTileEntity(TileEntityVoidicPowerCable.class, "tileEntityVoidicPowerCable");
		GameRegistry.registerTileEntity(TileEntityVoidicCharger.class, blocks.voidicCharger.getName());
		GameRegistry.registerTileEntity(TileEntityRealityStabilizer.class, "tileEntityRealityStabilizer");

		// GUI Handler
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		// Register Events
		VoidTickEvent = new VoidTickEvent();
		MinecraftForge.EVENT_BUS.register(VoidTickEvent);
		MinecraftForge.EVENT_BUS.register(new PickUpEvent());
		MinecraftForge.EVENT_BUS.register(new SpawnEvent());
		MinecraftForge.EVENT_BUS.register(new CraftingHandler());
		MinecraftForge.EVENT_BUS.register(BossMusicManager.instance); // We want to give this class a tick updater
		MinecraftForge.EVENT_BUS.register(new BlockBreakPlaceEvent());
		MinecraftForge.EVENT_BUS.register(infusionHandler);
		MinecraftForge.EVENT_BUS.register(new DamageEvent());
		MinecraftForge.EVENT_BUS.register(new XiaFlightHandler());

		// Register Projectiles and other misc entities
		registerEntity(VoidChain.class, "VoidChain", this, 128, 1, true);
		registerEntity(AcidBall.class, "AcidBall", this, 128, 1, true);
		registerEntity(EntityHookShot.class, "HookShot", this, 128, 1, true);
		registerEntity(EntityHerobrineFireball.class, "HerobrineFireball", this, 128, 1, true);
		registerEntity(EntityObsidianFlask.class, "EntityObsidianFlask", this, 128, 1, true);
		registerEntity(EntityHerobrineWitherSkull.class, "HerobrineWitherSkull", this, 128, 1, true);
		registerEntity(EntityHerobrineTNTPrimed.class, "HerobrineTNTPrimed", this, 128, 1, true);
		registerEntity(EntityHerobrineShadow.class, "HerobrineShadow", this, 128, 1, true);
		registerEntity(EntityLichInferno.class, "LichInferno", this, 128, 1, true);

		// Register Dimensions
		DimensionManager.registerDimension(dimensionIdVoid, DimensionType.register("The Void", "_void", dimensionIdVoid, WorldProviderVoid.class, false));
		DimensionManager.registerDimension(dimensionIdXia, DimensionType.register("???", "_xia", dimensionIdXia, WorldProviderXia.class, false));

		// Register World Gen
		GameRegistry.registerWorldGenerator(new WorldGeneratorVoid(), 0);

		MapGenStructureIO.registerStructure(MapGenVoidFortress.Start.class, "VoidFortress");
		StructureVoidFortressPieces.registerNetherFortressPieces();

		// Register Mobs
		registerEntity(EntityMobWraith.class, "Wraith", this, 64, 1, true);
		registerEntity(EntityMobSpectreChain.class, "SpectreChain", this, 64, 1, true);
		registerEntity(EntityMobVoidWrath.class, "VoidWrath", this, 64, 1, true);
		registerEntity(EntityMobLich.class, "Lich", this, 64, 1, true);
		registerEntity(EntityBossCorruptedPawn.class, "VoidBoss", this, 64, 1, true);
		registerEntity(EntityBossHerobrine.class, "Herobrine", this, 64, 1, true);
		registerEntity(EntityBossDol.class, "Dol", this, 64, 1, true);
		registerEntity(EntityBossZol.class, "Zol", this, 64, 1, true);
		registerEntity(EntityBossXia.class, "Xia", this, 64, 1, true);
		registerEntity(EntityBossXia2.class, "Xia2", this, 64, 1, true);
		registerEntity(EntityGhostPlayer.class, "GhostPlayer", this, 64, 1, true);
		registerEntity(EntityGhostBiped.class, "GhostBiped", this, 64, 1, true);
		registerEntity(EntityHerobrineCreeper.class, "HerobrineCreeper", this, 64, 1, true);

		// ... and their eggs
		EntityRegistry.registerEgg(EntityMobWraith.class, 0xFFFFFF, 0x000000);
		EntityRegistry.registerEgg(EntityMobSpectreChain.class, 0xFFFFFF, 0xAA0077);
		EntityRegistry.registerEgg(EntityMobVoidWrath.class, 0xFF0000, 0x000000);
		EntityRegistry.registerEgg(EntityMobLich.class, 0xAA00FF, 0x000000);
		EntityRegistry.registerEgg(EntityBossCorruptedPawn.class, 0x000000, 0xFF0000);
		EntityRegistry.registerEgg(EntityBossHerobrine.class, 0xFF0000, 0xFFAA00);
		EntityRegistry.registerEgg(EntityBossDol.class, 0xAAFF00, 0x000000);
		EntityRegistry.registerEgg(EntityBossZol.class, 0x00AAFF, 0x000000);
		EntityRegistry.registerEgg(EntityBossXia.class, 0xAA00FF, 0xFFFF00);
		EntityRegistry.registerEgg(EntityBossXia2.class, 0xAA00FF, 0xFFFF00);

		// Register Biomes
		Biome.getBiome(6).getSpawnableList(EnumCreatureType.MONSTER).add(new SpawnListEntry(EntityMobLich.class, 10, 0, 1));

		// if(thaumcraftIntegration != null) thaumcraftIntegration.init();

		// Proxy Stuff
		proxy.init();

	}

	@Override
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		logger.info("Starting VoidCraft PostInit");

		super.postInit(e);

		// Register Network
		channel.register(new ServerPacketHandler());

		// Proxy Stuff
		proxy.postInit();
		// if(thaumcraftIntegration != null) thaumcraftIntegration.postInit();

	}
}