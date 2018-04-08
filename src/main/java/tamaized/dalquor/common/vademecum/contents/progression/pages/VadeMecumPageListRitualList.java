package tamaized.dalquor.common.vademecum.contents.progression.pages;

import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.vademecum.IVadeMecumPage;
import tamaized.dalquor.common.vademecum.IVadeMecumPageProvider;
import tamaized.dalquor.common.vademecum.VadeMecumPage;
import tamaized.dalquor.common.vademecum.VadeMecumPageMultiBlock;
import tamaized.dalquor.common.vademecum.progression.VadeMecumRitualHandler;
import tamaized.dalquor.common.vademecum.progression.VadeMecumWordsOfPower;

import java.util.ArrayList;

public class VadeMecumPageListRitualList implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumPage> pages = new ArrayList<IVadeMecumPage>();
		pages.add(new VadeMecumPage("voidcraft.VadeMecum.progression.title.ritualList", "voidcraft.VadeMecum.progression.desc.ritualList"));
		for (IVadeMecumCapability.Category category : VadeMecumRitualHandler.getAvailiableRituals(cap))
			pages.add(new VadeMecumPageMultiBlock(VadeMecumWordsOfPower.getCategoryData(category).getName(), DalQuor.ritualList.getRitual(category)));
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
