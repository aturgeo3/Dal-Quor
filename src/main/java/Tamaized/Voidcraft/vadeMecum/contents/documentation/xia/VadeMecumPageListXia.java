package Tamaized.Voidcraft.vadeMecum.contents.documentation.xia;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.client.resources.I18n;

public class VadeMecumPageListXia implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] { new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.xia", voidCraft.modid + ".VadeMecum.docs.desc.xia.pg1") };
	}

}
