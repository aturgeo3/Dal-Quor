package tamaized.voidcraft.common.vademecum.contents.documentation.machines.charger;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListCharger implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidicCharger).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.voidicCharger.pg1"), new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.voidicCharger.pg2"), new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid + ".VadeMecum.recipe.normal", new ItemStack(VoidCraft.blocks.voidicCharger, 1)))};
	}

}
