package Tamaized.Voidcraft.vadeMecum.contents.documentation.voidicinfusion;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.voidicinfusion.pages.VadeMecumPageVoidicInfusion1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.voidicinfusion.pages.VadeMecumPageVoidicInfusion2;

public class VadeMecumPageListVoidicInfusion {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageVoidicInfusion1(),
				new VadeMecumPageVoidicInfusion2() };
	}

}
