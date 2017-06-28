package tamaized.voidcraft.common.vademecum.contents.documentation.mobs.wrath;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;

public class VadeMecumPageListVoidWrath implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(VoidCraft.modid+".VadeMecum.docs.title.voidWrath", VoidCraft.modid+".VadeMecum.docs.desc.voidWrath") };
	}

}
