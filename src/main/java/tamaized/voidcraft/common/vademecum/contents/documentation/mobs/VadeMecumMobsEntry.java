package tamaized.voidcraft.common.vademecum.contents.documentation.mobs;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.client.gui.VadeMecumGUI;
import tamaized.voidcraft.common.vademecum.VadeMecumEntry;
import tamaized.voidcraft.common.vademecum.contents.documentation.mobs.etherealGuardian.VadeMecumPageListEtherealGuardian;
import tamaized.voidcraft.common.vademecum.contents.documentation.mobs.herobrinecreeper.VadeMecumPageListHerobrineCreeper;
import tamaized.voidcraft.common.vademecum.contents.documentation.mobs.lich.VadeMecumPageListLich;
import tamaized.voidcraft.common.vademecum.contents.documentation.mobs.spectre.VadeMecumPageListSpectreChain;
import tamaized.voidcraft.common.vademecum.contents.documentation.mobs.voidicParrot.VadeMecumPageListVoidicParrot;
import tamaized.voidcraft.common.vademecum.contents.documentation.mobs.wraith.VadeMecumPageListVoidWraith;
import tamaized.voidcraft.common.vademecum.contents.documentation.mobs.wrath.VadeMecumPageListVoidWrath;
import tamaized.voidcraft.proxy.ClientProxy;
import tamaized.voidcraft.registry.VoidCraftItems;

public class VadeMecumMobsEntry extends VadeMecumEntry {

	public VadeMecumEntry voidWraith;
	public VadeMecumEntry chainedSpectre;
	public VadeMecumEntry voidWrath;
	public VadeMecumEntry voidLich;
	public VadeMecumEntry herobrineCreeper;
	public VadeMecumEntry etherealGuardian;
	public VadeMecumEntry voidicParrot;

	public VadeMecumMobsEntry(VadeMecumEntry back) {
		super("docs_Mobs", VoidCraft.modid + ".VadeMecum.docs.title.mobs", back, null);
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	@Override
	public void initObjects() {
		voidWraith = new VadeMecumEntry("docs_Mobs_voidWraith", "", this, new VadeMecumPageListVoidWraith());
		chainedSpectre = new VadeMecumEntry("docs_Mobs_chainedSpectre", "", this, new VadeMecumPageListSpectreChain());
		voidWrath = new VadeMecumEntry("docs_Mobs_voidWrath", "", this, new VadeMecumPageListVoidWrath());
		voidLich = new VadeMecumEntry("docs_Mobs_voidLich", "", this, new VadeMecumPageListLich());
		herobrineCreeper = new VadeMecumEntry("docs_Mobs_herobrineCreeper", "", this, new VadeMecumPageListHerobrineCreeper());
		etherealGuardian = new VadeMecumEntry("docs_Mobs_etherealGuardian", "", this, new VadeMecumPageListEtherealGuardian());
		voidicParrot = new VadeMecumEntry("docs_Mobs_voidicParrot", "", this, new VadeMecumPageListVoidicParrot());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(gui, getEntryID(Entry.VoidWraith), VoidCraft.modid + ".VadeMecum.docs.title.voidWraith", new ItemStack(VoidCraftItems.ectoplasm));
		addButton(gui, getEntryID(Entry.ChainedSpectre), VoidCraft.modid + ".VadeMecum.docs.title.chainedSpectre", new ItemStack(VoidCraftItems.voidChain));
		addButton(gui, getEntryID(Entry.VoidWrath), VoidCraft.modid + ".VadeMecum.docs.title.voidWrath", new ItemStack(VoidCraftItems.burnBone));
		addButton(gui, getEntryID(Entry.VoidLich), VoidCraft.modid + ".VadeMecum.docs.title.lich", new ItemStack(VoidCraftItems.voidCloth));
		addButton(gui, getEntryID(Entry.HerobrineCreeper), VoidCraft.modid + ".VadeMecum.docs.title.herobrineCreeper", new ItemStack(Items.GUNPOWDER));
		addButton(gui, getEntryID(Entry.EtherealGuardian), VoidCraft.modid + ".VadeMecum.docs.title.etherealGuardian", new ItemStack(VoidCraftItems.voidicPhlogiston));
		addButton(gui, getEntryID(Entry.VoidicParrot), VoidCraft.modid + ".VadeMecum.docs.title.voidicParrot", new ItemStack(Items.FEATHER));
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
			case VoidicParrot:
				gui.changeEntry(voidicParrot);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}

	public static enum Entry {
		VoidWraith, ChainedSpectre, VoidWrath, VoidLich, HerobrineCreeper, EtherealGuardian, VoidicParrot
	}

}
