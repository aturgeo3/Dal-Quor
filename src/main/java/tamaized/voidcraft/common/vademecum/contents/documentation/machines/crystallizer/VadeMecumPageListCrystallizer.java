package tamaized.voidcraft.common.vademecum.contents.documentation.machines.crystallizer;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListCrystallizer implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidicCrystallizer).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.voidicCrystallizer.pg1"), new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid + ".VadeMecum.recipe.normal", new ItemStack(VoidCraft.blocks.voidicCrystallizer, 1)))};
	}

}
