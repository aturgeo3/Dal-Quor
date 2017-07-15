package tamaized.voidcraft.common.vademecum.contents.documentation.machines.anchor;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListAnchor implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidicCharger).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.voidicAnchor.pg1"), new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.voidicAnchor.pg2"), new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid + ".VadeMecum.recipe.normal", new ItemStack(VoidCraft.blocks.voidicAnchor, 1)))};
	}

}
