package tamaized.voidcraft.common.vademecum.contents.documentation.items.voidcrystal;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidCrystal implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.voidcrystal).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidcrystal") };
	}

}
