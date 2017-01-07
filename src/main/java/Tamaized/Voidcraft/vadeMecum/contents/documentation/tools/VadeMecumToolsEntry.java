package Tamaized.Voidcraft.vadeMecum.contents.documentation.tools;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.realityTeleporter.VadeMecumPageListRealityTeleporter;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.spectreaxe.VadeMecumPageListSpectreAxe;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.spectrehoe.VadeMecumPageListSpectreHoe;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.spectrepick.VadeMecumPageListSpectrePick;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.spectreshovel.VadeMecumPageListSpectreShovel;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidaxe.VadeMecumPageListVoidAxe;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidhoe.VadeMecumPageListVoidHoe;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidicdrill.VadeMecumPageListVoidicDrill;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidpick.VadeMecumPageListVoidPick;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.voidspade.VadeMecumPageListVoidSpade;
import net.minecraft.item.ItemStack;

public class VadeMecumToolsEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidPick, VoidAxe, VoidShovel, VoidHoe, SpectrePick, SpectreAxe,

		SpectreShovel, SpectreHoe, VoidicDrill, RealityTeleporter
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
	public VadeMecumEntry spectrePick;
	public VadeMecumEntry spectreAxe;
	public VadeMecumEntry spectreShovel;
	public VadeMecumEntry spectreHoe;

	public VadeMecumEntry voidicDrill;
	public VadeMecumEntry realityTeleporter;

	public VadeMecumToolsEntry(VadeMecumEntry back) {
		super("docs_Tools", voidCraft.modid + ".VadeMecum.docs.title.tools", back, null);
	}

	@Override
	public void initObjects() {
		voidPick = new VadeMecumEntry("docs_Tools_voidPick", "", this, new VadeMecumPageListVoidPick());
		voidAxe = new VadeMecumEntry("docs_Tools_voidAxe", "", this, new VadeMecumPageListVoidAxe());
		voidShovel = new VadeMecumEntry("docs_Tools_voidShovel", "", this, new VadeMecumPageListVoidSpade());
		voidHoe = new VadeMecumEntry("docs_Tools_voidHoe", "", this, new VadeMecumPageListVoidHoe());
		spectrePick = new VadeMecumEntry("docs_Tools_spectrePick", "", this, new VadeMecumPageListSpectrePick());
		spectreAxe = new VadeMecumEntry("docs_Tools_spectreAxe", "", this, new VadeMecumPageListSpectreAxe());

		spectreShovel = new VadeMecumEntry("docs_Tools_spectreShovel", "", this, new VadeMecumPageListSpectreShovel());
		spectreHoe = new VadeMecumEntry("docs_Tools_spectreHoe", "", this, new VadeMecumPageListSpectreHoe());
		voidicDrill = new VadeMecumEntry("docs_Tools_voidicDrill", "", this, new VadeMecumPageListVoidicDrill());
		realityTeleporter = new VadeMecumEntry("docs_Items_realityTeleporter", "", this, new VadeMecumPageListRealityTeleporter());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidPick), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, new ItemStack(voidCraft.tools.voidPickaxe).getDisplayName(), new ItemStack(voidCraft.tools.voidPickaxe)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidAxe), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 1), 100, 20, new ItemStack(voidCraft.tools.voidAxe).getDisplayName(), new ItemStack(voidCraft.tools.voidAxe)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidShovel), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 2), 100, 20, new ItemStack(voidCraft.tools.voidSpade).getDisplayName(), new ItemStack(voidCraft.tools.voidSpade)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidHoe), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 3), 100, 20, new ItemStack(voidCraft.tools.voidHoe).getDisplayName(), new ItemStack(voidCraft.tools.voidHoe)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.SpectrePick), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 4), 100, 20, new ItemStack(voidCraft.tools.spectrePickaxe).getDisplayName(), new ItemStack(voidCraft.tools.spectrePickaxe)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.SpectreAxe), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 5), 100, 20, new ItemStack(voidCraft.tools.spectreAxe).getDisplayName(), new ItemStack(voidCraft.tools.spectreAxe)));

		addButton(new VadeMecumButton(gui, getEntryID(Entry.SpectreShovel), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 0), 100, 20, new ItemStack(voidCraft.tools.spectreSpade).getDisplayName(), new ItemStack(voidCraft.tools.spectreSpade)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.SpectreHoe), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 1), 100, 20, new ItemStack(voidCraft.tools.spectreHoe).getDisplayName(), new ItemStack(voidCraft.tools.spectreHoe)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidicDrill), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 2), 100, 20, new ItemStack(voidCraft.items.voidicDrill).getDisplayName(), new ItemStack(voidCraft.items.voidicDrill)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.RealityTeleporter), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 3), 100, 20, new ItemStack(voidCraft.items.realityTeleporter).getDisplayName(), new ItemStack(voidCraft.items.realityTeleporter)));

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
			case SpectrePick:
				gui.changeEntry(spectrePick);
				break;
			case SpectreAxe:
				gui.changeEntry(spectreAxe);
				break;
			case SpectreShovel:
				gui.changeEntry(spectreShovel);
				break;
			case SpectreHoe:
				gui.changeEntry(spectreHoe);
				break;
			case VoidicDrill:
				gui.changeEntry(voidicDrill);
				break;
			case RealityTeleporter:
				gui.changeEntry(realityTeleporter);
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
