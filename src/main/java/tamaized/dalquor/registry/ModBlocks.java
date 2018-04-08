package tamaized.dalquor.registry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
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
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.blocks.AIBlock;
import tamaized.dalquor.common.blocks.BlockDreamBed;
import tamaized.dalquor.common.blocks.BlockNoBreak;
import tamaized.dalquor.common.blocks.BlockPortalVoid;
import tamaized.dalquor.common.blocks.BlockPortalXia;
import tamaized.dalquor.common.blocks.BlockRealityHole;
import tamaized.dalquor.common.blocks.BlockVoidFire;
import tamaized.dalquor.common.blocks.BlockVoidcrystal;
import tamaized.dalquor.common.blocks.OreVoidcrystal;
import tamaized.dalquor.common.blocks.StarForgeBlock;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = DalQuor.modid)
public class ModBlocks {

	public static BlockVoidcrystal blockVoidcrystal;
	public static OreVoidcrystal oreVoidcrystal;
	public static BlockNoBreak blockNoBreak;
	public static TamBlock blockVoidbrick;
	public static TamBlockFence blockVoidfence;
	public static TamBlockStairs blockVoidstairs;
	public static TamBlockSlabDouble blockVoidBrickDoubleSlab;
	public static TamBlockSlabHalf blockVoidBrickHalfSlab;
	public static BlockPortalVoid blockPortalVoid;
	public static BlockPortalXia blockPortalXia;
	public static BlockVoidFire blockVoidFire;
	public static BlockRealityHole realityHole;
	public static TamBlock ritualBlock;
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

		modelList.add(blockVoidcrystal = new BlockVoidcrystal(ModCreativeTabs.tabVoid, Material.GLASS, "blockvoidcrystal", 7.0F));
		modelList.add(oreVoidcrystal = new OreVoidcrystal(ModCreativeTabs.tabVoid, Material.ROCK, "orevoidcrystal", 3.0F));
		modelList.add(blockNoBreak = new BlockNoBreak(ModCreativeTabs.tabVoid, Material.ROCK, "blocknobreak", -1F));
		modelList.add(blockVoidbrick = new TamBlock(ModCreativeTabs.tabVoid, Material.ROCK, "blockvoidbrick", 30.0F, SoundType.STONE));
		modelList.add(blockVoidfence = new TamBlockFence(ModCreativeTabs.tabVoid, Material.ROCK, MapColor.OBSIDIAN, "blockvoidfence", SoundType.STONE));
		modelList.add(blockVoidstairs = new TamBlockStairs(ModCreativeTabs.tabVoid, blockVoidbrick.getDefaultState(), "blockvoidstairs", SoundType.STONE));
		modelList.add(blockVoidBrickDoubleSlab = new TamBlockSlabDouble(ModCreativeTabs.tabVoid, Material.ROCK, "blockvoidbrickdoubleslab", Item.getItemFromBlock(blockVoidBrickHalfSlab)));
		modelList.add(blockVoidBrickHalfSlab = new TamBlockSlabHalf(ModCreativeTabs.tabVoid, Material.ROCK, "blockvoidbrickhalfslab", Item.getItemFromBlock(blockVoidBrickHalfSlab)));
		modelList.add(AIBlock = new AIBlock("aiblock"));
		modelList.add(realityHole = new BlockRealityHole(ModCreativeTabs.tabVoid, Material.CLOTH, "blockrealityhole", -1F));
		modelList.add(ritualBlock = new TamBlock(ModCreativeTabs.tabVoid, Material.IRON, "ritualblock", 3.0F, SoundType.STONE));
		modelList.add(cosmicMaterial = new TamBlock(ModCreativeTabs.tabVoid, Material.ROCK, "cosmicmaterial", 30.0F, SoundType.STONE));
		modelList.add(starforge = new TamBlock(ModCreativeTabs.tabVoid, Material.IRON, "starforge", -1.0F, SoundType.METAL));
		modelList.add(starforgeStation = new StarForgeBlock(ModCreativeTabs.tabVoid, Material.IRON, "starforgestation", -1.0F));

		modelList.add(blockPortalVoid = new BlockPortalVoid(ModCreativeTabs.tabVoid, "blockportalvoid"));
		modelList.add(blockPortalXia = new BlockPortalXia(ModCreativeTabs.tabVoid, "blockportalxia"));
		modelList.add(blockVoidFire = new BlockVoidFire(ModCreativeTabs.tabVoid, "firevoid"));

		modelList.add(dreamBed = new BlockDreamBed(ModCreativeTabs.tabVoid, "dreambed"));
		modelList.add(creationforge = new TamBlock(ModCreativeTabs.tabVoid, Material.IRON, "creationforge", -1.0F, SoundType.METAL));
		modelList.add(creationforge_ray = new TamBlock(ModCreativeTabs.tabVoid, Material.IRON, "creationforge_ray", -1.0F, SoundType.METAL));

	}

	@SideOnly(Side.CLIENT)
	public static void clientInit() {
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
		event.getRegistry().register(new ItemSlab(blockVoidBrickHalfSlab, blockVoidBrickHalfSlab, blockVoidBrickDoubleSlab).setRegistryName(blockVoidBrickHalfSlab.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for (ITamRegistry model : modelList)
			model.registerModel(event);
	}

}
