package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.charger;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListCharger implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.blocks.voidicCharger).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.voidicCharger.pg1"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.voidicCharger.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
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
