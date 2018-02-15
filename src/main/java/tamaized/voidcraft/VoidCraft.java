package tamaized.voidcraft;

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
import tamaized.tammodized.TamModBase;
import tamaized.tammodized.TamModized;
import tamaized.tammodized.proxy.AbstractProxy;
import tamaized.tammodized.registry.PortalHandlerRegistry;
import tamaized.voidcraft.client.entity.boss.extra.EntityDolXia;
import tamaized.voidcraft.common.blocks.TileEntityNoBreak;
import tamaized.voidcraft.common.blocks.tileentity.TileEntityAIBlock;
import tamaized.voidcraft.common.blocks.tileentity.TileEntityStarForge;
import tamaized.voidcraft.common.capabilities.elytraFlying.ElytraFlyingCapabilityHandler;
import tamaized.voidcraft.common.capabilities.elytraFlying.ElytraFlyingCapabilityStorage;
import tamaized.voidcraft.common.capabilities.elytraFlying.IElytraFlyingCapability;
import tamaized.voidcraft.common.capabilities.starforge.IStarForgeCapability;
import tamaized.voidcraft.common.capabilities.starforge.StarForgeCapabilityHandler;
import tamaized.voidcraft.common.capabilities.starforge.StarForgeCapabilityStorage;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.capabilities.vadeMecum.VadeMecumCapabilityHandler;
import tamaized.voidcraft.common.capabilities.vadeMecum.VadeMecumCapabilityStorage;
import tamaized.voidcraft.common.capabilities.vadeMecumItem.IVadeMecumItemCapability;
import tamaized.voidcraft.common.capabilities.vadeMecumItem.VadeMecumItemCapabilityHandler;
import tamaized.voidcraft.common.capabilities.vadeMecumItem.VadeMecumItemCapabilityStorage;
import tamaized.voidcraft.common.capabilities.voidicInfusion.IVoidicInfusionCapability;
import tamaized.voidcraft.common.capabilities.voidicInfusion.VoidicInfusionCapabilityHandler;
import tamaized.voidcraft.common.capabilities.voidicInfusion.VoidicInfusionCapabilityStorage;
import tamaized.voidcraft.common.capabilities.voidicPower.IVoidicPowerCapability;
import tamaized.voidcraft.common.capabilities.voidicPower.VoidicPowerCapabilityHandler;
import tamaized.voidcraft.common.capabilities.voidicPower.VoidicPowerCapabilityStorage;
import tamaized.voidcraft.common.entity.boss.EntityBossCorruptedPawn;
import tamaized.voidcraft.common.entity.boss.dragon.EntityVoidicDragon;
import tamaized.voidcraft.common.entity.boss.herobrine.EntityBossHerobrine;
import tamaized.voidcraft.common.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import tamaized.voidcraft.common.entity.boss.herobrine.extra.EntityHerobrineFireball;
import tamaized.voidcraft.common.entity.boss.herobrine.extra.EntityHerobrineShadow;
import tamaized.voidcraft.common.entity.boss.herobrine.extra.EntityHerobrineTNTPrimed;
import tamaized.voidcraft.common.entity.boss.herobrine.extra.EntityHerobrineWitherSkull;
import tamaized.voidcraft.common.entity.boss.lob.EntityLordOfBlades;
import tamaized.voidcraft.common.entity.boss.twins.EntityBossDol;
import tamaized.voidcraft.common.entity.boss.twins.EntityBossZol;
import tamaized.voidcraft.common.entity.boss.xia.EntityBossXia;
import tamaized.voidcraft.common.entity.boss.xia.EntityBossXia2;
import tamaized.voidcraft.common.entity.boss.xia.finalphase.EntityDragonXia;
import tamaized.voidcraft.common.entity.boss.xia.finalphase.EntityWitherbrine;
import tamaized.voidcraft.common.entity.boss.xia.finalphase.EntityZolXia;
import tamaized.voidcraft.common.entity.companion.EntityCompanionFireElemental;
import tamaized.voidcraft.common.entity.companion.EntityVoidParrot;
import tamaized.voidcraft.common.entity.ghost.EntityGhostPlayer;
import tamaized.voidcraft.common.entity.ghost.EntityGhostPlayerSlim;
import tamaized.voidcraft.common.entity.mob.EntityMobEtherealGuardian;
import tamaized.voidcraft.common.entity.mob.EntityMobLich;
import tamaized.voidcraft.common.entity.mob.EntityMobSpectreChain;
import tamaized.voidcraft.common.entity.mob.EntityMobVoidWrath;
import tamaized.voidcraft.common.entity.mob.EntityMobWraith;
import tamaized.voidcraft.common.entity.mob.dalquor.EntityHashalaq;
import tamaized.voidcraft.common.entity.mob.lich.EntityLichInferno;
import tamaized.voidcraft.common.entity.nonliving.EntityAcidBall;
import tamaized.voidcraft.common.entity.nonliving.EntityBlockSpell;
import tamaized.voidcraft.common.entity.nonliving.EntityCasterLightningBolt;
import tamaized.voidcraft.common.entity.nonliving.EntityChainedSkull;
import tamaized.voidcraft.common.entity.nonliving.EntityObsidianFlask;
import tamaized.voidcraft.common.entity.nonliving.EntitySpellImplosion;
import tamaized.voidcraft.common.entity.nonliving.EntitySpellRune;
import tamaized.voidcraft.common.entity.nonliving.EntityVoidChain;
import tamaized.voidcraft.common.entity.nonliving.ProjectileDisintegration;
import tamaized.voidcraft.common.events.BlockBreakPlaceEvent;
import tamaized.voidcraft.common.events.CapabilitySyncEvent;
import tamaized.voidcraft.common.events.CraftingHandler;
import tamaized.voidcraft.common.events.DamageEvent;
import tamaized.voidcraft.common.events.DeathEvent;
import tamaized.voidcraft.common.events.ItemEntityEvent;
import tamaized.voidcraft.common.events.LitStrikeEvent;
import tamaized.voidcraft.common.events.PickUpEvent;
import tamaized.voidcraft.common.events.PlayerRightClickEvent;
import tamaized.voidcraft.common.events.SpawnEvent;
import tamaized.voidcraft.common.events.VoidTickEvent;
import tamaized.voidcraft.common.events.VoidicInfusionHandler;
import tamaized.voidcraft.common.events.XiaFlightHandler;
import tamaized.voidcraft.common.gui.GuiHandler;
import tamaized.voidcraft.common.handlers.ConfigHandler;
import tamaized.voidcraft.common.handlers.ContributorHandler;
import tamaized.voidcraft.common.starforge.StarForgeEffectRecipeList;
import tamaized.voidcraft.common.starforge.effects.StarForgeEffectList;
import tamaized.voidcraft.common.structures.voidcity.MapGenVoidCity;
import tamaized.voidcraft.common.structures.voidcity.StructureVoidCityPieces;
import tamaized.voidcraft.common.structures.voidfortress.MapGenVoidFortress;
import tamaized.voidcraft.common.structures.voidfortress.StructureVoidFortressPieces;
import tamaized.voidcraft.common.vademecum.progression.RitualList;
import tamaized.voidcraft.common.world.WorldGeneratorVoid;
import tamaized.voidcraft.common.world.dim.dalquor.WorldProviderDalQuor;
import tamaized.voidcraft.common.world.dim.thevoid.ChunkProviderVoid;
import tamaized.voidcraft.common.world.dim.thevoid.TeleporterVoid;
import tamaized.voidcraft.common.world.dim.thevoid.WorldProviderVoid;
import tamaized.voidcraft.common.world.dim.xia.TeleporterXia;
import tamaized.voidcraft.common.world.dim.xia.WorldProviderXia;
import tamaized.voidcraft.network.NetworkMessages;
import tamaized.voidcraft.proxy.CommonProxy;
import tamaized.voidcraft.registry.VoidCraftAdvancements;
import tamaized.voidcraft.registry.VoidCraftArmors;
import tamaized.voidcraft.registry.VoidCraftBiomes;
import tamaized.voidcraft.registry.VoidCraftBlocks;
import tamaized.voidcraft.registry.VoidCraftCreativeTabs;
import tamaized.voidcraft.registry.VoidCraftFluids;
import tamaized.voidcraft.registry.VoidCraftItems;
import tamaized.voidcraft.registry.VoidCraftLootTables;
import tamaized.voidcraft.registry.VoidCraftMaterials;
import tamaized.voidcraft.registry.VoidCraftParticles;
import tamaized.voidcraft.registry.VoidCraftPotions;
import tamaized.voidcraft.registry.VoidCraftTools;

