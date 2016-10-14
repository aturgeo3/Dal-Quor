package Tamaized.Voidcraft.vadeMecum.entry.machines.musicbox;

import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.entry.machines.musicbox.pages.VadeMecumPageMusicBox1;
import Tamaized.Voidcraft.vadeMecum.entry.machines.musicbox.pages.VadeMecumPageMusicBox2;

public class VadeMecumPageListMusicBox {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPageMusicBox1(),
				new VadeMecumPageMusicBox2() };
	}

}
