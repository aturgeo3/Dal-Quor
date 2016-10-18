package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.realityhole.VadeMecumPageListRealityHole;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.ritual.VadeMecumPageListRitualBlock;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.softbedrock.VadeMecumPageListSoftBedrock;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidbrick.VadeMecumPageListVoidBrick;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidcrystalblock.VadeMecumPageListVoidCrystalBlock;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidcrystalore.VadeMecumPageListVoidCrystalOre;
import net.minecraft.item.ItemStack;

public class VadeMecumBlocksEntry extends VadeMecumEntry {

	public static enum Entry {
		VoidCrystalBlock, VoidCrystalOre, SoftBedrock, VoidBrick, HoleinReality, RitualBlock
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumEntry voidCrystalBlock;
	public VadeMecumEntry voidCrystalOre;
	public VadeMecumEntry softBedrock;
	public VadeMecumEntry voidBrick;
	public VadeMecumEntry holeInReality;
	public VadeMecumEntry ritualBlock;

	public VadeMecumBlocksEntry(VadeMecumEntry back) {
		super("docs_Blocks", "Blocks", back, null);
	}

	@Override
	public void initObjects() {
		voidCrystalBlock = new VadeMecumEntry("docs_Blocks_voidCrystalBlock", "", this, new VadeMecumPageListVoidCrystalBlock());
		voidCrystalOre = new VadeMecumEntry("docs_Blocks_voidCrystalOre", "", this, new VadeMecumPageListVoidCrystalOre());
		softBedrock = new VadeMecumEntry("docs_Blocks_softBedrock", "", this, new VadeMecumPageListSoftBedrock());
		voidBrick = new VadeMecumEntry("docs_Blocks_voidBrick", "", this, new VadeMecumPageListVoidBrick());
		holeInReality = new VadeMecumEntry("docs_Blocks_holeInReality", "", this, new VadeMecumPageListRealityHole());
		ritualBlock = new VadeMecumEntry("docs_Blocks_ritualBlock", "", this, new VadeMecumPageListRitualBlock());
	}

	@Override
	public void init(VadeMecumGUI gui) {
		initObjects();
		clearButtons();
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidCrystalBlock), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, "Void Crystal Block", new ItemStack(voidCraft.blocks.blockVoidcrystal)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidCrystalOre), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 1), 100, 20, "Void Crystal Ore", new ItemStack(voidCraft.blocks.oreVoidcrystal)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.SoftBedrock), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 2), 100, 20, "Soft Bedrock", new ItemStack(voidCraft.blocks.blockFakeBedrock)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.VoidBrick), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 3), 100, 20, "Void Brick", new ItemStack(voidCraft.blocks.blockVoidbrick)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.HoleinReality), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 4), 100, 20, "Hole in Reality", new ItemStack(voidCraft.blocks.realityHole)));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.RitualBlock), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 5), 100, 20, "Voidic Ritual Block", new ItemStack(voidCraft.blocks.ritualBlock)));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case VoidCrystalBlock:
				gui.changeEntry(voidCrystalBlock);
				break;
			case VoidCrystalOre:
				gui.changeEntry(voidCrystalOre);
				break;
			case SoftBedrock:
				gui.changeEntry(softBedrock);
				break;
			case VoidBrick:
				gui.changeEntry(voidBrick);
				break;
			case HoleinReality:
				gui.changeEntry(holeInReality);
				break;
			case RitualBlock:
				gui.changeEntry(ritualBlock);
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
