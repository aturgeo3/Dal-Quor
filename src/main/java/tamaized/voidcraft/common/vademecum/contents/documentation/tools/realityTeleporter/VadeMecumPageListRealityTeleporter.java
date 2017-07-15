package tamaized.voidcraft.common.vademecum.contents.documentation.tools.realityTeleporter;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListRealityTeleporter implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(new ItemStack(VoidCraft.items.realityTeleporter).getDisplayName(), VoidCraft.modid + ".VadeMecum.docs.desc.realityTeleporter.pg1"), new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.realityTeleporter.pg2"), new VadeMecumPageCrafting(new VadeMecumCraftingNormal(VoidCraft.modid + ".VadeMecum.recipe.normal", new ItemStack(VoidCraft.items.realityTeleporter)))};
	}

}
