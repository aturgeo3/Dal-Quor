package Tamaized.Voidcraft.vadeMecum.contents.documentation.items;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.chainedskull.VadeMecumPageListChainedSkull;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.charredbone.VadeMecumPageListCharredBone;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.dusts.VadeMecumPageListVoidDusts;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.ectoplasm.VadeMecumPageListEctoplasm;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.moltenvoidchain.VadeMecumPageListMoltenChain;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.moltenvoidchainpart.VadeMecumPageListMoltenChainPart;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.obsidianflask.VadeMecumPageListObsidianFlask;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidchain.VadeMecumPageListVoidChain;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidcloth.VadeMecumPageListVoidCloth;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidcrystal.VadeMecumPageListVoidCrystal;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidicsupressor.VadeMecumPageListSuppressor;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidstar.VadeMecumPageListVoidStar;
import net.minecraft.item.ItemStack;

public class VadeMecumItemsEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidCrystal, Ectoplasm, VoidChain, MoltenVoidChainPart, MoltenVoidChain, CharredBone, VoidStar, ObsidianFlask, VoidInfusedCloth, VoidDusts, ChainedSkull, VoidicSupressor, VoidicDrill
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidCrystal;
	public VadeMecumEntry ectoplasm;
	public VadeMecumEntry voidChain;
	public VadeMecumEntry moltenVoidChainPart;
	public VadeMecumEntry moltenVoidChain;
	public VadeMecumEntry charredBone;
	public VadeMecumEntry voidStar;
	public VadeMecumEntry obsidianFlask;
	public VadeMecumEntry voidInfusedCloth;
	public VadeMecumEntry voidDusts;
	public VadeMecumEntry chainedSkull;
	public VadeMecumEntry voidicSupressor;

	public VadeMecumItemsEntry(VadeMecumEntry back) {
		super("docs_Items", "Items", back, null);
	}

	@Override
	public void initObjects() {
		voidCrystal = new VadeMecumEntry("docs_Items_voidCrystal", "", this, new VadeMecumPageListVoidCrystal());
		ectoplasm = new VadeMecumEntry("docs_Items_ectoplasm", "", this, new VadeMecumPageListEctoplasm());
		voidChain = new VadeMecumEntry("docs_Items_voidChain", "", this, new VadeMecumPageListVoidChain());
		moltenVoidChainPart = new VadeMecumEntry("docs_Items_moltenVoidChainPart", "", this, new VadeMecumPageListMoltenChainPart());
		moltenVoidChain = new VadeMecumEntry("docs_Items_moltenVoidChain", "", this, new VadeMecumPageListMoltenChain());
		charredBone = new VadeMecumEntry("docs_Items_charredBone", "", this, new VadeMecumPageListCharredBone());
		voidStar = new VadeMecumEntry("docs_Items_voidStar", "", this, new VadeMecumPageListVoidStar());
		obsidianFlask = new VadeMecumEntry("docs_Items_obsidianFlask", "", this, new VadeMecumPageListObsidianFlask());
		voidInfusedCloth = new VadeMecumEntry("docs_Items_voidInfusedCloth", "", this, new VadeMecumPageListVoidCloth());
		voidDusts = new VadeMecumEntry("docs_Items_voidDusts", "", this, new VadeMecumPageListVoidDusts());
		chainedSkull = new VadeMecumEntry("docs_Items_chainedSkull", "", this, new VadeMecumPageListChainedSkull());
		voidicSupressor = new VadeMecumEntry("docs_Items_voidicSupressor", "", this, new VadeMecumPageListSuppressor());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidCrystal), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, "Void Crystal", new ItemStack(voidCraft.items.voidcrystal)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.Ectoplasm), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 1), 100, 20, "Ectoplasm", new ItemStack(voidCraft.items.ectoplasm)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidChain), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 2), 100, 20, "VoidChain", new ItemStack(voidCraft.items.voidChain)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.MoltenVoidChainPart), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 3), 100, 20, "Molten Void Chain Part", new ItemStack(voidCraft.items.MoltenvoidChainPart)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.MoltenVoidChain), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 4), 100, 20, "Molten Void Chain", new ItemStack(voidCraft.items.MoltenvoidChain)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.CharredBone), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 5), 100, 20, "Charred Bone", new ItemStack(voidCraft.items.burnBone)));

		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidStar), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 0), 100, 20, "Void Star", new ItemStack(voidCraft.items.voidStar)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.ObsidianFlask), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 1), 100, 20, "Obsidian Flask", new ItemStack(voidCraft.items.obsidianFlask)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidInfusedCloth), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 2), 100, 20, "Void Infused Cloth", new ItemStack(voidCraft.items.voidCloth)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidDusts), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 3), 100, 20, "Void Dusts", new ItemStack(voidCraft.items.diamondDust)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.ChainedSkull), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 4), 100, 20, "Chained Skull", new ItemStack(voidCraft.items.ChainedSkull)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidicSupressor), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 5), 100, 20, "Voidic Supressor", new ItemStack(voidCraft.items.voidicSuppressor)));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case VoidCrystal:
				gui.changeEntry(voidCrystal);
				break;
			case Ectoplasm:
				gui.changeEntry(ectoplasm);
				break;
			case VoidChain:
				gui.changeEntry(voidChain);
				break;
			case MoltenVoidChainPart:
				gui.changeEntry(moltenVoidChainPart);
				break;
			case MoltenVoidChain:
				gui.changeEntry(moltenVoidChain);
				break;
			case CharredBone:
				gui.changeEntry(charredBone);
				break;
			case VoidStar:
				gui.changeEntry(voidStar);
				break;
			case ObsidianFlask:
				gui.changeEntry(obsidianFlask);
				break;
			case VoidInfusedCloth:
				gui.changeEntry(voidInfusedCloth);
				break;
			case VoidDusts:
				gui.changeEntry(voidDusts);
				break;
			case ChainedSkull:
				gui.changeEntry(chainedSkull);
				break;
			case VoidicSupressor:
				gui.changeEntry(voidicSupressor);
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
