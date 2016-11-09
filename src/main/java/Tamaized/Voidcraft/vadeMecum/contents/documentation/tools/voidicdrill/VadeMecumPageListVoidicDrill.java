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
				new VadeMecumPage(new ItemStack(voidCraft.items.voidicDrill).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.voidicDrill"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
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
