package Tamaized.Voidcraft.vadeMecum;

import Tamaized.Voidcraft.vadeMecum.entry.armor.voidarmor.VadeMecumPageListVoidArmor;
import Tamaized.Voidcraft.vadeMecum.entry.blocks.VadeMecumBlocksEntry;
import Tamaized.Voidcraft.vadeMecum.entry.bosses.VadeMecumBossesEntry;
import Tamaized.Voidcraft.vadeMecum.entry.fruit.VadeMecumPageListFruit;
import Tamaized.Voidcraft.vadeMecum.entry.items.VadeMecumItemsEntry;
import Tamaized.Voidcraft.vadeMecum.entry.machines.VadeMecumMachinesEntry;
import Tamaized.Voidcraft.vadeMecum.entry.main.VadeMecumMainEntry;
import Tamaized.Voidcraft.vadeMecum.entry.mobs.VadeMecumMobsEntry;
import Tamaized.Voidcraft.vadeMecum.entry.thevoid.VadeMecumPageListVoid;
import Tamaized.Voidcraft.vadeMecum.entry.tools.VadeMecumToolsEntry;
import Tamaized.Voidcraft.vadeMecum.entry.voidicinfusion.VadeMecumPageListVoidicInfusion;
import Tamaized.Voidcraft.vadeMecum.entry.weapons.VadeMecumWeaponsEntry;

public class VadeMecumEntryList {

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

	public VadeMecumEntryList() {
		CREDITS = new VadeMecumMainEntry();
		MAIN = new VadeMecumMainEntry();
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
