package tamaized.voidcraft.common.vademecum.contents.documentation.thevoid;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;

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
