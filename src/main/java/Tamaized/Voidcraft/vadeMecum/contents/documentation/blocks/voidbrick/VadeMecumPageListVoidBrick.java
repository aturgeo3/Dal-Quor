package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidbrick;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidBrick {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Void Brick", "These bricks are made from cut Void Crystal Blocks. They appear to be much more sturdy than any other block found in this dimension, but the crystal that makes them up seems to have lost their conductivity for Voidic Power as a result of being cut. While their function as a material for building Void Portals has been lost, that doesn't mean that they're useless. Standing on one of these bricks will actually stall"),
				new VadeMecumPage("", "Voidic Infusion from being gained as if the bricks are trying to consume it from you at the same rate you gain it. Their firm frame could be used for making other objects, or simply for building and decoration."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(voidCraft.blocks.blockVoidcrystal),
						new ItemStack(voidCraft.blocks.blockVoidcrystal),
						null,
						new ItemStack(voidCraft.blocks.blockVoidcrystal),
						new ItemStack(voidCraft.blocks.blockVoidcrystal),
						null,
						null,
						null,
						null }, new ItemStack(voidCraft.blocks.blockVoidbrick, 1))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Void Brick Stairs", new ItemStack[] {
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						null,
						null,
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						null,
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick) }, new ItemStack(voidCraft.blocks.blockVoidstairs, 6))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Void Brick Fence", new ItemStack[] {
						null,
						null,
						null,
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick) }, new ItemStack(voidCraft.blocks.blockVoidfence, 6))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Void Brick Slab", new ItemStack[] {
						null,
						null,
						null,
						null,
						null,
						null,
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.blockVoidbrick) }, new ItemStack(voidCraft.blocks.blockVoidBrickHalfSlab, 6)))
		};
	}

}
