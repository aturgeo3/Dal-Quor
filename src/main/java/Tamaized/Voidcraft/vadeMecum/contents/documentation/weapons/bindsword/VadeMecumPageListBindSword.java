package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.bindsword;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.bindsword.pages.VadeMecumPageBindSword1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.bindsword.pages.VadeMecumPageBindSword2;

public class VadeMecumPageListBindSword {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageBindSword1(),
				new VadeMecumPageBindSword2() };
	}

}
