package Tamaized.Voidcraft.vadeMecum.contents.progression.ritualList;

import java.util.ArrayList;

import Tamaized.Voidcraft.VoidCraft;
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
		if (cap.hasCategory(IVadeMecumCapability.Category.INTRO)) {
			if (!cap.hasCategory(IVadeMecumCapability.Category.TOME)) {
				pages.add(new VadeMecumPageMultiBlock(IVadeMecumCapability.getCategoryName(IVadeMecumCapability.Category.TOME), VoidCraft.ritualList.getRitual(IVadeMecumCapability.Category.TOME)));
			} else {
				// Fire
				if (!cap.hasCategory(IVadeMecumCapability.Category.Flame)) {
					pages.add(new VadeMecumPageMultiBlock(IVadeMecumCapability.getCategoryName(IVadeMecumCapability.Category.Flame), VoidCraft.ritualList.getRitual(IVadeMecumCapability.Category.Flame)));
				} else {
					if (!cap.hasCategory(IVadeMecumCapability.Category.FireSheathe)) {
						pages.add(new VadeMecumPageMultiBlock(IVadeMecumCapability.getCategoryName(IVadeMecumCapability.Category.FireSheathe), VoidCraft.ritualList.getRitual(IVadeMecumCapability.Category.FireSheathe)));
					}
				}
				// Frost
				if (!cap.hasCategory(IVadeMecumCapability.Category.Freeze)) {
					pages.add(new VadeMecumPageMultiBlock(IVadeMecumCapability.getCategoryName(IVadeMecumCapability.Category.Freeze), VoidCraft.ritualList.getRitual(IVadeMecumCapability.Category.Freeze)));
				} else {
					if (!cap.hasCategory(IVadeMecumCapability.Category.FrostSheathe)) {
						pages.add(new VadeMecumPageMultiBlock(IVadeMecumCapability.getCategoryName(IVadeMecumCapability.Category.FrostSheathe), VoidCraft.ritualList.getRitual(IVadeMecumCapability.Category.FrostSheathe)));
					}
				}
				// Shock
				if (!cap.hasCategory(IVadeMecumCapability.Category.Shock)) {
					pages.add(new VadeMecumPageMultiBlock(IVadeMecumCapability.getCategoryName(IVadeMecumCapability.Category.Shock), VoidCraft.ritualList.getRitual(IVadeMecumCapability.Category.Shock)));
				} else {
					if (!cap.hasCategory(IVadeMecumCapability.Category.ShockSheathe)) {
						pages.add(new VadeMecumPageMultiBlock(IVadeMecumCapability.getCategoryName(IVadeMecumCapability.Category.ShockSheathe), VoidCraft.ritualList.getRitual(IVadeMecumCapability.Category.ShockSheathe)));
					}
				}
				// Acid
				if (!cap.hasCategory(IVadeMecumCapability.Category.AcidSpray)) {
					pages.add(new VadeMecumPageMultiBlock(IVadeMecumCapability.getCategoryName(IVadeMecumCapability.Category.AcidSpray), VoidCraft.ritualList.getRitual(IVadeMecumCapability.Category.AcidSpray)));
				} else {
					if (!cap.hasCategory(IVadeMecumCapability.Category.AcidSheathe)) {
						pages.add(new VadeMecumPageMultiBlock(IVadeMecumCapability.getCategoryName(IVadeMecumCapability.Category.AcidSheathe), VoidCraft.ritualList.getRitual(IVadeMecumCapability.Category.AcidSheathe)));
					}
				}
				// Void
				if (!cap.hasCategory(IVadeMecumCapability.Category.TOME)) {

				}
			}
		}
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
