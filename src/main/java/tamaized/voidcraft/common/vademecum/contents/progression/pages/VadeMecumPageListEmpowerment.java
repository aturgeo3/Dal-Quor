package tamaized.voidcraft.common.vademecum.contents.progression.pages;

import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;

public class VadeMecumPageListEmpowerment implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{

				new VadeMecumPage("voidcraft.VadeMecum.progression.title.empowerment", "voidcraft.VadeMecum.progression.desc.empowerment")

		};
	}

}
