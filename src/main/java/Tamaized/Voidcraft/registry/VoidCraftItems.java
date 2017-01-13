package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.blocks.TamBlockFarmland;
import Tamaized.TamModized.items.TamItem;
import Tamaized.TamModized.items.TamItemSeed;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityFakeBedrockFarmland;
import Tamaized.Voidcraft.items.ChainedSkull;
import Tamaized.Voidcraft.items.Debugger;
import Tamaized.Voidcraft.items.EmptyObsidianFlask;
import Tamaized.Voidcraft.items.EtherealFruit;
import Tamaized.Voidcraft.items.ObsidianFlask;
import Tamaized.Voidcraft.items.RealityTeleporter;
import Tamaized.Voidcraft.items.VadeMecum;
import Tamaized.Voidcraft.items.VoidStar;
import Tamaized.Voidcraft.items.VoidicDrill;
import Tamaized.Voidcraft.items.VoidicEssence;
import Tamaized.Voidcraft.items.VoidicSuppressor;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class VoidCraftItems implements ITamRegistry {

	public static ArrayList<ItemRecord> voidDiscs;
	private ArrayList<ITamModel> modelList;

	public static VadeMecum vadeMecum;

	public static TamItem ectoplasm;
	public static TamItem voidcrystal;
	public static EmptyObsidianFlask emptyObsidianFlask;
	public static ObsidianFlask obsidianFlask;
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
	// public static HookShot hookShot;

	public static VoidicSuppressor voidicSuppressor;
	public static VoidicDrill voidicDrill;
	public static RealityTeleporter realityTeleporter;

	public static TamItemSeed etherealSeed;
	public static EtherealFruit etherealFruit;
	public static EtherealFruit etherealFruit_redstone;
	public static EtherealFruit etherealFruit_lapis;
	public static EtherealFruit etherealFruit_gold;
	public static EtherealFruit etherealFruit_emerald;
	public static EtherealFruit etherealFruit_diamond;

	// public static VoidRecord record_noStrings;
	// public static VoidRecord record_bleedingThrough;
	// public static VoidRecord record_stringsAttached;
	// public static VoidRecord record_running;

	@Override
	public void preInit() {
		modelList = new ArrayList<ITamModel>();

		vadeMecum = new VadeMecum(voidCraft.tabs.tabVoid, "vadeMecum", 1); // Don't add this to the model list as it uses an ItemMeshDefinition

		modelList.add(emptyObsidianFlask = new EmptyObsidianFlask(voidCraft.tabs.tabVoid, "emptyObsidianFlask", 16));
		modelList.add(obsidianFlask = new ObsidianFlask(voidCraft.tabs.tabVoid, "obsidianFlask", 16));
		modelList.add(ectoplasm = new TamItem(voidCraft.tabs.tabVoid, "ectoplasm", 64));
		modelList.add(voidcrystal = new TamItem(voidCraft.tabs.tabVoid, "voidcrystal", 64));
		modelList.add(voidChain = new TamItem(voidCraft.tabs.tabVoid, "voidChain", 64));
		modelList.add(MoltenvoidChain = new TamItem(voidCraft.tabs.tabVoid, "MoltenvoidChain", 64));
		modelList.add(MoltenvoidChainPart = new TamItem(voidCraft.tabs.tabVoid, "MoltenvoidChainPart", 64));
		modelList.add(burnBone = new TamItem(voidCraft.tabs.tabVoid, "burnBone", 64));
		modelList.add(voidStar = new VoidStar(voidCraft.tabs.tabVoid, "voidStar", 1));
		modelList.add(ChainedSkull = new ChainedSkull(voidCraft.tabs.tabVoid, "ChainedSkull", 1));
		modelList.add(voidCloth = new TamItem(voidCraft.tabs.tabVoid, "voidCloth", 64));
		modelList.add(voidCrystalBucket = new TamItem(voidCraft.tabs.tabVoid, "voidCrystalBucket", 1));
		modelList.add(voidicEssence = new VoidicEssence(voidCraft.tabs.tabVoid, "voidicEssence", 1));
		modelList.add(voidicPhlogiston = new TamItem(voidCraft.tabs.tabVoid, "voidicPhlogiston", 64));
		modelList.add(voidicDragonScale = new TamItem(voidCraft.tabs.tabVoid, "voidicDragonScale", 64));
		modelList.add(astralEssence = new TamItem(voidCraft.tabs.tabVoid, "astralEssence", 64));
		modelList.add(quoriFragment = new TamItem(voidCraft.tabs.tabVoid, "quoriFragment", 64));
		
		// dust
		modelList.add(quartzDust = new TamItem(voidCraft.tabs.tabVoid, "quartzDust", 64));
		modelList.add(coalDust = new TamItem(voidCraft.tabs.tabVoid, "coalDust", 64));
		modelList.add(ironDust = new TamItem(voidCraft.tabs.tabVoid, "ironDust", 64));
		modelList.add(goldDust = new TamItem(voidCraft.tabs.tabVoid, "goldDust", 64));
		modelList.add(copperDust = new TamItem(voidCraft.tabs.tabVoid, "copperDust", 64));
		modelList.add(tinDust = new TamItem(voidCraft.tabs.tabVoid, "tinDust", 64));
		modelList.add(leadDust = new TamItem(voidCraft.tabs.tabVoid, "leadDust", 64));
		modelList.add(lapisDust = new TamItem(voidCraft.tabs.tabVoid, "lapisDust", 64));
		modelList.add(emeraldDust = new TamItem(voidCraft.tabs.tabVoid, "emeraldDust", 64));
		modelList.add(diamondDust = new TamItem(voidCraft.tabs.tabVoid, "diamondDust", 64));

		modelList.add(debugger = new Debugger(voidCraft.tabs.tabVoid, "debugger", 1));
		// modelList.add(hookShot = new HookShot(voidCraft.tabs.tabVoid, "hookShot", 1));

		modelList.add(voidicSuppressor = new VoidicSuppressor(voidCraft.tabs.tabVoid, "voidicSuppressor", 1));
		modelList.add(voidicDrill = new VoidicDrill(voidCraft.tabs.tabVoid, "voidicDrill", 1));
		modelList.add(realityTeleporter = new RealityTeleporter(voidCraft.tabs.tabVoid, "realityTeleporter", 1));

		ArrayList<TamBlockFarmland> soilList = new ArrayList<TamBlockFarmland>();
		soilList.add(voidCraft.blocks.blockFakeBedrockFarmland);
		modelList.add(etherealSeed = new TamItemSeed(voidCraft.tabs.tabVoid, "etherealSeed", 64, voidCraft.blocks.etherealPlant, soilList));
		modelList.add(etherealFruit = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.NORMAL, voidCraft.tabs.tabVoid, "etherealFruit", 64, 2, false));
		modelList.add(etherealFruit_redstone = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.REDSTONE, voidCraft.tabs.tabVoid, "etherealFruit_redstone", 64, 2, false));
		modelList.add(etherealFruit_lapis = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.LAPIS, voidCraft.tabs.tabVoid, "etherealFruit_lapis", 64, 2, false));
		modelList.add(etherealFruit_gold = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.GOLD, voidCraft.tabs.tabVoid, "etherealFruit_gold", 64, 2, false));
		modelList.add(etherealFruit_emerald = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.EMERALD, voidCraft.tabs.tabVoid, "etherealFruit_emerald", 64, 2, false));
		modelList.add(etherealFruit_diamond = new EtherealFruit(TileEntityFakeBedrockFarmland.Alteration.DIAMOND, voidCraft.tabs.tabVoid, "etherealFruit_diamond", 64, 2, false));

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

	@Override
	public void init() {

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

		GameRegistry.addShapelessRecipe(new ItemStack(voidcrystal, 9), voidCraft.blocks.blockVoidcrystal);
		GameRegistry.addShapelessRecipe(new ItemStack(voidCrystalBucket), voidcrystal, Items.BUCKET);
		GameRegistry.addRecipe(new ItemStack(emptyObsidianFlask, 4), "OGO", " O ", 'O', Blocks.OBSIDIAN, 'G', Blocks.GLASS);
		GameRegistry.addShapelessRecipe(new ItemStack(voidicSuppressor), voidcrystal, Items.COMPASS, Items.REDSTONE, voidCloth);
		GameRegistry.addRecipe(new ItemStack(voidicDrill), "BHB", "CZC", "ESE", 'B', voidCraft.blocks.blockVoidcrystal, 'H', voidCraft.blocks.realityHole, 'C', voidCloth, 'Z', voidCraft.blocks.voidicCharger, 'E', ectoplasm, 'S', voidStar);
		GameRegistry.addRecipe(new ItemStack(MoltenvoidChain), "XYX", "YXY", "XYX", 'Y', MoltenvoidChainPart, 'X', burnBone);
		GameRegistry.addRecipe(new ItemStack(ChainedSkull), "XYX", "YZY", "XYX", 'X', MoltenvoidChain, 'Y', burnBone, 'Z', new ItemStack(Items.SKULL, 1, 1));
		GameRegistry.addRecipe(new ItemStack(Items.SKULL, 1, 1), "XX", "XX", 'X', burnBone);
		GameRegistry.addRecipe(new ItemStack(realityTeleporter), "BEZ", "CHC", "BCB", 'B', burnBone, 'E', emeraldDust, 'Z', voidCraft.blocks.voidicCharger, 'C', MoltenvoidChain, 'H', voidCraft.blocks.realityHole);

		GameRegistry.addSmelting(voidCraft.blocks.oreVoidcrystal, new ItemStack(voidcrystal), 0.1F);
		GameRegistry.addSmelting(voidChain, new ItemStack(MoltenvoidChainPart), 0.1F);
		GameRegistry.addSmelting(voidCrystalBucket, voidCraft.fluids.voidBucket.getBucket(), 0.1F);
		// dust
		GameRegistry.addSmelting(ironDust, new ItemStack(Items.IRON_INGOT), 0);
		GameRegistry.addSmelting(goldDust, new ItemStack(Items.GOLD_INGOT), 0);
		GameRegistry.addSmelting(diamondDust, new ItemStack(Items.DIAMOND), 0);
		this.addPreSmelting(copperDust, "ingotCopper");
		this.addPreSmelting(tinDust, "ingotTin");
		this.addPreSmelting(leadDust, "ingotLead");

		// Discs
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(0)), "XZZ", "ZYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(1)), "ZXZ", "ZYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(2)), "ZZX", "ZYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(3)), "ZZZ", "XYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(2)), "ZZZ", "XYZ",
		// "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(3)), "ZZZ", "ZYX",
		// "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(4)), "ZZZ", "ZYZ",
		// "XZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		// GameRegistry.addRecipe(new ItemStack(voidDiscs.get(5)), "ZZZ", "ZYZ",
		// "ZXZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
	}

	@Override
	public void postInit() {

	}

	private void addPreSmelting(Item i, String s) {
		for (ItemStack ore : OreDictionary.getOres(s)) {
			if (!ore.isEmpty()) {
				GameRegistry.addSmelting(i, ore, ore.getItemDamage());
			}
		}
	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return modelList;
	}

	@Override
	public String getModID() {
		return voidCraft.modid;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientPreInit() {
		VadeMecumMeshDefinition.preRegister();
		ModelLoader.setCustomMeshDefinition(vadeMecum, new VadeMecumMeshDefinition());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientInit() {
		// VadeMecumMeshDefinition.register();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientPostInit() {
		// TODO Auto-generated method stub

	}

}
