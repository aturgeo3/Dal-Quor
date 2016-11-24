package Tamaized.Voidcraft.vadeMecum.contents.documentation.armor.voidarmor;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidArmor implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(voidCraft.armors.voidHelmet).getDisplayName(), new ItemStack[] {
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						ItemStack.EMPTY,
						new ItemStack(voidCraft.items.voidcrystal),
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						ItemStack.EMPTY }, new ItemStack(voidCraft.armors.voidHelmet))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(voidCraft.armors.voidChest).getDisplayName(), new ItemStack[] {
						new ItemStack(voidCraft.items.voidcrystal),
						ItemStack.EMPTY,
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal) }, new ItemStack(voidCraft.armors.voidChest))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(voidCraft.armors.voidLegs).getDisplayName(), new ItemStack[] {
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						ItemStack.EMPTY,
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						ItemStack.EMPTY,
						new ItemStack(voidCraft.items.voidcrystal) }, new ItemStack(voidCraft.armors.voidLegs))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(voidCraft.armors.voidBoots).getDisplayName(), new ItemStack[] {
						new ItemStack(voidCraft.items.voidcrystal),
						ItemStack.EMPTY,
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						ItemStack.EMPTY,
						new ItemStack(voidCraft.items.voidcrystal),
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						ItemStack.EMPTY }, new ItemStack(voidCraft.armors.voidBoots)))
				};
	}

}
