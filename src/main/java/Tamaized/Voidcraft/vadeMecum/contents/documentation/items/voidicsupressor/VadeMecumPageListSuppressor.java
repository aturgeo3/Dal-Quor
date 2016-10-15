package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidicsupressor;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidicsupressor.pages.VadeMecumPageSuppressor1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidicsupressor.pages.VadeMecumPageSuppressor2;

public class VadeMecumPageListSuppressor {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageSuppressor1(),
				new VadeMecumPageSuppressor2()};
	}

}
