package Tamaized.Voidcraft.common.vademecum.contents.documentation.blocks.softbedrock;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.common.vademecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPageCrafting;
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
