package Tamaized.Voidcraft.common;

import java.util.ArrayList;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Tamaized.Voidcraft.Addons.AE2.voidCraftAE;
import Tamaized.Voidcraft.Addons.thaumcraft.VoidCraftThaum;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.blocks.TileEntityNoBreak;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.common.handlers.VoidCraftServerPacketHandler;
import Tamaized.Voidcraft.common.server.VoidCraftCommonProxy;
import Tamaized.Voidcraft.events.PickUpEvent;
import Tamaized.Voidcraft.events.VoidTickEvent;
import Tamaized.Voidcraft.handlers.CraftingHandler;
import Tamaized.Voidcraft.items.VoidRecord;
import Tamaized.Voidcraft.items.entity.EntityHookShot;
import Tamaized.Voidcraft.machina.addons.MaceratorRecipeList;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;
import Tamaized.Voidcraft.mobs.entity.EntityMobLich;
import Tamaized.Voidcraft.mobs.entity.EntityMobSpectreChain;
import Tamaized.Voidcraft.mobs.entity.EntityMobVoidWrath;
import Tamaized.Voidcraft.mobs.entity.EntityMobWraith;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobDol;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobHerobrine;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobVoidBoss;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobZol;
import Tamaized.Voidcraft.mobs.xia.EntityMobXia;
import Tamaized.Voidcraft.mobs.xia.EntityMobXia2;
import Tamaized.Voidcraft.projectiles.AcidBall;
import Tamaized.Voidcraft.projectiles.HerobrineFireball;
import Tamaized.Voidcraft.projectiles.VoidChain;
import Tamaized.Voidcraft.registry.Achievements;
import Tamaized.Voidcraft.registry.Armors;
import Tamaized.Voidcraft.registry.Biomes;
import Tamaized.Voidcraft.registry.Materials;
import Tamaized.Voidcraft.registry.Tabs;
import Tamaized.Voidcraft.registry.Tools;
import Tamaized.Voidcraft.registry.VoidBlocks;
import Tamaized.Voidcraft.registry.VoidFluids;
import Tamaized.Voidcraft.registry.VoidItems;
import Tamaized.Voidcraft.sound.BossMusicManager;
import Tamaized.Voidcraft.structures.StructureTestPieces;
import Tamaized.Voidcraft.structures.StructureTestStart;
import Tamaized.Voidcraft.world.WorldGeneratorVoid;
import Tamaized.Voidcraft.world.dim.TheVoid.WorldProviderVoid;
import Tamaized.Voidcraft.world.dim.Xia.WorldProviderXia;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid=voidCraft.modid, name="VoidCraft", version=voidCraft.version)

public class voidCraft {
	
	protected final static String version = "0.5.9e_DEV";

	public static final String modid = "voidcraft";
	
	public static String getVersion(){
		return version;
	}
	
	
	@Instance(modid) 
	public static voidCraft instance = new voidCraft();

	public static FMLEventChannel channel;
	public static final String networkChannelName = "VoidCraft";

	@SidedProxy(clientSide = "Tamaized.Voidcraft.common.client.VoidCraftClientProxy", serverSide = "Tamaized.Voidcraft.common.server.VoidCraftCommonProxy")
	public static VoidCraftCommonProxy proxy;

	public static final int WILDCARD_VALUE = OreDictionary.WILDCARD_VALUE;

	public static final int guiIdMacerator = 0;
	public static final int guiIdBox = 1;
	public static final int guiIdInfuser = 2;
	public static final int guiIdHeimdall = 3;

	public VoidTickEvent VoidTickEvent;

	//Public API Integrations
	public static VoidCraftThaum thaumcraftIntegration;
	public static voidCraftAE aeIntegration;

	public static Materials materials = new Materials();
	public static Tabs tabs = new Tabs();
	public static Tools tools = new Tools();
	public static VoidItems items = new VoidItems();
	public static Armors armors = new Armors();
	public static VoidFluids fluids = new VoidFluids();
	public static VoidBlocks blocks = new VoidBlocks();
	public static Biomes biomes = new Biomes();
	public static Achievements achievements = new Achievements();
	
	//Discs
	public static ArrayList<Item> voidDiscs = new ArrayList<Item>();

	public static final int dimensionIdVoid = -2;
	public static final int dimensionIdXia = -3;

	public static MaceratorRecipeList maceratorList = new MaceratorRecipeList();
	
	public static Logger logger;


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = LogManager.getLogger("VoidCraft");
		
		logger.info("Uh oh, I guess we need to open a portal to the Void");
		logger.info("Starting VoidCraft PreInit");
	
		channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(networkChannelName);
		
		tabs.preInit();
		materials.preInit();
		tools.preInit();
		armors.preInit();
		items.preInit();
		blocks.preInit();
		fluids.preInit();
		
