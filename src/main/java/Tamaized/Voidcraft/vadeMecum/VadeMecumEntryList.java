package Tamaized.Voidcraft.vadeMecum;

import Tamaized.Voidcraft.voidCraft;
import net.minecraft.util.ResourceLocation;

public class VadeMecumEntryList {

	private static final String root = "VadeMecum/";
	private static final String blocks = root + "Blocks/";
	private static final String items = root + "Items/";

	public final VadeMecumEntry INTRO;

	public VadeMecumEntryList() {
		INTRO = VadeMecumEntryLoader.loadEntry(new ResourceLocation(voidCraft.modid, root + "intro.json"));
	}

}
