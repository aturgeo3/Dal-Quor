package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.angelicsword.VadeMecumPageListAngelicSword;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.archangelicsword.VadeMecumPageListArchSword;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.bindsword.VadeMecumPageListBindSword;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.demonsword.VadeMecumPageListDemonSword;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.moltensword.VadeMecumPageListMoltenSword;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.voidsword.VadeMecumPageListVoidSword;
import net.minecraft.item.ItemStack;

public class VadeMecumWeaponsEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidSword, AngelicSword, BindSword, MoltenSword, ArchAngelicSword, DemonSword
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidSword;
	public VadeMecumEntry angelicSword;
	public VadeMecumEntry bindSword;
	public VadeMecumEntry moltenSword;
	public VadeMecumEntry archAngelicSword;
	public VadeMecumEntry demonSword;

	public VadeMecumWeaponsEntry(VadeMecumEntry back) {
		super("docs_Weapons", "Weapons", back, null);
	}

	@Override
	public void initObjects() {
		voidSword = new VadeMecumEntry("docs_Weapons_voidSword", "", this, VadeMecumPageListVoidSword.getPageList());
		angelicSword = new VadeMecumEntry("docs_Weapons_angelicSword", "", this, VadeMecumPageListAngelicSword.getPageList());
		bindSword = new VadeMecumEntry("docs_Weapons_bindSword", "", this, VadeMecumPageListBindSword.getPageList());
		moltenSword = new VadeMecumEntry("docs_Weapons_moltenSword", "", this, VadeMecumPageListMoltenSword.getPageList());
		archAngelicSword = new VadeMecumEntry("docs_Weapons_archAngelicSword", "", this, VadeMecumPageListArchSword.getPageList());
		demonSword = new VadeMecumEntry("docs_Weapons_demonSword", "", this, VadeMecumPageListDemonSword.getPageList());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidSword), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, "Void Sword", new ItemStack(voidCraft.tools.voidSword)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.AngelicSword), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 1), 100, 20, "Angelic Sword", new ItemStack(voidCraft.tools.angelicSword)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.BindSword), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 2), 100, 20, "Sword of Binding", new ItemStack(voidCraft.tools.chainSword)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.MoltenSword), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 3), 100, 20, "Molten Sword", new ItemStack(voidCraft.tools.moltenSword)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.ArchAngelicSword), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 4), 100, 20, "ArchAngelic Sword", new ItemStack(voidCraft.tools.archSword)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.DemonSword), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 5), 100, 20, "Demonic Sword", new ItemStack(voidCraft.tools.demonSword)));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case VoidSword:
				gui.changeEntry(voidSword);
				break;
			case AngelicSword:
				gui.changeEntry(angelicSword);
				break;
			case BindSword:
				gui.changeEntry(bindSword);
				break;
			case MoltenSword:
				gui.changeEntry(moltenSword);
				break;
			case ArchAngelicSword:
				gui.changeEntry(archAngelicSword);
				break;
			case DemonSword:
				gui.changeEntry(demonSword);
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
