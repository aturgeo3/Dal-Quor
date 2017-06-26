package Tamaized.Voidcraft.common.vademecum.contents.documentation.machines.teleporter;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.common.vademecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPageCrafting;
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
