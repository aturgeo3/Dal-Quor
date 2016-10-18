package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidicdrill;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidicDrill implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Voidic Drill", "One of the few items that requires Voidic Power to function. Charge this device using a Voidic Charger. If this device contains power and it is being used via holding down right click, it will emit a beacon like laser that mines in a 3x3 area and has a range of up to 10 blocks. This laser will also deal damage to entites."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(voidCraft.blocks.blockVoidcrystal),
						new ItemStack(voidCraft.blocks.realityHole),
						new ItemStack(voidCraft.blocks.blockVoidcrystal),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.blocks.voidicCharger),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.voidStar),
						new ItemStack(voidCraft.items.ectoplasm) }, new ItemStack(voidCraft.items.voidicDrill)))};
	}

}
