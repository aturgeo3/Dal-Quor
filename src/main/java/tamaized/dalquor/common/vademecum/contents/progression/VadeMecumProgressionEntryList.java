package tamaized.dalquor.common.vademecum.contents.progression;

import tamaized.dalquor.common.vademecum.VadeMecumEntry;
import tamaized.dalquor.common.vademecum.contents.progression.pages.*;

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
	public final VadeMecumEntry TOME;
	public final VadeMecumEntry INFUSIONCONTROL;
	public final VadeMecumEntry POTIONS;
	public final VadeMecumEntry VOICE;
	public final VadeMecumEntry VOIDICCONTROL;
	public final VadeMecumEntry IMPROVEDCASTING;
	public final VadeMecumEntry EMPOWERMENT;
	public final VadeMecumEntry TOLERANCE;
	public final VadeMecumEntry TOTALCONTROL;
	public final VadeMecumEntry DREAMS;

	public VadeMecumProgressionEntryList() {
		MAIN = new VadeMecumProgressionEntry();
		RITUALBLOCKS = new VadeMecumEntry("progression_RITUALBLOCKS", "", MAIN, new VadeMecumPageListRitualBlock());
		RITUALLIST = new VadeMecumEntry("progression_RITUALLIST", "", MAIN, new VadeMecumPageListRitualList());
		TOME = new VadeMecumEntry("progression_TOME", "", MAIN, new VadeMecumPageListTome());
		INFUSIONCONTROL = new VadeMecumEntry("progression_INFUSIONCONTROL", "", MAIN, new VadeMecumPageListInfusionControl());
		POTIONS = new VadeMecumEntry("progression_POTIONS", "", MAIN, new VadeMecumPageListPotions());
		VOICE = new VadeMecumEntry("progression_VOICE", "", MAIN, new VadeMecumPageListVoice());
		VOIDICCONTROL = new VadeMecumEntry("progression_VOIDICCONTROL", "", MAIN, new VadeMecumPageListVoidicControl());
		IMPROVEDCASTING = new VadeMecumEntry("progression_IMPROVEDCASTING", "", MAIN, new VadeMecumPageListImprovedCasting());
		EMPOWERMENT = new VadeMecumEntry("progression_EMPOWERMENT", "", MAIN, new VadeMecumPageListEmpowerment());
		TOLERANCE = new VadeMecumEntry("progression_TOLERANCE", "", MAIN, new VadeMecumPageListTolerance());
		TOTALCONTROL = new VadeMecumEntry("progression_TOTALCONTROL", "", MAIN, new VadeMecumPageListTotalControl());
		DREAMS = new VadeMecumEntry("progression_DREAMS", "", MAIN, new VadeMecumPageListDreams());
	}

	public void preLoadObjects() {
		MAIN.initObjects();
		RITUALBLOCKS.initObjects();
		RITUALLIST.initObjects();
		TOME.initObjects();
		INFUSIONCONTROL.initObjects();
		POTIONS.initObjects();
		VOICE.initObjects();
		VOIDICCONTROL.initObjects();
		IMPROVEDCASTING.initObjects();
		EMPOWERMENT.initObjects();
		TOLERANCE.initObjects();
		TOTALCONTROL.initObjects();
		DREAMS.initObjects();
	}

}
