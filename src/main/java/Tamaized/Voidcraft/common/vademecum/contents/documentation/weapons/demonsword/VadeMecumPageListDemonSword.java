package tamaized.voidcraft.common.vademecum.contents.documentation.weapons.demonsword;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumCraftingInfuser;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import tamaized.voidcraft.common.vademecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListDemonSword implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.tools.demonSword).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.demonSword"),
				new VadeMecumPageCrafting(new VadeMecumCraftingInfuser(VoidCraft.modid+".VadeMecum.recipe.infusion", new ItemStack(VoidCraft.tools.demonSword))) };
	}

}
