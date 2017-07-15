package tamaized.voidcraft.common.vademecum.contents.documentation.machines.blastfurnace;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListBlastFurnace implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.blocks.voidBlastFurnace).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.blastfurnace"), new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid + ".VadeMecum.recipe.normal", new ItemStack(VoidCraft.blocks.voidBlastFurnace, 1)))};
	}

}
