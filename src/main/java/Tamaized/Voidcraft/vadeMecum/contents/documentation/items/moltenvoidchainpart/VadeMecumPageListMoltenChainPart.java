package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.moltenvoidchainpart;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingFurnace;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMoltenChainPart {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Molten Void Chain Part", "When one smelts a Void Chain the result is interesting. It is a ball of molten chain in which one would think would burn as you touch it, but in fact it is cool to the touch. The only explanation for this would be the voidic properties."),
				new VadeMecumPageCrafting(new VadeMecumCraftingFurnace("Furnace Recipe", new ItemStack(voidCraft.items.voidChain), new ItemStack(voidCraft.items.MoltenvoidChainPart)))};
	}

}
