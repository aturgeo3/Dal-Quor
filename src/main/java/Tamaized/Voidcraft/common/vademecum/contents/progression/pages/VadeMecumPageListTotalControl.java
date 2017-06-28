package tamaized.voidcraft.common.vademecum.contents.progression.pages;

import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;

public class VadeMecumPageListTotalControl implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {

				new VadeMecumPage("voidcraft.VadeMecum.progression.title.totalControl", "voidcraft.VadeMecum.progression.desc.totalControl.pg1"),

				new VadeMecumPage("", "voidcraft.VadeMecum.progression.desc.totalControl.pg2")

		};
	}

}
