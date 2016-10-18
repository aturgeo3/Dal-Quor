package Tamaized.Voidcraft.vadeMecum.contents.progression;

import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.progression.ritualBlock.VadeMecumPageListRitualBlock;
import Tamaized.Voidcraft.vadeMecum.contents.progression.ritualList.VadeMecumPageListRitualList;

public class VadeMecumProgressionEntryList {

	public static enum Entry {
		RitualBlocks
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public final VadeMecumEntry MAIN;
	public final VadeMecumEntry RITUALBLOCKS;
	public VadeMecumEntry RITUALLIST;

	public VadeMecumProgressionEntryList() {
		MAIN = new VadeMecumProgressionEntry();
		RITUALBLOCKS = new VadeMecumEntry("progression_RITUALBLOCKS", "", MAIN, new VadeMecumPageListRitualBlock());
		RITUALLIST = new VadeMecumEntry("progression_RITUALLIST", "", MAIN, new VadeMecumPageListRitualList());
	}

	public void preLoadObjects() {
		MAIN.initObjects();
		RITUALBLOCKS.initObjects();
		RITUALLIST.initObjects();
	}

}
