package Tamaized.Voidcraft.common;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Tamaized.Voidcraft.Addons.AE2.voidCraftAE;
import Tamaized.Voidcraft.Addons.thaumcraft.VoidCraftThaum;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.armor.DemonArmor;
import Tamaized.Voidcraft.armor.VoidArmor;
import Tamaized.Voidcraft.armor.XiaArmor;
import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.blocks.BasicVoidBlock;
import Tamaized.Voidcraft.blocks.BlockTeleporter;
import Tamaized.Voidcraft.blocks.BlockTeleporterXia;
import Tamaized.Voidcraft.blocks.FireVoid;
import Tamaized.Voidcraft.blocks.OreVoidcrystal;
import Tamaized.Voidcraft.blocks.TileEntityNoBreak;
import Tamaized.Voidcraft.blocks.VoidFence;
import Tamaized.Voidcraft.blocks.VoidSlab;
import Tamaized.Voidcraft.blocks.VoidStairs;
import Tamaized.Voidcraft.blocks.blockFakeBedrock;
import Tamaized.Voidcraft.blocks.blockNoBreak;
import Tamaized.Voidcraft.blocks.blockVoidbrick;
import Tamaized.Voidcraft.blocks.blockVoidcrystal;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.common.handlers.BucketHandler;
import Tamaized.Voidcraft.common.handlers.CraftingHandler;
import Tamaized.Voidcraft.common.handlers.VoidCraftServerPacketHandler;
import Tamaized.Voidcraft.common.server.VoidCraftCommonProxy;
import Tamaized.Voidcraft.events.PickUpEvent;
import Tamaized.Voidcraft.events.VoidTickEvent;
import Tamaized.Voidcraft.fluid.BlockVoidFluid;
import Tamaized.Voidcraft.items.BasicVoidItems;
import Tamaized.Voidcraft.items.ChainedSkull;
import Tamaized.Voidcraft.items.Debugger;
import Tamaized.Voidcraft.items.ItemVoidCraftBucket;
import Tamaized.Voidcraft.items.VoidBurner;
import Tamaized.Voidcraft.items.VoidRecord;
import Tamaized.Voidcraft.machina.Heimdall;
import Tamaized.Voidcraft.machina.VoidBox;
import Tamaized.Voidcraft.machina.VoidInfuser;
import Tamaized.Voidcraft.machina.VoidMacerator;
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
import Tamaized.Voidcraft.sound.BossMusicManager;
import Tamaized.Voidcraft.structures.StructureTestPieces;
import Tamaized.Voidcraft.structures.StructureTestStart;
import Tamaized.Voidcraft.tools.VoidAxe;
import Tamaized.Voidcraft.tools.VoidHoe;
import Tamaized.Voidcraft.tools.VoidPickaxe;
import Tamaized.Voidcraft.tools.VoidSpade;
import Tamaized.Voidcraft.tools.VoidSword;
import Tamaized.Voidcraft.tools.angelicSword;
import Tamaized.Voidcraft.tools.archSword;
import Tamaized.Voidcraft.tools.chainSword;
import Tamaized.Voidcraft.tools.demonSword;
import Tamaized.Voidcraft.tools.moltenSword;
import Tamaized.Voidcraft.tools.spectreAxe;
import Tamaized.Voidcraft.tools.spectrePickaxe;
import Tamaized.Voidcraft.world.WorldGeneratorVoid;
import Tamaized.Voidcraft.world.dim.TheVoid.BiomeGenVoid;
import Tamaized.Voidcraft.world.dim.TheVoid.WorldProviderVoid;
import Tamaized.Voidcraft.world.dim.Xia.BiomeGenXia;
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
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid=voidCraft.modid, name="VoidCraft", version=voidCraft.version)

public class voidCraft {
	
	protected final static String version = "0.5.8a";

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

	//Materials
	public static ToolMaterial voidTools;
	public static ToolMaterial spectreTools;
	public static ToolMaterial chainTools;
	public static ToolMaterial MoltenTools;
	public static ToolMaterial ArchTools;
	public static ToolMaterial DemonTools;
	public static ArmorMaterial voidArmor;
	public static ArmorMaterial demonArmor;
	public static ArmorMaterial xiaArmor;
	
	//Creative Tabs
	public static CreativeTabs tabVoid;
	public static CreativeTabs tForge;

	//Items
	public static Item ectoplasm;
	public static Item voidcrystal;
	public static Item voidPickaxe;
	public static Item voidSpade;
	public static Item voidAxe;
	public static Item voidSword;
	public static Item voidHoe;
	public static Item angelicSword;
	public static Item chainSword;
	public static Item moltenSword;
	public static Item archSword;
	public static Item demonSword;
	public static Item spectrePickaxe;
	public static Item spectreAxe;
	public static Item voidHelmet;
	public static Item voidChest;
	public static Item voidLegs;
	public static Item voidBoots;
	public static Item demonHelmet;
	public static Item demonChest;
	public static Item demonLegs;
	public static Item demonBoots;
	public static Item xiaChest;
	public static Item voidBurner;
	public static Item voidChain;
	public static Item MoltenvoidChain;
	public static Item MoltenvoidChainPart;
	public static Item burnBone;
	public static Item voidStar;
	public static Item ChainedSkull;
	public static Item HookShot;
	public static Item voidCloth;
	public static Item voidBucket;
	public static Item voidCrystalBucket;
	//-discs
	public static ArrayList<Item> voidDiscs = new ArrayList<Item>();
	
	public static Item ironDust;
	public static Item goldDust;
	public static Item diamondDust;
	public static Item copperDust;
	public static Item tinDust;
	public static Item leadDust;
	public static Item coalDust;
	public static Item quartzDust;

