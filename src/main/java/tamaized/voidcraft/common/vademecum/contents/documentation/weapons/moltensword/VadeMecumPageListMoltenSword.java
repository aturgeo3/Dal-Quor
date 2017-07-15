package tamaized.voidcraft.common.vademecum.contents.documentation.weapons.moltensword;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListMoltenSword implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.tools.moltenSword).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.moltenSword"), new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid + ".VadeMecum.recipe.normal", new ItemStack(VoidCraft.tools.moltenSword)))};
	}

}
