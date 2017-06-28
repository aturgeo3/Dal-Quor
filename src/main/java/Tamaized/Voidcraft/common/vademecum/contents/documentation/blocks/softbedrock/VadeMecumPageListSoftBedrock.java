package tamaized.voidcraft.common.vademecum.contents.documentation.blocks.softbedrock;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumCraftingNormal;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import tamaized.voidcraft.common.vademecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListSoftBedrock implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.blockFakeBedrock).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.blockFakeBedrock.pg1"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.blockFakeBedrock.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.blocks.blockFakeBedrockStairs).getDisplayName(), new ItemStack(VoidCraft.blocks.blockFakeBedrockStairs, 6))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.blocks.blockFakeBedrockFence).getDisplayName(), new ItemStack(VoidCraft.blocks.blockFakeBedrockFence, 6))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(VoidCraft.blocks.blockFakeBedrockHalfSlab).getDisplayName(), new ItemStack(VoidCraft.blocks.blockFakeBedrockHalfSlab, 6))) };
	}

}
