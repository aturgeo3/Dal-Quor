package tamaized.voidcraft.common.vademecum.contents.progression.pages;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import tamaized.voidcraft.common.vademecum.VadeMecumPageMultiBlock;
import tamaized.voidcraft.common.vademecum.progression.VadeMecumRitualHandler;
import tamaized.voidcraft.common.vademecum.progression.VadeMecumWordsOfPower;

import java.util.ArrayList;

public class VadeMecumPageListRitualList implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumPage> pages = new ArrayList<IVadeMecumPage>();
		pages.add(new VadeMecumPage("voidcraft.VadeMecum.progression.title.ritualList", "voidcraft.VadeMecum.progression.desc.ritualList"));
		for (IVadeMecumCapability.Category category : VadeMecumRitualHandler.getAvailiableRituals(cap))
			pages.add(new VadeMecumPageMultiBlock(VadeMecumWordsOfPower.getCategoryData(category).getName(), VoidCraft.ritualList.getRitual(category)));
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
