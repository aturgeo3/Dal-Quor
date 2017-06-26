package Tamaized.Voidcraft.common.vademecum.contents.documentation.items.voidicsteel;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.common.vademecum.VadeMecumCraftingBlastFurnace;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidicSteel implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.voidicSteel).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidicsteel"),
				new VadeMecumPageCrafting(new VadeMecumCraftingBlastFurnace(VoidCraft.modid+".VadeMecum.recipe.blastfurnace", new ItemStack(VoidCraft.items.voidicSteel)))};
	}

}
