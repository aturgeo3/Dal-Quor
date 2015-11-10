package Tamaized.Voidcraft.Addons.thaumcraft;

import java.util.ArrayList;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.Addons.thaumcraft.Spells.SpellAcid;
import Tamaized.Voidcraft.Addons.thaumcraft.Spells.VoidSpellFocus;
import Tamaized.Voidcraft.Addons.thaumcraft.items.ItemCorruptedSword;
import Tamaized.Voidcraft.common.voidCraft;
import thaumcraft.api.ThaumcraftApi;

public class VoidCraftSpells {
	
	private ThaumcraftApi instance;
	
	public static Item acid;

	public VoidCraftSpells(ThaumcraftApi i) {
		instance = i;
	}
	
	public void preInit(){
		acid = new SpellAcid().setUnlocalizedName("voidfociAcid").setCreativeTab(voidCraft.tabs.tabVoid);
	}
	
	public void init(){
		GameRegistry.registerItem(acid, "voidfociAcid");
	}
	
	public void postInit(){
		
	}

}
