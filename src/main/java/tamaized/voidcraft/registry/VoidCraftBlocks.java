package tamaized.voidcraft.registry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.tammodized.common.blocks.TamBlock;
import tamaized.tammodized.common.blocks.TamBlockFence;
import tamaized.tammodized.common.blocks.TamBlockStairs;
import tamaized.tammodized.common.blocks.slab.TamBlockSlabDouble;
import tamaized.tammodized.common.blocks.slab.TamBlockSlabHalf;
import tamaized.tammodized.registry.ITamRegistry;
import tamaized.tammodized.registry.TamColorRegistry;
import tamaized.voidcraft.common.blocks.*;
import tamaized.voidcraft.common.blocks.spell.BlockSpellIceSpike;
import tamaized.voidcraft.common.blocks.tileentity.TileEntityFakeBedrockFarmland;
import tamaized.voidcraft.common.machina.*;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class VoidCraftBlocks {

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
	private static List<ITamRegistry> modelList;

	static {
		modelList = new ArrayList<>();

		modelList.add(blockVoidcrystal = new BlockVoidcrystal(VoidCraftCreativeTabs.tabVoid, Material.GLASS, "blockvoidcrystal", 7.0F));
		modelList.add(oreVoidcrystal = new OreVoidcrystal(VoidCraftCreativeTabs.tabVoid, Material.ROCK, "orevoidcrystal", 3.0F));
		modelList.add(blockFakeBedrock = new BlockFakeBedrock(VoidCraftCreativeTabs.tabVoid, Blocks.BEDROCK.getMaterial(Blocks.BEDROCK.getDefaultState()), "blockfakebedrock", 30.0F));
		modelList.add(blockFakeBedrockFarmland = new BlockFakeBedrockFarmland(VoidCraftCreativeTabs.tabVoid, Blocks.BEDROCK.getMaterial(Blocks.BEDROCK.getDefaultState()), "blockfakebedrockfarmland", 30.0F));
		modelList.add(blockFakeBedrockFence = new TamBlockFence(VoidCraftCreativeTabs.tabVoid, Material.ROCK, MapColor.OBSIDIAN, "blockfakebedrockfence", SoundType.STONE));
		modelList.add(blockFakeBedrockStairs = new TamBlockStairs(VoidCraftCreativeTabs.tabVoid, blockFakeBedrock.getDefaultState(), "blockfakebedrockstairs", SoundType.STONE));
		modelList.add(blockFakeBedrockDoubleSlab = new TamBlockSlabDouble(VoidCraftCreativeTabs.tabVoid, Material.ROCK, "blockfakebedrockdoubleslab", Item.getItemFromBlock(blockFakeBedrockHalfSlab)));
		modelList.add(blockFakeBedrockHalfSlab = new TamBlockSlabHalf(VoidCraftCreativeTabs.tabVoid, Material.ROCK, "blockfakebedrockhalfslab", Item.getItemFromBlock(blockFakeBedrockHalfSlab)));
		modelList.add(blockNoBreak = new BlockNoBreak(VoidCraftCreativeTabs.tabVoid, Material.ROCK, "blocknobreak", -1F));
		modelList.add(blockVoidbrick = new TamBlock(VoidCraftCreativeTabs.tabVoid, Material.ROCK, "blockvoidbrick", 30.0F, SoundType.STONE));
		modelList.add(blockVoidfence = new TamBlockFence(VoidCraftCreativeTabs.tabVoid, Material.ROCK, MapColor.OBSIDIAN, "blockvoidfence", SoundType.STONE));
		modelList.add(blockVoidstairs = new TamBlockStairs(VoidCraftCreativeTabs.tabVoid, blockVoidbrick.getDefaultState(), "blockvoidstairs", SoundType.STONE));
		modelList.add(blockVoidBrickDoubleSlab = new TamBlockSlabDouble(VoidCraftCreativeTabs.tabVoid, Material.ROCK, "blockvoidbrickdoubleslab", Item.getItemFromBlock(blockVoidBrickHalfSlab)));
		modelList.add(blockVoidBrickHalfSlab = new TamBlockSlabHalf(VoidCraftCreativeTabs.tabVoid, Material.ROCK, "blockvoidbrickhalfslab", Item.getItemFromBlock(blockVoidBrickHalfSlab)));
		modelList.add(AIBlock = new AIBlock("aiblock"));
		modelList.add(realityHole = new BlockRealityHole(VoidCraftCreativeTabs.tabVoid, Material.CLOTH, "blockrealityhole", -1F));
		modelList.add(ritualBlock = new TamBlock(VoidCraftCreativeTabs.tabVoid, Material.IRON, "ritualblock", 3.0F, SoundType.STONE));
		modelList.add(cosmicMaterial = new TamBlock(VoidCraftCreativeTabs.tabVoid, Material.ROCK, "cosmicmaterial", 30.0F, SoundType.STONE));
		modelList.add(starforge = new TamBlock(VoidCraftCreativeTabs.tabVoid, Material.IRON, "starforge", -1.0F, SoundType.METAL));
		modelList.add(starforgeStation = new StarForgeBlock(VoidCraftCreativeTabs.tabVoid, Material.IRON, "starforgestation", -1.0F));

		modelList.add(blockPortalVoid = new BlockPortalVoid(VoidCraftCreativeTabs.tabVoid, "blockportalvoid"));
		modelList.add(blockPortalXia = new BlockPortalXia(VoidCraftCreativeTabs.tabVoid, "blockportalxia"));
		modelList.add(fireVoid = new FireVoid(VoidCraftCreativeTabs.tabVoid, "firevoid"));

		modelList.add(voidMacerator = new VoidMacerator(VoidCraftCreativeTabs.tabVoid, Material.ROCK, "voidmacerator", 3.5F));
		modelList.add(voidBlastFurnace = new VoidBlastFurnace(VoidCraftCreativeTabs.tabVoid, Material.IRON, "voidblastfurnace", 7.0F));
		modelList.add(Heimdall = new Heimdall(VoidCraftCreativeTabs.tabVoid, Material.IRON, "heimdall", 3.5F));
		modelList.add(voidBox = new VoidBox(VoidCraftCreativeTabs.tabVoid, Material.IRON, "voidbox", 7.0F));
		modelList.add(voidInfuserInert = new TamBlock(VoidCraftCreativeTabs.tabVoid, Material.IRON, "voidinfuserinert", 7.0F, SoundType.METAL));
		modelList.add(voidInfuser = new VoidInfuser(VoidCraftCreativeTabs.tabVoid, Material.IRON, "voidinfuser", 7.0F));
		modelList.add(voidicGen = new VoidicPowerGen(VoidCraftCreativeTabs.tabVoid, Material.IRON, "voidicgen", 3.5f));
		modelList.add(voidicCable = new VoidicPowerCable(VoidCraftCreativeTabs.tabVoid, Material.CIRCUITS, "voidiccable", 1.5f));
		modelList.add(voidicCharger = new VoidicPowerCharger(VoidCraftCreativeTabs.tabVoid, Material.IRON, "voidiccharger", 3.5f));
		modelList.add(realityStabilizer = new RealityStabilizer(VoidCraftCreativeTabs.tabVoid, Material.IRON, "realitystabilizer", 3.5f));
		modelList.add(voidicAlchemyTable = new VoidicAlchemyTable(VoidCraftCreativeTabs.tabVoid, Material.IRON, "voidicalchemytable", 3.5f));
		modelList.add(realityTeleporterBlock = new RealityTeleporterBlock(VoidCraftCreativeTabs.tabVoid, Material.IRON, "realityteleporterblock", 3.5f));
		modelList.add(voidicAnchor = new VoidicAnchor(VoidCraftCreativeTabs.tabVoid, Material.IRON, "voidicanchor", 3.5f));
		modelList.add(voidicCrystallizer = new VoidicCrystallizer(VoidCraftCreativeTabs.tabVoid, Material.IRON, "voidiccrystallizer", 3.5f));

		modelList.add(etherealPlant = new BlockEtherealPlant(VoidCraftCreativeTabs.tabVoid, "etherealplant", 1.0f));

		modelList.add(iceSpike = new BlockSpellIceSpike(VoidCraftCreativeTabs.tabVoid, Material.ICE, "blockspellicespike", -1F));

		modelList.add(dreamBed = new BlockDreamBed(VoidCraftCreativeTabs.tabVoid, "dreambed"));
		modelList.add(creationforge = new TamBlock(VoidCraftCreativeTabs.tabVoid, Material.IRON, "creationforge", -1.0F, SoundType.METAL));
		modelList.add(creationforge_ray = new TamBlock(VoidCraftCreativeTabs.tabVoid, Material.IRON, "creationforge_ray", -1.0F, SoundType.METAL));

	}

	@SideOnly(Side.CLIENT)
	public static void clientInit() {
		TamColorRegistry.registerBlockColors(blockFakeBedrockFarmland, (state, blockAccess, pos, tintIndex) -> {
			if (blockAccess != null && pos != null && blockAccess.getTileEntity(pos) != null) {
				return TileEntityFakeBedrockFarmland.getColor(((TileEntityFakeBedrockFarmland) blockAccess.getTileEntity(pos)).getAlteration());
			}
			return 0xFFFFFF;
		});
		TamColorRegistry.registerBlockColors(etherealPlant, (state, blockAccess, pos, tintIndex) -> {
			if (blockAccess != null && pos != null && blockAccess.getTileEntity(pos.down()) != null) {
				return TileEntityFakeBedrockFarmland.getColor(((TileEntityFakeBedrockFarmland) blockAccess.getTileEntity(pos.down())).getAlteration());
			}
			return 0xFFFFFF;
		});
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for (ITamRegistry b : modelList)
			b.registerBlock(event);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (ITamRegistry b : modelList)
			b.registerItem(event);
		event.getRegistry().register(new ItemSlab(blockFakeBedrockHalfSlab, blockFakeBedrockHalfSlab, blockFakeBedrockDoubleSlab).setRegistryName(blockFakeBedrockHalfSlab.getRegistryName()));
		event.getRegistry().register(new ItemSlab(blockVoidBrickHalfSlab, blockVoidBrickHalfSlab, blockVoidBrickDoubleSlab).setRegistryName(blockVoidBrickHalfSlab.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for (ITamRegistry model : modelList)
			model.registerModel(event);
	}

}
