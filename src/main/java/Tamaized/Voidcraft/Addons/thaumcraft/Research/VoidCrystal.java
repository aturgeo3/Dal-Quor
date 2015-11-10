package Tamaized.Voidcraft.Addons.thaumcraft.Research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import Tamaized.Voidcraft.Addons.thaumcraft.VoidCraftThaumRecipes;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidCrystal extends ResearchItem {
	
	private final static AspectList aspects = new AspectList()
	.add(Aspect.TAINT, 1)
	.add(Aspect.VOID, 1)
	.add(Aspect.ENTROPY, 1);

	public VoidCrystal(VoidCraftThaumRecipes recipes) {
		
		super("vc.VoidCrystal", "VoidCraft", aspects, 2, 1, 1, new ResourceLocation("VoidCraft:textures/items/voidcrystal.png")); 
		
		this.setHidden();
		this.setItemTriggers(new ItemStack(voidCraft.items.voidcrystal));
		
		ResearchPage[] pages = {
				new ResearchPage(
						"This isn't crystallized Vacuos that's for sure.. no, it's different, much much different. It doesn't even seem to be connected to the Eldritch.\n\n"
						+ "Perhaps more will be revealed in the future with furthur study.")
		};
		
		this.setPages(pages);
	}

}
