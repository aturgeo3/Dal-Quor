package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.moltenvoidchainpart;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingFurnace;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMoltenChainPart implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.items.MoltenvoidChainPart).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.MoltenvoidChainPart"),
				new VadeMecumPageCrafting(new VadeMecumCraftingFurnace(voidCraft.modid+".VadeMecum.recipe.furnace", new ItemStack(voidCraft.items.voidChain), new ItemStack(voidCraft.items.MoltenvoidChainPart)))};
	}

}
