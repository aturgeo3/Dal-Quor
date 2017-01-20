package Tamaized.Voidcraft.vadeMecum.contents.progression.ritualList;

import java.util.ArrayList;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageMultiBlock;
import Tamaized.Voidcraft.vadeMecum.progression.RitualList;

public class VadeMecumPageListRitualList implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumPage> pages = new ArrayList<IVadeMecumPage>();
		pages.add(new VadeMecumPage("Voidic Ritual List", "This page will display the available rituals that can be invoked to advance your Vade Mecum Knowledge"));
		if (cap.getObtainedCategories().contains(IVadeMecumCapability.Category.INTRO)) {
			if (!cap.getObtainedCategories().contains(IVadeMecumCapability.Category.TOME)) {
				pages.add(new VadeMecumPageMultiBlock("Words of Power", voidCraft.ritualList.getRitual(IVadeMecumCapability.Category.TOME)));
			} else {
				// Fire
				if (!cap.getObtainedCategories().contains(IVadeMecumCapability.Category.Flame)) {
					pages.add(new VadeMecumPageMultiBlock("Flame", voidCraft.ritualList.getRitual(IVadeMecumCapability.Category.Flame)));
				}
				// Frost
				if (!cap.getObtainedCategories().contains(IVadeMecumCapability.Category.Freeze)) {
					pages.add(new VadeMecumPageMultiBlock("Frost", voidCraft.ritualList.getRitual(IVadeMecumCapability.Category.Freeze)));
				}
				// Shock
				if (!cap.getObtainedCategories().contains(IVadeMecumCapability.Category.Shock)) {
					pages.add(new VadeMecumPageMultiBlock("Shock", voidCraft.ritualList.getRitual(IVadeMecumCapability.Category.Shock)));
				}
				// Acid
				if (!cap.getObtainedCategories().contains(IVadeMecumCapability.Category.AcidSpray)) {
					pages.add(new VadeMecumPageMultiBlock("Acid", voidCraft.ritualList.getRitual(IVadeMecumCapability.Category.AcidSpray)));
				}
				// Void
				if (!cap.getObtainedCategories().contains(IVadeMecumCapability.Category.TOME)) {

				}
			}
		}
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
