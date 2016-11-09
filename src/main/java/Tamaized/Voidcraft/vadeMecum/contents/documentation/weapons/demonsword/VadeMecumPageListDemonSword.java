package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.demonsword;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingInfuser;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListDemonSword implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.tools.demonSword).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.demonSword"),
				new VadeMecumPageCrafting(new VadeMecumCraftingInfuser(voidCraft.modid+".VadeMecum.recipe.infusion", new ItemStack(voidCraft.tools.archSword), new ItemStack(voidCraft.tools.demonSword))) };
	}

}
