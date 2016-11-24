package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.voidsword;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidSword implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(voidCraft.tools.voidSword).getDisplayName(), new ItemStack[] {
						ItemStack.EMPTY,
						new ItemStack(voidCraft.items.voidcrystal),
						ItemStack.EMPTY,
						new ItemStack(Blocks.OBSIDIAN),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(Blocks.OBSIDIAN),
						ItemStack.EMPTY,
						new ItemStack(Items.DIAMOND),
						ItemStack.EMPTY }, new ItemStack(voidCraft.tools.voidSword))) };
	}

}
