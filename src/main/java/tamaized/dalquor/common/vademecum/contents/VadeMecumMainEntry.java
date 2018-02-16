package tamaized.dalquor.common.vademecum.contents;

import net.minecraft.item.ItemStack;
import tamaized.dalquor.VoidCraft;
import tamaized.dalquor.client.gui.VadeMecumGUI;
import tamaized.dalquor.common.vademecum.VadeMecumEntry;
import tamaized.dalquor.common.vademecum.contents.progression.VadeMecumProgressionEntryList;
import tamaized.dalquor.registry.VoidCraftBlocks;
import tamaized.dalquor.registry.VoidCraftItems;

import static tamaized.dalquor.common.vademecum.contents.VadeMecumMainEntry.Entry.Docs;

public class VadeMecumMainEntry extends VadeMecumEntry {

	public static enum Entry {
		Docs, Progression
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public final VadeMecumProgressionEntryList Progression;

	public VadeMecumMainEntry() {
		super("mainEntry", new ItemStack(VoidCraftItems.vadeMecum).getDisplayName(), null, null);
		Progression = new VadeMecumProgressionEntryList();
	}

	@Override
	public void init(VadeMecumGUI gui) {
		clearButtons();
		addButton(gui, getEntryID(Entry.Progression), "voidcraft.VadeMecum.title.progression", new ItemStack(VoidCraftBlocks.ritualBlock));
		addButton(gui, getEntryID(Docs), "voidcraft.VadeMecum.title.docs", new ItemStack(VoidCraftItems.voidcrystal));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case Docs:
				break;
			case Progression:
				gui.changeEntry(Progression.MAIN);
				break;
			default:
				break;
		}
	}

	public void preLoadObject() {
		VoidCraft.instance.logger.info("Preloading Vade Mecum Entry Objects");
		Progression.preLoadObjects();
	}

}
