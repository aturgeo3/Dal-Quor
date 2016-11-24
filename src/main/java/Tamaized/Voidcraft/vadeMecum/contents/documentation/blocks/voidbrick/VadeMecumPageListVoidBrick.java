package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidbrick;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidBrick implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.blocks.blockVoidbrick).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.blockVoidbrick.pg1"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.blockVoidbrick.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(voidCraft.blocks.blockVoidcrystal),
						new ItemStack(voidCraft.blocks.blockVoidcrystal),
						ItemStack.EMPTY,
						new ItemStack(voidCraft.blocks.blockVoidcrystal),
						new ItemStack(voidCraft.blocks.blockVoidcrystal),
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						ItemStack.EMPTY }, new ItemStack(voidCraft.blocks.blockVoidbrick, 1))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(voidCraft.blocks.blockVoidstairs).getDisplayName(), new ItemStack[] {
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						ItemStack.EMPTY,
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick) }, new ItemStack(voidCraft.blocks.blockVoidstairs, 6))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(voidCraft.blocks.blockVoidfence).getDisplayName(), new ItemStack[] {
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick) }, new ItemStack(voidCraft.blocks.blockVoidfence, 6))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(voidCraft.blocks.blockVoidBrickHalfSlab).getDisplayName(), new ItemStack[] {
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						ItemStack.EMPTY,
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick) }, new ItemStack(voidCraft.blocks.blockVoidBrickHalfSlab, 6)))
		};
	}

}
