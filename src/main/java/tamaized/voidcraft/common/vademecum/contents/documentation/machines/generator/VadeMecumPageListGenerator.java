package tamaized.voidcraft.common.vademecum.contents.documentation.machines.generator;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListGenerator implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidicGen).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.voidicGen.pg1"), new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.voidicGen.pg2"), new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid + ".VadeMecum.recipe.normal", new ItemStack(VoidCraft.blocks.voidicGen, 1)))};
	}

}
