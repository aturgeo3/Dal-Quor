package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.realityTeleporter;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListRealityTeleporter implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.items.realityTeleporter).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.realityTeleporter.pg1"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.realityTeleporter.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(voidCraft.items.burnBone),
						new ItemStack(voidCraft.items.emeraldDust),
						new ItemStack(voidCraft.blocks.voidicCharger),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.blocks.realityHole),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.burnBone),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.burnBone) }, new ItemStack(voidCraft.items.realityTeleporter)))};
	}

}
