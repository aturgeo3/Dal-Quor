package tamaized.dalquor.common.vademecum.contents.progression.pages;

import net.minecraft.item.ItemStack;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.vademecum.*;

public class VadeMecumPageListRitualBlock implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{

				new VadeMecumPage("voidcraft.VadeMecum.progression.title.ritualBlock", "voidcraft.VadeMecum.progression.desc.ritualBlock"),

				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("voidcraft.VadeMecum.recipe.normal", new ItemStack(DalQuor.blocks.ritualBlock)))

		};
	}

}
