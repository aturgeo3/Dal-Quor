package Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.wrath;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListVoidWrath implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Void's Wrath", "Only found within a Void Fortress. These entities are very sturdy and deal quite a bit of damage. They are melee based. They drop Charred bones upon death.") };
	}

}