@Mod(modid = VoidCraft.modid, name = "VoidCraft", version = VoidCraft.version, acceptedMinecraftVersions = "[1.12,)", dependencies = "required-before:" + TamModized.modid + "@[${tamversion},)")
public class VoidCraft extends TamModBase {

	// public static final boolean isDevBuild = false;

	public static final String version = "${version}";
	public static final String modid = "voidcraft";
	public static final VoidCraftMaterials materials = new VoidCraftMaterials();
	public static final VoidCraftCreativeTabs tabs = new VoidCraftCreativeTabs();
	public static final VoidCraftTools tools = new VoidCraftTools();
	public static final VoidCraftItems items = new VoidCraftItems();

	//	public static boolean isAetherLoaded = false;
	public static final VoidCraftPotions potions = new VoidCraftPotions();
	public static final VoidCraftArmors armors = new VoidCraftArmors();
	public static final VoidCraftFluids fluids = new VoidCraftFluids();
	public static final VoidCraftBlocks blocks = new VoidCraftBlocks();
	public static final VoidCraftBiomes biomes = new VoidCraftBiomes();
	public static final VoidCraftAdvancements advancements = new VoidCraftAdvancements();
	public static final VoidCraftLootTables lootTables = new VoidCraftLootTables();
	public static final VoidCraftParticles particles = new VoidCraftParticles();
	@Instance(modid)
	public static VoidCraft instance = new VoidCraft();
	public static FMLEventChannel channel;
	public static SimpleNetworkWrapper network;
	@SidedProxy(clientSide = "tamaized.voidcraft.proxy.ClientProxy", serverSide = "tamaized.voidcraft.proxy.ServerProxy")
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
		logger = LogManager.getLogger("VoidCraft");
		logger.info("Uh oh, I guess we need to open a portal to the Void");

