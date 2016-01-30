package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.items.BasicVoidItems;
import Tamaized.Voidcraft.items.ChainedSkull;
import Tamaized.Voidcraft.items.Debugger;
import Tamaized.Voidcraft.items.HookShot;
import Tamaized.Voidcraft.items.VoidBurner;
import Tamaized.Voidcraft.items.VoidRecord;
import Tamaized.Voidcraft.items.VoidStar;

public class VoidItems extends RegistryBase {
	
	public static ArrayList<Item> voidDiscs;

	public static Item ectoplasm;
	public static Item voidcrystal;
	public static Item voidBurner;
	public static Item voidChain;
	public static Item MoltenvoidChain;
	public static Item MoltenvoidChainPart;
	public static Item burnBone;
	public static Item voidStar;
	public static Item ChainedSkull;
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
	public static Item hookShot;

	@Override
	public void preInit() {
		voidBurner = new VoidBurner("voidBurner").setCreativeTab(voidCraft.tabs.tabVoid);
		ectoplasm = new BasicVoidItems("ectoplasm").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		voidcrystal = new BasicVoidItems("voidcrystal").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		voidChain = new BasicVoidItems("voidChain").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		MoltenvoidChain = new BasicVoidItems("MoltenvoidChain").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		MoltenvoidChainPart = new BasicVoidItems("MoltenvoidChainPart").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		burnBone = new BasicVoidItems("burnBone").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		voidStar = new VoidStar("voidStar").setMaxStackSize(1).setCreativeTab(voidCraft.tabs.tabVoid);
		ChainedSkull = new ChainedSkull("ChainedSkull").setMaxStackSize(1).setCreativeTab(voidCraft.tabs.tabVoid);
		voidCloth = new BasicVoidItems("voidCloth").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		voidCrystalBucket = new BasicVoidItems("voidCrystalBucket").setMaxStackSize(1).setCreativeTab(voidCraft.tabs.tabVoid);
	
		// dust
		ironDust = new BasicVoidItems("ironDust").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		goldDust = new BasicVoidItems("goldDust").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		diamondDust = new BasicVoidItems("diamondDust").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		coalDust = new BasicVoidItems("coalDust").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		copperDust = new BasicVoidItems("copperDust").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		tinDust = new BasicVoidItems("tinDust").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		leadDust = new BasicVoidItems("leadDust").setMaxStackSize(64).setCreativeTab(voidCraft.tabs.tabVoid);
		quartzDust = new BasicVoidItems("quartzDust").setCreativeTab(voidCraft.tabs.tabVoid).setMaxStackSize(64);

		debugger = new Debugger("debugger").setCreativeTab(voidCraft.tabs.tabVoid).setMaxStackSize(1);
		hookShot = new HookShot("hookShot").setCreativeTab(voidCraft.tabs.tabVoid).setMaxStackSize(1);
		
		voidDiscs = new ArrayList<Item>();
		voidDiscs.add(new VoidRecord("voidCraft:Lavender Town", 271, "voidDisc1"));
		voidDiscs.add(new VoidRecord("voidCraft:Lensko - Cetus", 289, "voidDisc2"));
		voidDiscs.add(new VoidRecord("voidCraft:Starfox- Assault-Starwolf theme", 173, "voidDisc3"));
		voidDiscs.add(new VoidRecord("voidCraft:They Will Die", 250, "voidDisc4"));
		voidDiscs.add(new VoidRecord("voidCraft:Warriors", 171, "voidDisc5"));
		voidDiscs.add(new VoidRecord("voidCraft:Imagine Dragons - Shots (Broiler Remix)", 190, "voidDisc6"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Asgore", 154, "voidDisc7"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Core", 164, "voidDisc8"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Megalovania", 156, "voidDisc9"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Muffet", 100, "voidDisc10"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Papyrus", 58, "voidDisc11"));
		voidDiscs.add(new VoidRecord("voidCraft:Undertale - Undyne", 156, "voidDisc12"));
	}

	@Override
	public void init() {

		OreDictionary.registerOre("dustIron", ironDust);
		OreDictionary.registerOre("dustGold", goldDust);
		OreDictionary.registerOre("dustCoal", coalDust);
		OreDictionary.registerOre("dustDiamond", diamondDust);
		OreDictionary.registerOre("dustCopper", copperDust);
		OreDictionary.registerOre("dustTin", tinDust);
		OreDictionary.registerOre("dustLead", leadDust);
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
		
		//Discs
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(0)), "XZZ", "ZYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(1)), "ZXZ", "ZYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(2)), "ZZZ", "XYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(3)), "ZZZ", "ZYX", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(4)), "ZZZ", "ZYZ", "XZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.coal);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(5)), "ZZZ", "ZYZ", "ZXZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.coal);
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

	@SideOnly(Side.CLIENT)
	@Override
	public void setupRender() {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    	//renderItem.getItemModelMesher().register(ectoplasm, 0, new ModelResourceLocation(voidCraft.modid+":test/"+((IBasicVoid)ectoplasm).getName(), "inventory"));
    	renderItem.getItemModelMesher().register(ectoplasm, 0, new ModelResourceLocation("voidcraft:test/ectoplasm", "inventory"));
    	renderItem.getItemModelMesher().register(voidBurner, 0, new ModelResourceLocation(voidCraft.modid+":"+((IBasicVoid)voidBurner).getName(), "inventory"));
    	//renderItem.getItemModelMesher().register(burnBone, 0, new ModelResourceLocation(voidCraft.modid+":"+((IBasicVoid)burnBone).getName(), "inventory"));
	}

}