	public static Item debugger;

	public static final int idFluidVoid = 198;
	public static Fluid fluidVoid;
	public static Block fluidVoidz;

	public static Material materialFluidVoid;

	public static Block blockVoidcrystal;
	public static Block oreVoidcrystal;
	public static Block blockFakeBedrock;
	public static Block blockNoBreak;
	public static Block blockVoidbrick;
	public static Block blockVoidfence;
	public static Block blockVoidstairs;
	public static Block blockVoidBrickSlab;
	public static Block blockTeleporterVoid;
	public static Block blockTeleporterXia;
	public static Block fireVoid;
	public static Block voidBox;
	public static Block voidMacerator;
	public static Block blockVoidFluid;
	public static Block voidInfuserInert;
	public static Block voidInfuser;
	public static Block Heimdall;
	public static Block AIBlock;

	public static BiomeGenBase biomeVoid;
	public static BiomeGenBase biomeXia;

	public static Achievement voidCraftAchMainLine_1;
	public static Achievement voidCraftAchMainLine_2;
	public static Achievement voidCraftAchMainLine_3;
	public static Achievement voidCraftAchMainLine_4;
	public static Achievement voidCraftAchMainLine_5;
	public static Achievement voidCraftAchMainLine_6;
	public static Achievement voidCraftAchMainLine_7;
	public static Achievement voidCraftAchMainLine_8;
	
	public static Achievement voidCraftAchSideLine1_1;
	public static Achievement voidCraftAchSideLine1_2;
	public static Achievement voidCraftAchSideLine1_3;
	public static Achievement voidCraftAchSideLine1_4;
	public static Achievement voidCraftAchSideLine1_5;
	
	public static Achievement voidCraftAchSideLine2_1;
	public static Achievement voidCraftAchSideLine2_2;
	public static Achievement voidCraftAchSideLine2_3;
	public static Achievement voidCraftAchSideLine2_4;
	public static Achievement voidCraftAchSideLine2_5;

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

		// Enum Tool Material
		voidTools = EnumHelper.addToolMaterial("voidcrystal", 3, 2000, 12.0F, 8.0F, 30);
		spectreTools = EnumHelper.addToolMaterial("ectoplasm", 4, 4000, 30.0F, 9.0F, 30);
		chainTools = EnumHelper.addToolMaterial("voidChain", 5, 6000, 35.0F, 10.0F, 30);
		MoltenTools = EnumHelper.addToolMaterial("MoltenvoidChain", 6, 8000, 40.0F, 11.0F, 30);
		ArchTools = EnumHelper.addToolMaterial("burnBone", 7, 10000, 45.0F, 12.0F, 30);
		DemonTools = EnumHelper.addToolMaterial("voidStar", 8, 12000, 50.0F, 13.0F, 30);

		// Enum Armor Material
		voidArmor = EnumHelper.addArmorMaterial("Void", 120, new int[] {4, 8, 6, 4}, 30); //22
		demonArmor = EnumHelper.addArmorMaterial("Demon", 240, new int[] {4, 10, 6, 4}, 30); //24
		xiaArmor = EnumHelper.addArmorMaterial("Xia", 480, new int[] {1, 10, 1, 1}, 30);

		// Creative Tabs
		tabVoid = new CreativeTabs("tabVoid") {
			@Override
			public Item getTabIconItem() {
				return Item.getItemFromBlock(blockTeleporterVoid);
			}
		};
	
		tForge = new CreativeTabs("tForge") {
			@Override
			public Item getTabIconItem() {
				return Item.getItemFromBlock(blockNoBreak);
			}
		};

		// Items
		voidBurner = new VoidBurner().setUnlocalizedName("voidBurner").setCreativeTab(tabVoid).setTextureName("VoidCraft:voidBurner");
		ectoplasm = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("ectoplasm").setTextureName("VoidCraft:ectoplasm");
		voidcrystal = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("voidcrystal").setTextureName("VoidCraft:voidcrystal");
		MoltenvoidChain = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("MoltenvoidChain").setTextureName("VoidCraft:MoltenvoidChain");
		MoltenvoidChainPart = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("MoltenvoidChainPart").setTextureName("VoidCraft:MoltenvoidChainPart");
		burnBone = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("burnBone").setTextureName("VoidCraft:Bone");
		voidStar = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("voidStar").setTextureName("VoidCraft:voidStar");
		ChainedSkull = new ChainedSkull().setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("ChainedSkull").setTextureName("VoidCraft:ChainedSkull");
		voidCloth = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("voidCloth").setTextureName("VoidCraft:voidCloth");
		voidCrystalBucket = new BasicVoidItems().setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("voidCrystalBucket").setTextureName("VoidCraft:voidCrystalBucket");
	
		// dust
		ironDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("ironDust").setTextureName("voidCraft:itemDustIron");
		goldDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("goldDust").setTextureName("voidCraft:itemDustGold");
		diamondDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("diamondDust").setTextureName("voidCraft:itemDustDiamond");
		coalDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("coalDust").setTextureName("voidCraft:itemDustCoal");
		copperDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("copperDust").setTextureName("voidCraft:itemDustCopper");
		tinDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("tinDust").setTextureName("voidCraft:itemDustTin");
		leadDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("leadDust").setTextureName("voidCraft:itemDustLead");
		quartzDust = new BasicVoidItems().setCreativeTab(tabVoid).setMaxStackSize(64).setUnlocalizedName("quartzDust").setTextureName("voidCraft:itemDustQuartz");
	
