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
				new VadeMecumPage(new ItemStack(voidCraft.items.MoltenvoidChain).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.MoltenvoidChain"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
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
