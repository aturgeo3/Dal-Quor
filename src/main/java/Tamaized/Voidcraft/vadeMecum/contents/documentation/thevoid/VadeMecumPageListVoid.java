package Tamaized.Voidcraft.vadeMecum.contents.documentation.thevoid;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.thevoid.pages.VadeMecumPageVoid1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.thevoid.pages.VadeMecumPageVoid2;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.thevoid.pages.VadeMecumPageVoid3;

public class VadeMecumPageListVoid {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageVoid1(),
				new VadeMecumPageVoid2(),
				new VadeMecumPageVoid3() };
	}

}
