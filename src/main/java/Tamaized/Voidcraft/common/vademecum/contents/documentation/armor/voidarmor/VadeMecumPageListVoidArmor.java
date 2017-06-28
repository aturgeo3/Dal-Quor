package tamaized.voidcraft.common.vademecum.contents.documentation.armor.voidarmor;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumCraftingNormal;
import tamaized.voidcraft.common.vademecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidArmor implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.armors.voidHelmet).getDisplayName(), new ItemStack(VoidCraft.armors.voidHelmet))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.armors.voidChest).getDisplayName(), new ItemStack(VoidCraft.armors.voidChest))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.armors.voidLegs).getDisplayName(), new ItemStack(VoidCraft.armors.voidLegs))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.armors.voidBoots).getDisplayName(), new ItemStack(VoidCraft.armors.voidBoots)))
				};
	}

}
