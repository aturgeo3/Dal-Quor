package Tamaized.Voidcraft.Addons.thaumcraft.Research;

import Tamaized.Voidcraft.Addons.thaumcraft.VoidCraftThaumRecipes;
import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class CorruptedSwordResearch extends ResearchItem {
	
	private final static AspectList aspects = new AspectList()
	.add(Aspect.TAINT, 1)
	.add(Aspect.VOID, 1)
	.add(Aspect.ENTROPY, 1);

	public CorruptedSwordResearch(VoidCraftThaumRecipes recipes) {
		
		super("vc.CorruptedSword", "VoidCraft", aspects, 0, 0, 1, new ResourceLocation("VoidCraft:textures/items/Thaumcraft/corruptedSword.png")); 
		
		this.setParents("vc.VoidCrystal");
		
		ResearchPage[] pages = {
				new ResearchPage("Combining Magic with the crystals of the Void allows for some interesting results...\n\n"
						+ "It seems as if combining a Void Crystal Sword with perditio and vitium allows the sword to drain the life from it's victims, healing the user for a small amount."),
				new ResearchPage(recipes.listCrucible.get("vc.CorruptedSword"))
		};
		
		this.setPages(pages);
	}

}
