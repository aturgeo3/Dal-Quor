package tamaized.voidcraft.common.vademecum.contents.documentation.items.voidicsteel;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListVoidicSteel implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.items.voidicSteel).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.voidicsteel"), new VadeMecumPageCrafting(new VadeMecumCraftingBlastFurnace(VoidCraft.modid + ".VadeMecum.recipe.blastfurnace", new ItemStack(VoidCraft.items.voidicSteel)))};
	}

}
