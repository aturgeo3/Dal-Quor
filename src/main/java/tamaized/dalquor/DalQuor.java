package tamaized.dalquor;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import tamaized.dalquor.client.entity.boss.extra.EntityDolXia;
import tamaized.dalquor.common.blocks.TileEntityNoBreak;
import tamaized.dalquor.common.blocks.tileentity.TileEntityAIBlock;
import tamaized.dalquor.common.blocks.tileentity.TileEntityStarForge;
import tamaized.dalquor.common.capabilities.elytraFlying.ElytraFlyingCapabilityHandler;
import tamaized.dalquor.common.capabilities.elytraFlying.ElytraFlyingCapabilityStorage;
import tamaized.dalquor.common.capabilities.elytraFlying.IElytraFlyingCapability;
import tamaized.dalquor.common.capabilities.starforge.IStarForgeCapability;
import tamaized.dalquor.common.capabilities.starforge.StarForgeCapabilityHandler;
import tamaized.dalquor.common.capabilities.starforge.StarForgeCapabilityStorage;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.capabilities.vadeMecum.VadeMecumCapabilityHandler;
import tamaized.dalquor.common.capabilities.vadeMecum.VadeMecumCapabilityStorage;
import tamaized.dalquor.common.capabilities.vadeMecumItem.IVadeMecumItemCapability;
import tamaized.dalquor.common.capabilities.vadeMecumItem.VadeMecumItemCapabilityHandler;
import tamaized.dalquor.common.capabilities.vadeMecumItem.VadeMecumItemCapabilityStorage;
import tamaized.dalquor.common.capabilities.voidicInfusion.IVoidicInfusionCapability;
import tamaized.dalquor.common.capabilities.voidicInfusion.VoidicInfusionCapabilityHandler;
import tamaized.dalquor.common.capabilities.voidicInfusion.VoidicInfusionCapabilityStorage;
import tamaized.dalquor.common.capabilities.voidicPower.IVoidicPowerCapability;
import tamaized.dalquor.common.capabilities.voidicPower.VoidicPowerCapabilityHandler;
import tamaized.dalquor.common.capabilities.voidicPower.VoidicPowerCapabilityStorage;
import tamaized.dalquor.common.entity.boss.EntityBossCorruptedPawn;
import tamaized.dalquor.common.entity.boss.dragon.EntityVoidicDragon;
import tamaized.dalquor.common.entity.boss.herobrine.EntityBossHerobrine;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineFireball;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineShadow;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineTNTPrimed;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineWitherSkull;
import tamaized.dalquor.common.entity.boss.lob.EntityLordOfBlades;
import tamaized.dalquor.common.entity.boss.twins.EntityBossDol;
import tamaized.dalquor.common.entity.boss.twins.EntityBossZol;
import tamaized.dalquor.common.entity.boss.xia.EntityBossXia;
import tamaized.dalquor.common.entity.boss.xia.EntityBossXia2;
import tamaized.dalquor.common.entity.boss.xia.finalphase.EntityDragonXia;
import tamaized.dalquor.common.entity.boss.xia.finalphase.EntityWitherbrine;
import tamaized.dalquor.common.entity.boss.xia.finalphase.EntityZolXia;
import tamaized.dalquor.common.entity.companion.EntityCompanionFireElemental;
import tamaized.dalquor.common.entity.companion.EntityVoidParrot;
import tamaized.dalquor.common.entity.ghost.EntityGhostPlayer;
import tamaized.dalquor.common.entity.ghost.EntityGhostPlayerSlim;
import tamaized.dalquor.common.entity.mob.EntityMobEtherealGuardian;
import tamaized.dalquor.common.entity.mob.EntityMobLich;
import tamaized.dalquor.common.entity.mob.EntityMobSpectreChain;
import tamaized.dalquor.common.entity.mob.EntityMobVoidWrath;
import tamaized.dalquor.common.entity.mob.EntityMobWraith;
import tamaized.dalquor.common.entity.mob.dalquor.EntityHashalaq;
import tamaized.dalquor.common.entity.mob.lich.EntityLichInferno;
import tamaized.dalquor.common.entity.nonliving.EntityAcidBall;
import tamaized.dalquor.common.entity.nonliving.EntityBlockSpell;
import tamaized.dalquor.common.entity.nonliving.EntityCasterLightningBolt;
import tamaized.dalquor.common.entity.nonliving.EntityChainedSkull;
import tamaized.dalquor.common.entity.nonliving.EntityObsidianFlask;
import tamaized.dalquor.common.entity.nonliving.EntitySpellImplosion;
import tamaized.dalquor.common.entity.nonliving.EntitySpellRune;
import tamaized.dalquor.common.entity.nonliving.EntityVoidChain;
import tamaized.dalquor.common.entity.nonliving.ProjectileDisintegration;
import tamaized.dalquor.common.events.BlockBreakPlaceEvent;
import tamaized.dalquor.common.events.CapabilitySyncEvent;
import tamaized.dalquor.common.events.CraftingHandler;
import tamaized.dalquor.common.events.DamageEvent;
import tamaized.dalquor.common.events.DeathEvent;
import tamaized.dalquor.common.events.ItemEntityEvent;
import tamaized.dalquor.common.events.LitStrikeEvent;
import tamaized.dalquor.common.events.PickUpEvent;
import tamaized.dalquor.common.events.PlayerRightClickEvent;
import tamaized.dalquor.common.events.SpawnEvent;
import tamaized.dalquor.common.events.VoidTickEvent;
import tamaized.dalquor.common.events.VoidicInfusionHandler;
import tamaized.dalquor.common.events.XiaFlightHandler;
import tamaized.dalquor.common.gui.GuiHandler;
import tamaized.dalquor.common.handlers.ConfigHandler;
import tamaized.dalquor.common.handlers.ContributorHandler;
import tamaized.dalquor.common.starforge.StarForgeEffectRecipeList;
import tamaized.dalquor.common.starforge.effects.StarForgeEffectList;
import tamaized.dalquor.common.structures.voidcity.MapGenVoidCity;
import tamaized.dalquor.common.structures.voidcity.StructureVoidCityPieces;
import tamaized.dalquor.common.structures.voidfortress.MapGenVoidFortress;
import tamaized.dalquor.common.structures.voidfortress.StructureVoidFortressPieces;
import tamaized.dalquor.common.vademecum.progression.RitualList;
import tamaized.dalquor.common.world.WorldGeneratorVoid;
import tamaized.dalquor.common.world.dim.dalquor.WorldProviderDalQuor;
import tamaized.dalquor.common.world.dim.thevoid.ChunkProviderVoid;
import tamaized.dalquor.common.world.dim.thevoid.TeleporterVoid;
import tamaized.dalquor.common.world.dim.thevoid.WorldProviderVoid;
import tamaized.dalquor.common.world.dim.xia.TeleporterXia;
import tamaized.dalquor.common.world.dim.xia.WorldProviderXia;
import tamaized.dalquor.network.NetworkMessages;
import tamaized.dalquor.proxy.CommonProxy;
import tamaized.dalquor.registry.ModAdvancements;
import tamaized.dalquor.registry.ModArmors;
import tamaized.dalquor.registry.ModBiomes;
import tamaized.dalquor.registry.ModBlocks;
import tamaized.dalquor.registry.ModCreativeTabs;
import tamaized.dalquor.registry.ModFluids;
import tamaized.dalquor.registry.ModItems;
import tamaized.dalquor.registry.ModLootTables;
import tamaized.dalquor.registry.ModMaterials;
import tamaized.dalquor.registry.ModParticles;
import tamaized.dalquor.registry.ModPotions;
import tamaized.dalquor.registry.ModTools;
import tamaized.tammodized.TamModBase;
import tamaized.tammodized.TamModized;
import tamaized.tammodized.proxy.AbstractProxy;
import tamaized.tammodized.registry.PortalHandlerRegistry;

