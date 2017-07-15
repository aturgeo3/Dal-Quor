package tamaized.voidcraft.common.vademecum.contents.documentation.items.moltenvoidchainpart;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListMoltenChainPart implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.items.MoltenvoidChainPart).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.MoltenvoidChainPart"), new VadeMecumPageCrafting(new VadeMecumCraftingFurnace(VoidCraft.modid + ".VadeMecum.recipe.furnace", new ItemStack(VoidCraft.items.MoltenvoidChainPart)))};
	}

}
