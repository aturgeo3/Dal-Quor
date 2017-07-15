package tamaized.voidcraft.common.vademecum.contents.documentation.armor;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.client.gui.VadeMecumGUI;
import tamaized.voidcraft.common.vademecum.VadeMecumEntry;
import tamaized.voidcraft.common.vademecum.contents.documentation.armor.voidarmor.VadeMecumPageListVoidArmor;
import tamaized.voidcraft.common.vademecum.contents.documentation.armor.xia.VadeMecumPageListXiaArmor;
import tamaized.voidcraft.proxy.ClientProxy;

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
		addButton(gui, getEntryID(Entry.Void), VoidCraft.modid + ".VadeMecum.docs.title.voidArmor", new ItemStack(VoidCraft.armors.voidHelmet));
		addButton(gui, getEntryID(Entry.Xia), VoidCraft.modid + ".VadeMecum.docs.title.xiaArmor", new ItemStack(VoidCraft.armors.xiaHelmet));
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

}
