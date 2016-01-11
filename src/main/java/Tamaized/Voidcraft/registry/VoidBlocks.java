package Tamaized.Voidcraft.registry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.blocks.BasicVoidBlock;
import Tamaized.Voidcraft.blocks.BlockFakeBedrock;
import Tamaized.Voidcraft.blocks.BlockNoBreak;
import Tamaized.Voidcraft.blocks.BlockPortalVoid;
import Tamaized.Voidcraft.blocks.BlockPortalXia;
import Tamaized.Voidcraft.blocks.BlockVoidbrick;
import Tamaized.Voidcraft.blocks.BlockVoidcrystal;
import Tamaized.Voidcraft.blocks.FireVoid;
import Tamaized.Voidcraft.blocks.OreVoidcrystal;
import Tamaized.Voidcraft.blocks.VoidFence;
import Tamaized.Voidcraft.blocks.VoidSlab;
import Tamaized.Voidcraft.blocks.VoidStairs;
import Tamaized.Voidcraft.blocks.XiaBlock;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.Heimdall;
import Tamaized.Voidcraft.machina.VoidBox;
import Tamaized.Voidcraft.machina.VoidInfuser;
import Tamaized.Voidcraft.machina.VoidMacerator;

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
	public static Block xiaBlock;

	@Override
	public void preInit() {

		blockVoidcrystal = new BlockVoidcrystal(Material.glass).setHardness(7.0F).setStepSound(Blocks.glass.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		oreVoidcrystal = new OreVoidcrystal(Material.rock).setHardness(3.0F).setStepSound(Blocks.stone.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		blockFakeBedrock = new BlockFakeBedrock(Blocks.bedrock.getMaterial()).setHardness(30.0F).setStepSound(Blocks.bedrock.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		blockNoBreak = new BlockNoBreak(Material.rock).setLightLevel(1.0F).setHardness(-1F).setStepSound(Blocks.wool.stepSound).setCreativeTab(voidCraft.tabs.tForge).setLightLevel(1.0F).setResistance(100);
		blockVoidbrick = new BlockVoidbrick(Material.rock).setHardness(30.0F).setStepSound(Blocks.stone.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		blockVoidfence = new VoidFence(Material.rock).setCreativeTab(voidCraft.tabs.tabVoid);
		blockVoidstairs = new VoidStairs(blockVoidbrick, 0).setCreativeTab(voidCraft.tabs.tabVoid);
		blockVoidBrickSlab = new VoidSlab(Material.rock).setCreativeTab(voidCraft.tabs.tabVoid);
		AIBlock = new AIBlock().setBlockUnbreakable();
		xiaBlock = new XiaBlock().setBlockUnbreakable();

		blockPortalVoid = new BlockPortalVoid("VoidCraft:blockPortalVoid").setCreativeTab(voidCraft.tabs.tabVoid).setHardness(-1F);
		blockPortalXia = new BlockPortalXia("VoidCraft:blockPortalXia").setCreativeTab(voidCraft.tabs.tabVoid).setHardness(-1F);
		fireVoid = new FireVoid().setCreativeTab(voidCraft.tabs.tabVoid);

		voidMacerator = new VoidMacerator().setCreativeTab(voidCraft.tabs.tabVoid).setHardness(3.5F);
		Heimdall = new Heimdall(Material.iron).setCreativeTab(voidCraft.tabs.tabVoid).setHardness(3.5F);
		voidBox = new VoidBox().setHardness(7.0F).setStepSound(Blocks.stone.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		voidInfuserInert = new BasicVoidBlock(Material.iron).setHardness(7.0F).setStepSound(Blocks.iron_block.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		voidInfuser = new VoidInfuser().setHardness(7.0F).setStepSound(Blocks.iron_block.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		
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
