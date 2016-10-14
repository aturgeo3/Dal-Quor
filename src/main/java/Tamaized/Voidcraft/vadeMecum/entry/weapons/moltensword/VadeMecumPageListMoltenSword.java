package Tamaized.Voidcraft.vadeMecum.entry.weapons.moltensword;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.entry.weapons.moltensword.pages.VadeMecumPageMoltenSword1;
import Tamaized.Voidcraft.vadeMecum.entry.weapons.moltensword.pages.VadeMecumPageMoltenSword2;

public class VadeMecumPageListMoltenSword {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageMoltenSword1(),
				new VadeMecumPageMoltenSword2() };
	}

}