@Mod(modid = DalQuor.modid, name = "Dal Quor", version = DalQuor.version, acceptedMinecraftVersions = "[1.12,)", dependencies = "required-before:" + TamModized.modid + "@[${tamversion},)")
public class DalQuor extends TamModBase {

	public static final String version = "${version}";
	public static final String modid = "dalquor";
	public static final ModMaterials materials = new ModMaterials();
	public static final ModCreativeTabs tabs = new ModCreativeTabs();
	public static final ModTools tools = new ModTools();
	public static final ModItems items = new ModItems();
	public static final ModPotions potions = new ModPotions();
	public static final ModArmors armors = new ModArmors();
	public static final ModFluids fluids = new ModFluids();
	public static final ModBlocks blocks = new ModBlocks();
	public static final ModBiomes biomes = new ModBiomes();
	public static final ModAdvancements advancements = new ModAdvancements();
	public static final ModLootTables lootTables = new ModLootTables();
	public static final ModParticles particles = new ModParticles();
	@Instance(modid)
	public static DalQuor instance = new DalQuor();
	public static FMLEventChannel channel;
	public static SimpleNetworkWrapper network;
	@SidedProxy(clientSide = "tamaized.dalquor.proxy.ClientProxy", serverSide = "tamaized.dalquor.proxy.ServerProxy")
	public static CommonProxy proxy;
	public static RitualList ritualList;

	public static String getVersion() {
		return version;
	}

	public static void reloadRitualList() {
		ritualList = new RitualList();
	}

	@Override
	protected AbstractProxy getProxy() {
		return proxy;
	}

	@Override
	public String getModID() {
		return modid;
	}

