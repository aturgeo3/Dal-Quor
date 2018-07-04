package tamaized.dalquor.common.vademecum.contents.progression.pages;

import net.minecraft.item.ItemStack;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.vademecum.IVadeMecumPage;
import tamaized.dalquor.common.vademecum.IVadeMecumPageProvider;
import tamaized.dalquor.common.vademecum.VadeMecumCraftingNormal;
import tamaized.dalquor.common.vademecum.VadeMecumPage;
import tamaized.dalquor.common.vademecum.VadeMecumPageCrafting;
import tamaized.dalquor.registry.ModBlocks;

public class VadeMecumPageListRitualBlock implements IVadeMecumPageProvider {

	@Override
	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{

				new VadeMecumPage("dalquor.VadeMecum.progression.title.ritualBlock", "dalquor.VadeMecum.progression.desc.ritualBlock"),

				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("dalquor.VadeMecum.recipe.normal", new ItemStack(ModBlocks.ritualBlock)))

		};
	}

}
