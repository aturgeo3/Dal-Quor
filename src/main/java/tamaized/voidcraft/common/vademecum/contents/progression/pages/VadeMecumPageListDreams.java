package tamaized.voidcraft.common.vademecum.contents.progression.pages;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;

public class VadeMecumPageListDreams implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.progression.title.dreams", VoidCraft.modid + ".VadeMecum.progression.desc.dreams.pg1"),

				new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.progression.desc.dreams.pg2")

		};
	}

}
