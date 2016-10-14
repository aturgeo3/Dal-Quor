package Tamaized.Voidcraft.vadeMecum.entry.armor.voidarmor;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.entry.armor.voidarmor.pages.VadeMecumPageVoidArmor1;
import Tamaized.Voidcraft.vadeMecum.entry.armor.voidarmor.pages.VadeMecumPageVoidArmor2;
import Tamaized.Voidcraft.vadeMecum.entry.armor.voidarmor.pages.VadeMecumPageVoidArmor3;
import Tamaized.Voidcraft.vadeMecum.entry.armor.voidarmor.pages.VadeMecumPageVoidArmor4;

public class VadeMecumPageListVoidArmor {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageVoidArmor1(),
				new VadeMecumPageVoidArmor2(),
				new VadeMecumPageVoidArmor3(),
				new VadeMecumPageVoidArmor4() };
	}

}
