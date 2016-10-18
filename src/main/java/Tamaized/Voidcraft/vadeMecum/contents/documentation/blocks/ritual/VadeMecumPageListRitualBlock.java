package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.ritual;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListRitualBlock implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Voidic Ritual Block", "This block is used to invoke rituals to empower the Void Vade Mecum"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(Blocks.STONEBRICK),
						new ItemStack(Blocks.STONEBRICK) }, new ItemStack(voidCraft.blocks.ritualBlock, 8)))
				};
	}

}
