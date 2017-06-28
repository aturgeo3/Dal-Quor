package tamaized.voidcraft.common.vademecum.contents.documentation.items.voidicsteel;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumCraftingBlastFurnace;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import tamaized.voidcraft.common.vademecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidicSteel implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.voidicSteel).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidicsteel"),
				new VadeMecumPageCrafting(new VadeMecumCraftingBlastFurnace(VoidCraft.modid+".VadeMecum.recipe.blastfurnace", new ItemStack(VoidCraft.items.voidicSteel)))};
	}

}
