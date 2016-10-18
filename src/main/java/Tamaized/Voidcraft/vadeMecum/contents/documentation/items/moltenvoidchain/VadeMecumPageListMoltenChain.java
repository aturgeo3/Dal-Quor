package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.moltenvoidchain;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMoltenChain implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Molten Void Chain", "Molten Chain Parts are far too pliable for any real purpose. That is where the fully formed Molten Chains come into play. Combine these parts with Charred Bones and you get a sturdy malleable chain capable of permanently changing shape as needed unlike the parts which revert back into a ball form."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(voidCraft.items.burnBone),
						new ItemStack(voidCraft.items.MoltenvoidChainPart),
						new ItemStack(voidCraft.items.burnBone),
						new ItemStack(voidCraft.items.MoltenvoidChainPart),
						new ItemStack(voidCraft.items.burnBone),
						new ItemStack(voidCraft.items.MoltenvoidChainPart),
						new ItemStack(voidCraft.items.burnBone),
						new ItemStack(voidCraft.items.MoltenvoidChainPart),
						new ItemStack(voidCraft.items.burnBone) }, new ItemStack(voidCraft.items.MoltenvoidChain)))};
	}

}
