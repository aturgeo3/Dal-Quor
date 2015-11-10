package Tamaized.Voidcraft.registery;

import cpw.mods.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.items.BasicVoidItems;
import Tamaized.Voidcraft.items.ChainedSkull;
import Tamaized.Voidcraft.items.Debugger;
import Tamaized.Voidcraft.items.VoidBurner;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class VoidItems extends RegistryBase {

	public static Item ectoplasm;
	public static Item voidcrystal;
	public static Item voidBurner;
	public static Item voidChain;
	public static Item MoltenvoidChain;
	public static Item MoltenvoidChainPart;
	public static Item burnBone;
	public static Item voidStar;
	public static Item ChainedSkull;
	public static Item HookShot;
	public static Item voidCloth;
	public static Item voidCrystalBucket;

	public static Item ironDust;
	public static Item goldDust;
	public static Item diamondDust;
	public static Item copperDust;
	public static Item tinDust;
	public static Item leadDust;
	public static Item coalDust;
	public static Item quartzDust;

	public static Item debugger;

	@Override
	public void preInit() {
		voidBurner = new VoidBurner().setUnlocalizedName("voidBurner").setCreativeTab(voidCraft.tabs.tabVoid).setTextureName("VoidCraft:voidBurner");
		ectoplasm = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("ectoplasm").setTextureName("VoidCraft:ectoplasm");
		voidcrystal = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("voidcrystal").setTextureName("VoidCraft:voidcrystal");
		voidChain = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("voidChain").setTextureName("VoidCraft:voidChain");
		MoltenvoidChain = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("MoltenvoidChain").setTextureName("VoidCraft:MoltenvoidChain");
		MoltenvoidChainPart = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("MoltenvoidChainPart").setTextureName("VoidCraft:MoltenvoidChainPart");
		burnBone = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("burnBone").setTextureName("VoidCraft:Bone");
		voidStar = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("voidStar").setTextureName("VoidCraft:voidStar");
		ChainedSkull = new ChainedSkull().setMaxStackSize(1).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("ChainedSkull").setTextureName("VoidCraft:ChainedSkull");
		voidCloth = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("voidCloth").setTextureName("VoidCraft:voidCloth");
		voidCrystalBucket = new BasicVoidItems().setMaxStackSize(1).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("voidCrystalBucket").setTextureName("VoidCraft:voidCrystalBucket");
	
		// dust
		ironDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("ironDust").setTextureName("voidCraft:itemDustIron");
		goldDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("goldDust").setTextureName("voidCraft:itemDustGold");
		diamondDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("diamondDust").setTextureName("voidCraft:itemDustDiamond");
		coalDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("coalDust").setTextureName("voidCraft:itemDustCoal");
		copperDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("copperDust").setTextureName("voidCraft:itemDustCopper");
		tinDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("tinDust").setTextureName("voidCraft:itemDustTin");
		leadDust = new BasicVoidItems().setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("leadDust").setTextureName("voidCraft:itemDustLead");
		quartzDust = new BasicVoidItems().setCreativeTab(voidCraft.tabs.tabVoid).setMaxStackSize(64).setUnlocalizedName("quartzDust").setTextureName("voidCraft:itemDustQuartz");

		debugger = new Debugger().setCreativeTab(voidCraft.tabs.tabVoid).setMaxStackSize(64).setUnlocalizedName("debugger").setTextureName("voidCraft:debug");
	}

	@Override
	public void init() {
		GameRegistry.registerItem(voidCrystalBucket, voidCrystalBucket.getUnlocalizedName());
		GameRegistry.registerItem(voidcrystal, voidcrystal.getUnlocalizedName());
		GameRegistry.registerItem(ectoplasm, ectoplasm.getUnlocalizedName());
		GameRegistry.registerItem(voidChain, voidChain.getUnlocalizedName());
		GameRegistry.registerItem(MoltenvoidChainPart, MoltenvoidChainPart.getUnlocalizedName());
		GameRegistry.registerItem(MoltenvoidChain, MoltenvoidChain.getUnlocalizedName());
		GameRegistry.registerItem(burnBone, burnBone.getUnlocalizedName());
		GameRegistry.registerItem(voidCloth, voidCloth.getUnlocalizedName());
		GameRegistry.registerItem(voidStar, voidStar.getUnlocalizedName());
		GameRegistry.registerItem(voidBurner, voidBurner.getUnlocalizedName());
		GameRegistry.registerItem(ChainedSkull, ChainedSkull.getUnlocalizedName());
		GameRegistry.registerItem(debugger, debugger.getUnlocalizedName());

		GameRegistry.registerItem(ironDust, ironDust.getUnlocalizedName());
		OreDictionary.registerOre("dustIron", ironDust);
		GameRegistry.registerItem(goldDust, goldDust.getUnlocalizedName());
		OreDictionary.registerOre("dustGold", goldDust);
		GameRegistry.registerItem(coalDust, coalDust.getUnlocalizedName());
		OreDictionary.registerOre("dustCoal", coalDust);
		GameRegistry.registerItem(diamondDust, diamondDust.getUnlocalizedName());
		OreDictionary.registerOre("dustDiamond", diamondDust);
		GameRegistry.registerItem(copperDust, copperDust.getUnlocalizedName());
		OreDictionary.registerOre("dustCopper", copperDust);
		GameRegistry.registerItem(tinDust, tinDust.getUnlocalizedName());
		OreDictionary.registerOre("dustTin", tinDust);
		GameRegistry.registerItem(leadDust, leadDust.getUnlocalizedName());
		OreDictionary.registerOre("dustLead", leadDust);
		GameRegistry.registerItem(quartzDust, quartzDust.getUnlocalizedName());
		OreDictionary.registerOre("dustQuartz", quartzDust);
		
		GameRegistry.addShapelessRecipe(new ItemStack(voidcrystal, 9), voidCraft.blocks.blockVoidcrystal);
		GameRegistry.addShapelessRecipe(new ItemStack(voidCrystalBucket), voidcrystal, Items.bucket);
		GameRegistry.addShapelessRecipe(new ItemStack(voidBurner), voidcrystal, new ItemStack(Items.flint_and_steel, 1, voidCraft.WILDCARD_VALUE));
		GameRegistry.addRecipe(new ItemStack(MoltenvoidChain), "XYX", "YXY", "XYX", 'Y', MoltenvoidChainPart, 'X', burnBone);
		GameRegistry.addRecipe(new ItemStack(ChainedSkull), "XYX", "YZY", "XYX", 'X', MoltenvoidChain, 'Y', burnBone, 'Z', new ItemStack(Items.skull, 1, 1));
		GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 1), "XX", "XX", 'X', burnBone);

		GameRegistry.addSmelting(voidCraft.blocks.oreVoidcrystal, new ItemStack(voidcrystal), 0.1F);
		GameRegistry.addSmelting(voidChain, new ItemStack(MoltenvoidChainPart), 0.1F);
		GameRegistry.addSmelting(voidCrystalBucket, new ItemStack(voidCraft.fluids.voidBucket), 0.1F);
		// dust
		GameRegistry.addSmelting(ironDust, new ItemStack(Items.iron_ingot), 0);
		GameRegistry.addSmelting(goldDust, new ItemStack(Items.gold_ingot), 0);
		GameRegistry.addSmelting(diamondDust, new ItemStack(Items.diamond), 0);
		this.addPreSmelting(copperDust, "ingotCopper");
		this.addPreSmelting(tinDust, "ingotTin");
		this.addPreSmelting(leadDust, "ingotLead");

		voidCraft.maceratorList.addToOreDict("oreIron", new ItemStack(ironDust, 4));
		voidCraft.maceratorList.addToOreDict("oreGold", new ItemStack(goldDust, 4));
		voidCraft.maceratorList.addToOreDict("oreDiamond", new ItemStack(diamondDust, 4));
		voidCraft.maceratorList.addToOreDict("oreCopper", new ItemStack(copperDust, 4));
		voidCraft.maceratorList.addToOreDict("oreTin", new ItemStack(tinDust, 4));
		voidCraft.maceratorList.addToOreDict("oreLead", new ItemStack(leadDust, 4));
		voidCraft.maceratorList.addToOreDict("oreQuartz", new ItemStack(quartzDust, 4));
		voidCraft.maceratorList.addToOreDict("ingotIron", new ItemStack(ironDust, 1));
		voidCraft.maceratorList.addToOreDict("ingotGold", new ItemStack(goldDust, 1));
		voidCraft.maceratorList.addToOreDict("gemQuartz", new ItemStack(quartzDust, 1));
		voidCraft.maceratorList.addToOreDict("gemDiamond", new ItemStack(diamondDust, 1));
		voidCraft.maceratorList.addToOreDict("ingotCopper", new ItemStack(copperDust, 1));
		voidCraft.maceratorList.addToOreDict("ingotTin", new ItemStack(tinDust, 1));
		voidCraft.maceratorList.addToOreDict("ingotLead", new ItemStack(leadDust, 1));
		//--HardCode
		voidCraft.maceratorList.addToHardCode(new ItemStack(Items.coal, 1), new ItemStack(coalDust, 4));
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub

	}
	
	private void addPreSmelting(Item i, String s){
		for(ItemStack ore : OreDictionary.getOres(s)){
			if(ore != null){
				GameRegistry.addSmelting(i, ore, ore.getItemDamage());
			}
		}
	}

}
