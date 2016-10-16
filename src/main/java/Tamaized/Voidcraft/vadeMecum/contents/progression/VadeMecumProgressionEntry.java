package Tamaized.Voidcraft.vadeMecum.contents.progression;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.handlers.VadeMecumPacketHandler;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VadeMecumProgressionEntry extends VadeMecumEntry {

	public static enum Entry {
		Test1, Test2
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public VadeMecumProgressionEntry() {
		super("progressionMainEntry", "Void Vade Mecum - Progression", null, null);
	}

	@Override
	public void init(VadeMecumGUI gui) {
		clearButtons();
		addButton(new VadeMecumButton(gui, getEntryID(Entry.Test1), gui.getX() + 48 + (170 * 0), gui.getY() + 35 + (25 * 0), 100, 20, "Blocks", new ItemStack(Item.getItemFromBlock(voidCraft.blocks.blockVoidcrystal))));
		addButton(new VadeMecumButton(gui, getEntryID(Entry.Test2), gui.getX() + 48 + (170 * 1), gui.getY() + 35 + (25 * 4), 100, 20, "Voidic Infusion", new ItemStack(voidCraft.items.voidicSuppressor)));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (getEntryFromID(id)) {
			case Test1:
				gui.sendPacketUpdates(VadeMecumPacketHandler.RequestType.ACTIVE_CURRENT_SET, IVadeMecumCapability.getActivePowerID(IVadeMecumCapability.ActivePower.TEST));
				break;
			case Test2:
				gui.sendPacketUpdates(VadeMecumPacketHandler.RequestType.ACTIVE_CURRENT_SET, -1);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList);
				break;
		}
	}

	public int getPageLength() {
		return 1;
	}

}
