package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.charredbone;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListCharredBone implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Charred Bone", "Taken from your fallen enemies, the Void Wraths. These very sturdy creatures are nothing but bone, so it only makes sense for these bones to also be sturdy right?")};
	}

}
