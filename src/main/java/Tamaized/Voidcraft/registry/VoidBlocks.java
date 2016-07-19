package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.TamModized.blocks.TamBlock;
import Tamaized.TamModized.blocks.TamBlockFence;
import Tamaized.TamModized.blocks.TamBlockStairs;
import Tamaized.TamModized.blocks.slab.TamBlockSlab;
import Tamaized.TamModized.blocks.slab.TamBlockSlabDouble;
import Tamaized.TamModized.blocks.slab.TamBlockSlabHalf;
import Tamaized.TamModized.blocks.slab.TamItemBlockSlab;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.blocks.BlockFakeBedrock;
import Tamaized.Voidcraft.blocks.BlockNoBreak;
import Tamaized.Voidcraft.blocks.BlockPortalVoid;
import Tamaized.Voidcraft.blocks.BlockPortalXia;
import Tamaized.Voidcraft.blocks.BlockRealityHole;
import Tamaized.Voidcraft.blocks.BlockVoidcrystal;
import Tamaized.Voidcraft.blocks.FireVoid;
import Tamaized.Voidcraft.blocks.OreVoidcrystal;
import Tamaized.Voidcraft.blocks.XiaBlock;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.Heimdall;
import Tamaized.Voidcraft.machina.RealityStabilizer;
import Tamaized.Voidcraft.machina.VoidBox;
import Tamaized.Voidcraft.machina.VoidInfuser;
import Tamaized.Voidcraft.machina.VoidMacerator;
import Tamaized.Voidcraft.machina.VoidicPowerCable;
import Tamaized.Voidcraft.machina.VoidicPowerCharger;
import Tamaized.Voidcraft.machina.VoidicPowerGen;

public class VoidBlocks implements ITamRegistry {

	private static ArrayList<ITamModel> modelList;

	public static BlockVoidcrystal blockVoidcrystal;
	public static OreVoidcrystal oreVoidcrystal;
	public static BlockFakeBedrock blockFakeBedrock;
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

	public static VoidBox voidBox;
	public static VoidMacerator voidMacerator;
	public static TamBlock voidInfuserInert;
	public static VoidInfuser voidInfuser;
	public static Heimdall Heimdall;

	public static VoidicPowerGen voidicGen;
	public static VoidicPowerCable voidicCable;
	public static VoidicPowerCharger voidicCharger;
	public static RealityStabilizer realityStabilizer;

	public static AIBlock AIBlock;
	public static XiaBlock xiaBlock;

	@Override
	public void preInit() {
		modelList = new ArrayList<ITamModel>();

		modelList.add(blockVoidcrystal = new BlockVoidcrystal(voidCraft.tabs.tabVoid, Material.GLASS, "blockVoidcrystal", 7.0F));
		modelList.add(oreVoidcrystal = new OreVoidcrystal(voidCraft.tabs.tabVoid, Material.ROCK, "oreVoidcrystal", 3.0F));
		modelList.add(blockFakeBedrock = new BlockFakeBedrock(voidCraft.tabs.tabVoid, Blocks.BEDROCK.getMaterial(Blocks.BEDROCK.getDefaultState()), "blockFakeBedrock", 30.0F));
		modelList.add(blockNoBreak = new BlockNoBreak(voidCraft.tabs.tForge, Material.ROCK, "blockNoBreak", -1F));
		modelList.add(blockVoidbrick = new TamBlock(voidCraft.tabs.tabVoid, Material.ROCK, "blockVoidbrick", 30.0F));
		modelList.add(blockVoidfence = new TamBlockFence(voidCraft.tabs.tabVoid, Material.ROCK, MapColor.OBSIDIAN, "blockVoidfence"));
		modelList.add(blockVoidstairs = new TamBlockStairs(voidCraft.tabs.tabVoid, blockVoidbrick.getDefaultState(), "blockVoidstairs"));
		modelList.add(blockVoidBrickDoubleSlab = new TamBlockSlabDouble(voidCraft.tabs.tabVoid, Material.ROCK, "blockVoidBrickDoubleSlab", Item.getItemFromBlock(blockVoidBrickHalfSlab)));
		modelList.add(blockVoidBrickHalfSlab = new TamBlockSlabHalf(voidCraft.tabs.tabVoid, Material.ROCK, "blockVoidBrickHalfSlab", Item.getItemFromBlock(blockVoidBrickHalfSlab)));
		modelList.add(AIBlock = new AIBlock("AIBlock"));
		modelList.add(xiaBlock = new XiaBlock("xiaBlock"));
		modelList.add(realityHole = new BlockRealityHole(voidCraft.tabs.tabVoid, Material.CLOTH, "blockRealityHole", -1F));

		modelList.add(blockPortalVoid = new BlockPortalVoid(voidCraft.tabs.tabVoid, "blockPortalVoid"));
		modelList.add(blockPortalXia = new BlockPortalXia(voidCraft.tabs.tabVoid, "blockPortalXia"));
		modelList.add(fireVoid = new FireVoid(voidCraft.tabs.tabVoid, "fireVoid"));

		modelList.add(voidMacerator = new VoidMacerator(voidCraft.tabs.tabVoid, Material.ROCK, "voidMacerator", 3.5F));
		modelList.add(Heimdall = new Heimdall(voidCraft.tabs.tabVoid, Material.IRON, "Heimdall", 3.5F));
		modelList.add(voidBox = new VoidBox(voidCraft.tabs.tabVoid, Material.IRON, "voidBox", 7.0F));
		modelList.add(voidInfuserInert = new TamBlock(voidCraft.tabs.tabVoid, Material.IRON, "voidInfuserInert", 7.0F));
		modelList.add(voidInfuser = new VoidInfuser(voidCraft.tabs.tabVoid, Material.IRON, "voidInfuser", 7.0F));
		modelList.add(voidicGen = new VoidicPowerGen(voidCraft.tabs.tabVoid, Material.IRON, "voidicGen", 3.5f));
		modelList.add(voidicCable = new VoidicPowerCable(voidCraft.tabs.tabVoid, Material.CIRCUITS, "voidicCable", 1.5f));
		modelList.add(voidicCharger = new VoidicPowerCharger(voidCraft.tabs.tabVoid, Material.IRON, "voidicCharger", 3.5f));
		modelList.add(realityStabilizer = new RealityStabilizer(voidCraft.tabs.tabVoid, Material.IRON, "realityStabilizer", 3.5f));

		// Slabs have to be registered outside of their class
		GameRegistry.registerBlock(blockVoidBrickHalfSlab, TamItemBlockSlab.class, voidCraft.modid+":blocks/" + ((TamBlockSlab) blockVoidBrickHalfSlab).getName(), blockVoidBrickHalfSlab, blockVoidBrickDoubleSlab, false);
		GameRegistry.registerBlock(blockVoidBrickDoubleSlab, TamItemBlockSlab.class, voidCraft.modid+":blocks/" + ((TamBlockSlab) blockVoidBrickDoubleSlab).getName(), blockVoidBrickHalfSlab, blockVoidBrickDoubleSlab, false);
		
	}
	