		debugger = new Debugger().setCreativeTab(tabVoid).setMaxStackSize(64).setUnlocalizedName("debugger").setTextureName("voidCraft:debug");
	
		// projectiles
		voidChain = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(tabVoid).setUnlocalizedName("voidChain").setTextureName("VoidCraft:voidChain");
			
		// disc
		voidDiscs.add(new VoidRecord("voidCraft:Lavender Town", 271).setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("voidDisc1").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Lensko - Cetus", 289).setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("voidDisc2").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Starfox- Assault-Starwolf theme", 173).setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("voidDisc3").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:They Will Die", 250).setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("voidDisc4").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Warriors", 171).setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("voidDisc5").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Imagine Dragons - Shots (Broiler Remix)", 190).setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("voidDisc6").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Asgore", 154).setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("voidDisc7").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Core", 164).setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("voidDisc8").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Megalovania", 156).setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("voidDisc9").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Muffet", 100).setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("voidDisc10").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Papyrus", 58).setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("voidDisc11").setTextureName("VoidCraft:voidDisc"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Undyne", 156).setMaxStackSize(1).setCreativeTab(tabVoid).setUnlocalizedName("voidDisc12").setTextureName("VoidCraft:voidDisc"));
		
		// VoidBox 
		voidBox = new VoidBox().setHardness(7.0F).setStepSound(Blocks.stone.stepSound).setCreativeTab(tabVoid).setBlockName("voidBox").setBlockTextureName("VoidCraft:voidBox");
		// VoidInfuser 
		voidInfuserInert = new BasicVoidBlock(Material.iron).setHardness(7.0F).setStepSound(Blocks.iron_block.stepSound).setCreativeTab(tabVoid).setBlockName("voidInfuserInert").setBlockTextureName("VoidCraft:voidInfuser");
		voidInfuser = new VoidInfuser().setHardness(7.0F).setStepSound(Blocks.iron_block.stepSound).setCreativeTab(tabVoid).setBlockName("voidInfuser").setBlockTextureName("VoidCraft:voidInfuser");

		// Tools
		// void
		voidPickaxe = new VoidPickaxe(voidTools).setCreativeTab(tabVoid).setUnlocalizedName("voidPickaxe").setTextureName("VoidCraft:voidcrystal_Pickaxe");
		voidSpade = new VoidSpade(voidTools).setCreativeTab(tabVoid).setUnlocalizedName("voidShovel").setTextureName("VoidCraft:voidcrystal_Shovel");
		voidAxe = new VoidAxe(voidTools).setCreativeTab(tabVoid).setUnlocalizedName("voidAxe").setTextureName("VoidCraft:voidcrystal_Axe");
		voidSword = new VoidSword(voidTools).setCreativeTab(tabVoid).setUnlocalizedName("voidSword").setTextureName("VoidCraft:voidcrystal_Sword");
		voidHoe = new VoidHoe(voidTools).setCreativeTab(tabVoid).setUnlocalizedName("voidHoe").setTextureName("VoidCraft:voidcrystal_Hoe");

		// spectre
		angelicSword = new angelicSword(spectreTools).setCreativeTab(tabVoid).setUnlocalizedName("angelicSword").setTextureName("VoidCraft:angelicSword");
		spectrePickaxe = new spectrePickaxe(spectreTools).setCreativeTab(tabVoid).setUnlocalizedName("spectrePickaxe").setTextureName("VoidCraft:spectre_Pickaxe");
		spectreAxe = new spectreAxe(spectreTools).setCreativeTab(tabVoid).setUnlocalizedName("spectreAxe").setTextureName("VoidCraft:spectre_Axe");
		// chain
		chainSword = new chainSword(chainTools).setCreativeTab(tabVoid).setUnlocalizedName("chainSword").setTextureName("VoidCraft:bindSword");
		// molten
		moltenSword = new moltenSword(MoltenTools).setCreativeTab(tabVoid).setUnlocalizedName("moltenSword").setTextureName("VoidCraft:moltenVoidSword");
		// arch
		archSword = new archSword(ArchTools).setCreativeTab(tabVoid).setUnlocalizedName("archSword").setTextureName("VoidCraft:archAngelicSword");
		// demon
		demonSword = new demonSword(DemonTools).setCreativeTab(tabVoid).setUnlocalizedName("demonSword").setTextureName("VoidCraft:demonSword");

		// Armor
		//void
		voidHelmet = new VoidArmor(voidArmor, 0, 0, "void").setUnlocalizedName("voidHelmet");
		voidChest = new VoidArmor(voidArmor, 0, 1, "void").setUnlocalizedName("voidChest");
		voidLegs = new VoidArmor(voidArmor, 0, 2, "void").setUnlocalizedName("voidLegs");
		voidBoots = new VoidArmor(voidArmor, 0, 3, "void").setUnlocalizedName("voidBoots");
		//demon
		demonHelmet = new DemonArmor(demonArmor, 0, 0, "demon").setUnlocalizedName("demonHelmet");
		demonChest = new DemonArmor(demonArmor, 0, 1, "demon").setUnlocalizedName("demonChest");
		demonLegs = new DemonArmor(demonArmor, 0, 2, "demon").setUnlocalizedName("demonLegs");
		demonBoots = new DemonArmor(demonArmor, 0, 3, "demon").setUnlocalizedName("demonBoots");
		//xia
		xiaChest = new XiaArmor(xiaArmor, 0, 1, "xia").setUnlocalizedName("xiaChest");
	
		// Blocks
		blockVoidcrystal = new blockVoidcrystal(Material.glass).setHardness(7.0F).setStepSound(Blocks.glass.stepSound).setCreativeTab(tabVoid).setBlockName("blockVoidcrystal").setBlockTextureName("VoidCraft:VoidCrystalBlock");
		oreVoidcrystal = new OreVoidcrystal(Material.rock).setHardness(3.0F).setStepSound(Blocks.stone.stepSound).setCreativeTab(tabVoid).setBlockName("oreVoidcrystal");
		blockFakeBedrock = new blockFakeBedrock(Blocks.bedrock.getMaterial()).setHardness(30.0F).setStepSound(Blocks.bedrock.stepSound).setCreativeTab(tabVoid).setBlockName("blockFakeBedrock").setBlockTextureName("minecraft:bedrock");
		blockNoBreak = new blockNoBreak(Material.rock).setLightLevel(1.0F).setHardness(-1F).setStepSound(Blocks.wool.stepSound).setCreativeTab(tForge).setBlockName("blockNoBreak").setLightLevel(1.0F).setResistance(100).setBlockTextureName("VoidCraft:limbo/limbo");
		blockVoidbrick = new blockVoidbrick(Material.rock).setHardness(30.0F).setStepSound(Blocks.stone.stepSound).setCreativeTab(tabVoid).setBlockName("blockVoidbrick").setBlockTextureName("VoidCraft:VoidBrick");
		blockVoidfence = new VoidFence("VoidCraft:VoidBrick", Material.rock).setCreativeTab(tabVoid).setBlockName("blockVoidfence");
		blockVoidstairs = new VoidStairs(blockVoidbrick, 0).setCreativeTab(tabVoid).setBlockName("blockVoidstairs").setBlockTextureName("VoidCraft:VoidBrick");
		blockVoidBrickSlab = new VoidSlab(false, Material.rock).setCreativeTab(tabVoid).setBlockName("blockVoidslab").setBlockTextureName("VoidCraft:VoidBrick");
		voidMacerator = new VoidMacerator().setCreativeTab(tabVoid).setHardness(3.5F).setBlockName("voidMaceratorOff");
		Heimdall = new Heimdall(Material.iron).setCreativeTab(tabVoid).setHardness(3.5F).setBlockTextureName("voidCraft:heimdall").setBlockName("Heimdall");
		AIBlock = new AIBlock().setBlockUnbreakable().setBlockName("aiBlock");

		// Portal
		blockTeleporterVoid = new BlockTeleporter().setBlockName("blockTeleporterVoid").setCreativeTab(tabVoid);
		blockTeleporterXia = new BlockTeleporterXia().setBlockName("blockTeleporterXia").setCreativeTab(tabVoid);
		fireVoid = new FireVoid().setBlockName("voidfire").setCreativeTab(tabVoid).setBlockTextureName("VoidCraft:fireVoid");
	
		// Fluid
		fluidVoid = new Fluid("void");
		materialFluidVoid = new MaterialLiquid(MapColor.purpleColor);
		// -This has to be here for Fluids
		FluidRegistry.registerFluid(fluidVoid);
		// +This here must be last
		blockVoidFluid = new BlockVoidFluid(fluidVoid, Material.water).setDensity(-400).setBlockName("fluidVoid").setBlockTextureName("VoidCraft:voidFluid");
		voidBucket = new ItemVoidCraftBucket(blockVoidFluid).setUnlocalizedName("voidBucket").setMaxStackSize(1).setContainerItem(Items.bucket).setCreativeTab(tabVoid).setTextureName("VoidCraft:voidBucket");
		FluidContainerRegistry.registerFluidContainer(new FluidContainerData(FluidRegistry.getFluidStack(fluidVoid.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(voidBucket), new ItemStack(Items.bucket)));
		BucketHandler.INSTANCE.buckets.put(blockVoidFluid, voidBucket);
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
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
		
		//MISC BLOCK STUFF
		GameRegistry.registerBlock(blockVoidFluid, "blockVoidFluid");
		
		//Blocks 
		GameRegistry.registerBlock(oreVoidcrystal, oreVoidcrystal.getUnlocalizedName());
		GameRegistry.registerBlock(blockFakeBedrock, blockFakeBedrock.getUnlocalizedName());
		GameRegistry.registerBlock(blockNoBreak, blockNoBreak.getUnlocalizedName());
		GameRegistry.registerBlock(blockVoidcrystal, blockVoidcrystal.getUnlocalizedName());
		GameRegistry.registerBlock(blockVoidbrick, blockVoidbrick.getUnlocalizedName());
		GameRegistry.registerBlock(blockVoidstairs, blockVoidstairs.getUnlocalizedName());
		GameRegistry.registerBlock(blockVoidBrickSlab, blockVoidBrickSlab.getUnlocalizedName());
		GameRegistry.registerBlock(blockVoidfence, blockVoidfence.getUnlocalizedName());
		GameRegistry.registerBlock(voidMacerator, voidMacerator.getUnlocalizedName());
		GameRegistry.registerBlock(voidBox, voidBox.getUnlocalizedName());
		GameRegistry.registerBlock(voidInfuserInert, voidInfuserInert.getUnlocalizedName());
		GameRegistry.registerBlock(voidInfuser, voidInfuser.getUnlocalizedName());
		GameRegistry.registerBlock(Heimdall, Heimdall.getUnlocalizedName());
		GameRegistry.registerBlock(AIBlock, AIBlock.getUnlocalizedName());
		GameRegistry.registerBlock(fireVoid, fireVoid.getUnlocalizedName());
		
		//Items
		//-THE ITEMS
		GameRegistry.registerItem(voidCrystalBucket, voidCrystalBucket.getUnlocalizedName());
		GameRegistry.registerItem(voidBucket, voidBucket.getUnlocalizedName());
		GameRegistry.registerItem(voidcrystal, voidcrystal.getUnlocalizedName());
		GameRegistry.registerItem(ectoplasm, ectoplasm.getUnlocalizedName());
		GameRegistry.registerItem(voidChain, voidChain.getUnlocalizedName());
		GameRegistry.registerItem(MoltenvoidChainPart, MoltenvoidChainPart.getUnlocalizedName());
		GameRegistry.registerItem(MoltenvoidChain, MoltenvoidChain.getUnlocalizedName());
		GameRegistry.registerItem(burnBone, burnBone.getUnlocalizedName());
		GameRegistry.registerItem(voidCloth, voidCloth.getUnlocalizedName());
		GameRegistry.registerItem(voidStar, voidStar.getUnlocalizedName());
		GameRegistry.registerItem(debugger, debugger.getUnlocalizedName());
		//-TOOLS
		GameRegistry.registerItem(voidSword, voidSword.getUnlocalizedName());
		GameRegistry.registerItem(voidPickaxe, voidPickaxe.getUnlocalizedName());
		GameRegistry.registerItem(voidAxe, voidAxe.getUnlocalizedName());
		GameRegistry.registerItem(voidSpade, voidSpade.getUnlocalizedName());
		GameRegistry.registerItem(voidHoe, voidHoe.getUnlocalizedName());
		GameRegistry.registerItem(spectrePickaxe, spectrePickaxe.getUnlocalizedName());
		GameRegistry.registerItem(spectreAxe, spectreAxe.getUnlocalizedName());
		GameRegistry.registerItem(voidBurner, voidBurner.getUnlocalizedName());
		//-SWORDS
		GameRegistry.registerItem(angelicSword, angelicSword.getUnlocalizedName());
		GameRegistry.registerItem(chainSword, chainSword.getUnlocalizedName());
		GameRegistry.registerItem(moltenSword, moltenSword.getUnlocalizedName());
		GameRegistry.registerItem(archSword, archSword.getUnlocalizedName());
		GameRegistry.registerItem(demonSword, demonSword.getUnlocalizedName());
		//-ARMOR
		GameRegistry.registerItem(voidHelmet, voidHelmet.getUnlocalizedName());
		GameRegistry.registerItem(voidChest, voidChest.getUnlocalizedName());
		GameRegistry.registerItem(voidLegs, voidLegs.getUnlocalizedName());
		GameRegistry.registerItem(voidBoots, voidBoots.getUnlocalizedName());
		GameRegistry.registerItem(demonHelmet, demonHelmet.getUnlocalizedName());
		GameRegistry.registerItem(demonChest, demonChest.getUnlocalizedName());
		GameRegistry.registerItem(demonLegs, demonLegs.getUnlocalizedName());
		GameRegistry.registerItem(demonBoots, demonBoots.getUnlocalizedName());
		GameRegistry.registerItem(xiaChest, xiaChest.getUnlocalizedName());
		//-MISC
		GameRegistry.registerItem(ChainedSkull, ChainedSkull.getUnlocalizedName());
		//-DISCS
		for(Item disc : voidDiscs){
			GameRegistry.registerItem(disc, disc.getUnlocalizedName());
		}
		
		// dust for ore Dict
		GameRegistry.registerItem(ironDust, ironDust.getUnlocalizedName());
		OreDictionary.registerOre("dustIron", ironDust);
		GameRegistry.registerItem(goldDust, goldDust.getUnlocalizedName());
		OreDictionary.registerOre("dustGold", goldDust);
		GameRegistry.registerItem(coalDust, coalDust.getUnlocalizedName());
		OreDictionary.registerOre("dustCoal", coalDust);
		GameRegistry.registerItem(diamondDust, diamondDust.getUnlocalizedName());
		OreDictionary.registerOre("dustDiamond", diamondDust);
		GameRegistry.registerItem(copperDust, copperDust.getUnlocalizedName());
		OreDictionary.registerOre("dustCopper", copperDust);
		GameRegistry.registerItem(tinDust, tinDust.getUnlocalizedName());
		OreDictionary.registerOre("dustTin", tinDust);
		GameRegistry.registerItem(leadDust, leadDust.getUnlocalizedName());
		OreDictionary.registerOre("dustLead", leadDust);
		GameRegistry.registerItem(quartzDust, quartzDust.getUnlocalizedName());
		OreDictionary.registerOre("dustQuartz", quartzDust);
		
		//Projectiles
		EntityRegistry.registerGlobalEntityID(VoidChain.class, "VoidChain", EntityRegistry.findGlobalUniqueEntityId()); 
		EntityRegistry.registerModEntity(VoidChain.class, "VoidChain", 0, this, 128, 1, true);
		EntityRegistry.registerGlobalEntityID(AcidBall.class, "AcidBall", EntityRegistry.findGlobalUniqueEntityId()); 
		EntityRegistry.registerModEntity(AcidBall.class, "AcidBall", 1, this, 128, 1, true);
		EntityRegistry.registerGlobalEntityID(HerobrineFireball.class, "HerobrineFireball", EntityRegistry.findGlobalUniqueEntityId()); 
		EntityRegistry.registerModEntity(HerobrineFireball.class, "HerobrineFireball", 2, this, 128, 1, true);
		
		
		

		// biome
		Height bvoidmm = new Height(-1F, 0.1F);
		biomeVoid = new BiomeGenVoid(251).setBiomeName("The Void").setHeight(bvoidmm).setTemperatureRainfall(0.10F, 0.0F).setDisableRain();
		biomeXia = new BiomeGenXia(252).setBiomeName("???").setHeight(bvoidmm).setTemperatureRainfall(0.10F, 0.0F).setDisableRain();
		//BiomeManager.coolBiomes.add(new BiomeEntry(biomeVoid, 100));

		//Dimension
		DimensionManager.registerProviderType(dimensionIdVoid, WorldProviderVoid.class, false);
		DimensionManager.registerDimension(dimensionIdVoid, dimensionIdVoid);
		
		DimensionManager.registerProviderType(dimensionIdXia, WorldProviderXia.class, false);
		DimensionManager.registerDimension(dimensionIdXia, dimensionIdXia);

		GameRegistry.registerBlock(blockTeleporterVoid, blockTeleporterVoid.getUnlocalizedName());
		GameRegistry.registerBlock(blockTeleporterXia, blockTeleporterXia.getUnlocalizedName());

		// crafting
		// -blocks
		GameRegistry.addRecipe(new ItemStack(blockVoidcrystal), "XXX", "XXX", "XXX", 'X', voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidInfuserInert), "XYX", "YZY", "XYX", 'X', blockVoidbrick, 'Y', voidCloth, 'Z', Items.cauldron);
		GameRegistry.addShapelessRecipe(new ItemStack(voidInfuser), voidInfuserInert, voidStar);
		GameRegistry.addRecipe(new ItemStack(voidBox), "XXX", "XYX", "XZX", 'X', voidCloth, 'Y', Blocks.jukebox, 'Z', voidStar);
		// -items
		GameRegistry.addShapelessRecipe(new ItemStack(voidcrystal, 9), blockVoidcrystal);
		// +Crystal bucket thing
		GameRegistry.addShapelessRecipe(new ItemStack(voidCrystalBucket), voidcrystal, Items.bucket);
		// +Records
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(0)), "ZCC", "CXC", "CCC", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(1)), "CZC", "CXC", "CCC", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(2)), "CCZ", "CXC", "CCC", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(3)), "CCC", "ZXC", "CCC", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(4)), "CCC", "CXZ", "CCC", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(5)), "CCC", "CXC", "ZCC", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		//GameRegistry.addRecipe(new ItemStack(voidDiscs.get(6)), "CCC", "CXC", "CZC", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		//GameRegistry.addRecipe(new ItemStack(voidDiscs.get(7)), "CCC", "CXC", "CCZ", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		//GameRegistry.addRecipe(new ItemStack(voidDiscs.get(8)), "ZZC", "CXC", "CCC", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		//GameRegistry.addRecipe(new ItemStack(voidDiscs.get(9)), "ZCZ", "CXC", "CCC", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		//GameRegistry.addRecipe(new ItemStack(voidDiscs.get(10)), "ZCC", "ZXC", "CCC", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		//GameRegistry.addRecipe(new ItemStack(voidDiscs.get(11)), "ZCC", "CXZ", "CCC", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		//GameRegistry.addRecipe(new ItemStack(voidDiscs.get(12)), "ZCC", "CXC", "ZCC", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		//GameRegistry.addRecipe(new ItemStack(voidDiscs.get(13)), "ZCC", "CXC", "CZC", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		//GameRegistry.addRecipe(new ItemStack(voidDiscs.get(14)), "ZCC", "CXC", "CCZ", 'Z', burnBone, 'X', voidcrystal, 'C', Items.coal);
		// -voidburner
		GameRegistry.addShapelessRecipe(new ItemStack(voidBurner), voidcrystal, new ItemStack(Items.flint_and_steel, 1, WILDCARD_VALUE));
		// -armor
		// +void
		GameRegistry.addRecipe(new ItemStack(voidHelmet, 1), "XXX", "X X", 'X', voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidChest, 1), "X X", "XXX", "XXX", 'X', voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidLegs, 1), "XXX", "X X", "X X", 'X', voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidBoots, 1), "X X", "X X", 'X', voidcrystal);
		/*
		// +demon
		GameRegistry.addRecipe(new ItemStack(demonHelmet, 1), new Object[] {
			"OXO", "O O", "   ", 'O', voidcrystal, 'X', voidStar });
		GameRegistry.addRecipe(new ItemStack(demonChest, 1), new Object[] {
			"O O", "OXO", "OOO", 'O', voidcrystal, 'X', voidStar });
		GameRegistry.addRecipe(new ItemStack(demonLegs, 1), new Object[] {
			"OXO", "O O", "O O", 'O', voidcrystal, 'X', voidStar });
		GameRegistry.addRecipe(new ItemStack(demonBoots, 1), new Object[] {
			"O O", "OXO", "   ", 'O', voidcrystal, 'X', voidStar });
		*/
		// -tools
		// +void
		GameRegistry.addRecipe(new ItemStack(voidPickaxe, 1), "XXX", " S ", " S ", 'X', voidcrystal, 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(voidAxe, 1), " XX", " SX", " S ", 'X', voidcrystal, 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(voidSpade, 1), " X ", " S ", " S ", 'X', voidcrystal, 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(voidHoe, 1), " XX", " S ", " S ", 'X', voidcrystal, 'S', Items.stick);
		GameRegistry.addRecipe(new ItemStack(voidSword, 1), " X ", " X ", " S ", 'X', voidcrystal, 'S', Items.stick);
		// +spectre
		GameRegistry.addRecipe(new ItemStack(spectrePickaxe, 1), "XXX", "XSX", "XXX", 'S', new ItemStack(voidPickaxe, 1, WILDCARD_VALUE), 'X', ectoplasm);
		GameRegistry.addRecipe(new ItemStack(spectreAxe, 1), "XXX", "XSX", "XXX", 'S', new ItemStack(voidAxe, 1, WILDCARD_VALUE), 'X', ectoplasm);
		GameRegistry.addRecipe(new ItemStack(angelicSword, 1), "XXX", "XSX", "XXX", 'S', new ItemStack(voidSword, 1, WILDCARD_VALUE), 'X', ectoplasm);
		// +molten and beyond
		GameRegistry.addRecipe(new ItemStack(MoltenvoidChain), "XYX", "YXY", "XYX", 'Y', MoltenvoidChainPart, 'X', burnBone);
		GameRegistry.addRecipe(new ItemStack(moltenSword), "XXX", "XSX", "XXX", 'X', MoltenvoidChain, 'S', new ItemStack(chainSword, 1, WILDCARD_VALUE));
		GameRegistry.addRecipe(new ItemStack(chainSword), "XXX", "XSX", "XXX", 'X', voidChain, 'S', new ItemStack(voidSword, 1, WILDCARD_VALUE));
		GameRegistry.addRecipe(new ItemStack(archSword), "SSS", "XYZ", "SSS", 'S', MoltenvoidChain, 'X', new ItemStack(chainSword, 1, WILDCARD_VALUE), 'Y', new ItemStack(angelicSword, 1, WILDCARD_VALUE), 'Z', new ItemStack(moltenSword, 1, WILDCARD_VALUE));
		//GameRegistry.addRecipe(new ItemStack(demonSword), " Y ", "XZX",
		//	" X ", 'X', burnBone, 'Z', voidStar, 'Y', voidcrystal);
		// -misc
		// +Boss
		GameRegistry.addRecipe(new ItemStack(ChainedSkull), "XYX", "YZY", "XYX", 'X', MoltenvoidChain, 'Y', burnBone, 'Z', new ItemStack(Items.skull, 1, 1));
		// +skull
		GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 1), "XX", "XX", 'X', burnBone);
		// +brick
		GameRegistry.addRecipe(new ItemStack(blockVoidbrick), "XX", "XX", 'X', blockVoidcrystal);
		// +stairs
		GameRegistry.addRecipe(new ItemStack(blockVoidstairs, 6), "X  ", "XX ", "XXX", 'X', blockVoidbrick);
		GameRegistry.addRecipe(new ItemStack(blockVoidstairs, 6), "  X", " XX", "XXX", 'X', blockVoidbrick);
		GameRegistry.addRecipe(new ItemStack(blockVoidBrickSlab, 6), "XXX", 'X', blockVoidbrick);
		// +fence
		GameRegistry.addRecipe(new ItemStack(blockVoidfence), "X X", "XXX", "X X", 'X', voidcrystal);
		
		//Smelting
		GameRegistry.addSmelting(oreVoidcrystal, new ItemStack(voidcrystal), 0.1F);
		GameRegistry.addSmelting(voidChain, new ItemStack(MoltenvoidChainPart), 0.1F);
		GameRegistry.addSmelting(voidCrystalBucket, new ItemStack(voidBucket), 0.1F);
		// dust
		GameRegistry.addSmelting(ironDust, new ItemStack(Items.iron_ingot), 0);
		GameRegistry.addSmelting(goldDust, new ItemStack(Items.gold_ingot), 0);
		GameRegistry.addSmelting(diamondDust, new ItemStack(Items.diamond), 0);
		this.addPreSmelting(copperDust, "ingotCopper");
		this.addPreSmelting(tinDust, "ingotTin");
		this.addPreSmelting(leadDust, "ingotLead");

		// World Gen
		GameRegistry.registerWorldGenerator(new WorldGeneratorVoid(), 0);
		
		MapGenStructureIO.registerStructure(StructureTestStart.class, "VoidFortress");
		StructureTestPieces.func_143049_a();
		

		// Mobs
		EntityRegistry.registerGlobalEntityID(EntityMobWraith.class, "Wraith", EntityRegistry.findGlobalUniqueEntityId(), 0xFFFFFF, 0x000000);
		EntityRegistry.addSpawn(EntityMobWraith.class, 1, 0, 1, EnumCreatureType.monster, biomeVoid);
		
		EntityRegistry.registerGlobalEntityID(EntityMobSpectreChain.class, "SpectreChain",EntityRegistry.findGlobalUniqueEntityId(), 0xAA00FF, 0x000000);
		EntityRegistry.addSpawn(EntityMobSpectreChain.class, 1, 0, 1, EnumCreatureType.monster, biomeVoid);

		EntityRegistry.registerGlobalEntityID(EntityMobVoidWrath.class, "VoidWrath", EntityRegistry.findGlobalUniqueEntityId(), 0xFF0000, 0x000000);
		EntityRegistry.addSpawn(EntityMobVoidWrath.class, 0, 0, 0, EnumCreatureType.monster, biomeVoid);
		
		//TODO MORE CONTENT TO ENTITY
		EntityRegistry.registerGlobalEntityID(EntityMobLich.class, "Lich", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0x4444FF);
		EntityRegistry.addSpawn(EntityMobLich.class, 3, 0, 1, EnumCreatureType.monster, BiomeGenBase.swampland);
		
		//TODO FIX THE ENTITY
		EntityRegistry.registerGlobalEntityID(EntityMobVoidBoss.class, "VoidBoss", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xFF0000);
		EntityRegistry.addSpawn(EntityMobVoidBoss.class, 0, 0, 0, EnumCreatureType.monster, biomeVoid);
		
		//npcs
		EntityRegistry.registerGlobalEntityID(EntityMobHerobrine.class, "Herobrine", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xFF0000);
		EntityRegistry.addSpawn(EntityMobHerobrine.class, 0, 0, 0, EnumCreatureType.monster, this.biomeVoid);
		
		EntityRegistry.registerGlobalEntityID(EntityMobDol.class, "Dol", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0x775500);
		EntityRegistry.addSpawn(EntityMobDol.class, 0, 0, 0, EnumCreatureType.monster, this.biomeVoid);
		
		EntityRegistry.registerGlobalEntityID(EntityMobZol.class, "Zol", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0x8888FF);
		EntityRegistry.addSpawn(EntityMobZol.class, 0, 0, 0, EnumCreatureType.monster, this.biomeVoid);
		
		EntityRegistry.registerGlobalEntityID(EntityMobXia.class, "Xia", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xFFFF00);
		EntityRegistry.addSpawn(EntityMobXia.class, 0, 0, 0, EnumCreatureType.monster, this.biomeVoid);
		
		EntityRegistry.registerGlobalEntityID(EntityMobXia2.class, "Xia2", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xFFFF00);
		EntityRegistry.addSpawn(EntityMobXia2.class, 0, 0, 0, EnumCreatureType.monster, this.biomeVoid);
		
		//Add Void Macerator Recipes
		//--OreDict
		maceratorList.addToOreDict("oreIron", new ItemStack(ironDust, 4));
		maceratorList.addToOreDict("oreGold", new ItemStack(goldDust, 4));
		maceratorList.addToOreDict("oreDiamond", new ItemStack(diamondDust, 4));
		maceratorList.addToOreDict("oreCopper", new ItemStack(copperDust, 4));
		maceratorList.addToOreDict("oreTin", new ItemStack(tinDust, 4));
		maceratorList.addToOreDict("oreLead", new ItemStack(leadDust, 4));
		maceratorList.addToOreDict("oreQuartz", new ItemStack(quartzDust, 4));
		maceratorList.addToOreDict("ingotIron", new ItemStack(ironDust, 1));
		maceratorList.addToOreDict("ingotGold", new ItemStack(goldDust, 1));
		maceratorList.addToOreDict("gemQuartz", new ItemStack(quartzDust, 1));
		maceratorList.addToOreDict("gemDiamond", new ItemStack(diamondDust, 1));
		maceratorList.addToOreDict("ingotCopper", new ItemStack(copperDust, 1));
		maceratorList.addToOreDict("ingotTin", new ItemStack(tinDust, 1));
		maceratorList.addToOreDict("ingotLead", new ItemStack(leadDust, 1));
		//--HardCode
		maceratorList.addToHardCode(new ItemStack(Items.coal, 1), new ItemStack(coalDust, 4));
		
		//Register Achievements
		voidCraftAchMainLine_1 = new Achievement("achievement.achM_1", "achM_1", 0, 0, voidcrystal, (Achievement) null).setSpecial().initIndependentStat().registerStat();
		voidCraftAchMainLine_2 = new Achievement("achievement.achM_2", "achM_2", 0, 3, blockVoidcrystal, voidCraftAchMainLine_1).setSpecial().registerStat();
		voidCraftAchMainLine_3 = new Achievement("achievement.achM_3", "achM_3", 0, 6, voidInfuser, voidCraftAchMainLine_2).registerStat();
		voidCraftAchMainLine_4 = new Achievement("achievement.achM_4", "achM_4", 0, 9, voidMacerator, voidCraftAchMainLine_3).registerStat();
		
		voidCraftAchSideLine1_1 = new Achievement("achievement.achS1_1", "achS1_1", 3, 0, voidSword, voidCraftAchMainLine_1).registerStat();
		voidCraftAchSideLine1_2 = new Achievement("achievement.achS1_2", "achS1_2", 6, 0, angelicSword, voidCraftAchSideLine1_1).setSpecial().registerStat();
		
		voidCraftAchSideLine2_1 = new Achievement("achievement.achS2_1", "achS2_1", 6, 3, chainSword, voidCraftAchSideLine1_1).registerStat();
		voidCraftAchSideLine2_2 = new Achievement("achievement.achS2_2", "achS2_2", 6, 6, moltenSword, voidCraftAchSideLine2_1).registerStat();
		voidCraftAchSideLine2_3 = new Achievement("achievement.achS2_3", "achS2_3", 4, 6, archSword, voidCraftAchSideLine2_2).setSpecial().registerStat();
		voidCraftAchSideLine2_4 = new Achievement("achievement.achS2_4", "achS2_4", 2, 6, demonSword, voidCraftAchSideLine2_3).setSpecial().registerStat();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e){ 
		logger.info("Starting VoidCraft PostInit");
		
		//Achievement page must be in post-init
		Achievement[] achArray = {
				voidCraftAchMainLine_1,
				voidCraftAchMainLine_2,
				voidCraftAchMainLine_3,
				voidCraftAchMainLine_4,
				voidCraftAchSideLine1_1,
				voidCraftAchSideLine1_2,
				voidCraftAchSideLine2_1,
				voidCraftAchSideLine2_2,
				voidCraftAchSideLine2_3,
				voidCraftAchSideLine2_4
		};
		AchievementPage.registerAchievementPage(new AchievementPage("VoidCraft", achArray));
		
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
		
		
		channel.register(new VoidCraftServerPacketHandler());
		proxy.registerNetwork();
		proxy.registerRenders();
		proxy.registerBlocks();
		proxy.registerRenderInformation();
		proxy.registerItems();
		proxy.registerMISC();
		proxy.registerAchievements();
	}

	private void addPreSmelting(Item i, String s){
		for(ItemStack ore : OreDictionary.getOres(s)){
			if(ore != null){
				GameRegistry.addSmelting(i, ore, ore.getItemDamage());
			}
		}
	}
	
	

}





























