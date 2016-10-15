package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.archangelicsword;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.archangelicsword.pages.VadeMecumPageArchSword1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.archangelicsword.pages.VadeMecumPageArchSword2;

public class VadeMecumPageListArchSword {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageArchSword1(),
				new VadeMecumPageArchSword2() };
	}

}
