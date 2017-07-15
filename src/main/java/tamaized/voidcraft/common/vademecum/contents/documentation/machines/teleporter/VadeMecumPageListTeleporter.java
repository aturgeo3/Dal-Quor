package tamaized.voidcraft.common.vademecum.contents.documentation.machines.teleporter;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListTeleporter implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.blocks.realityTeleporterBlock).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.realityTeleporterBlock.pg1"), new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.realityTeleporterBlock.pg2"), new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.realityTeleporterBlock.pg3"), new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.realityTeleporterBlock.pg4"), new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid + ".VadeMecum.recipe.normal", new ItemStack(VoidCraft.blocks.realityTeleporterBlock, 1)))};
	}

}
