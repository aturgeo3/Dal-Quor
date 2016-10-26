package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.angelicsword;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListAngelicSword implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Angelic Sword", "This unique sword does lower damage than the rest of the voidic sword but in turn it'll one-shot most of the voidic mobs."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.tools.voidSword),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm),
						new ItemStack(voidCraft.items.ectoplasm) }, new ItemStack(voidCraft.tools.angelicSword))) };
	}

}
