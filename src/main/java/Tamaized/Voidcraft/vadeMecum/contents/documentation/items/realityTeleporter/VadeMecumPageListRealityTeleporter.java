package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.realityTeleporter;

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
				new VadeMecumPage("Reality Teleporter", "The Reality Teleporter is a device which when powered with Voidic Power and contains at least one Hole In Reality, it will teleport the user up to a maximum distance of 64 blocks away. If the user is looking at a block they will be sent in front of that block instead. The 64 range limit is still true for looking at blocks."),
				new VadeMecumPage("", "Like all Voidic Powered items, this can be charged using a Voidic Charger. To access the GUI you can Shift Right click at any time, if the device lacks enough resources the GUI will open on its own upon right clicking. This uses 200 power and 1 Hole in Reality per use."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
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
