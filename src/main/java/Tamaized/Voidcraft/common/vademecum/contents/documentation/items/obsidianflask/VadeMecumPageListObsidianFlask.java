package tamaized.voidcraft.common.vademecum.contents.documentation.items.obsidianflask;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumCraftingNormal;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import tamaized.voidcraft.common.vademecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListObsidianFlask implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.emptyObsidianFlask).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.emptyObsidianFlask.pg1"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.emptyObsidianFlask.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid+".VadeMecum.recipe.normal", new ItemStack(VoidCraft.items.emptyObsidianFlask, 4))) };
	}

}
