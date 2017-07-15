package tamaized.voidcraft.common.vademecum.contents.documentation.items.moltenvoidchain;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListMoltenChain implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.items.MoltenvoidChain).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.MoltenvoidChain"), new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid + ".VadeMecum.recipe.normal", new ItemStack(VoidCraft.items.MoltenvoidChain)))};
	}

}
