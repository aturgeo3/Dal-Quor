package Tamaized.Voidcraft.vadeMecum.entry.items.moltenvoidchain;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.entry.items.moltenvoidchain.pages.VadeMecumPageMoltenChain1;
import Tamaized.Voidcraft.vadeMecum.entry.items.moltenvoidchain.pages.VadeMecumPageMoltenChain2;

public class VadeMecumPageListMoltenChain {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageMoltenChain1(),
				new VadeMecumPageMoltenChain2()};
	}

}
