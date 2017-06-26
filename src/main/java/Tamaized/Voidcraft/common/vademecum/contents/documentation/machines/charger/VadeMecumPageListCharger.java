package Tamaized.Voidcraft.common.vademecum.contents.documentation.machines.charger;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.common.vademecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListCharger implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidicCharger).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidicCharger.pg1"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.voidicCharger.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid+".VadeMecum.recipe.normal", new ItemStack(VoidCraft.blocks.voidicCharger, 1))) };
	}

}
