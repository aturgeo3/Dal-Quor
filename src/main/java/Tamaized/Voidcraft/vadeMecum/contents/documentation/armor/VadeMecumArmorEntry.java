package Tamaized.Voidcraft.vadeMecum.contents.documentation.armor;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.armor.voidarmor.VadeMecumPageListVoidArmor;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.armor.xia.VadeMecumPageListXiaArmor;
import net.minecraft.item.ItemStack;

public class VadeMecumArmorEntry extends VadeMecumEntry {

	public static enum Entry {
		Void, Xia
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidCrystal;
	public VadeMecumEntry xia;

	public VadeMecumArmorEntry(VadeMecumEntry back) {
		super("docs_ARMOR", "Armors", back, null);
	}

	@Override
	public void initObjects() {
		voidCrystal = new VadeMecumEntry("docs_Armor_voidCrystal", "", this, new VadeMecumPageListVoidArmor());
		xia = new VadeMecumEntry("docs_Armor_xia", "", this, new VadeMecumPageListXiaArmor());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(new VadeMecumButton(gui, getEntryID(Entry.Void), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, voidCraft.modid + ".VadeMecum.docs.title.voidArmor", new ItemStack(voidCraft.armors.voidHelmet)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.Xia), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 1), 100, 20, voidCraft.modid + ".VadeMecum.docs.title.xiaArmor", new ItemStack(voidCraft.armors.xiaHelmet)));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case Void:
				gui.changeEntry(voidCrystal);
				break;
			case Xia:
				gui.changeEntry(xia);
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
