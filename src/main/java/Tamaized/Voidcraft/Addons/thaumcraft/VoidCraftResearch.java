package Tamaized.Voidcraft.Addons.thaumcraft;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchCategories;
import Tamaized.Voidcraft.Addons.thaumcraft.Research.CorruptedSword;
import Tamaized.Voidcraft.Addons.thaumcraft.Research.VoidCrystal;

public class VoidCraftResearch {
	
	public VoidCraftResearch(VoidCraftThaumRecipes recipes){
		
		ResearchCategories.registerCategory("VoidCraft", new ResourceLocation("VoidCraft:textures/items/Blade_of_Corruption.png"), new ResourceLocation("VoidCraft:textures/GUI/Thaumcraft/VoidBG-3.gif"));
		
		ResearchCategories.addResearch(new VoidCrystal(recipes));
		ResearchCategories.addResearch(new CorruptedSword(recipes));
		
	}

}
