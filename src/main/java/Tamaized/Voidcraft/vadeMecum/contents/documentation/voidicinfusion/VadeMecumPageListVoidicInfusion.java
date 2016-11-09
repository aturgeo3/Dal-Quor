package Tamaized.Voidcraft.vadeMecum.contents.documentation.voidicinfusion;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.client.resources.I18n;

public class VadeMecumPageListVoidicInfusion implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(voidCraft.modid+".VadeMecum.docs.title.voidicInfusion", voidCraft.modid+".VadeMecum.docs.desc.voidicInfusion.pg1"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.voidicInfusion.pg2") };
	}

}
