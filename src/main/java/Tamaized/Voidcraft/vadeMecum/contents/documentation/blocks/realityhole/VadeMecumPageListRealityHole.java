package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.realityhole;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListRealityHole implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Hole in Reality", "This Rift in Space links back to the Overworld. Stepping into one will result in being teleported to around the same coordinates from where you were in the void. Being around these rifts reminds you of the sensation you feel when you're being watched by... him...")
				};
	}

}
