package tamaized.voidcraft.common.vademecum.contents.documentation.items.moltenvoidchainpart;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumCraftingFurnace;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import tamaized.voidcraft.common.vademecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMoltenChainPart implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.MoltenvoidChainPart).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.MoltenvoidChainPart"),
				new VadeMecumPageCrafting(new VadeMecumCraftingFurnace(VoidCraft.modid+".VadeMecum.recipe.furnace", new ItemStack(VoidCraft.items.MoltenvoidChainPart)))};
	}

}
