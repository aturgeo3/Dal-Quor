package tamaized.dalquor.common.vademecum.contents.progression.pages;

import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.vademecum.IVadeMecumPage;
import tamaized.dalquor.common.vademecum.IVadeMecumPageProvider;
import tamaized.dalquor.common.vademecum.VadeMecumPage;

import java.util.ArrayList;

public class VadeMecumPageListInfusionControl implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumPage> pages = new ArrayList<IVadeMecumPage>();
		pages.add(new VadeMecumPage("dalquor.VadeMecum.progression.title.infusionControl", "dalquor.VadeMecum.progression.desc.infusionControl.main"));
		for (IVadeMecumCapability.Passive passive : IVadeMecumCapability.Passive.values()) {
			if (cap.canHavePassive(passive)) {
				pages.add(new VadeMecumPage(IVadeMecumCapability.getPassiveName(passive), "dalquor.VadeMecum.passive.".concat(passive.name()).concat(".desc")));
			}
		}
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
