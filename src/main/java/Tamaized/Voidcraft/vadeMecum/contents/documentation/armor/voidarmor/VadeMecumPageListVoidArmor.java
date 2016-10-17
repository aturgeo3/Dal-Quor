package Tamaized.Voidcraft.vadeMecum.contents.documentation.armor.voidarmor;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidArmor {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Void Helmet", new ItemStack[] {
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						null,
						new ItemStack(voidCraft.items.voidcrystal),
						null,
						null,
						null }, new ItemStack(voidCraft.armors.voidHelmet))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Void Chestplate", new ItemStack[] {
						new ItemStack(voidCraft.items.voidcrystal),
						null,
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal) }, new ItemStack(voidCraft.armors.voidChest))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Void Leggings", new ItemStack[] {
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						null,
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						null,
						new ItemStack(voidCraft.items.voidcrystal) }, new ItemStack(voidCraft.armors.voidLegs))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Void Boots", new ItemStack[] {
						new ItemStack(voidCraft.items.voidcrystal),
						null,
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						null,
						new ItemStack(voidCraft.items.voidcrystal),
						null,
						null,
						null }, new ItemStack(voidCraft.armors.voidBoots)))
				};
	}

}
