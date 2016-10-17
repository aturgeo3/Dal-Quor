package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.specterpick;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListSpecterPick {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Specter Pickaxe", new ItemStack[] {
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
