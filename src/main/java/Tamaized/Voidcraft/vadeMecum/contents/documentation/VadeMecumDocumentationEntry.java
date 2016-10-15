package Tamaized.Voidcraft.vadeMecum.contents.documentation;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VadeMecumDocumentationEntry extends VadeMecumEntry {

	public VadeMecumDocumentationEntry() {
		super("Void Vade Mecum - Documentation", null, null);
	}

	@Override
	public void init(VadeMecumGUI gui) {
		clearButtons();
		addButton(new VadeMecumButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.BLOCKS), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, "Blocks", new ItemStack(Item.getItemFromBlock(voidCraft.blocks.blockVoidcrystal))));
		addButton(new VadeMecumButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.MACHINES), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 1), 100, 20, "Machines", new ItemStack(Item.getItemFromBlock(voidCraft.blocks.voidMacerator))));
		addButton(new VadeMecumButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.ITEMS), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 2), 100, 20, "Items", new ItemStack(voidCraft.items.voidcrystal)));
		addButton(new VadeMecumButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.TOOLS), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 3), 100, 20, "Tools", new ItemStack(voidCraft.tools.voidPickaxe)));
		addButton(new VadeMecumButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.WEAPONS), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 4), 100, 20, "Weapons", new ItemStack(voidCraft.tools.demonSword)));
		addButton(new VadeMecumButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.ARMOR), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 5), 100, 20, "Armor", new ItemStack(voidCraft.armors.voidChest)));
		addButton(new VadeMecumButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.ETHEREALFRUIT), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 0), 100, 20, "Ethereal Fruit", new ItemStack(voidCraft.items.etherealFruit)));
		addButton(new VadeMecumButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.MOBS), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 1), 100, 20, "Mobs", new ItemStack(Items.SKULL, 1, 1)));
		addButton(new VadeMecumButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.BOSSES), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 2), 100, 20, "Bosses", new ItemStack(voidCraft.items.ChainedSkull)));
		addButton(new VadeMecumButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.VOID), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 3), 100, 20, "The Void", new ItemStack(Item.getItemFromBlock(voidCraft.blocks.blockPortalVoid))));
		addButton(new VadeMecumButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.VOIDICINFUSION), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 4), 100, 20, "Voidic Infusion", new ItemStack(voidCraft.items.voidicSuppressor)));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (VadeMecumDocumentationEntryList.getEntryFromID(id)) {
			case BLOCKS:
				gui.changeEntry(gui.vadeMecumEntryList.Docs.BLOCKS);
				break;
			case MACHINES:
				gui.changeEntry(gui.vadeMecumEntryList.Docs.MACHINES);
				break;
			case ITEMS:
				gui.changeEntry(gui.vadeMecumEntryList.Docs.ITEMS);
				break;
			case TOOLS:
				gui.changeEntry(gui.vadeMecumEntryList.Docs.TOOLS);
				break;
			case WEAPONS:
				gui.changeEntry(gui.vadeMecumEntryList.Docs.WEAPONS);
				break;
			case ARMOR:
				gui.changeEntry(gui.vadeMecumEntryList.Docs.ARMOR);
				break;
			case ETHEREALFRUIT:
				gui.changeEntry(gui.vadeMecumEntryList.Docs.ETHEREALFRUIT);
				break;
			case MOBS:
				gui.changeEntry(gui.vadeMecumEntryList.Docs.MOBS);
				break;
			case BOSSES:
				gui.changeEntry(gui.vadeMecumEntryList.Docs.BOSSES);
				break;
			case VOID:
				gui.changeEntry(gui.vadeMecumEntryList.Docs.VOID);
				break;
			case VOIDICINFUSION:
				gui.changeEntry(gui.vadeMecumEntryList.Docs.VOIDICINFUSION);
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
