package tamaized.voidcraft.common.vademecum.contents.documentation.machines.alchemy;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListAlchemy implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidicAlchemyTable).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.voidicAlchemyTable.pg1"), new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.voidicAlchemyTable.pg2"), new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid + ".VadeMecum.recipe.normal", new ItemStack(VoidCraft.blocks.voidicAlchemyTable, 1)))};
	}

}
