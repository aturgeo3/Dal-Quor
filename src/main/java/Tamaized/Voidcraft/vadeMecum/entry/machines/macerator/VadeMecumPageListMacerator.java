package Tamaized.Voidcraft.vadeMecum.entry.machines.macerator;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.entry.machines.macerator.pages.VadeMecumPageMacerator1;
import Tamaized.Voidcraft.vadeMecum.entry.machines.macerator.pages.VadeMecumPageMacerator2;

public class VadeMecumPageListMacerator {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageMacerator1(),
				new VadeMecumPageMacerator2() };
	}

}
