package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.bindsword;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListBindSword {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Sword of Binding", "Foes struck with this sword are inflicted with a 5 second slowness effect."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.tools.voidSword),
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.items.voidChain) }, new ItemStack(voidCraft.tools.chainSword))) };
	}

}
