package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidcrystalblock;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidCrystalBlock implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.blocks.blockVoidcrystal).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.voidCrystalBlock.pg1"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.voidCrystalBlock.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal) }, new ItemStack(voidCraft.blocks.blockVoidcrystal))) };
	}

}
