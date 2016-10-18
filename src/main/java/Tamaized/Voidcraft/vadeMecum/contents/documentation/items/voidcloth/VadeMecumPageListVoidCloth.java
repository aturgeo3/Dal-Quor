package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidcloth;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListVoidCloth implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Void Cloth", "For some unknown reason the only way to obtain this cloth is from taking it from a lifeless corpse of a Void Lich. Though they were already undead to begin with. Even with the cloth in your possesion you can not figure out how exactly it is made or why it has the binding properties that it does. The material proves very useful though.") };
	}

}
