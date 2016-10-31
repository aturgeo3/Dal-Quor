package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.spectrepick;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListSpectrePick implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Spectre Pickaxe", new ItemStack[] {
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.tools.voidPickaxe),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm) }, new ItemStack(voidCraft.tools.spectrePickaxe))) };
	}

}
