package Tamaized.Voidcraft.registry;

import Tamaized.TamModized.blocks.TamBlockFarmland;
import Tamaized.TamModized.items.TamItem;
import Tamaized.TamModized.items.TamItemSeed;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.common.blocks.tileentity.TileEntityFakeBedrockFarmland;
import Tamaized.Voidcraft.common.entity.nonliving.EntityObsidianFlask;
import Tamaized.Voidcraft.common.events.DamageEvent;
import Tamaized.Voidcraft.common.items.*;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelDynBucket;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class VoidCraftItems {

	public static ArrayList<ItemRecord> voidDiscs;
	public static VadeMecum vadeMecum;
	public static TamItem ectoplasm;
	public static TamItem voidcrystal;
	public static EmptyObsidianFlask emptyObsidianFlask;
	public static ObsidianFlask obsidianFlask;
	public static ObsidianFlask obsidianFlaskFire;
	public static ObsidianFlask obsidianFlaskFreeze;
	public static ObsidianFlask obsidianFlaskShock;
	public static ObsidianFlask obsidianFlaskAcid;
	public static ObsidianFlask obsidianFlaskVoid;
	public static TamItem voidChain;
	public static TamItem MoltenvoidChain;
	public static TamItem MoltenvoidChainPart;
	public static TamItem burnBone;
	public static VoidStar voidStar;
	public static ChainedSkull ChainedSkull;
	public static TamItem voidCloth;
	public static TamItem voidCrystalBucket;
	public static VoidicEssence voidicEssence;
	public static TamItem voidicPhlogiston;
	public static TamItem voidicDragonScale;
	public static TamItem astralEssence;
	public static TamItem quoriFragment;
	public static TamItem voidicSteel;
	public static TamItem quartzDust;
	public static TamItem coalDust;
	public static TamItem ironDust;
	public static TamItem goldDust;
	public static TamItem copperDust;
	public static TamItem tinDust;
	public static TamItem leadDust;
	public static TamItem lapisDust;
	public static TamItem emeraldDust;
	public static TamItem diamondDust;
	public static Debugger debugger;
	public static CreativeVoidBucket creativeVoidBucket;
	public static VoidicSuppressor voidicSuppressor;
	// public static HookShot hookShot;
	public static VoidicDrill voidicDrill;
	public static RealityTeleporter realityTeleporter;
	public static VoidCrystalShield voidCrystalShield;
	public static TamItemSeed etherealSeed;
	public static EtherealFruit etherealFruit;
	public static EtherealFruit etherealFruit_redstone;
	public static EtherealFruit etherealFruit_lapis;
	public static EtherealFruit etherealFruit_gold;
	public static EtherealFruit etherealFruit_emerald;
	public static EtherealFruit etherealFruit_diamond;
	public static ItemDreamBed dreamBed;
	private static ArrayList<ITamRegistry> modelList;

	// public static VoidRecord record_noStrings;
	// public static VoidRecord record_bleedingThrough;
	// public static VoidRecord record_stringsAttached;
	// public static VoidRecord record_running;

	static {
		modelList = new ArrayList<>();

		modelList.add(vadeMecum = new VadeMecum(VoidCraftCreativeTabs.tabVoid, "vademecum", 1));

		modelList.add(emptyObsidianFlask = new EmptyObsidianFlask(VoidCraftCreativeTabs.tabVoid, "emptyobsidianflask", 16));
		modelList.add(obsidianFlask = new ObsidianFlask(EntityObsidianFlask.Type.Normal, VoidCraftCreativeTabs.tabVoid, "obsidianflask", 16));
		modelList.add(obsidianFlaskFire = new ObsidianFlask(EntityObsidianFlask.Type.Fire, VoidCraftCreativeTabs.tabVoid, "flask_fire", 16));
		modelList.add(obsidianFlaskFreeze = new ObsidianFlask(EntityObsidianFlask.Type.Freeze, VoidCraftCreativeTabs.tabVoid, "flask_freeze", 16));
		modelList.add(obsidianFlaskShock = new ObsidianFlask(EntityObsidianFlask.Type.Shock, VoidCraftCreativeTabs.tabVoid, "flask_shock", 16));
		modelList.add(obsidianFlaskAcid = new ObsidianFlask(EntityObsidianFlask.Type.Acid, VoidCraftCreativeTabs.tabVoid, "flask_acid", 16));
		modelList.add(obsidianFlaskVoid = new ObsidianFlask(EntityObsidianFlask.Type.Void, VoidCraftCreativeTabs.tabVoid, "flask_void", 16));
		modelList.add(ectoplasm = new TamItem(VoidCraftCreativeTabs.tabVoid, "ectoplasm", 64));
		modelList.add(voidcrystal = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidcrystal", 64));
		modelList.add(voidChain = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidchain", 64));
		modelList.add(MoltenvoidChain = new TamItem(VoidCraftCreativeTabs.tabVoid, "moltenvoidchain", 64));
		modelList.add(MoltenvoidChainPart = new TamItem(VoidCraftCreativeTabs.tabVoid, "moltenvoidchainpart", 64));
		modelList.add(burnBone = new TamItem(VoidCraftCreativeTabs.tabVoid, "burnbone", 64));
		modelList.add(voidStar = new VoidStar(VoidCraftCreativeTabs.tabVoid, "voidstar", 1));
		modelList.add(ChainedSkull = new ChainedSkull(VoidCraftCreativeTabs.tabVoid, "chainedskull", 1));
		modelList.add(voidCloth = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidcloth", 64));
		modelList.add(voidCrystalBucket = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidcrystalbucket", 1));
		modelList.add(voidicEssence = new VoidicEssence(VoidCraftCreativeTabs.tabVoid, "voidicessence", 1));
		modelList.add(voidicPhlogiston = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidicphlogiston", 64));
		modelList.add(voidicDragonScale = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidicdragonscale", 64));
		modelList.add(astralEssence = new TamItem(VoidCraftCreativeTabs.tabVoid, "astralessence", 64));
		modelList.add(quoriFragment = new TamItem(VoidCraftCreativeTabs.tabVoid, "quorifragment", 64));

		modelList.add(voidicSteel = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidicsteel", 64));

		// dust
		modelList.add(quartzDust = new TamItem(VoidCraftCreativeTabs.tabVoid, "quartzdust", 64));
		modelList.add(coalDust = new TamItem(VoidCraftCreativeTabs.tabVoid, "coaldust", 64));
		modelList.add(ironDust = new TamItem(VoidCraftCreativeTabs.tabVoid, "irondust", 64));
		modelList.add(goldDust = new TamItem(VoidCraftCreativeTabs.tabVoid, "golddust", 64));
		modelList.add(copperDust = new TamItem(VoidCraftCreativeTabs.tabVoid, "copperdust", 64));
		modelList.add(tinDust = new TamItem(VoidCraftCreativeTabs.tabVoid, "tindust", 64));
		modelList.add(leadDust = new TamItem(VoidCraftCreativeTabs.tabVoid, "leaddust", 64));
		modelList.add(lapisDust = new TamItem(VoidCraftCreativeTabs.tabVoid, "lapisdust", 64));
		modelList.add(emeraldDust = new TamItem(VoidCraftCreativeTabs.tabVoid, "emeralddust", 64));
		modelList.add(diamondDust = new TamItem(VoidCraftCreativeTabs.tabVoid, "diamonddust", 64));

		modelList.add(debugger = new Debugger(VoidCraftCreativeTabs.tabVoid, "debugger", 1));
		creativeVoidBucket = new CreativeVoidBucket(VoidCraftCreativeTabs.tabVoid, "creativevoidbucket", 1);
		// modelList.add(hookShot = new HookShot(VoidCraftCreativeTabs.tabVoid, "hookShot", 1));

		modelList.add(voidicSuppressor = new VoidicSuppressor(VoidCraftCreativeTabs.tabVoid, "voidicsuppressor", 1));
		modelList.add(voidicDrill = new VoidicDrill(VoidCraftCreativeTabs.tabVoid, "voidicdrill", 1));
		modelList.add(realityTeleporter = new RealityTeleporter(VoidCraftCreativeTabs.tabVoid, "realityteleporter", 1));

		modelList.add(voidCrystalShield = new VoidCrystalShield(VoidCraftCreativeTabs.tabVoid, "voidcrystalshield"));
		DamageEvent.shieldRegistry.add(voidCrystalShield);

		ArrayList<TamBlockFarmland> soilList = new ArrayList<TamBlockFarmland>();
		soilList.add(VoidCraftBlocks.blockFakeBedrockFarmland);
		modelList.add(etherealSeed = new TamItemSeed(VoidCraftCreativeTabs.tabVoid, "etherealseed", 64, VoidCraftBlocks.etherealPlant, soilList));
		modelList.add(etherealFruit = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.NORMAL, VoidCraftCreativeTabs.tabVoid, "etherealfruit", 64, 2, false));
		modelList.add(etherealFruit_redstone = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.REDSTONE, VoidCraftCreativeTabs.tabVoid, "etherealfruit_redstone", 64, 2, false));
		modelList.add(etherealFruit_lapis = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.LAPIS, VoidCraftCreativeTabs.tabVoid, "etherealfruit_lapis", 64, 2, false));
		modelList.add(etherealFruit_gold = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.GOLD, VoidCraftCreativeTabs.tabVoid, "etherealfruit_gold", 64, 2, false));
		modelList.add(etherealFruit_emerald = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.EMERALD, VoidCraftCreativeTabs.tabVoid, "etherealfruit_emerald", 64, 2, false));
		modelList.add(etherealFruit_diamond = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.DIAMOND, VoidCraftCreativeTabs.tabVoid, "etherealfruit_diamond", 64, 2, false));

		modelList.add(dreamBed = new ItemDreamBed(VoidCraftCreativeTabs.tabVoid, "dreambed"));

		// modelList.add(record_noStrings = new VoidRecord("Approaching Nirvana - No Strings Attached", VoidSoundEvents.MusicDiscSoundEvents.No_Strings_Attached, "voidDisc1"));
		// modelList.add(record_bleedingThrough = new VoidRecord("Haven - Bleeding Through", VoidSoundEvents.MusicDiscSoundEvents.Haven_Bleeding_Through, "voidDisc2"));
		// modelList.add(record_stringsAttached = new VoidRecord("Approaching Nirvana - Strings Attached", VoidSoundEvents.MusicDiscSoundEvents.Strings_Attached, "voidDisc3"));
		// modelList.add(record_running = new VoidRecord("Approaching Nirvana - Running", VoidSoundEvents.MusicDiscSoundEvents.Running, "voidDisc4"));

		voidDiscs = new ArrayList<ItemRecord>();
		// voidDiscs.add(record_noStrings);
		// voidDiscs.add(record_bleedingThrough);
		// voidDiscs.add(record_stringsAttached);
		// voidDiscs.add(record_running);
	}

	public static void init() {
		OreDictionary.registerOre("ingotSteel", voidicSteel);

		OreDictionary.registerOre("dustQuartz", quartzDust);
		OreDictionary.registerOre("dustCoal", coalDust);
		OreDictionary.registerOre("dustIron", ironDust);
		OreDictionary.registerOre("dustGold", goldDust);
		OreDictionary.registerOre("dustCopper", copperDust);
		OreDictionary.registerOre("dustTin", tinDust);
		OreDictionary.registerOre("dustLead", leadDust);
		OreDictionary.registerOre("dustLapis", lapisDust);
		OreDictionary.registerOre("dustEmerald", emeraldDust);
		OreDictionary.registerOre("dustDiamond", diamondDust);

		GameRegistry.addSmelting(VoidCraftBlocks.oreVoidcrystal, new ItemStack(voidcrystal), 0.1F);
		GameRegistry.addSmelting(voidChain, new ItemStack(MoltenvoidChainPart), 0.1F);
		GameRegistry.addSmelting(voidCrystalBucket, VoidCraftFluids.voidBucket.getBucket(), 0.1F);
		// dust
		GameRegistry.addSmelting(ironDust, new ItemStack(Items.IRON_INGOT), 0);
		GameRegistry.addSmelting(goldDust, new ItemStack(Items.GOLD_INGOT), 0);
		GameRegistry.addSmelting(diamondDust, new ItemStack(Items.DIAMOND), 0);
		addPreSmelting(copperDust, "ingotCopper");
		addPreSmelting(tinDust, "ingotTin");
		addPreSmelting(leadDust, "ingotLead");

	}

	private static void addPreSmelting(Item i, String s) {
		for (ItemStack ore : OreDictionary.getOres(s)) {
			if (!ore.isEmpty()) {
				GameRegistry.addSmelting(i, ore, ore.getItemDamage());
			}
		}
	}

/*	public void clientPreInit() {
//		VadeMecumMeshDefinition.preRegister();
//		ModelLoader.setCustomMeshDefinition(vademecum, new VadeMecumMeshDefinition());
	}*/

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for (ITamRegistry b : modelList)
			b.registerBlock(event);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (ITamRegistry b : modelList)
			b.registerItem(event);
		event.getRegistry().register(creativeVoidBucket);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for (ITamRegistry b : modelList)
			b.registerModel(event);
		ModelLoader.setCustomModelResourceLocation(creativeVoidBucket, 0, ModelDynBucket.LOCATION);
	}

}
