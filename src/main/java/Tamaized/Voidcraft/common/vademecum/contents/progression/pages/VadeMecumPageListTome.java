package Tamaized.Voidcraft.common.vademecum.contents.progression.pages;

import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPage;

public class VadeMecumPageListTome implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("voidcraft.VadeMecum.progression.title.tome", "voidcraft.VadeMecum.progression.desc.tome.pg1"),
				new VadeMecumPage("", "voidcraft.VadeMecum.progression.desc.tome.pg2")
				};
	}

}
