package Tamaized.Voidcraft.vadeMecum.entry.blocks.voidcrystalblock;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.entry.blocks.voidcrystalblock.pages.VadeMecumPageVoidCrystalBlock1;
import Tamaized.Voidcraft.vadeMecum.entry.blocks.voidcrystalblock.pages.VadeMecumPageVoidCrystalBlock2;
import Tamaized.Voidcraft.vadeMecum.entry.blocks.voidcrystalblock.pages.VadeMecumPageVoidCrystalBlock3;

public class VadeMecumPageListVoidCrystalBlock {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageVoidCrystalBlock1(),
				new VadeMecumPageVoidCrystalBlock2(),
				new VadeMecumPageVoidCrystalBlock3() };
	}

}
