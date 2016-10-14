package Tamaized.Voidcraft.vadeMecum.entry.thevoid;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.entry.thevoid.pages.VadeMecumPageVoid1;
import Tamaized.Voidcraft.vadeMecum.entry.thevoid.pages.VadeMecumPageVoid2;
import Tamaized.Voidcraft.vadeMecum.entry.thevoid.pages.VadeMecumPageVoid3;

public class VadeMecumPageListVoid {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageVoid1(),
				new VadeMecumPageVoid2(),
				new VadeMecumPageVoid3() };
	}

}
