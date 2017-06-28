package tamaized.voidcraft.common.vademecum.contents.documentation.machines.macerator;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumCraftingInfuser;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import tamaized.voidcraft.common.vademecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMacerator implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidMacerator).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidMacerator"),
				new VadeMecumPageCrafting(new VadeMecumCraftingInfuser(VoidCraft.modid+".VadeMecum.recipe.infusion", new ItemStack(VoidCraft.blocks.voidMacerator, 1))) };
	}

}
