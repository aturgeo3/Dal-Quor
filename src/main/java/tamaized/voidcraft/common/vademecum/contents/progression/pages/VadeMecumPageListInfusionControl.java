package tamaized.voidcraft.common.vademecum.contents.progression.pages;

import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;

import java.util.ArrayList;

public class VadeMecumPageListInfusionControl implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumPage> pages = new ArrayList<IVadeMecumPage>();
		pages.add(new VadeMecumPage("voidcraft.VadeMecum.progression.title.infusionControl", "voidcraft.VadeMecum.progression.desc.infusionControl.main"));
		for (IVadeMecumCapability.Passive passive : IVadeMecumCapability.Passive.values()) {
			if (cap.canHavePassive(passive)) {
				pages.add(new VadeMecumPage(IVadeMecumCapability.getPassiveName(passive), "voidcraft.VadeMecum.passive.".concat(passive.name()).concat(".desc")));
			}
		}
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
