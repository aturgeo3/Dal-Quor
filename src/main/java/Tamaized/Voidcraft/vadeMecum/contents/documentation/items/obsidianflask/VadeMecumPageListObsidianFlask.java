package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.obsidianflask;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.obsidianflask.pages.VadeMecumPageObsidianFlask1;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.obsidianflask.pages.VadeMecumPageObsidianFlask2;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.obsidianflask.pages.VadeMecumPageObsidianFlask3;

public class VadeMecumPageListObsidianFlask {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageObsidianFlask1(),
				new VadeMecumPageObsidianFlask2(),
				new VadeMecumPageObsidianFlask3() };
	}

}