		// disc
		voidDiscs.add(new VoidRecord("voidCraft:Lavender Town", 271).setMaxStackSize(1).setCreativeTab(tabs.tabVoid).setUnlocalizedName("voidDisc1").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Lensko - Cetus", 289).setMaxStackSize(1).setCreativeTab(tabs.tabVoid).setUnlocalizedName("voidDisc2").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Starfox- Assault-Starwolf theme", 173).setMaxStackSize(1).setCreativeTab(tabs.tabVoid).setUnlocalizedName("voidDisc3").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:They Will Die", 250).setMaxStackSize(1).setCreativeTab(tabs.tabVoid).setUnlocalizedName("voidDisc4").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Warriors", 171).setMaxStackSize(1).setCreativeTab(tabs.tabVoid).setUnlocalizedName("voidDisc5").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Imagine Dragons - Shots (Broiler Remix)", 190).setMaxStackSize(1).setCreativeTab(tabs.tabVoid).setUnlocalizedName("voidDisc6").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Asgore", 154).setMaxStackSize(1).setCreativeTab(tabs.tabVoid).setUnlocalizedName("voidDisc7").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Core", 164).setMaxStackSize(1).setCreativeTab(tabs.tabVoid).setUnlocalizedName("voidDisc8").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Megalovania", 156).setMaxStackSize(1).setCreativeTab(tabs.tabVoid).setUnlocalizedName("voidDisc9").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Muffet", 100).setMaxStackSize(1).setCreativeTab(tabs.tabVoid).setUnlocalizedName("voidDisc10").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Papyrus", 58).setMaxStackSize(1).setCreativeTab(tabs.tabVoid).setUnlocalizedName("voidDisc11").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Undyne", 156).setMaxStackSize(1).setCreativeTab(tabs.tabVoid).setUnlocalizedName("voidDisc12").setTextureName("VoidCraft:voidDisc"));
		
		//API Loader
		if (Loader.isModLoaded("Thaumcraft")) {
			logger.info("Thaumcraft Detected. Attempting to load API");
			try {
				thaumcraftIntegration = new VoidCraftThaum();
				logger.info("Loaded ThaumcraftAPI into VoidCraft");
			}catch (Exception e1) {
				logger.info("Error while adding ThaumcraftAPI into VoidCraft");
				e1.printStackTrace(System.err);
			}
		}
		
		if(thaumcraftIntegration != null) thaumcraftIntegration.preInit();
		
	}



	@EventHandler
	public void InitVoidCraft(FMLInitializationEvent event){ 
		logger.info("Starting VoidCraft Init");
		
		//Register Handlers into the Instance
		VoidTickEvent = new VoidTickEvent();
					
		//register GUI Handler
		//NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
		//GuiHandler guiHandler = new GuiHandler();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		//Register Events
		FMLCommonHandler.instance().bus().register(VoidTickEvent);
		MinecraftForge.EVENT_BUS.register(new PickUpEvent());
		FMLCommonHandler.instance().bus().register(new CraftingHandler()); 
		FMLCommonHandler.instance().bus().register(BossMusicManager.instance); //We want to give this class a tick updater
				
		//Tile Entities
		GameRegistry.registerTileEntity(TileEntityVoidMacerator.class, "tileEntityVoidMacerator");
		GameRegistry.registerTileEntity(TileEntityVoidBox.class, "tileEntityVoidBox");
		GameRegistry.registerTileEntity(TileEntityVoidInfuser.class, "tileEntityVoidInfuser");
		GameRegistry.registerTileEntity(TileEntityHeimdall.class, "tileEntityHeimdall");
		GameRegistry.registerTileEntity(TileEntityNoBreak.class, "tileEntityNoBreak");
		GameRegistry.registerTileEntity(TileEntityAIBlock.class, "tileEntityAIBlock");
		
		fluids.init();
		blocks.init();
		items.init();
		tools.init();
		armors.init();
		biomes.init();
		achievements.init();
		
		for(Item disc : voidDiscs){
			GameRegistry.registerItem(disc, disc.getUnlocalizedName());
		}
		
		//Projectiles
		//EntityRegistry.registerGlobalEntityID(VoidChain.class, "VoidChain", EntityRegistry.findGlobalUniqueEntityId()); 
		EntityRegistry.registerModEntity(VoidChain.class, "VoidChain", 0, this, 128, 1, true);
		
		//EntityRegistry.registerGlobalEntityID(AcidBall.class, "AcidBall", EntityRegistry.findGlobalUniqueEntityId()); 
		EntityRegistry.registerModEntity(AcidBall.class, "AcidBall", 1, this, 128, 1, true);
		
		//EntityRegistry.registerGlobalEntityID(Hook.class, "Hook", EntityRegistry.findGlobalUniqueEntityId()); 
		EntityRegistry.registerModEntity(EntityHookShot.class, "HookShot", 2, this, 128, 1, true);
		
		//EntityRegistry.registerGlobalEntityID(HerobrineFireball.class, "HerobrineFireball", EntityRegistry.findGlobalUniqueEntityId()); 
		EntityRegistry.registerModEntity(HerobrineFireball.class, "HerobrineFireball", 3, this, 128, 1, true);

		//Dimension
		DimensionManager.registerProviderType(dimensionIdVoid, WorldProviderVoid.class, false);
		DimensionManager.registerDimension(dimensionIdVoid, dimensionIdVoid);
		
		DimensionManager.registerProviderType(dimensionIdXia, WorldProviderXia.class, false);
		DimensionManager.registerDimension(dimensionIdXia, dimensionIdXia);
		
		//Discs
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(0)), "ZCC", "CXC", "CCC", 'Z', items.burnBone, 'X', items.voidcrystal, 'C', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(1)), "CZC", "CXC", "CCC", 'Z', items.burnBone, 'X', items.voidcrystal, 'C', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(2)), "CCZ", "CXC", "CCC", 'Z', items.burnBone, 'X', items.voidcrystal, 'C', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(3)), "CCC", "ZXC", "CCC", 'Z', items.burnBone, 'X', items.voidcrystal, 'C', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(4)), "CCC", "CXZ", "CCC", 'Z', items.burnBone, 'X', items.voidcrystal, 'C', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(5)), "CCC", "CXC", "ZCC", 'Z', items.burnBone, 'X', items.voidcrystal, 'C', Items.coal);

		// World Gen
		GameRegistry.registerWorldGenerator(new WorldGeneratorVoid(), 0);
		
		MapGenStructureIO.registerStructure(StructureTestStart.class, "VoidFortress");
		StructureTestPieces.func_143049_a();
		

		// Mobs
		EntityRegistry.registerGlobalEntityID(EntityMobWraith.class, "Wraith", EntityRegistry.findGlobalUniqueEntityId(), 0xFFFFFF, 0x000000);
		EntityRegistry.addSpawn(EntityMobWraith.class, 1, 0, 1, EnumCreatureType.monster, biomes.biomeVoid);
		
		EntityRegistry.registerGlobalEntityID(EntityMobSpectreChain.class, "SpectreChain",EntityRegistry.findGlobalUniqueEntityId(), 0xAA00FF, 0x000000);
		EntityRegistry.addSpawn(EntityMobSpectreChain.class, 1, 0, 1, EnumCreatureType.monster, biomes.biomeVoid);

		EntityRegistry.registerGlobalEntityID(EntityMobVoidWrath.class, "VoidWrath", EntityRegistry.findGlobalUniqueEntityId(), 0xFF0000, 0x000000);
		EntityRegistry.addSpawn(EntityMobVoidWrath.class, 0, 0, 0, EnumCreatureType.monster, biomes.biomeVoid);
		
		//TODO MORE CONTENT TO ENTITY
		EntityRegistry.registerGlobalEntityID(EntityMobLich.class, "Lich", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0x4444FF);
		EntityRegistry.addSpawn(EntityMobLich.class, 3, 0, 1, EnumCreatureType.monster, BiomeGenBase.swampland);
		
		//TODO FIX THE ENTITY
		EntityRegistry.registerGlobalEntityID(EntityMobVoidBoss.class, "VoidBoss", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xFF0000);
		EntityRegistry.addSpawn(EntityMobVoidBoss.class, 0, 0, 0, EnumCreatureType.monster, biomes.biomeVoid);
		
		//npcs
		EntityRegistry.registerGlobalEntityID(EntityMobHerobrine.class, "Herobrine", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xFF0000);
		EntityRegistry.addSpawn(EntityMobHerobrine.class, 0, 0, 0, EnumCreatureType.monster, biomes.biomeVoid);
		
		EntityRegistry.registerGlobalEntityID(EntityMobDol.class, "Dol", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0x775500);
		EntityRegistry.addSpawn(EntityMobDol.class, 0, 0, 0, EnumCreatureType.monster, biomes.biomeVoid);
		
		EntityRegistry.registerGlobalEntityID(EntityMobZol.class, "Zol", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0x8888FF);
		EntityRegistry.addSpawn(EntityMobZol.class, 0, 0, 0, EnumCreatureType.monster, biomes.biomeVoid);
		
		EntityRegistry.registerGlobalEntityID(EntityMobXia.class, "Xia", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xFFFF00);
		EntityRegistry.addSpawn(EntityMobXia.class, 0, 0, 0, EnumCreatureType.monster, biomes.biomeVoid);
		
		EntityRegistry.registerGlobalEntityID(EntityMobXia2.class, "Xia2", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xFFFF00);
		EntityRegistry.addSpawn(EntityMobXia2.class, 0, 0, 0, EnumCreatureType.monster, biomes.biomeVoid);

		if(thaumcraftIntegration != null) thaumcraftIntegration.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e){ 
		logger.info("Starting VoidCraft PostInit");
		
		achievements.postInit();
		
		channel.register(new VoidCraftServerPacketHandler());
		proxy.registerNetwork();
		proxy.registerRenders();
		proxy.registerBlocks();
		proxy.registerRenderInformation();
		proxy.registerItems();
		proxy.registerMISC();
		proxy.registerAchievements();

		if(thaumcraftIntegration != null) thaumcraftIntegration.postInit();
	}

	
	
	

}





























