package Tamaized.Voidcraft.Addons.thaumcraft;

import cpw.mods.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.Addons.thaumcraft.items.ItemCorruptedSword;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.tools.angelicSword;
import net.minecraft.item.Item;
import thaumcraft.api.ThaumcraftApi;

public class VoidCraftItems {
	
	private ThaumcraftApi instance;
	
	public static Item corruptedSword;
	
	public VoidCraftItems(ThaumcraftApi i){
		instance = i;
	}
	
	public void preInit(){
		corruptedSword = new ItemCorruptedSword(voidCraft.materials.voidTools).setCreativeTab(voidCraft.tabs.tabVoid).setUnlocalizedName("corruptedSword").setTextureName("VoidCraft:Thaumcraft/corruptedSword");
	}
	
	public void init(){
		GameRegistry.registerItem(corruptedSword, corruptedSword.getUnlocalizedName());
	}
	
	public void postInit(){
		
	}
	
}
