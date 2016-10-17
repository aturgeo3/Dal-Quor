package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.moltensword;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMoltenSword {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Molten Sword", "Foes struck by this sword are lit on fire and are inflicted with a weakness and nausea debuff for 5 seconds."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.tools.chainSword),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.MoltenvoidChain) }, new ItemStack(voidCraft.tools.moltenSword))) };
	}

}
