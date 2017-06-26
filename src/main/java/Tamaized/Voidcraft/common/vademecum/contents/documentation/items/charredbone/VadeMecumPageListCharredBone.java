package Tamaized.Voidcraft.common.vademecum.contents.documentation.items.charredbone;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPage;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListCharredBone implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.items.burnBone).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.burnBone")};
	}

}
