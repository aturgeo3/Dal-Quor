package Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.herobrinecreeper;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListHerobrineCreeper implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Herobrine Creeper", "Only found within a Void Fortress. These are enhanced Creepers with different visuals. The explosion radius and damage are a bit larger. Though they do not harm the world.") };
	}

}
