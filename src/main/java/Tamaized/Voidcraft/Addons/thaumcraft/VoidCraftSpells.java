package Tamaized.Voidcraft.Addons.thaumcraft;

import java.util.ArrayList;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.Addons.thaumcraft.Spells.SpellAcid;
import Tamaized.Voidcraft.Addons.thaumcraft.Spells.VoidSpellFocus;
import Tamaized.Voidcraft.common.voidCraft;
import thaumcraft.api.ThaumcraftApi;

public class VoidCraftSpells {
	
	public static SpellAcid acid;
	private ArrayList<VoidSpellFocus> ItemList = new ArrayList<VoidSpellFocus>();

	public VoidCraftSpells(ThaumcraftApi instance) {
		
		acid = new SpellAcid();
		
		((SpellAcid) acid
			.setUnlocalizedName("voidfociAcid")
			.setCreativeTab(voidCraft.tabVoid))
			.setMyName("Wand Focus: Acid");
		
		GameRegistry.registerItem(acid, "voidfociAcid");
		
		ItemList.add(acid);
		
	}
	
	public ArrayList<VoidSpellFocus> getItems(){
		return ItemList;
	}

}