	@Override
	@EventHandler
	public void FMLpreInit(FMLPreInitializationEvent event) {
		super.FMLpreInit(event);
	}

	@Override
	@EventHandler
	public void FMLinit(FMLInitializationEvent event) {
		super.FMLinit(event);
	}

	@Override
	@EventHandler
	public void FMLpostInit(FMLPostInitializationEvent event) {
		super.FMLpostInit(event);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		logger = LogManager.getLogger("Dal Quor");
		logger.info("Uh oh, I guess we need to open a portal to the Void");

		ModAdvancements.register();

		ContributorHandler.start();

		network = NetworkRegistry.INSTANCE.newSimpleChannel(modid);
		NetworkMessages.register(network);

		CapabilityManager.INSTANCE.register(IVoidicInfusionCapability.class, new VoidicInfusionCapabilityStorage(), VoidicInfusionCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IVadeMecumCapability.class, new VadeMecumCapabilityStorage(), VadeMecumCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IVadeMecumItemCapability.class, new VadeMecumItemCapabilityStorage(), VadeMecumItemCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IElytraFlyingCapability.class, new ElytraFlyingCapabilityStorage(), ElytraFlyingCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IVoidicPowerCapability.class, new VoidicPowerCapabilityStorage(), VoidicPowerCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IStarForgeCapability.class, new StarForgeCapabilityStorage(), StarForgeCapabilityHandler.class);
		MinecraftForge.EVENT_BUS.register(new tamaized.dalquor.common.capabilities.EventHandler());

	}

	@Override
	public void init(FMLInitializationEvent event) {
		ModFluids.init();
		ModItems.init();
		ModBiomes.init();

		StarForgeEffectList.register();
		StarForgeEffectRecipeList.instance.register();

		GameRegistry.registerTileEntity(TileEntityNoBreak.class, "tileEntityNoBreak");
		GameRegistry.registerTileEntity(TileEntityAIBlock.class, "tileEntityAIBlock");
		GameRegistry.registerTileEntity(TileEntityStarForge.class, "tileEntityStarForge");

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		MinecraftForge.EVENT_BUS.register(new VoidTickEvent());
		MinecraftForge.EVENT_BUS.register(new PickUpEvent());
		MinecraftForge.EVENT_BUS.register(new SpawnEvent());
		MinecraftForge.EVENT_BUS.register(new CraftingHandler());
		MinecraftForge.EVENT_BUS.register(new BlockBreakPlaceEvent());
		MinecraftForge.EVENT_BUS.register(new VoidicInfusionHandler());
		MinecraftForge.EVENT_BUS.register(new DamageEvent());
		MinecraftForge.EVENT_BUS.register(new XiaFlightHandler());
		MinecraftForge.EVENT_BUS.register(new CapabilitySyncEvent());
		// MinecraftForge.EVENT_BUS.register(new CustomElytraHandler());
		MinecraftForge.EVENT_BUS.register(new ItemEntityEvent());
		MinecraftForge.EVENT_BUS.register(new DeathEvent());
		MinecraftForge.EVENT_BUS.register(new PlayerRightClickEvent());
		MinecraftForge.EVENT_BUS.register(new LitStrikeEvent());

		DimensionManager.registerDimension(ConfigHandler.dimensionIdVoid, DimensionType.register("The Void", "_void", ConfigHandler.dimensionIdVoid, WorldProviderVoid.class, false));
		DimensionManager.registerDimension(ConfigHandler.dimensionIdXia, DimensionType.register("???", "_xia", ConfigHandler.dimensionIdXia, WorldProviderXia.class, false));
		DimensionManager.registerDimension(ConfigHandler.dimensionIdDalQuor, DimensionType.register("Dal Quor", "_dalquor", ConfigHandler.dimensionIdDalQuor, WorldProviderDalQuor.class, false));

		PortalHandlerRegistry.register(ModBlocks.blockPortalVoid, ConfigHandler.dimensionIdVoid, TeleporterVoid.class);
		PortalHandlerRegistry.register(ModBlocks.blockPortalXia, ConfigHandler.dimensionIdXia, TeleporterXia.class);

		GameRegistry.registerWorldGenerator(new WorldGeneratorVoid(), 0);
		new WorldType(modid + "_void") {
			@Override
			public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
				return new ChunkProviderVoid(world, true, world.getSeed());
			}
		};

		MapGenStructureIO.registerStructure(MapGenVoidFortress.Start.class, "VoidFortress");
		MapGenStructureIO.registerStructure(MapGenVoidCity.Start.class, "VoidCity");
		StructureVoidFortressPieces.registerNetherFortressPieces();
		StructureVoidCityPieces.registerPieces();

