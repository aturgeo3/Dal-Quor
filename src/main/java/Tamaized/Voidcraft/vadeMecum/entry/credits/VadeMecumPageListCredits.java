package Tamaized.Voidcraft.vadeMecum.entry.credits;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.entry.voidicinfusion.pages.VadeMecumPageVoidicInfusion1;
import Tamaized.Voidcraft.vadeMecum.entry.voidicinfusion.pages.VadeMecumPageVoidicInfusion2;

public class VadeMecumPageListCredits {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageVoidicInfusion1(),
				new VadeMecumPageVoidicInfusion2() };
	}

}
