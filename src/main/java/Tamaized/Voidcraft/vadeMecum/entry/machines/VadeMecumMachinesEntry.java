package Tamaized.Voidcraft.vadeMecum.entry.machines;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.entry.blocks.voidcrystalblock.VadeMecumPageListVoidCrystalBlock;
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

public class VadeMecumMachinesEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidInfusionAltar, VoidInfusedMacerator, Heimdall, VoidicGenerator, VoidicCable, VoidMusicBox, VoidicCharger, RealityStabilizer, AlchemyTable
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidInfusionAltar;
	public VadeMecumEntry voidInfusedMacerator;
	public VadeMecumEntry heimdall;
	public VadeMecumEntry voidicGenerator;
	public VadeMecumEntry voidicCable;
	public VadeMecumEntry voidMusicBox;
	public VadeMecumEntry voidicCharger;
	public VadeMecumEntry realityStabilizer;
	public VadeMecumEntry alchemyTable;

	public VadeMecumMachinesEntry(VadeMecumEntry back) {
		super("Machines", back, null);
	}

	@Override
	public void initObjects() {
		voidInfusionAltar = new VadeMecumEntry("", this, VadeMecumPageListInfusionAltar.getPageList());
		voidInfusedMacerator = new VadeMecumEntry("", this, VadeMecumPageListMacerator.getPageList());
		heimdall = new VadeMecumEntry("", this, VadeMecumPageListHeimdall.getPageList());
		voidicGenerator = new VadeMecumEntry("", this, VadeMecumPageListGenerator.getPageList());
		voidicCable = new VadeMecumEntry("", this, VadeMecumPageListCable.getPageList());
		voidMusicBox = new VadeMecumEntry("", this, VadeMecumPageListMusicBox.getPageList());
		voidicCharger = new VadeMecumEntry("", this, VadeMecumPageListCharger.getPageList());
		realityStabilizer = new VadeMecumEntry("", this, VadeMecumPageListStabilizer.getPageList());
		alchemyTable = new VadeMecumEntry("", this, VadeMecumPageListAlchemy.getPageList());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidInfusionAltar), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, "Void Infusion Altar", new ItemStack(voidCraft.blocks.voidInfuser)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidInfusedMacerator), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 1), 100, 20, "Void Infused Macerator", new ItemStack(voidCraft.blocks.voidMacerator)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.Heimdall), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 2), 100, 20, "Heimdall", new ItemStack(voidCraft.blocks.Heimdall)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidicGenerator), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 3), 100, 20, "Voidic Generator", new ItemStack(voidCraft.blocks.voidicGen)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidicCable), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 4), 100, 20, "Voidic Cable", new ItemStack(voidCraft.blocks.voidicCable)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidMusicBox), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 5), 100, 20, "Void Music Box", new ItemStack(voidCraft.blocks.voidBox)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidicCharger), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 0), 100, 20, "Voidic Charger", new ItemStack(voidCraft.blocks.voidicCharger)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.RealityStabilizer), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 1), 100, 20, "Reality Stabilizer", new ItemStack(voidCraft.blocks.realityStabilizer)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.AlchemyTable), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 2), 100, 20, "Alchemy Table", new ItemStack(voidCraft.blocks.voidicAlchemyTable)));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case VoidInfusionAltar:
				gui.changeEntry(voidInfusionAltar);
				break;
			case VoidInfusedMacerator:
				gui.changeEntry(voidInfusedMacerator);
				break;
			case Heimdall:
				gui.changeEntry(heimdall);
				break;
			case VoidicGenerator:
				gui.changeEntry(voidicGenerator);
				break;
			case VoidicCable:
				gui.changeEntry(voidicCable);
				break;
			case VoidMusicBox:
				gui.changeEntry(voidMusicBox);
				break;
			case VoidicCharger:
				gui.changeEntry(voidicCharger);
				break;
			case RealityStabilizer:
				gui.changeEntry(realityStabilizer);
				break;
			case AlchemyTable:
				gui.changeEntry(alchemyTable);
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