		VoidCraftAdvancements.register();

		ContributorHandler.start();

		network = NetworkRegistry.INSTANCE.newSimpleChannel(modid);
		NetworkMessages.register(network);

		CapabilityManager.INSTANCE.register(IVoidicInfusionCapability.class, new VoidicInfusionCapabilityStorage(), VoidicInfusionCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IVadeMecumCapability.class, new VadeMecumCapabilityStorage(), VadeMecumCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IVadeMecumItemCapability.class, new VadeMecumItemCapabilityStorage(), VadeMecumItemCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IElytraFlyingCapability.class, new ElytraFlyingCapabilityStorage(), ElytraFlyingCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IVoidicPowerCapability.class, new VoidicPowerCapabilityStorage(), VoidicPowerCapabilityHandler.class);
		CapabilityManager.INSTANCE.register(IStarForgeCapability.class, new StarForgeCapabilityStorage(), StarForgeCapabilityHandler.class);
		MinecraftForge.EVENT_BUS.register(new tamaized.voidcraft.common.capabilities.EventHandler());

	}

	@Override
	public void init(FMLInitializationEvent event) {
		VoidCraftFluids.init();
		VoidCraftItems.init();
		VoidCraftBiomes.init();

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

		PortalHandlerRegistry.register(VoidCraftBlocks.blockPortalVoid, ConfigHandler.dimensionIdVoid, TeleporterVoid.class);
		PortalHandlerRegistry.register(VoidCraftBlocks.blockPortalXia, ConfigHandler.dimensionIdXia, TeleporterXia.class);

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
		VoidCraftLootTables.postInit();

		ForgeChunkManager.setForcedChunkLoadingCallback(this, (tickets, world) -> {
			for (Ticket ticket : tickets) {
				ForgeChunkManager.releaseTicket(ticket);
			}
		});

		reloadRitualList();

	}

}