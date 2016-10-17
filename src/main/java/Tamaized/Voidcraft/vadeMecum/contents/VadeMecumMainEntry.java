package Tamaized.Voidcraft.vadeMecum.contents;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.VadeMecumDocumentationEntryList;
import Tamaized.Voidcraft.vadeMecum.contents.progression.VadeMecumProgressionEntryList;
import net.minecraft.item.ItemStack;

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

	public final VadeMecumDocumentationEntryList Docs;
	public final VadeMecumProgressionEntryList Progression;

	public VadeMecumMainEntry() {
		super("mainEntry", "Void Vade Mecum", null, null);
		Docs = new VadeMecumDocumentationEntryList();
		Progression = new VadeMecumProgressionEntryList();
	}

	@Override
	public void init(VadeMecumGUI gui) {
		clearButtons();
		addButton(new VadeMecumButton(gui, getEntryID(Entry.Progression), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, "Mysteries of the Void", new ItemStack(voidCraft.blocks.ritualBlock)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.Docs), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 1), 100, 20, "Documentation", new ItemStack(voidCraft.items.voidcrystal)));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case Docs:
				gui.changeEntry(Docs.MAIN);
				break;
			case Progression:
				gui.changeEntry(Progression.MAIN);
				break;
			default:
				break;
		}
	}

	public int getPageLength() {
		return 1;
	}

	public void preLoadObject() {
		voidCraft.logger.info("Preloading Vade Mecum Entry Objects");
		Docs.preLoadObjects();
		Progression.preLoadObjects();
	}

}
