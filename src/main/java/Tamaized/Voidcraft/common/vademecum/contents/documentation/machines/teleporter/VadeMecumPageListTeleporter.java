package tamaized.voidcraft.common.vademecum.contents.documentation.machines.teleporter;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumCraftingNormal;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import tamaized.voidcraft.common.vademecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListTeleporter implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.realityTeleporterBlock).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.realityTeleporterBlock.pg1"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.realityTeleporterBlock.pg2"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.realityTeleporterBlock.pg3"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.realityTeleporterBlock.pg4"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid+".VadeMecum.recipe.normal", new ItemStack(VoidCraft.blocks.realityTeleporterBlock, 1))) };
	}

}
