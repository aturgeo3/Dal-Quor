package Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.herobrinecreeper.VadeMecumPageListHerobrineCreeper;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.lich.VadeMecumPageListLich;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.specter.VadeMecumPageListSpecterChain;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.wraith.VadeMecumPageListVoidWraith;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.wrath.VadeMecumPageListVoidWrath;
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
		super("docs_Mobs", "Mobs", back, null);
	}

	@Override
	public void initObjects() {
		voidWraith = new VadeMecumEntry("docs_Mobs_voidWraith", "", this, VadeMecumPageListVoidWraith.getPageList());
		chainedSpecter = new VadeMecumEntry("docs_Mobs_chainedSpecter", "", this, VadeMecumPageListSpecterChain.getPageList());
		voidWrath = new VadeMecumEntry("docs_Mobs_voidWrath", "", this, VadeMecumPageListVoidWrath.getPageList());
		voidLich = new VadeMecumEntry("docs_Mobs_voidLich", "", this, VadeMecumPageListLich.getPageList());
		herobrineCreeper = new VadeMecumEntry("docs_Mobs_herobrineCreeper", "", this, VadeMecumPageListHerobrineCreeper.getPageList());
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
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}

	public int getPageLength() {
		return 1;
	}

}
