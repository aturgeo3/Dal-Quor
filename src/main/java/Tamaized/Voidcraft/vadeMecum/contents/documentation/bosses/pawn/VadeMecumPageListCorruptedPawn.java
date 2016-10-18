package Tamaized.Voidcraft.vadeMecum.contents.documentation.bosses.pawn;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListCorruptedPawn implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Corrupted Pawn", "Spawned into the world with a Chained Skull followed by an explosion. These deadly entities are very quick, deal massive damage and have a very large health pool. They attack most other creatures in sight. One must be very prepared before taking one on. Upon death, they drop their inner core, the Void Star.") };
	}

}