		registerEntity(EntityVoidChain.class, "EntityVoidChain", this, modid, 128, 1, true);
		registerEntity(EntityAcidBall.class, "EntityAcidBall", this, modid, 128, 1, true);
		// registerEntity(EntityHookShot.class, "HookShot", this, 128, 1, true);
		registerEntity(EntityHerobrineFireball.class, "HerobrineFireball", this, modid, 128, 1, true);
		registerEntity(EntityObsidianFlask.class, "EntityObsidianFlask", this, modid, 128, 1, true);
		registerEntity(EntityHerobrineWitherSkull.class, "HerobrineWitherSkull", this, modid, 128, 1, true);
		registerEntity(EntityHerobrineTNTPrimed.class, "HerobrineTNTPrimed", this, modid, 128, 1, true);
		registerEntity(EntityHerobrineShadow.class, "HerobrineShadow", this, modid, 128, 1, true);
		registerEntity(EntityLichInferno.class, "LichInferno", this, modid, 128, 1, true);
		registerEntity(ProjectileDisintegration.class, "ProjectileDisintegration", this, modid, 128, 1, true);
		registerEntity(EntitySpellRune.class, "EntitySpellRune", this, modid, 64, 1, true);
		registerEntity(EntityCasterLightningBolt.class, "EntityCasterLightningBolt", this, modid, 64, 1, true);
		registerEntity(EntitySpellImplosion.class, "EntitySpellImplosion", this, modid, 64, 1, true);
		registerEntityWithEgg(EntityMobWraith.class, "Wraith", this, modid, 64, 1, true, 0xFFFFFF, 0x000000);
		registerEntityWithEgg(EntityMobSpectreChain.class, "SpectreChain", this, modid, 64, 1, true, 0xFFFFFF, 0xAA0077);
		registerEntityWithEgg(EntityMobVoidWrath.class, "VoidWrath", this, modid, 64, 1, true, 0xFF0000, 0x000000);
		registerEntityWithEgg(EntityMobLich.class, "Lich", this, modid, 64, 1, true, 0xAA00FF, 0x000000);
		registerEntity(EntityBossCorruptedPawn.class, "VoidBoss", this, modid, 64, 1, true);
		registerEntity(EntityBossHerobrine.class, "Herobrine", this, modid, 64, 1, true);
		registerEntity(EntityBossDol.class, "Dol", this, modid, 64, 1, true);
		registerEntity(EntityBossZol.class, "Zol", this, modid, 64, 1, true);
		registerEntity(EntityBossXia.class, "xia", this, modid, 64, 1, true);
		registerEntity(EntityBossXia2.class, "xia2", this, modid, 250, 1, true);
		registerEntity(EntityGhostPlayer.class, "GhostPlayer", this, modid, 64, 1, true);
		registerEntity(EntityGhostPlayerSlim.class, "GhostPlayerSlim", this, modid, 64, 1, true);
		registerEntityWithEgg(EntityHerobrineCreeper.class, "HerobrineCreeper", this, modid, 64, 1, true, 0xFF0000, 0x000000);
		registerEntity(EntityWitherbrine.class, "Witherbrine", this, modid, 250, 1, true);
		registerEntity(EntityDragonXia.class, "DragonXia", this, modid, 250, 1, true);
		registerEntity(EntityDolXia.class, "DolXia", this, modid, 250, 1, true);
		registerEntity(EntityZolXia.class, "ZolXia", this, modid, 250, 1, true);
		registerEntity(EntityVoidicDragon.class, "VoidicDragon", this, modid, 64, 1, true);
		registerEntityWithEgg(EntityMobEtherealGuardian.class, "EtherealGuardian", this, modid, 64, 1, true, 0xFF0000, 0x000000);
		registerEntity(EntityCompanionFireElemental.class, "FireElemental", this, modid, 64, 1, true);
		registerEntityWithEgg(EntityHashalaq.class, "Hashalaq", this, modid, 64, 1, true, 0x000000, 0x00FFFF);
		registerEntityWithEgg(EntityLordOfBlades.class, "LordOfBlades", this, modid, 250, 1, true, 0x777777, 0x000000);
		registerEntityWithEgg(EntityVoidParrot.class, "VoidParrot", this, modid, 250, 1, true, 0x7700FF, 0x000000);
		registerEntity(EntityChainedSkull.class, "ChainedSkull", this, modid, 250, 1, true);
		registerEntity(EntityBlockSpell.class, "BlockSpell", this, modid, 250, 1, true);

		Biome.getBiome(6).getSpawnableList(EnumCreatureType.MONSTER).add(new SpawnListEntry(EntityMobLich.class, 10, 0, 1));

	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		ModLootTables.postInit();

		ForgeChunkManager.setForcedChunkLoadingCallback(this, (tickets, world) -> {
			for (Ticket ticket : tickets) {
				ForgeChunkManager.releaseTicket(ticket);
			}
		});

		reloadRitualList();

	}

}