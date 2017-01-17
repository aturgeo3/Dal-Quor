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
		super("docs_Weapons", voidCraft.modid + ".VadeMecum.docs.title.weps", back, null);
	}

	@Override
	public void initObjects() {
		voidSword = new VadeMecumEntry("docs_Weapons_voidSword", "", this, new VadeMecumPageListVoidSword());
		angelicSword = new VadeMecumEntry("docs_Weapons_angelicSword", "", this, new VadeMecumPageListAngelicSword());
		bindSword = new VadeMecumEntry("docs_Weapons_bindSword", "", this, new VadeMecumPageListBindSword());
		moltenSword = new VadeMecumEntry("docs_Weapons_moltenSword", "", this, new VadeMecumPageListMoltenSword());
		archAngelicSword = new VadeMecumEntry("docs_Weapons_archAngelicSword", "", this, new VadeMecumPageListArchSword());
		demonSword = new VadeMecumEntry("docs_Weapons_demonSword", "", this, new VadeMecumPageListDemonSword());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(gui, getEntryID(Entry.VoidSword), new ItemStack(voidCraft.tools.voidSword).getDisplayName(), new ItemStack(voidCraft.tools.voidSword));
		addButton(gui, getEntryID(Entry.AngelicSword), new ItemStack(voidCraft.tools.angelicSword).getDisplayName(), new ItemStack(voidCraft.tools.angelicSword));
		addButton(gui, getEntryID(Entry.BindSword), new ItemStack(voidCraft.tools.chainSword).getDisplayName(), new ItemStack(voidCraft.tools.chainSword));
		addButton(gui, getEntryID(Entry.MoltenSword), new ItemStack(voidCraft.tools.moltenSword).getDisplayName(), new ItemStack(voidCraft.tools.moltenSword));
		addButton(gui, getEntryID(Entry.ArchAngelicSword), new ItemStack(voidCraft.tools.archSword).getDisplayName(), new ItemStack(voidCraft.tools.archSword));
		addButton(gui, getEntryID(Entry.DemonSword), new ItemStack(voidCraft.tools.demonSword).getDisplayName(), new ItemStack(voidCraft.tools.demonSword));
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

}
