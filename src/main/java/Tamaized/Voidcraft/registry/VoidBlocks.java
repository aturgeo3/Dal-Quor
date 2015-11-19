package Tamaized.Voidcraft.registry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.blocks.BasicVoidBlock;
import Tamaized.Voidcraft.blocks.BlockPortalVoid;
import Tamaized.Voidcraft.blocks.BlockPortalXia;
import Tamaized.Voidcraft.blocks.FireVoid;
import Tamaized.Voidcraft.blocks.OreVoidcrystal;
import Tamaized.Voidcraft.blocks.VoidFence;
import Tamaized.Voidcraft.blocks.VoidSlab;
import Tamaized.Voidcraft.blocks.VoidStairs;
import Tamaized.Voidcraft.blocks.blockFakeBedrock;
import Tamaized.Voidcraft.blocks.blockNoBreak;
import Tamaized.Voidcraft.blocks.blockVoidbrick;
import Tamaized.Voidcraft.blocks.blockVoidcrystal;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.Heimdall;
import Tamaized.Voidcraft.machina.VoidBox;
import Tamaized.Voidcraft.machina.VoidInfuser;
import Tamaized.Voidcraft.machina.VoidMacerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class VoidBlocks extends RegistryBase {

	public static Block blockVoidcrystal;
	public static Block oreVoidcrystal;
	public static Block blockFakeBedrock;
	public static Block blockNoBreak;
	public static Block blockVoidbrick;
	public static Block blockVoidfence;
	public static Block blockVoidstairs;
	public static Block blockVoidBrickSlab;
	public static Block blockPortalVoid;
	public static Block blockPortalXia;
	public static Block fireVoid;
	public static Block voidBox;
	public static Block voidMacerator;
	public static Block voidInfuserInert;
	public static Block voidInfuser;
	public static Block Heimdall;
	public static Block AIBlock;

	@Override
	public void preInit() {

		blockVoidcrystal = new blockVoidcrystal(Material.glass).setHardness(7.0F).setStepSound(Blocks.glass.stepSound).setCreativeTab(voidCraft.tabs.tabVoid).setBlockName("blockVoidcrystal").setBlockTextureName("VoidCraft:VoidCrystalBlock");
		oreVoidcrystal = new OreVoidcrystal(Material.rock).setHardness(3.0F).setStepSound(Blocks.stone.stepSound).setCreativeTab(voidCraft.tabs.tabVoid).setBlockName("oreVoidcrystal");
		blockFakeBedrock = new blockFakeBedrock(Blocks.bedrock.getMaterial()).setHardness(30.0F).setStepSound(Blocks.bedrock.stepSound).setCreativeTab(voidCraft.tabs.tabVoid).setBlockName("blockFakeBedrock").setBlockTextureName("minecraft:bedrock");
		blockNoBreak = new blockNoBreak(Material.rock).setLightLevel(1.0F).setHardness(-1F).setStepSound(Blocks.wool.stepSound).setCreativeTab(voidCraft.tabs.tForge).setBlockName("blockNoBreak").setLightLevel(1.0F).setResistance(100).setBlockTextureName("VoidCraft:limbo/limbo");
		blockVoidbrick = new blockVoidbrick(Material.rock).setHardness(30.0F).setStepSound(Blocks.stone.stepSound).setCreativeTab(voidCraft.tabs.tabVoid).setBlockName("blockVoidbrick").setBlockTextureName("VoidCraft:VoidBrick");
		blockVoidfence = new VoidFence("VoidCraft:VoidBrick", Material.rock).setCreativeTab(voidCraft.tabs.tabVoid).setBlockName("blockVoidfence");
		blockVoidstairs = new VoidStairs(blockVoidbrick, 0).setCreativeTab(voidCraft.tabs.tabVoid).setBlockName("blockVoidstairs").setBlockTextureName("VoidCraft:VoidBrick");
		blockVoidBrickSlab = new VoidSlab(false, Material.rock).setCreativeTab(voidCraft.tabs.tabVoid).setBlockName("blockVoidslab").setBlockTextureName("VoidCraft:VoidBrick");
		AIBlock = new AIBlock().setBlockUnbreakable().setBlockName("aiBlock");

		blockPortalVoid = new BlockPortalVoid("VoidCraft:blockPortalVoid").setBlockName("blockPortalVoid").setCreativeTab(voidCraft.tabs.tabVoid).setHardness(-1F);
		blockPortalXia = new BlockPortalXia("VoidCraft:blockPortalXia").setBlockName("blockPortalXia").setCreativeTab(voidCraft.tabs.tabVoid).setHardness(-1F);
		fireVoid = new FireVoid().setBlockName("voidfire").setCreativeTab(voidCraft.tabs.tabVoid).setBlockTextureName("VoidCraft:fireVoid");

		voidMacerator = new VoidMacerator().setCreativeTab(voidCraft.tabs.tabVoid).setHardness(3.5F).setBlockName("voidMaceratorOff");
		Heimdall = new Heimdall(Material.iron).setCreativeTab(voidCraft.tabs.tabVoid).setHardness(3.5F).setBlockTextureName("voidCraft:heimdall").setBlockName("Heimdall");
		voidBox = new VoidBox().setHardness(7.0F).setStepSound(Blocks.stone.stepSound).setCreativeTab(voidCraft.tabs.tabVoid).setBlockName("voidBox").setBlockTextureName("VoidCraft:voidBox");
		voidInfuserInert = new BasicVoidBlock(Material.iron).setHardness(7.0F).setStepSound(Blocks.iron_block.stepSound).setCreativeTab(voidCraft.tabs.tabVoid).setBlockName("voidInfuserInert").setBlockTextureName("VoidCraft:voidInfuser");
		voidInfuser = new VoidInfuser().setHardness(7.0F).setStepSound(Blocks.iron_block.stepSound).setCreativeTab(voidCraft.tabs.tabVoid).setBlockName("voidInfuser").setBlockTextureName("VoidCraft:voidInfuser");
		
	}

	@Override
	public void init() {
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
		GameRegistry.registerBlock(blockPortalVoid, blockPortalVoid.getUnlocalizedName());
		GameRegistry.registerBlock(blockPortalXia, blockPortalXia.getUnlocalizedName());

		GameRegistry.addRecipe(new ItemStack(blockVoidcrystal), "XXX", "XXX", "XXX", 'X', voidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidInfuserInert), "XYX", "YZY", "XYX", 'X', blockVoidbrick, 'Y', voidCraft.items.voidCloth, 'Z', Items.cauldron);
		GameRegistry.addShapelessRecipe(new ItemStack(voidInfuser), voidInfuserInert, voidCraft.items.voidStar);
		GameRegistry.addRecipe(new ItemStack(voidBox), "XXX", "XYX", "XZX", 'X', voidCraft.items.voidCloth, 'Y', Blocks.jukebox, 'Z', voidCraft.items.voidStar);
		GameRegistry.addRecipe(new ItemStack(blockVoidbrick), "XX", "XX", 'X', blockVoidcrystal);
		GameRegistry.addRecipe(new ItemStack(blockVoidstairs, 6), "X  ", "XX ", "XXX", 'X', blockVoidbrick);
		GameRegistry.addRecipe(new ItemStack(blockVoidstairs, 6), "  X", " XX", "XXX", 'X', blockVoidbrick);
		GameRegistry.addRecipe(new ItemStack(blockVoidBrickSlab, 6), "XXX", 'X', blockVoidbrick);
		GameRegistry.addRecipe(new ItemStack(blockVoidfence), "X X", "XXX", "X X", 'X', voidCraft.items.voidcrystal);
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub

	}

}
