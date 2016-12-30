package Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.etherealGuardian.VadeMecumPageListEtherealGuardian;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.herobrinecreeper.VadeMecumPageListHerobrineCreeper;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.lich.VadeMecumPageListLich;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.spectre.VadeMecumPageListSpectreChain;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.wraith.VadeMecumPageListVoidWraith;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.wrath.VadeMecumPageListVoidWrath;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumMobsEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidWraith, ChainedSpectre, VoidWrath, VoidLich, HerobrineCreeper, EtherealGuardian
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidWraith;
	public VadeMecumEntry chainedSpectre;
	public VadeMecumEntry voidWrath;
	public VadeMecumEntry voidLich;
	public VadeMecumEntry herobrineCreeper;
	public VadeMecumEntry etherealGuardian;

	public VadeMecumMobsEntry(VadeMecumEntry back) {
		super("docs_Mobs", voidCraft.modid+".VadeMecum.docs.title.mobs", back, null);
	}

	@Override
	public void initObjects() {
		voidWraith = new VadeMecumEntry("docs_Mobs_voidWraith", "", this, new VadeMecumPageListVoidWraith());
		chainedSpectre = new VadeMecumEntry("docs_Mobs_chainedSpectre", "", this, new VadeMecumPageListSpectreChain());
		voidWrath = new VadeMecumEntry("docs_Mobs_voidWrath", "", this, new VadeMecumPageListVoidWrath());
		voidLich = new VadeMecumEntry("docs_Mobs_voidLich", "", this, new VadeMecumPageListLich());
		herobrineCreeper = new VadeMecumEntry("docs_Mobs_herobrineCreeper", "", this, new VadeMecumPageListHerobrineCreeper());
		etherealGuardian = new VadeMecumEntry("docs_Mobs_etherealGuardian", "", this, new VadeMecumPageListEtherealGuardian());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidWraith), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, voidCraft.modid+".VadeMecum.docs.title.voidWraith", new ItemStack(voidCraft.items.ectoplasm)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.ChainedSpectre), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 1), 100, 20, voidCraft.modid+".VadeMecum.docs.title.chainedSpectre", new ItemStack(voidCraft.items.voidChain)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidWrath), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 2), 100, 20, voidCraft.modid+".VadeMecum.docs.title.voidWrath", new ItemStack(voidCraft.items.burnBone)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidLich), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 3), 100, 20, voidCraft.modid+".VadeMecum.docs.title.lich", new ItemStack(voidCraft.items.voidCloth)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.HerobrineCreeper), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 4), 100, 20, voidCraft.modid+".VadeMecum.docs.title.herobrineCreeper", new ItemStack(Items.GUNPOWDER)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.EtherealGuardian), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 5), 100, 20, voidCraft.modid+".VadeMecum.docs.title.etherealGuardian", new ItemStack(voidCraft.items.voidicPhlogiston)));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case VoidWraith:
				gui.changeEntry(voidWraith);
				break;
			case ChainedSpectre:
				gui.changeEntry(chainedSpectre);
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
			case EtherealGuardian:
				gui.changeEntry(etherealGuardian);
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
