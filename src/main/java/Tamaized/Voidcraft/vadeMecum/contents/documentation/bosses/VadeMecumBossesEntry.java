package Tamaized.Voidcraft.vadeMecum.contents.documentation.bosses;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.bosses.pawn.VadeMecumPageListCorruptedPawn;
import net.minecraft.item.ItemStack;

public class VadeMecumBossesEntry extends VadeMecumEntry {

	public static enum Entry {
		CorruptedPawn
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry corruptedPawn;

	public VadeMecumBossesEntry(VadeMecumEntry back) {
		super("docs_Bosses", "Bosses", back, null);
	}

	@Override
	public void initObjects() {
		corruptedPawn = new VadeMecumEntry("docs_Bosses_corruptedPawn", "", this, new VadeMecumPageListCorruptedPawn());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(new VadeMecumButton(gui, getEntryID(Entry.CorruptedPawn), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, "Corrupted Pawn", new ItemStack(voidCraft.items.ChainedSkull)));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case CorruptedPawn:
				gui.changeEntry(corruptedPawn);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}

	@Override
	public int getPageLength(VadeMecumGUI gui) {
		return 1;
	}

}
