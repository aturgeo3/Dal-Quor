package Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.lich;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListLich implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Void Lich", "Only found within a Swampland and quite rare. Liches will do a variety of attacks. These attacks are a Lightning Bolt, a Fireball, the summoning of undead aid which consists of Void Wraiths, Chained Specters, Void Wraths, and Wither Skeletons. Liches can cast a spreading ring of Void Fire. And they can incase their target in stone. Liches do not like each other and will attack one another on sight. Upon death they'll drop Void Cloth.") };
	}

}
