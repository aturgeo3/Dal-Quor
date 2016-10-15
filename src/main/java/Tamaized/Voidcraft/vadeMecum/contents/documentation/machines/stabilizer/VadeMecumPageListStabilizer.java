package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.stabilizer;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.stabilizer.pages.VadeMecumPageStabilizer1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.stabilizer.pages.VadeMecumPageStabilizer2;

public class VadeMecumPageListStabilizer {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageStabilizer1(),
				new VadeMecumPageStabilizer2() };
	}

}
