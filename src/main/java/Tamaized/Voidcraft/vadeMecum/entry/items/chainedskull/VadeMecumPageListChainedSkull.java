package Tamaized.Voidcraft.vadeMecum.entry.items.chainedskull;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.entry.items.chainedskull.pages.VadeMecumPageChainedSkull1;
import Tamaized.Voidcraft.vadeMecum.entry.items.chainedskull.pages.VadeMecumPageChainedSkull2;

public class VadeMecumPageListChainedSkull {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageChainedSkull1(),
				new VadeMecumPageChainedSkull2()};
	}

}
