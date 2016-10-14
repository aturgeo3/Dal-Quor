package Tamaized.Voidcraft.vadeMecum.entry.machines.generator;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.entry.machines.generator.pages.VadeMecumPageGenerator1;
import Tamaized.Voidcraft.vadeMecum.entry.machines.generator.pages.VadeMecumPageGenerator2;
import Tamaized.Voidcraft.vadeMecum.entry.machines.generator.pages.VadeMecumPageGenerator3;

public class VadeMecumPageListGenerator {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageGenerator1(),
				new VadeMecumPageGenerator2(),
				new VadeMecumPageGenerator3() };
	}

}
