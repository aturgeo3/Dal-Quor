package tamaized.voidcraft.common.vademecum.contents.documentation.bosses.herobrine;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;

public class VadeMecumPageListHerobrine implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.herobrine", VoidCraft.modid + ".VadeMecum.docs.desc.herobrine")};
	}

}
