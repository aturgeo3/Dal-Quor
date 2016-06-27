package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
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
import Tamaized.Voidcraft.items.VoidicSuppressor;
import Tamaized.Voidcraft.sound.VoidSoundEvents;

public class VoidItems extends RegistryBase {
	
	public static ArrayList<Item> voidDiscs;
	private static ArrayList<Item> itemList;

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

	public static Item voidicSuppressor;
	
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
		
		voidicSuppressor = new VoidicSuppressor("voidicSuppressor").setCreativeTab(voidCraft.tabs.tabVoid).setMaxStackSize(1);
		
		voidDiscs = new ArrayList<Item>();
		voidDiscs.add(new VoidRecord("No Strings Attached (Original Mix)", VoidSoundEvents.MusicDiscSoundEvents.No_Strings_Attached, "voidDisc1"));
		voidDiscs.add(new VoidRecord("Haven - Bleeding Through", VoidSoundEvents.MusicDiscSoundEvents.Haven_Bleeding_Through, "voidDisc2"));
		
		itemList = new ArrayList<Item>();
		itemList.add(ectoplasm);
		itemList.add(voidcrystal);
		itemList.add(voidBurner);
		itemList.add(voidChain);
		itemList.add(MoltenvoidChain);
		itemList.add(MoltenvoidChainPart);
		itemList.add(burnBone);
		itemList.add(voidStar);
		itemList.add(ChainedSkull);
		itemList.add(voidCloth);
		itemList.add(voidCrystalBucket);
		itemList.add(ironDust);
		itemList.add(goldDust);
		itemList.add(diamondDust);
		itemList.add(ectoplasm);
		itemList.add(copperDust);
		itemList.add(tinDust);
		itemList.add(leadDust);
		itemList.add(coalDust);
		itemList.add(quartzDust);
		itemList.add(debugger);
		itemList.add(hookShot);
		itemList.add(voidicSuppressor);
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
		GameRegistry.addShapelessRecipe(new ItemStack(voidCrystalBucket), voidcrystal, Items.BUCKET);
		GameRegistry.addShapelessRecipe(new ItemStack(voidBurner), voidcrystal, new ItemStack(Items.FLINT_AND_STEEL, 1, voidCraft.WILDCARD_VALUE));
		GameRegistry.addShapelessRecipe(new ItemStack(voidicSuppressor), voidcrystal, Items.COMPASS, Items.REDSTONE, voidCloth);
		GameRegistry.addRecipe(new ItemStack(MoltenvoidChain), "XYX", "YXY", "XYX", 'Y', MoltenvoidChainPart, 'X', burnBone);
		GameRegistry.addRecipe(new ItemStack(ChainedSkull), "XYX", "YZY", "XYX", 'X', MoltenvoidChain, 'Y', burnBone, 'Z', new ItemStack(Items.SKULL, 1, 1));
		GameRegistry.addRecipe(new ItemStack(Items.SKULL, 1, 1), "XX", "XX", 'X', burnBone);

		GameRegistry.addSmelting(voidCraft.blocks.oreVoidcrystal, new ItemStack(voidcrystal), 0.1F);
		GameRegistry.addSmelting(voidChain, new ItemStack(MoltenvoidChainPart), 0.1F);
		GameRegistry.addSmelting(voidCrystalBucket, voidCraft.fluids.getBucket(), 0.1F);
		// dust
		GameRegistry.addSmelting(ironDust, new ItemStack(Items.IRON_INGOT), 0);
		GameRegistry.addSmelting(goldDust, new ItemStack(Items.GOLD_INGOT), 0);
		GameRegistry.addSmelting(diamondDust, new ItemStack(Items.DIAMOND), 0);
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
		voidCraft.maceratorList.addToHardCode(new ItemStack(Items.COAL, 1), new ItemStack(coalDust, 4));
		
		//Discs
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(0)), "XZZ", "ZYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		GameRegistry.addRecipe(new ItemStack(voidDiscs.get(1)), "ZXZ", "ZYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		//GameRegistry.addRecipe(new ItemStack(voidDiscs.get(2)), "ZZZ", "XYZ", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		//GameRegistry.addRecipe(new ItemStack(voidDiscs.get(3)), "ZZZ", "ZYX", "ZZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		//GameRegistry.addRecipe(new ItemStack(voidDiscs.get(4)), "ZZZ", "ZYZ", "XZZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
		//GameRegistry.addRecipe(new ItemStack(voidDiscs.get(5)), "ZZZ", "ZYZ", "ZXZ", 'X', burnBone, 'Y', voidcrystal, 'Z', Items.COAL);
	}

	@Override
	public void postInit() {
		
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
		for(Item i : itemList){
			renderItem.getItemModelMesher().register(i, 0, new Tamaized.Voidcraft.common.client.ScrewModelResourceLocation("items/", ((IBasicVoid)i).getName(), "inventory"));
		}
		
		for(Item i : voidDiscs){
			renderItem.getItemModelMesher().register(i, 0, new Tamaized.Voidcraft.common.client.ScrewModelResourceLocation("discs/", ((IBasicVoid)i).getName(), "inventory"));
			System.out.println(i+":"+((IBasicVoid)i).getName());
		}
	}

}
