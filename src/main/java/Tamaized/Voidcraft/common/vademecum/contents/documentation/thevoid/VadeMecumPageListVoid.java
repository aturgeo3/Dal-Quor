package Tamaized.Voidcraft.common.vademecum.contents.documentation.thevoid;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPage;

public class VadeMecumPageListVoid implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(VoidCraft.modid+".VadeMecum.docs.title.theVoid", VoidCraft.modid+".VadeMecum.docs.desc.theVoid.pg1"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.theVoid.pg2"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.theVoid.pg3"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.theVoid.pg4"),
				new VadeMecumPage("", VoidCraft.modid+".VadeMecum.docs.desc.theVoid.pg5") };
	}

}
