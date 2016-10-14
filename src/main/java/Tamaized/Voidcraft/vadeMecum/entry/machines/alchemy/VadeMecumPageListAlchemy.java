package Tamaized.Voidcraft.vadeMecum.entry.machines.alchemy;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.entry.machines.alchemy.pages.VadeMecumPageAlchemy1;
import Tamaized.Voidcraft.vadeMecum.entry.machines.alchemy.pages.VadeMecumPageAlchemy2;
import Tamaized.Voidcraft.vadeMecum.entry.machines.alchemy.pages.VadeMecumPageAlchemy3;

public class VadeMecumPageListAlchemy {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageAlchemy1(),
				new VadeMecumPageAlchemy2(),
				new VadeMecumPageAlchemy3() };
	}

}
