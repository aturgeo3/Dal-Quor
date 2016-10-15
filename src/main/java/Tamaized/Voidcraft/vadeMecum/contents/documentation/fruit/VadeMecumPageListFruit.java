package Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit.pages.VadeMecumPageFruit1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit.pages.VadeMecumPageFruit2;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit.pages.VadeMecumPageFruit3;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit.pages.VadeMecumPageFruit4;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit.pages.VadeMecumPageFruit5;

public class VadeMecumPageListFruit {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageFruit1(),
				new VadeMecumPageFruit2(),
				new VadeMecumPageFruit3(),
				new VadeMecumPageFruit4(),
				new VadeMecumPageFruit5() };
	}

}
