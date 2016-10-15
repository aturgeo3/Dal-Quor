package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.demonsword;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.demonsword.pages.VadeMecumPageDemonSword1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.demonsword.pages.VadeMecumPageDemonSword2;

public class VadeMecumPageListDemonSword {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageDemonSword1(),
				new VadeMecumPageDemonSword2() };
	}

}
