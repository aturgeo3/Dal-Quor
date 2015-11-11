package Tamaized.Voidcraft.Addons.thaumcraft;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.common.Thaumcraft;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidCraftThaumRecipes {

	private ThaumcraftApi instance;
	
	public Map<String, ShapelessArcaneRecipe> listShapeless = new HashMap<String, ShapelessArcaneRecipe>();
	public Map<String, ShapedArcaneRecipe> listShaped = new HashMap<String, ShapedArcaneRecipe>();
	public Map<String, CrucibleRecipe> listCrucible = new HashMap<String, CrucibleRecipe>();

	public VoidCraftThaumRecipes(ThaumcraftApi i) {
		instance = i;
	}
	
	public void register(){
		
		listShaped.put("vc.FociAcid", instance.addArcaneCraftingRecipe("vc.FociAcid", new ItemStack(voidCraft.thaumcraftIntegration.spells.acid), new AspectList().add(Aspect.EARTH, 20).add(Aspect.ENTROPY, 10), "XOX", "OZO", "XOX", 'X', ItemApi.getItem("itemShard", 3), 'O', Items.quartz, 'Z', voidCraft.items.voidcrystal));
		
		listCrucible.put("vc.CorruptedSword", instance.addCrucibleRecipe("vc.CorruptedSword", new ItemStack(voidCraft.thaumcraftIntegration.items.corruptedSword), new ItemStack(voidCraft.tools.voidSword), new AspectList().add(Aspect.ENTROPY, 24).add(Aspect.TAINT, 12)));
	}

}
