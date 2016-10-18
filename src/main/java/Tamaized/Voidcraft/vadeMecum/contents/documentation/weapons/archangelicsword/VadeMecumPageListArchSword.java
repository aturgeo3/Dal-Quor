package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.archangelicsword;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListArchSword implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Arch-Angelic Sword", "Foes struck by this sword are dealt with nausea, blindness, poison, wither, weakness and are lit on fire. These effects last for about 10 seconds."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.tools.chainSword),
						new ItemStack(voidCraft.tools.angelicSword),
						new ItemStack(voidCraft.tools.moltenSword),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.MoltenvoidChain) }, new ItemStack(voidCraft.tools.archSword))) };
	}

}
