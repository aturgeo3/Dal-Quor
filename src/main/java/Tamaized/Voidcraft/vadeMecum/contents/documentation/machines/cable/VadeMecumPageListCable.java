package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.cable;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.cable.pages.VadeMecumPageCable1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.cable.pages.VadeMecumPageCable2;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.cable.pages.VadeMecumPageCable3;

public class VadeMecumPageListCable {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageCable1(),
				new VadeMecumPageCable2(),
				new VadeMecumPageCable3() };
	}

}
