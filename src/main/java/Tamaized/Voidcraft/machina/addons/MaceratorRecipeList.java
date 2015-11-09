package Tamaized.Voidcraft.machina.addons;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MaceratorRecipeList {
	
	private Map<String, ItemStack> oreDictList = new HashMap<String, ItemStack>();
	private Map<ItemStack, ItemStack> hardcodeList = new HashMap<ItemStack, ItemStack>();
	
	public Map<String, ItemStack> getOreDictList(){
		return oreDictList;
	}
	
	public Map<ItemStack, ItemStack> getHardCodeList(){
		return hardcodeList;
	}
	
	public void addToOreDict(String s, ItemStack is){
		voidCraft.logger.info("Adding Void Macerator recipe: (oreDictionary) "+s+" => "+is);
		oreDictList.put(s, is);
	}
	
	public void addToHardCode(ItemStack i, ItemStack is){
		voidCraft.logger.info("Adding Void Macerator recipe: "+i+" => "+is);
		hardcodeList.put(i, is);
	}
	
	public void printMaps(){
		
		voidCraft.logger.info("oreDictList:");
		for (Entry<String, ItemStack> entry : oreDictList.entrySet()) {
			voidCraft.logger.info("key: " + entry.getKey() + "; value: " + entry.getValue() );
        }
		
		voidCraft.logger.info("hardcodeList:");
		for (Entry<ItemStack, ItemStack> entry : hardcodeList.entrySet()) {
			voidCraft.logger.info("key: " + entry.getKey() + "; value: " + entry.getValue() );
        }
	}

}
