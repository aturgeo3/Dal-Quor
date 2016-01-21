package Tamaized.Voidcraft.registry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.blocks.BasicVoidBlock;
import Tamaized.Voidcraft.blocks.BasicVoidBlockContainer;
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

		blockVoidcrystal = new BlockVoidcrystal(Material.glass, "blockVoidcrystal").setHardness(7.0F).setStepSound(Blocks.glass.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		oreVoidcrystal = new OreVoidcrystal(Material.rock, "oreVoidcrystal").setHardness(3.0F).setStepSound(Blocks.stone.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		blockFakeBedrock = new BlockFakeBedrock(Blocks.bedrock.getMaterial(), "blockFakeBedrock").setHardness(30.0F).setStepSound(Blocks.bedrock.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		blockNoBreak = new BlockNoBreak(Material.rock, "blockNoBreak").setLightLevel(1.0F).setHardness(-1F).setStepSound(Blocks.wool.stepSound).setCreativeTab(voidCraft.tabs.tForge).setLightLevel(1.0F).setResistance(100);
		blockVoidbrick = new BlockVoidbrick(Material.rock, "blockVoidbrick").setHardness(30.0F).setStepSound(Blocks.stone.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		blockVoidfence = new VoidFence(Material.rock, "blockVoidfence").setCreativeTab(voidCraft.tabs.tabVoid);
		blockVoidstairs = new VoidStairs(blockVoidbrick, 0, "blockVoidstairs").setCreativeTab(voidCraft.tabs.tabVoid);
		blockVoidBrickSlab = new VoidSlab(Material.rock, "blockVoidBrickSlab").setCreativeTab(voidCraft.tabs.tabVoid);
		AIBlock = new AIBlock("AIBlock").setBlockUnbreakable();
		xiaBlock = new XiaBlock("xiaBlock").setBlockUnbreakable();

		blockPortalVoid = new BlockPortalVoid("blockPortalVoid").setCreativeTab(voidCraft.tabs.tabVoid).setHardness(-1F);
		blockPortalXia = new BlockPortalXia("blockPortalXia").setCreativeTab(voidCraft.tabs.tabVoid).setHardness(-1F);
		fireVoid = new FireVoid("fireVoid").setCreativeTab(voidCraft.tabs.tabVoid);

		voidMacerator = new VoidMacerator("voidMacerator").setCreativeTab(voidCraft.tabs.tabVoid).setHardness(3.5F);
		Heimdall = new Heimdall(Material.iron, "Heimdall").setCreativeTab(voidCraft.tabs.tabVoid).setHardness(3.5F);
		voidBox = new VoidBox("voidBox").setHardness(7.0F).setStepSound(Blocks.stone.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		voidInfuserInert = new BasicVoidBlock(Material.iron, "voidInfuserInert").setHardness(7.0F).setStepSound(Blocks.iron_block.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		voidInfuser = new VoidInfuser("voidInfuser").setHardness(7.0F).setStepSound(Blocks.iron_block.stepSound).setCreativeTab(voidCraft.tabs.tabVoid);
		
	}

	@Override
	public void init() {

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
		
	}
	
	@SideOnly(Side.CLIENT)
	public void setupRender(){
		ItemModelMesher modelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		modelMesher.register(Item.getItemFromBlock(blockVoidcrystal), 0, new ModelResourceLocation(voidCraft.modid+":blockVoidcrystal", "inventory"));
		modelMesher.register(Item.getItemFromBlock(oreVoidcrystal), 0, new ModelResourceLocation(voidCraft.modid+":oreVoidcrystal", "inventory"));
		modelMesher.register(Item.getItemFromBlock(blockFakeBedrock), 0, new ModelResourceLocation(voidCraft.modid+":blockFakeBedrock", "inventory"));
		modelMesher.register(Item.getItemFromBlock(blockNoBreak), 0, new ModelResourceLocation(voidCraft.modid+":blockNoBreak", "inventory"));
		modelMesher.register(Item.getItemFromBlock(blockVoidbrick), 0, new ModelResourceLocation(voidCraft.modid+":blockVoidbrick", "inventory"));
		modelMesher.register(Item.getItemFromBlock(blockVoidfence), 0, new ModelResourceLocation(voidCraft.modid+":blockVoidfence", "inventory"));
		modelMesher.register(Item.getItemFromBlock(blockVoidstairs), 0, new ModelResourceLocation(voidCraft.modid+":blockVoidstairs", "inventory"));
		modelMesher.register(Item.getItemFromBlock(blockVoidBrickSlab), 0, new ModelResourceLocation(voidCraft.modid+":blockVoidBrickSlab", "inventory"));
		modelMesher.register(Item.getItemFromBlock(AIBlock), 0, new ModelResourceLocation(voidCraft.modid+":AIBlock", "inventory"));
		modelMesher.register(Item.getItemFromBlock(xiaBlock), 0, new ModelResourceLocation(voidCraft.modid+":xiaBlock", "inventory"));
		modelMesher.register(Item.getItemFromBlock(blockPortalVoid), 0, new ModelResourceLocation(voidCraft.modid+":blockPortalVoid", "inventory"));
		modelMesher.register(Item.getItemFromBlock(blockPortalXia), 0, new ModelResourceLocation(voidCraft.modid+":blockPortalXia", "inventory"));
		modelMesher.register(Item.getItemFromBlock(fireVoid), 0, new ModelResourceLocation(voidCraft.modid+":fireVoid", "inventory"));
		modelMesher.register(Item.getItemFromBlock(voidMacerator), 0, new ModelResourceLocation(voidCraft.modid+":voidMacerator", "inventory"));
		modelMesher.register(Item.getItemFromBlock(Heimdall), 0, new ModelResourceLocation(voidCraft.modid+":Heimdall", "inventory"));
		modelMesher.register(Item.getItemFromBlock(voidBox), 0, new ModelResourceLocation(voidCraft.modid+":voidBox", "inventory"));
		modelMesher.register(Item.getItemFromBlock(voidInfuserInert), 0, new ModelResourceLocation(voidCraft.modid+":voidInfuserInert", "inventory"));
		modelMesher.register(Item.getItemFromBlock(voidInfuser), 0, new ModelResourceLocation(voidCraft.modid+":voidInfuser", "inventory"));
	}

}
