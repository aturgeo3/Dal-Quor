package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.charger;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.charger.pages.VadeMecumPageCharger1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.charger.pages.VadeMecumPageCharger2;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.charger.pages.VadeMecumPageCharger3;

public class VadeMecumPageListCharger {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageCharger1(),
				new VadeMecumPageCharger2(),
				new VadeMecumPageCharger3() };
	}

}
