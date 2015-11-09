package Tamaized.Voidcraft.Addons.thaumcraft;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidCraftThaumRecipes {
	
	public Map<String, ShapelessArcaneRecipe> list = new HashMap<String, ShapelessArcaneRecipe>();

	public VoidCraftThaumRecipes(ThaumcraftApi instance) {
		
		list.put("vc.CorruptedSword", instance.addShapelessArcaneCraftingRecipe("vc.CorruptedSword", new ItemStack(voidCraft.demonSword), new AspectList().add(Aspect.ENTROPY, 25), voidCraft.voidSword));
		
	}

}
