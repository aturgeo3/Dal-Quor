package Tamaized.Voidcraft.vadeMecum.contents.documentation.thevoid;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListVoid implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(voidCraft.modid+".VadeMecum.docs.title.theVoid", voidCraft.modid+".VadeMecum.docs.desc.theVoid.pg1"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.theVoid.pg2"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.theVoid.pg3"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.theVoid.pg4"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.theVoid.pg5") };
	}

}
