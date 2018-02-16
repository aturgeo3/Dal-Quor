package tamaized.dalquor.common.vademecum.contents.progression.pages;

import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.vademecum.IVadeMecumPage;
import tamaized.dalquor.common.vademecum.IVadeMecumPageProvider;
import tamaized.dalquor.common.vademecum.VadeMecumPage;

public class VadeMecumPageListTome implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage("voidcraft.VadeMecum.progression.title.tome", "voidcraft.VadeMecum.progression.desc.tome.pg1"), new VadeMecumPage("", "voidcraft.VadeMecum.progression.desc.tome.pg2")};
	}

}
