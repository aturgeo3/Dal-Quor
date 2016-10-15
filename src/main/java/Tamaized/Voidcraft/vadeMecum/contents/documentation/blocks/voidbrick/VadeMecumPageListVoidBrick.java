package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidbrick;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidbrick.pages.VadeMecumPageVoidBrick1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidbrick.pages.VadeMecumPageVoidBrick2;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidbrick.pages.VadeMecumPageVoidBrick3;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidbrick.pages.VadeMecumPageVoidBrick4;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidbrick.pages.VadeMecumPageVoidBrick5;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidbrick.pages.VadeMecumPageVoidBrick6;

public class VadeMecumPageListVoidBrick {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageVoidBrick1(),
				new VadeMecumPageVoidBrick2(),
				new VadeMecumPageVoidBrick3(),
				new VadeMecumPageVoidBrick4(),
				new VadeMecumPageVoidBrick5(),
				new VadeMecumPageVoidBrick6() };
	}

}
