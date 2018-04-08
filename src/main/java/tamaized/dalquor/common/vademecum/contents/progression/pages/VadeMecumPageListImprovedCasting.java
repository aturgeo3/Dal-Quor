package tamaized.dalquor.common.vademecum.contents.progression.pages;

import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.vademecum.IVadeMecumPage;
import tamaized.dalquor.common.vademecum.IVadeMecumPageProvider;
import tamaized.dalquor.common.vademecum.VadeMecumPage;

public class VadeMecumPageListImprovedCasting implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{

				new VadeMecumPage("dalquor.VadeMecum.progression.title.improvedCasting", "dalquor.VadeMecum.progression.desc.improvedCasting.pg1"),

				new VadeMecumPage("", "dalquor.VadeMecum.progression.desc.improvedCasting.pg2")};
	}

}
