package Tamaized.Voidcraft.vadeMecum.entry.main;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntryList;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VadeMecumMainEntry extends VadeMecumEntry {

	public VadeMecumMainEntry() {
		super("Void Vade Mecum", null, null);
	}

	@Override
	public void init(VadeMecumGUI gui) {
		clearButtons();
		addButton(new VadeMecumButton(gui, VadeMecumEntryList.getEntryID(VadeMecumEntryList.Entry.BLOCKS), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, "Blocks", new ItemStack(Item.getItemFromBlock(voidCraft.blocks.blockVoidcrystal))));
		addButton(new VadeMecumButton(gui, VadeMecumEntryList.getEntryID(VadeMecumEntryList.Entry.MACHINES), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 1), 100, 20, "Machines", new ItemStack(Item.getItemFromBlock(voidCraft.blocks.voidMacerator))));
		addButton(new VadeMecumButton(gui, VadeMecumEntryList.getEntryID(VadeMecumEntryList.Entry.ITEMS), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 2), 100, 20, "Items", new ItemStack(voidCraft.items.voidcrystal)));
		addButton(new VadeMecumButton(gui, VadeMecumEntryList.getEntryID(VadeMecumEntryList.Entry.TOOLS), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 3), 100, 20, "Tools", new ItemStack(voidCraft.tools.voidPickaxe)));
		addButton(new VadeMecumButton(gui, VadeMecumEntryList.getEntryID(VadeMecumEntryList.Entry.WEAPONS), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 4), 100, 20, "Weapons", new ItemStack(voidCraft.tools.demonSword)));
		addButton(new VadeMecumButton(gui, VadeMecumEntryList.getEntryID(VadeMecumEntryList.Entry.ARMOR), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 5), 100, 20, "Armor", new ItemStack(voidCraft.armors.voidChest)));
		addButton(new VadeMecumButton(gui, VadeMecumEntryList.getEntryID(VadeMecumEntryList.Entry.ETHEREALFRUIT), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 0), 100, 20, "Ethereal Fruit", new ItemStack(voidCraft.items.etherealFruit)));
		addButton(new VadeMecumButton(gui, VadeMecumEntryList.getEntryID(VadeMecumEntryList.Entry.MOBS), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 1), 100, 20, "Mobs", new ItemStack(Items.SKULL, 1, 1)));
		addButton(new VadeMecumButton(gui, VadeMecumEntryList.getEntryID(VadeMecumEntryList.Entry.BOSSES), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 2), 100, 20, "Bosses", new ItemStack(voidCraft.items.ChainedSkull)));
		addButton(new VadeMecumButton(gui, VadeMecumEntryList.getEntryID(VadeMecumEntryList.Entry.VOID), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 3), 100, 20, "The Void", new ItemStack(Item.getItemFromBlock(voidCraft.blocks.blockPortalVoid))));
		addButton(new VadeMecumButton(gui, VadeMecumEntryList.getEntryID(VadeMecumEntryList.Entry.VOIDICINFUSION), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 4), 100, 20, "Voidic Infusion", new ItemStack(voidCraft.items.voidicSuppressor)));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (VadeMecumEntryList.getEntryFromID(id)) {
			case BLOCKS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.BLOCKS);
				break;
			case MACHINES:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.MACHINES);
				break;
			case ITEMS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.ITEMS);
				break;
			case TOOLS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.TOOLS);
				break;
			case WEAPONS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.WEAPONS);
				break;
			case ARMOR:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.ARMOR);
				break;
			case ETHEREALFRUIT:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.ETHEREALFRUIT);
				break;
			case MOBS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.MOBS);
				break;
			case BOSSES:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.BOSSES);
				break;
			case VOID:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.VOID);
				break;
			case VOIDICINFUSION:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.VOIDICINFUSION);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.MAIN);
				break;
		}
	}

	public int getPageLength() {
		return 1;
	}

}
