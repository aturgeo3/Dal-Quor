package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.generator;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListGenerator implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.blocks.voidicGen).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.voidicGen.pg1"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.voidicGen.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(voidCraft.blocks.voidInfuser),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE) }, new ItemStack(voidCraft.blocks.voidicGen, 1))) };
	}

}
