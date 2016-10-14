package Tamaized.Voidcraft.vadeMecum.entry.mobs;

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
import Tamaized.Voidcraft.vadeMecum.entry.mobs.herobrinecreeper.VadeMecumPageListHerobrineCreeper;
import Tamaized.Voidcraft.vadeMecum.entry.mobs.lich.VadeMecumPageListLich;
import Tamaized.Voidcraft.vadeMecum.entry.mobs.specter.VadeMecumPageListSpecterChain;
import Tamaized.Voidcraft.vadeMecum.entry.mobs.wraith.VadeMecumPageListVoidWraith;
import Tamaized.Voidcraft.vadeMecum.entry.mobs.wrath.VadeMecumPageListVoidWrath;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumMobsEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidWraith, ChainedSpecter, VoidWrath, VoidLich, HerobrineCreeper
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidWraith;
	public VadeMecumEntry chainedSpecter;
	public VadeMecumEntry voidWrath;
	public VadeMecumEntry voidLich;
	public VadeMecumEntry herobrineCreeper;

	public VadeMecumMobsEntry(VadeMecumEntry back) {
		super("Machines", back, null);
	}

	@Override
	public void initObjects() {
		voidWraith = new VadeMecumEntry("", this, VadeMecumPageListVoidWraith.getPageList());
		chainedSpecter = new VadeMecumEntry("", this, VadeMecumPageListSpecterChain.getPageList());
		voidWrath = new VadeMecumEntry("", this, VadeMecumPageListVoidWrath.getPageList());
		voidLich = new VadeMecumEntry("", this, VadeMecumPageListLich.getPageList());
		herobrineCreeper = new VadeMecumEntry("", this, VadeMecumPageListHerobrineCreeper.getPageList());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidWraith), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, "Void Wraith", new ItemStack(voidCraft.items.ectoplasm)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.ChainedSpecter), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 1), 100, 20, "Chained Specter", new ItemStack(voidCraft.items.voidChain)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidWrath), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 2), 100, 20, "Void's Wrath", new ItemStack(voidCraft.items.burnBone)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidLich), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 3), 100, 20, "Void Lich", new ItemStack(voidCraft.items.voidCloth)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.HerobrineCreeper), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 4), 100, 20, "Herobrine Creeper", new ItemStack(Items.GUNPOWDER)));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case VoidWraith:
				gui.changeEntry(voidWraith);
				break;
			case ChainedSpecter:
				gui.changeEntry(chainedSpecter);
				break;
			case VoidWrath:
				gui.changeEntry(voidWrath);
				break;
			case VoidLich:
				gui.changeEntry(voidLich);
				break;
			case HerobrineCreeper:
				gui.changeEntry(herobrineCreeper);
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
