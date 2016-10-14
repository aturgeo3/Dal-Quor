package Tamaized.Voidcraft.vadeMecum.entry.items.dusts;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.entry.items.dusts.pages.VadeMecumPageVoidDusts1;
import Tamaized.Voidcraft.vadeMecum.entry.items.dusts.pages.VadeMecumPageVoidDusts2;

public class VadeMecumPageListVoidDusts {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageVoidDusts1(),
				new VadeMecumPageVoidDusts2() };
	}

}
