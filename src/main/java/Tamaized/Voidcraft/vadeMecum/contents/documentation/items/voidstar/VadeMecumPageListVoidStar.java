package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidstar;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListVoidStar implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Void Star", "Very similar to the Nether Star. These are dropped from the Corrupted Pawn, in fact they are the cores of such beings. These stars are capable of opening rifts to the void but only when contained correctly.")};
	}

}
