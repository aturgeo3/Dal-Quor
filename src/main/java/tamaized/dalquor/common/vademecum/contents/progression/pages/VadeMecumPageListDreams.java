package tamaized.dalquor.common.vademecum.contents.progression.pages;

import tamaized.dalquor.VoidCraft;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.vademecum.IVadeMecumPage;
import tamaized.dalquor.common.vademecum.IVadeMecumPageProvider;
import tamaized.dalquor.common.vademecum.VadeMecumPage;

public class VadeMecumPageListDreams implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{

				new VadeMecumPage(VoidCraft.modid + ".VadeMecum.progression.title.dreams", VoidCraft.modid + ".VadeMecum.progression.desc.dreams.pg1"),

				new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.progression.desc.dreams.pg2")

		};
	}

}
