package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.softbedrock;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.softbedrock.pages.VadeMecumPageSoftBedrock1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.softbedrock.pages.VadeMecumPageSoftBedrock2;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.softbedrock.pages.VadeMecumPageSoftBedrock3;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.softbedrock.pages.VadeMecumPageSoftBedrock4;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.softbedrock.pages.VadeMecumPageSoftBedrock5;

public class VadeMecumPageListSoftBedrock {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageSoftBedrock1(),
				new VadeMecumPageSoftBedrock2(),
				new VadeMecumPageSoftBedrock3(),
				new VadeMecumPageSoftBedrock4(),
				new VadeMecumPageSoftBedrock5() };
	}

}
