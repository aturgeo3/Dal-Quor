package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.specteraxe;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListSpecterAxe {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Specter Axe", new ItemStack[] {
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.tools.voidAxe),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm) }, new ItemStack(voidCraft.tools.spectreAxe))) };
	}

}
