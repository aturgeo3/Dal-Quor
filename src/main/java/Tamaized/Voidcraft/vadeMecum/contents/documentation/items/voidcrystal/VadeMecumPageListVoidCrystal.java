package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidcrystal;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListVoidCrystal implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Void Crystal", "Crystallized Void, how exactly these formed and why they're found in The End is unknown. To gather these crystals, simply venture into The End and look around for Void Crystal Ore.") };
	}

}
