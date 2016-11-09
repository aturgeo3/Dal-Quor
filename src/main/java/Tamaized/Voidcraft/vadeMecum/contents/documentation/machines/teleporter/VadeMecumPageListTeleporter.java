package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.teleporter;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListTeleporter implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.blocks.realityTeleporterBlock).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.realityTeleporterBlock.pg1"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.realityTeleporterBlock.pg2"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.realityTeleporterBlock.pg3"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.realityTeleporterBlock.pg4"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.items.emeraldDust),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.blocks.realityHole),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.voidicCharger),
						new ItemStack(voidCraft.blocks.blockVoidbrick) }, new ItemStack(voidCraft.blocks.realityTeleporterBlock, 1))) };
	}

}
