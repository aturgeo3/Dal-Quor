package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.specteraxe.VadeMecumPageListSpecterAxe;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.specterpick.VadeMecumPageListSpecterPick;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidaxe.VadeMecumPageListVoidAxe;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidhoe.VadeMecumPageListVoidHoe;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidicdrill.VadeMecumPageListVoidicDrill;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidpick.VadeMecumPageListVoidPick;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidspade.VadeMecumPageListVoidSpade;
import net.minecraft.item.ItemStack;

public class VadeMecumToolsEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidPick, VoidAxe, VoidShovel, VoidHoe, SpecterPick, SpecterAxe, VoidicDrill
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidPick;
	public VadeMecumEntry voidAxe;
	public VadeMecumEntry voidShovel;
	public VadeMecumEntry voidHoe;
	public VadeMecumEntry specterPick;
	public VadeMecumEntry specterAxe;

	public VadeMecumEntry voidicDrill;

	public VadeMecumToolsEntry(VadeMecumEntry back) {
		super("docs_Tools", "Tools", back, null);
	}

	@Override
	public void initObjects() {
		voidPick = new VadeMecumEntry("docs_Tools_voidPick", "", this, VadeMecumPageListVoidPick.getPageList());
		voidAxe = new VadeMecumEntry("docs_Tools_voidAxe", "", this, VadeMecumPageListVoidAxe.getPageList());
		voidShovel = new VadeMecumEntry("docs_Tools_voidShovel", "", this, VadeMecumPageListVoidSpade.getPageList());
		voidHoe = new VadeMecumEntry("docs_Tools_voidHoe", "", this, VadeMecumPageListVoidHoe.getPageList());
		specterPick = new VadeMecumEntry("docs_Tools_specterPick", "", this, VadeMecumPageListSpecterPick.getPageList());
		specterAxe = new VadeMecumEntry("docs_Tools_specterAxe", "", this, VadeMecumPageListSpecterAxe.getPageList());

		voidicDrill = new VadeMecumEntry("docs_Tools_voidicDrill", "", this, VadeMecumPageListVoidicDrill.getPageList());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidPick), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, "Void Pickaxe", new ItemStack(voidCraft.tools.voidPickaxe)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidAxe), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 1), 100, 20, "Void Axe", new ItemStack(voidCraft.tools.voidAxe)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidShovel), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 2), 100, 20, "Void Shovel", new ItemStack(voidCraft.tools.voidSpade)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidHoe), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 3), 100, 20, "Void Hoe", new ItemStack(voidCraft.tools.voidHoe)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.SpecterPick), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 4), 100, 20, "Specter Pickaxe", new ItemStack(voidCraft.tools.spectrePickaxe)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.SpecterAxe), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 5), 100, 20, "Specter Axe", new ItemStack(voidCraft.tools.spectreAxe)));

		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidicDrill), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 0), 100, 20, "Voidic Drill", new ItemStack(voidCraft.items.voidicDrill)));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case VoidPick:
				gui.changeEntry(voidPick);
				break;
			case VoidAxe:
				gui.changeEntry(voidAxe);
				break;
			case VoidShovel:
				gui.changeEntry(voidShovel);
				break;
			case VoidHoe:
				gui.changeEntry(voidHoe);
				break;
			case SpecterPick:
				gui.changeEntry(specterPick);
				break;
			case SpecterAxe:
				gui.changeEntry(specterAxe);
				break;
			case VoidicDrill:
				gui.changeEntry(voidicDrill);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}

	public int getPageLength() {
		return 1;
	}

}
