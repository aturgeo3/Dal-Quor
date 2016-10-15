package Tamaized.Voidcraft.vadeMecum.contents.documentation.items;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
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
		super("Items", back, null);
	}

	@Override
	public void initObjects() {
		voidCrystal = new VadeMecumEntry("", this, VadeMecumPageListVoidCrystal.getPageList());
		ectoplasm = new VadeMecumEntry("", this, VadeMecumPageListEctoplasm.getPageList());
		voidChain = new VadeMecumEntry("", this, VadeMecumPageListVoidChain.getPageList());
		moltenVoidChainPart = new VadeMecumEntry("", this, VadeMecumPageListMoltenChainPart.getPageList());
		moltenVoidChain = new VadeMecumEntry("", this, VadeMecumPageListMoltenChain.getPageList());
		charredBone = new VadeMecumEntry("", this, VadeMecumPageListCharredBone.getPageList());
		voidStar = new VadeMecumEntry("", this, VadeMecumPageListVoidStar.getPageList());
		obsidianFlask = new VadeMecumEntry("", this, VadeMecumPageListObsidianFlask.getPageList());
		voidInfusedCloth = new VadeMecumEntry("", this, VadeMecumPageListVoidCloth.getPageList());
		voidDusts = new VadeMecumEntry("", this, VadeMecumPageListVoidDusts.getPageList());
		chainedSkull = new VadeMecumEntry("", this, VadeMecumPageListChainedSkull.getPageList());
		voidicSupressor = new VadeMecumEntry("", this, VadeMecumPageListSuppressor.getPageList());
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
				gui.changeEntry(gui.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}

	public int getPageLength() {
		return 1;
	}

}
