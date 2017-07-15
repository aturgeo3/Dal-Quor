package tamaized.voidcraft.common.vademecum.contents.documentation.weapons.demonsword;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListDemonSword implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.tools.demonSword).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.demonSword"), new VadeMecumPageCrafting(new VadeMecumCraftingInfuser(VoidCraft.modid + ".VadeMecum.recipe.infusion", new ItemStack(VoidCraft.tools.demonSword)))};
	}

}
