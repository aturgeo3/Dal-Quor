package Tamaized.Voidcraft.common.vademecum.contents.progression.pages;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPage;

public class VadeMecumPageListDreams implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {

				new VadeMecumPage(VoidCraft.modid+".VadeMecum.progression.title.dreams", VoidCraft.modid+".VadeMecum.progression.desc.dreams.pg1"),

				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.progression.desc.dreams.pg2")

		};
	}

}
