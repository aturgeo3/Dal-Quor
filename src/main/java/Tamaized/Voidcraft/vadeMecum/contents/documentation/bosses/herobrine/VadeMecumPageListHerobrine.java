package Tamaized.Voidcraft.vadeMecum.contents.documentation.bosses.herobrine;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListHerobrine implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(voidCraft.modid+".VadeMecum.docs.title.herobrine", voidCraft.modid+".VadeMecum.docs.desc.herobrine") };
	}

}
