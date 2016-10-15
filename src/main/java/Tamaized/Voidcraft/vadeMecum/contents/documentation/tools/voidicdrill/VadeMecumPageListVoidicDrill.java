package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidicdrill;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidicdrill.pages.VadeMecumPageVoidicDrill1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidicdrill.pages.VadeMecumPageVoidicDrill2;

public class VadeMecumPageListVoidicDrill {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageVoidicDrill1(),
				new VadeMecumPageVoidicDrill2()};
	}

}
