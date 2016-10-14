package Tamaized.Voidcraft.vadeMecum.entry.bosses;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.entry.blocks.voidcrystalblock.VadeMecumPageListVoidCrystalBlock;
import Tamaized.Voidcraft.vadeMecum.entry.bosses.pawn.VadeMecumPageListCorruptedPawn;
import Tamaized.Voidcraft.vadeMecum.entry.machines.alchemy.VadeMecumPageListAlchemy;
import Tamaized.Voidcraft.vadeMecum.entry.machines.cable.VadeMecumPageListCable;
import Tamaized.Voidcraft.vadeMecum.entry.machines.charger.VadeMecumPageListCharger;
import Tamaized.Voidcraft.vadeMecum.entry.machines.generator.VadeMecumPageListGenerator;
import Tamaized.Voidcraft.vadeMecum.entry.machines.heimdall.VadeMecumPageListHeimdall;
import Tamaized.Voidcraft.vadeMecum.entry.machines.infusionAltar.VadeMecumPageListInfusionAltar;
import Tamaized.Voidcraft.vadeMecum.entry.machines.macerator.VadeMecumPageListMacerator;
import Tamaized.Voidcraft.vadeMecum.entry.machines.musicbox.VadeMecumPageListMusicBox;
import Tamaized.Voidcraft.vadeMecum.entry.machines.stabilizer.VadeMecumPageListStabilizer;
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
		super("Machines", back, null);
	}

	@Override
	public void initObjects() {
		corruptedPawn = new VadeMecumEntry("", this, VadeMecumPageListCorruptedPawn.getPageList());
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
				gui.changeEntry(ClientProxy.vadeMecumEntryList.MAIN);
				break;
		}
	}

	public int getPageLength() {
		return 1;
	}

}
