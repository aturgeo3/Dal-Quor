package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.charger;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListCharger {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Voidic Charger", "End Crystals are perhaps the most mysterious devices in the known universe. Aside from the fact that they are fragile and easily broken, all that is known of them is that they feed some sort of energy to the Ender Dragon to heal it. However, that is all you need to know to use them for your own purposes. By placing a Void Star in the center of an End Crystal, wrapping it in Void Cloth, and fortifying it with Void Infused Diamond Dust, one creates a Voidic"),
				new VadeMecumPage("", "Charger. A device capable of charging certain items with Voidic Power. However, it should be noted that, due to its shape, it can only take in power from the Bottom. So plan accordingly when making your network of Voidic Generators and Void Cables."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(voidCraft.items.diamondDust),
						new ItemStack(voidCraft.items.voidStar),
						new ItemStack(voidCraft.items.diamondDust),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(Items.END_CRYSTAL),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.items.diamondDust),
						new ItemStack(voidCraft.blocks.voidicCable),
						new ItemStack(voidCraft.items.diamondDust) }, new ItemStack(voidCraft.blocks.voidicCharger, 1))) };
	}

}
