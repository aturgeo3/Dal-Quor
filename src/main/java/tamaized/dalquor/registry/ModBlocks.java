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
import tamaized.dalquor.common.blocks.BlockEthericPlatform;
import tamaized.dalquor.common.blocks.OreVoidcrystal;
import tamaized.dalquor.common.blocks.StarForgeBlock;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = DalQuor.modid)
public class ModBlocks {

	public static BlockEthericPlatform ethericPlatform;
	public static OreVoidcrystal oreVoidcrystal;
	public static BlockNoBreak noBreak;
	public static TamBlock voidbrick;
	public static TamBlockFence voidfence;
	public static TamBlockStairs voidstairs;
	public static TamBlockSlabDouble voidBrickDoubleSlab;
	public static TamBlockSlabHalf voidBrickHalfSlab;
	public static BlockPortalVoid portalVoid;
	public static BlockPortalXia portalXia;
	public static BlockVoidFire voidFire;
	public static BlockRealityHole realityHole;
	public static TamBlock ritualBlock;
	public static AIBlock aiBlock;
	public static TamBlock cosmicMaterial;
	public static TamBlock starforge;
	public static StarForgeBlock starforgeStation;
	public static BlockDreamBed dreamBed;
	public static TamBlock creationforge;
	public static TamBlock creationforge_ray;
	private static List<ITamRegistry> modelList;

	static {
		modelList = new ArrayList<>();

		modelList.add(ethericPlatform = new BlockEthericPlatform(ModCreativeTabs.tabVoid, Material.GLASS, "ethericplatform", 7.0F));
		modelList.add(oreVoidcrystal = new OreVoidcrystal(ModCreativeTabs.tabVoid, Material.ROCK, "orevoidcrystal", 3.0F));
		modelList.add(noBreak = new BlockNoBreak(ModCreativeTabs.tabVoid, Material.ROCK, "nobreak", -1F));
		modelList.add(voidbrick = new TamBlock(ModCreativeTabs.tabVoid, Material.ROCK, "voidbrick", 30.0F, SoundType.STONE));
		modelList.add(voidfence = new TamBlockFence(ModCreativeTabs.tabVoid, Material.ROCK, MapColor.OBSIDIAN, "voidfence", SoundType.STONE));
		modelList.add(voidstairs = new TamBlockStairs(ModCreativeTabs.tabVoid, voidbrick.getDefaultState(), "voidstairs", SoundType.STONE));
		modelList.add(voidBrickDoubleSlab = new TamBlockSlabDouble(ModCreativeTabs.tabVoid, Material.ROCK, "voidbrickdoubleslab", Item.getItemFromBlock(voidBrickHalfSlab)));
		modelList.add(voidBrickHalfSlab = new TamBlockSlabHalf(ModCreativeTabs.tabVoid, Material.ROCK, "voidbrickhalfslab", Item.getItemFromBlock(voidBrickHalfSlab)));
		modelList.add(aiBlock = new AIBlock("aiblock"));
		modelList.add(realityHole = new BlockRealityHole(ModCreativeTabs.tabVoid, Material.CLOTH, "realityhole", -1F));
		modelList.add(ritualBlock = new TamBlock(ModCreativeTabs.tabVoid, Material.IRON, "ritual", 3.0F, SoundType.STONE));
		modelList.add(cosmicMaterial = new TamBlock(ModCreativeTabs.tabVoid, Material.ROCK, "cosmicmaterial", 30.0F, SoundType.STONE));
		modelList.add(starforge = new TamBlock(ModCreativeTabs.tabVoid, Material.IRON, "starforge", -1.0F, SoundType.METAL));
		modelList.add(starforgeStation = new StarForgeBlock(ModCreativeTabs.tabVoid, Material.IRON, "starforgestation", -1.0F));

		modelList.add(portalVoid = new BlockPortalVoid(ModCreativeTabs.tabVoid, "portalvoid"));
		modelList.add(portalXia = new BlockPortalXia(ModCreativeTabs.tabVoid, "portalxia"));
		modelList.add(voidFire = new BlockVoidFire(ModCreativeTabs.tabVoid, "firevoid"));

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
	public static void registerItems(RegistryEvent.Register<Item> event) { // TODO: move over to forge blockstates
		for (ITamRegistry b : modelList)
			b.registerItem(event);
		event.getRegistry().register(new ItemSlab(voidBrickHalfSlab, voidBrickHalfSlab, voidBrickDoubleSlab).setRegistryName(voidBrickHalfSlab.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for (ITamRegistry model : modelList)
			model.registerModel(event);
	}

}
