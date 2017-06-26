package Tamaized.Voidcraft.common.vademecum.contents.documentation.items.moltenvoidchainpart;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.common.vademecum.VadeMecumCraftingFurnace;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMoltenChainPart implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.MoltenvoidChainPart).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.MoltenvoidChainPart"),
				new VadeMecumPageCrafting(new VadeMecumCraftingFurnace(VoidCraft.modid+".VadeMecum.recipe.furnace", new ItemStack(VoidCraft.items.MoltenvoidChainPart)))};
	}

}
