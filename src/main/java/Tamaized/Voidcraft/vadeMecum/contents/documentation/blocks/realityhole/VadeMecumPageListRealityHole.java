package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.realityhole;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListRealityHole implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Hole in Reality", "This Rift in Space can link to many dimensions. Stepping into one will result in being teleported to one of these dimensions, at random, around the same coordinates from where you were in the void, give or take ~200 blocks. Being around these rifts reminds you of the sensation you feel when you're being watched by... him...")
				};
	}

}
