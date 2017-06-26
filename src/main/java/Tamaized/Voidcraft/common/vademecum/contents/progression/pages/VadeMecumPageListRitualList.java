package Tamaized.Voidcraft.common.vademecum.contents.progression.pages;

import java.util.ArrayList;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPageMultiBlock;
import Tamaized.Voidcraft.common.vademecum.progression.VadeMecumRitualHandler;
import Tamaized.Voidcraft.common.vademecum.progression.VadeMecumWordsOfPower;

public class VadeMecumPageListRitualList implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumPage> pages = new ArrayList<IVadeMecumPage>();
		pages.add(new VadeMecumPage("voidcraft.VadeMecum.progression.title.ritualList", "voidcraft.VadeMecum.progression.desc.ritualList"));
		for (IVadeMecumCapability.Category category : VadeMecumRitualHandler.getAvailiableRituals(cap))
			pages.add(new VadeMecumPageMultiBlock(VadeMecumWordsOfPower.getCategoryData(category).getName(), VoidCraft.ritualList.getRitual(category)));
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
