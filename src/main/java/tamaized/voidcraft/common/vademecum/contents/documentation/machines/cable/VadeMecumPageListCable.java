package tamaized.voidcraft.common.vademecum.contents.documentation.machines.cable;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListCable implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidicCable).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.voidicCable.pg1"), new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.voidicCable.pg2"), new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid + ".VadeMecum.recipe.normal", new ItemStack(VoidCraft.blocks.voidicCable, 8)))};
	}

}
