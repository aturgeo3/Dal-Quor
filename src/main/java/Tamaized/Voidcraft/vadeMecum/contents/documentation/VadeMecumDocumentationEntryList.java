package Tamaized.Voidcraft.vadeMecum.contents.documentation;

import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.armor.voidarmor.VadeMecumPageListVoidArmor;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.VadeMecumBlocksEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.bosses.VadeMecumBossesEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit.VadeMecumPageListFruit;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.VadeMecumItemsEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.VadeMecumMachinesEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.VadeMecumMobsEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.thevoid.VadeMecumPageListVoid;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.VadeMecumToolsEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.voidicinfusion.VadeMecumPageListVoidicInfusion;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.VadeMecumWeaponsEntry;

public class VadeMecumDocumentationEntryList {

	public static enum Entry {
		CREDITS, MAIN, BLOCKS, MACHINES, ITEMS, TOOLS, WEAPONS, ARMOR, ETHEREALFRUIT, MOBS, BOSSES, VOID, VOIDICINFUSION
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public final VadeMecumEntry CREDITS;
	public final VadeMecumEntry MAIN;
	public final VadeMecumEntry BLOCKS;
	public final VadeMecumEntry MACHINES;
	public final VadeMecumEntry ITEMS;
	public final VadeMecumEntry TOOLS;
	public final VadeMecumEntry WEAPONS;
	public final VadeMecumEntry ARMOR;
	public final VadeMecumEntry ETHEREALFRUIT;
	public final VadeMecumEntry MOBS;
	public final VadeMecumEntry BOSSES;
	public final VadeMecumEntry VOID;
	public final VadeMecumEntry VOIDICINFUSION;

	public VadeMecumDocumentationEntryList() {
		CREDITS = new VadeMecumDocumentationEntry();
		MAIN = new VadeMecumDocumentationEntry();
		BLOCKS = new VadeMecumBlocksEntry(MAIN);
		MACHINES = new VadeMecumMachinesEntry(MAIN);
		ITEMS = new VadeMecumItemsEntry(MAIN);
		TOOLS = new VadeMecumToolsEntry(MAIN);
		WEAPONS = new VadeMecumWeaponsEntry(MAIN);
		ARMOR = new VadeMecumEntry("", MAIN, VadeMecumPageListVoidArmor.getPageList());
		ETHEREALFRUIT = new VadeMecumEntry("", MAIN, VadeMecumPageListFruit.getPageList());
		MOBS = new VadeMecumMobsEntry(MAIN);
		BOSSES = new VadeMecumBossesEntry(MAIN);
		VOID = new VadeMecumEntry("", MAIN, VadeMecumPageListVoid.getPageList());
		VOIDICINFUSION = new VadeMecumEntry("", MAIN, VadeMecumPageListVoidicInfusion.getPageList());
	}

}
