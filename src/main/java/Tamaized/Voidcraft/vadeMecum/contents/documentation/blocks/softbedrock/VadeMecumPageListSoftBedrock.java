package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.softbedrock;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListSoftBedrock implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.blocks.blockFakeBedrock).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.blockFakeBedrock.pg1"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.blockFakeBedrock.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(voidCraft.blocks.blockFakeBedrockStairs).getDisplayName(), new ItemStack[] {
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						null,
						null,
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						null,
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock) }, new ItemStack(voidCraft.blocks.blockFakeBedrockStairs, 6))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(voidCraft.blocks.blockFakeBedrockFence).getDisplayName(), new ItemStack[] {
						null,
						null,
						null,
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock),
						new ItemStack(voidCraft.blocks.blockFakeBedrock) }, new ItemStack(voidCraft.blocks.blockFakeBedrockFence, 6))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(voidCraft.blocks.blockFakeBedrockHalfSlab).getDisplayName(), new ItemStack[] {
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
