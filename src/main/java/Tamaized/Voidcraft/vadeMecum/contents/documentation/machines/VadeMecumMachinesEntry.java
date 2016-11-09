package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.alchemy.VadeMecumPageListAlchemy;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.cable.VadeMecumPageListCable;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.charger.VadeMecumPageListCharger;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.generator.VadeMecumPageListGenerator;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.heimdall.VadeMecumPageListHeimdall;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.infusionAltar.VadeMecumPageListInfusionAltar;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.macerator.VadeMecumPageListMacerator;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.musicbox.VadeMecumPageListMusicBox;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.stabilizer.VadeMecumPageListStabilizer;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.teleporter.VadeMecumPageListTeleporter;
import net.minecraft.item.ItemStack;

public class VadeMecumMachinesEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidInfusionAltar, VoidInfusedMacerator, Heimdall, VoidicGenerator, VoidicCable, VoidMusicBox, VoidicCharger, RealityStabilizer, AlchemyTable, Teleporter
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
	public VadeMecumEntry teleporter;

	public VadeMecumMachinesEntry(VadeMecumEntry back) {
		super("docs_Machines", voidCraft.modid+".VadeMecum.docs.title.machines", back, null);
	}

	@Override
	public void initObjects() {
		voidInfusionAltar = new VadeMecumEntry("docs_Machines_voidInfusionAltar", "", this, new VadeMecumPageListInfusionAltar());
		voidInfusedMacerator = new VadeMecumEntry("docs_Machines_voidInfusedMacerator", "", this, new VadeMecumPageListMacerator());
		heimdall = new VadeMecumEntry("docs_Machines_heimdall", "", this, new VadeMecumPageListHeimdall());
		voidicGenerator = new VadeMecumEntry("docs_Machines_voidicGenerator", "", this, new VadeMecumPageListGenerator());
		voidicCable = new VadeMecumEntry("docs_Machines_voidicCable", "", this, new VadeMecumPageListCable());
		voidMusicBox = new VadeMecumEntry("docs_Machines_voidMusicBox", "", this, new VadeMecumPageListMusicBox());
		
		voidicCharger = new VadeMecumEntry("docs_Machines_voidicCharger", "", this, new VadeMecumPageListCharger());
		realityStabilizer = new VadeMecumEntry("docs_Machines_realityStabilizer", "", this, new VadeMecumPageListStabilizer());
		alchemyTable = new VadeMecumEntry("docs_Machines_alchemyTable", "", this, new VadeMecumPageListAlchemy());
		teleporter = new VadeMecumEntry("docs_Machines_teleporter", "", this, new VadeMecumPageListTeleporter());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidInfusionAltar), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, new ItemStack(voidCraft.blocks.voidInfuser).getDisplayName(), new ItemStack(voidCraft.blocks.voidInfuser)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidInfusedMacerator), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 1), 100, 20, new ItemStack(voidCraft.blocks.voidMacerator).getDisplayName(), new ItemStack(voidCraft.blocks.voidMacerator)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.Heimdall), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 2), 100, 20, new ItemStack(voidCraft.blocks.Heimdall).getDisplayName(), new ItemStack(voidCraft.blocks.Heimdall)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidicGenerator), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 3), 100, 20, new ItemStack(voidCraft.blocks.voidicGen).getDisplayName(), new ItemStack(voidCraft.blocks.voidicGen)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidicCable), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 4), 100, 20, new ItemStack(voidCraft.blocks.voidicCable).getDisplayName(), new ItemStack(voidCraft.blocks.voidicCable)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidMusicBox), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 5), 100, 20, new ItemStack(voidCraft.blocks.voidBox).getDisplayName(), new ItemStack(voidCraft.blocks.voidBox)));
		
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidicCharger), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 0), 100, 20, new ItemStack(voidCraft.blocks.voidicCharger).getDisplayName(), new ItemStack(voidCraft.blocks.voidicCharger)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.RealityStabilizer), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 1), 100, 20, new ItemStack(voidCraft.blocks.realityStabilizer).getDisplayName(), new ItemStack(voidCraft.blocks.realityStabilizer)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.AlchemyTable), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 2), 100, 20, new ItemStack(voidCraft.blocks.voidicAlchemyTable).getDisplayName(), new ItemStack(voidCraft.blocks.voidicAlchemyTable)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.Teleporter), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 3), 100, 20, new ItemStack(voidCraft.blocks.realityTeleporterBlock).getDisplayName(), new ItemStack(voidCraft.blocks.realityTeleporterBlock)));
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
			case Teleporter:
				gui.changeEntry(teleporter);
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