	@Override
	public void init() {
		GameRegistry.addRecipe(new ItemStack(blockVoidcrystal), "XXX", "XXX", "XXX", 'X', voidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidInfuserInert), "XYX", "YZY", "XYX", 'X', blockVoidbrick, 'Y', voidCraft.items.voidCloth, 'Z', Items.CAULDRON);
		GameRegistry.addShapelessRecipe(new ItemStack(voidInfuser), voidInfuserInert, voidCraft.items.voidStar);
		GameRegistry.addRecipe(new ItemStack(voidBox), "XXX", "XYX", "XZX", 'X', voidCraft.items.voidCloth, 'Y', Blocks.JUKEBOX, 'Z', voidCraft.items.voidStar);
		GameRegistry.addRecipe(new ItemStack(blockVoidbrick), "XX", "XX", 'X', blockVoidcrystal);
		GameRegistry.addRecipe(new ItemStack(blockVoidstairs, 6), "X  ", "XX ", "XXX", 'X', blockVoidbrick);
		GameRegistry.addRecipe(new ItemStack(blockVoidstairs, 6), "  X", " XX", "XXX", 'X', blockVoidbrick);
		GameRegistry.addRecipe(new ItemStack(blockVoidBrickHalfSlab, 6), "XXX", 'X', blockVoidbrick);
		GameRegistry.addRecipe(new ItemStack(blockVoidfence), "X X", "XXX", "X X", 'X', voidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidicCable, 8), "XXX", "XYX", "XXX", 'Y', blockVoidcrystal, 'X', Items.REDSTONE);
		GameRegistry.addRecipe(new ItemStack(voidicGen), "XXX", "XYX", "XXX", 'Y', voidInfuser, 'X', Items.REDSTONE);
		GameRegistry.addRecipe(new ItemStack(voidicCharger), "DSD", "CZC", "DPD", 'D', voidCraft.items.diamondDust, 'S', voidCraft.items.voidStar, 'C', voidCraft.items.voidCloth, 'Z', Items.END_CRYSTAL, 'P', voidicCable);
	}

	@Override
	public void postInit() {

	}

	@SideOnly(Side.CLIENT)
	public void setupRender() {/*
								 * ItemModelMesher modelMesher =
								 * Minecraft.getMinecraft
								 * ().getRenderItem().getItemModelMesher(); for
								 * (Block b : blockList) {
								 * modelMesher.register(Item
								 * .getItemFromBlock(b), 0, new
								 * Tamaized.Voidcraft
								 * .common.client.ScrewModelResourceLocation
								 * ("blocks/", ((IBasicVoid) b).getName(),
								 * "inventory")); }
								 */
	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return modelList;
	}

	@Override
	public String getModID() {
		return voidCraft.modid;
	}

}
