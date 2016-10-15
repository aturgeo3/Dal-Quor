package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.infusionAltar;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.infusionAltar.pages.VadeMecumPageInfusionAltar1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.infusionAltar.pages.VadeMecumPageInfusionAltar2;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.infusionAltar.pages.VadeMecumPageInfusionAltar3;

public class VadeMecumPageListInfusionAltar {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageInfusionAltar1(),
				new VadeMecumPageInfusionAltar2(),
				new VadeMecumPageInfusionAltar3() };
	}

}
