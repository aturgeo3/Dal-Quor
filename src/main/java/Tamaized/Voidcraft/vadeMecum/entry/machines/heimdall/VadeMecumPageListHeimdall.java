package Tamaized.Voidcraft.vadeMecum.entry.machines.heimdall;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.entry.machines.heimdall.pages.VadeMecumPageHeimdall1;
import Tamaized.Voidcraft.vadeMecum.entry.machines.heimdall.pages.VadeMecumPageHeimdall2;
import Tamaized.Voidcraft.vadeMecum.entry.machines.heimdall.pages.VadeMecumPageHeimdall3;

public class VadeMecumPageListHeimdall {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageHeimdall1(),
				new VadeMecumPageHeimdall2(),
				new VadeMecumPageHeimdall3() };
	}

}
