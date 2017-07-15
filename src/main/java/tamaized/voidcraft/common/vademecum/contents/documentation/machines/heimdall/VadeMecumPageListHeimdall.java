package tamaized.voidcraft.common.vademecum.contents.documentation.machines.heimdall;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListHeimdall implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.blocks.Heimdall).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.Heimdall.pg1"), new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.Heimdall.pg2"), new VadeMecumPageCrafting(new VadeMecumCraftingInfuser(VoidCraft.modid + ".VadeMecum.recipe.infusion", new ItemStack(VoidCraft.blocks.Heimdall, 1)))};
	}

}
