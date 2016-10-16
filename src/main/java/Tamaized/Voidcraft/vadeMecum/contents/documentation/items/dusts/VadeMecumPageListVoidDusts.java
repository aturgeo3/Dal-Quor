package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.dusts;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.dusts.pages.VadeMecumPageVoidDusts1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.dusts.pages.VadeMecumPageVoidDusts2;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.dusts.pages.VadeMecumPageVoidDusts3;

public class VadeMecumPageListVoidDusts {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageVoidDusts1(),
				new VadeMecumPageVoidDusts2(),
				new VadeMecumPageVoidDusts3() };
	}

}
