package Tamaized.Voidcraft.registry;

import Tamaized.TamModized.blocks.TamBlock;
import Tamaized.TamModized.blocks.TamBlockFence;
import Tamaized.TamModized.blocks.TamBlockStairs;
import Tamaized.TamModized.blocks.slab.TamBlockSlabDouble;
import Tamaized.TamModized.blocks.slab.TamBlockSlabHalf;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.TamModized.registry.RegistryHelper;
import Tamaized.TamModized.registry.TamColorRegistry;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.blocks.*;
import Tamaized.Voidcraft.blocks.spell.BlockSpellIceSpike;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityFakeBedrockFarmland;
import Tamaized.Voidcraft.machina.*;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class VoidCraftBlocks implements ITamRegistry {

	public static BlockVoidcrystal blockVoidcrystal;
	public static OreVoidcrystal oreVoidcrystal;
	public static BlockFakeBedrock blockFakeBedrock;
	public static BlockFakeBedrockFarmland blockFakeBedrockFarmland;
	public static TamBlockFence blockFakeBedrockFence;
	public static TamBlockStairs blockFakeBedrockStairs;
	public static TamBlockSlabDouble blockFakeBedrockDoubleSlab;
	public static TamBlockSlabHalf blockFakeBedrockHalfSlab;
	public static BlockNoBreak blockNoBreak;
	public static TamBlock blockVoidbrick;
	public static TamBlockFence blockVoidfence;
	public static TamBlockStairs blockVoidstairs;
	public static TamBlockSlabDouble blockVoidBrickDoubleSlab;
	public static TamBlockSlabHalf blockVoidBrickHalfSlab;
	public static BlockPortalVoid blockPortalVoid;
	public static BlockPortalXia blockPortalXia;
	public static FireVoid fireVoid;
	public static BlockRealityHole realityHole;
	public static TamBlock ritualBlock;
	public static VoidBox voidBox;
	public static VoidMacerator voidMacerator;
	public static TamBlock voidInfuserInert;
	public static VoidInfuser voidInfuser;
	public static Heimdall Heimdall;
	public static VoidicPowerGen voidicGen;
	public static VoidicPowerCable voidicCable;
	public static VoidicPowerCharger voidicCharger;
	public static RealityStabilizer realityStabilizer;
	public static VoidicAlchemyTable voidicAlchemyTable;
	public static RealityTeleporterBlock realityTeleporterBlock;
	public static VoidBlastFurnace voidBlastFurnace;
	public static VoidicAnchor voidicAnchor;
	public static VoidicCrystallizer voidicCrystallizer;
	public static BlockEtherealPlant etherealPlant;
	public static BlockSpellIceSpike iceSpike;
	public static AIBlock AIBlock;
	public static TamBlock cosmicMaterial;
	public static TamBlock starforge;
	public static StarForgeBlock starforgeStation;
	public static BlockDreamBed dreamBed;
	public static TamBlock creationforge;
	public static TamBlock creationforge_ray;
	private static ArrayList<ITamModel> modelList;

	@Override
	public void preInit() {
		modelList = new ArrayList<ITamModel>();

		modelList.add(blockVoidcrystal = new BlockVoidcrystal(VoidCraft.tabs.tabVoid, Material.GLASS, "blockvoidcrystal", 7.0F));
		modelList.add(oreVoidcrystal = new OreVoidcrystal(VoidCraft.tabs.tabVoid, Material.ROCK, "orevoidcrystal", 3.0F));
		modelList.add(blockFakeBedrock = new BlockFakeBedrock(VoidCraft.tabs.tabVoid, Blocks.BEDROCK.getMaterial(Blocks.BEDROCK.getDefaultState()), "blockfakebedrock", 30.0F));
		modelList.add(blockFakeBedrockFarmland = new BlockFakeBedrockFarmland(VoidCraft.tabs.tabVoid, Blocks.BEDROCK.getMaterial(Blocks.BEDROCK.getDefaultState()), "blockfakebedrockfarmland", 30.0F));
		modelList.add(blockFakeBedrockFence = new TamBlockFence(VoidCraft.tabs.tabVoid, Material.ROCK, MapColor.OBSIDIAN, "blockfakebedrockfence", SoundType.STONE));
		modelList.add(blockFakeBedrockStairs = new TamBlockStairs(VoidCraft.tabs.tabVoid, blockFakeBedrock.getDefaultState(), "blockfakebedrockstairs", SoundType.STONE));
		modelList.add(blockFakeBedrockDoubleSlab = new TamBlockSlabDouble(VoidCraft.tabs.tabVoid, Material.ROCK, "blockfakebedrockdoubleslab", Item.getItemFromBlock(blockFakeBedrockHalfSlab)));
		modelList.add(blockFakeBedrockHalfSlab = new TamBlockSlabHalf(VoidCraft.tabs.tabVoid, Material.ROCK, "blockfakebedrockhalfslab", Item.getItemFromBlock(blockFakeBedrockHalfSlab)));
		modelList.add(blockNoBreak = new BlockNoBreak(VoidCraft.tabs.tabVoid, Material.ROCK, "blocknobreak", -1F));
		modelList.add(blockVoidbrick = new TamBlock(VoidCraft.tabs.tabVoid, Material.ROCK, "blockvoidbrick", 30.0F, SoundType.STONE));
		modelList.add(blockVoidfence = new TamBlockFence(VoidCraft.tabs.tabVoid, Material.ROCK, MapColor.OBSIDIAN, "blockvoidfence", SoundType.STONE));
		modelList.add(blockVoidstairs = new TamBlockStairs(VoidCraft.tabs.tabVoid, blockVoidbrick.getDefaultState(), "blockvoidstairs", SoundType.STONE));
		modelList.add(blockVoidBrickDoubleSlab = new TamBlockSlabDouble(VoidCraft.tabs.tabVoid, Material.ROCK, "blockvoidbrickdoubleslab", Item.getItemFromBlock(blockVoidBrickHalfSlab)));
		modelList.add(blockVoidBrickHalfSlab = new TamBlockSlabHalf(VoidCraft.tabs.tabVoid, Material.ROCK, "blockvoidbrickhalfslab", Item.getItemFromBlock(blockVoidBrickHalfSlab)));
		modelList.add(AIBlock = new AIBlock("aiblock"));
		modelList.add(realityHole = new BlockRealityHole(VoidCraft.tabs.tabVoid, Material.CLOTH, "blockrealityhole", -1F));
		modelList.add(ritualBlock = new TamBlock(VoidCraft.tabs.tabVoid, Material.IRON, "ritualblock", 3.0F, SoundType.STONE));
		modelList.add(cosmicMaterial = new TamBlock(VoidCraft.tabs.tabVoid, Material.ROCK, "cosmicmaterial", 30.0F, SoundType.STONE));
		modelList.add(starforge = new TamBlock(VoidCraft.tabs.tabVoid, Material.IRON, "starforge", -1.0F, SoundType.METAL));
		modelList.add(starforgeStation = new StarForgeBlock(VoidCraft.tabs.tabVoid, Material.IRON, "starforgestation", -1.0F));

		modelList.add(blockPortalVoid = new BlockPortalVoid(VoidCraft.tabs.tabVoid, "blockportalvoid"));
		modelList.add(blockPortalXia = new BlockPortalXia(VoidCraft.tabs.tabVoid, "blockportalxia"));
		modelList.add(fireVoid = new FireVoid(VoidCraft.tabs.tabVoid, "firevoid"));

		modelList.add(voidMacerator = new VoidMacerator(VoidCraft.tabs.tabVoid, Material.ROCK, "voidmacerator", 3.5F));
		modelList.add(voidBlastFurnace = new VoidBlastFurnace(VoidCraft.tabs.tabVoid, Material.IRON, "voidblastfurnace", 7.0F));
		modelList.add(Heimdall = new Heimdall(VoidCraft.tabs.tabVoid, Material.IRON, "heimdall", 3.5F));
		modelList.add(voidBox = new VoidBox(VoidCraft.tabs.tabVoid, Material.IRON, "voidbox", 7.0F));
		modelList.add(voidInfuserInert = new TamBlock(VoidCraft.tabs.tabVoid, Material.IRON, "voidinfuserinert", 7.0F, SoundType.METAL));
		modelList.add(voidInfuser = new VoidInfuser(VoidCraft.tabs.tabVoid, Material.IRON, "voidinfuser", 7.0F));
		modelList.add(voidicGen = new VoidicPowerGen(VoidCraft.tabs.tabVoid, Material.IRON, "voidicgen", 3.5f));
		modelList.add(voidicCable = new VoidicPowerCable(VoidCraft.tabs.tabVoid, Material.CIRCUITS, "voidiccable", 1.5f));
		modelList.add(voidicCharger = new VoidicPowerCharger(VoidCraft.tabs.tabVoid, Material.IRON, "voidiccharger", 3.5f));
		modelList.add(realityStabilizer = new RealityStabilizer(VoidCraft.tabs.tabVoid, Material.IRON, "realitystabilizer", 3.5f));
		modelList.add(voidicAlchemyTable = new VoidicAlchemyTable(VoidCraft.tabs.tabVoid, Material.IRON, "voidicalchemytable", 3.5f));
		modelList.add(realityTeleporterBlock = new RealityTeleporterBlock(VoidCraft.tabs.tabVoid, Material.IRON, "realityteleporterblock", 3.5f));
		modelList.add(voidicAnchor = new VoidicAnchor(VoidCraft.tabs.tabVoid, Material.IRON, "voidicanchor", 3.5f));
		modelList.add(voidicCrystallizer = new VoidicCrystallizer(VoidCraft.tabs.tabVoid, Material.IRON, "voidiccrystallizer", 3.5f));

		modelList.add(etherealPlant = new BlockEtherealPlant(VoidCraft.tabs.tabVoid, "etherealplant", 1.0f));

		modelList.add(iceSpike = new BlockSpellIceSpike(VoidCraft.tabs.tabVoid, Material.ICE, "blockspellicespike", -1F));

		modelList.add(dreamBed = new BlockDreamBed(VoidCraft.tabs.tabVoid, "dreambed"));
		modelList.add(creationforge = new TamBlock(VoidCraft.tabs.tabVoid, Material.IRON, "creationforge", -1.0F, SoundType.METAL));
		modelList.add(creationforge_ray = new TamBlock(VoidCraft.tabs.tabVoid, Material.IRON, "creationforge_ray", -1.0F, SoundType.METAL));

		// Slabs have to be registered outside of their class
		RegistryHelper.registerBlockSlab(blockVoidBrickHalfSlab, blockVoidBrickDoubleSlab);
		RegistryHelper.registerBlockSlab(blockFakeBedrockHalfSlab, blockFakeBedrockDoubleSlab);

	}

	@Override
	public void init() {
		VoidCraft.addShapedRecipe(new ItemStack(blockVoidcrystal), 3, 3,

				VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal,

				VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal,

				VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal

		);
		VoidCraft.addShapedRecipe(new ItemStack(ritualBlock, 8), 3, 3,

				Blocks.STONEBRICK, Blocks.STONEBRICK, Blocks.STONEBRICK,

				Blocks.STONEBRICK, VoidCraft.items.voidcrystal, Blocks.STONEBRICK,

				Blocks.STONEBRICK, Blocks.STONEBRICK, Blocks.STONEBRICK

		);
		VoidCraft.addShapedRecipe(new ItemStack(voidInfuserInert), 3, 3,

				blockVoidbrick, VoidCraft.items.voidCloth, blockVoidbrick,

				VoidCraft.items.voidCloth, Items.CAULDRON, VoidCraft.items.voidCloth,

				blockVoidbrick, VoidCraft.items.voidCloth, blockVoidbrick

		);
		VoidCraft.addShapelessRecipe(new ItemStack(voidInfuser), voidInfuserInert, VoidCraft.items.voidStar);
		VoidCraft.addShapedRecipe(new ItemStack(voidBlastFurnace), 3, 3,

				VoidCraft.items.voidCloth, blockVoidbrick, VoidCraft.items.voidCloth,

				blockVoidbrick, VoidCraft.items.MoltenvoidChainPart, blockVoidbrick,

				VoidCraft.items.voidCloth, blockVoidbrick, VoidCraft.items.voidCloth

		);
		VoidCraft.addShapedRecipe(new ItemStack(voidBox), 3, 3,

				VoidCraft.items.voidCloth, VoidCraft.items.voidCloth, VoidCraft.items.voidCloth,

				VoidCraft.items.voidCloth, Blocks.JUKEBOX, VoidCraft.items.voidCloth,

				VoidCraft.items.voidCloth, VoidCraft.items.voidStar, VoidCraft.items.voidCloth

		);
		VoidCraft.addShapedRecipe(new ItemStack(blockVoidbrick), 2, 2,

				blockVoidcrystal, blockVoidcrystal,

				blockVoidcrystal, blockVoidcrystal

		);
		VoidCraft.addShapedRecipe(new ItemStack(blockVoidstairs, 6), 3, 3,

				blockVoidbrick, ItemStack.EMPTY, ItemStack.EMPTY,

				blockVoidbrick, blockVoidbrick, ItemStack.EMPTY,

				blockVoidbrick, blockVoidbrick, blockVoidbrick

		);
		VoidCraft.addShapedRecipe(new ItemStack(blockVoidBrickHalfSlab, 6), 3, 1,

				blockVoidbrick, blockVoidbrick, blockVoidbrick

		);
		VoidCraft.addShapedRecipe(new ItemStack(blockVoidfence, 6), 3, 2,

				blockVoidbrick, blockVoidbrick, blockVoidbrick,

				blockVoidbrick, blockVoidbrick, blockVoidbrick

		);
		VoidCraft.addShapedRecipe(new ItemStack(blockFakeBedrockStairs, 6), 3, 3,

				blockFakeBedrock, ItemStack.EMPTY, ItemStack.EMPTY,

				blockFakeBedrock, blockFakeBedrock, ItemStack.EMPTY,

				blockFakeBedrock, blockFakeBedrock, blockFakeBedrock

		);
		VoidCraft.addShapedRecipe(new ItemStack(blockFakeBedrockHalfSlab, 6), 3, 1,

				blockFakeBedrock, blockFakeBedrock, blockFakeBedrock

		);
		VoidCraft.addShapedRecipe(new ItemStack(blockFakeBedrockFence, 6), 3, 2,

				blockFakeBedrock, blockFakeBedrock, blockFakeBedrock,

				blockFakeBedrock, blockFakeBedrock, blockFakeBedrock

		);
		VoidCraft.addShapedRecipe(new ItemStack(voidicCable, 8), 3, 3,

				Items.REDSTONE, Items.REDSTONE, Items.REDSTONE,

				Items.REDSTONE, blockVoidcrystal, Items.REDSTONE,

				Items.REDSTONE, Items.REDSTONE, Items.REDSTONE

		);
		VoidCraft.addShapedRecipe(new ItemStack(voidicGen), 3, 3,

				Items.REDSTONE, Items.REDSTONE, Items.REDSTONE,

				Items.REDSTONE, voidInfuser, Items.REDSTONE,

				Items.REDSTONE, Items.REDSTONE, Items.REDSTONE

		);
		VoidCraft.addShapedRecipe(new ItemStack(voidicCharger), 3, 3,

				VoidCraft.items.diamondDust, VoidCraft.items.voidStar, VoidCraft.items.diamondDust,

				VoidCraft.items.voidCloth, Items.END_CRYSTAL, VoidCraft.items.voidCloth,

				VoidCraft.items.diamondDust, voidicCable, VoidCraft.items.diamondDust

		);
		VoidCraft.addShapedRecipe(new ItemStack(voidicAnchor), 3, 3,

				VoidCraft.items.diamondDust, VoidCraft.items.voidStar, VoidCraft.items.diamondDust,

				VoidCraft.items.voidCloth, Items.END_CRYSTAL, VoidCraft.items.voidCloth,

				VoidCraft.items.voidicSteel, voidicCharger, VoidCraft.items.voidicSteel

		);
		VoidCraft.addShapedRecipe(new ItemStack(realityStabilizer), 3, 3,

				VoidCraft.items.voidicSteel, VoidCraft.items.diamondDust, VoidCraft.items.voidicSteel,

				VoidCraft.items.diamondDust, VoidCraft.items.voidStar, VoidCraft.items.diamondDust,

				VoidCraft.items.voidicSteel, voidicCharger, VoidCraft.items.voidicSteel

		);
		VoidCraft.addShapedRecipe(new ItemStack(voidicAlchemyTable), 3, 3,

				blockVoidbrick, VoidCraft.items.voidicSteel, blockVoidbrick,

				VoidCraft.items.voidicSteel, Items.BREWING_STAND, VoidCraft.items.voidicSteel,

				blockVoidbrick, voidInfuserInert, blockVoidbrick

		);
		VoidCraft.addShapedRecipe(new ItemStack(realityTeleporterBlock), 3, 3,

				VoidCraft.items.voidicSteel, VoidCraft.items.emeraldDust, VoidCraft.items.voidicSteel,

				VoidCraft.items.voidCloth, realityHole, VoidCraft.items.voidCloth,

				VoidCraft.items.voidicSteel, voidicCharger, VoidCraft.items.voidicSteel

		);
		VoidCraft.addShapedRecipe(new ItemStack(voidicCrystallizer), 3, 3,

				VoidCraft.items.voidicSteel, VoidCraft.items.voidChain, VoidCraft.items.voidicSteel,

				VoidCraft.items.voidicSteel, VoidCraft.items.ectoplasm, VoidCraft.items.voidicSteel,

				VoidCraft.items.voidicSteel, VoidCraft.items.voidChain, VoidCraft.items.voidicSteel

		);
	}

	@Override
	public void postInit() {

	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return modelList;
	}

	@Override
	public String getModID() {
		return VoidCraft.modid;
	}

	@Override
	public void clientPreInit() {

	}

	@Override
	public void clientInit() {

		final net.minecraft.client.renderer.color.IBlockColor fakeBedrockFarmlandColorHandler = (state, blockAccess, pos, tintIndex) -> {
			if (blockAccess != null && pos != null && blockAccess.getTileEntity(pos) != null) {
				return TileEntityFakeBedrockFarmland.getColor(((TileEntityFakeBedrockFarmland) blockAccess.getTileEntity(pos)).getAlteration());
			}
			return 0xFFFFFF;
		};

		final net.minecraft.client.renderer.color.IBlockColor etherealPlantColorHandler = (state, blockAccess, pos, tintIndex) -> {
			if (blockAccess != null && pos != null && blockAccess.getTileEntity(pos.down()) != null) {
				return TileEntityFakeBedrockFarmland.getColor(((TileEntityFakeBedrockFarmland) blockAccess.getTileEntity(pos.down())).getAlteration());
			}
			return 0xFFFFFF;
		};

		TamColorRegistry.registerBlockColors(blockFakeBedrockFarmland, fakeBedrockFarmlandColorHandler);
		TamColorRegistry.registerBlockColors(etherealPlant, etherealPlantColorHandler);
	}

	@Override
	public void clientPostInit() {

	}

}
