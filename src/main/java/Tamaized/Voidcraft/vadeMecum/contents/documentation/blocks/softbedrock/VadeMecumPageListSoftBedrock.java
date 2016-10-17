package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.softbedrock;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListSoftBedrock {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Soft Bedrock", "Upon entering the Void, you notice that the vast majority of your surroundings is comprised of Bedrock. But unlike the flat, rigid, and unyielding layer found in the overworld, this \"Soft\" Bedrock makes a clearly defined landscape and feels plush beneath your feet. It has the firmness of stone, so it could be used as a solid building block. Yet it also has the consistency of fertile soil and can be tilled with a strong enough tool."),
				new VadeMecumPage("", "Which presents the question could something actually grow in this dimension? Time to find out..."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Soft Bedrock Stairs", new ItemStack[] {
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						null,
						null,
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						null,
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock) }, new ItemStack(voidCraft.blocks.blockFakeBedrockStairs, 6))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Soft Bedrock Fence", new ItemStack[] {
						null,
						null,
						null,
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock) }, new ItemStack(voidCraft.blocks.blockFakeBedrockFence, 6))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Soft Bedrock Slab", new ItemStack[] {
						null,
						null,
						null,
						null,
						null,
						null,
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock) }, new ItemStack(voidCraft.blocks.blockFakeBedrockHalfSlab, 6))) };
	}

}
