package Tamaized.Voidcraft.vadeMecum.contents.documentation;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.proxy.ClientProxy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumButton;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VadeMecumDocumentationEntry extends VadeMecumEntry {

	public VadeMecumDocumentationEntry() {
		super("docsMainEntry", voidCraft.modid + ".VadeMecum.title.docs", null, null);
	}

	@Override
	public void init(VadeMecumGUI gui) {
		clearButtons();
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.BLOCKS), voidCraft.modid + ".VadeMecum.docs.title.blocks", new ItemStack(Item.getItemFromBlock(voidCraft.blocks.blockVoidcrystal)));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.MACHINES), voidCraft.modid + ".VadeMecum.docs.title.machines", new ItemStack(Item.getItemFromBlock(voidCraft.blocks.voidMacerator)));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.ITEMS), voidCraft.modid + ".VadeMecum.docs.title.items", new ItemStack(voidCraft.items.voidcrystal));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.TOOLS), voidCraft.modid + ".VadeMecum.docs.title.tools", new ItemStack(voidCraft.tools.voidPickaxe));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.WEAPONS), voidCraft.modid + ".VadeMecum.docs.title.weps", new ItemStack(voidCraft.tools.demonSword));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.ARMOR), voidCraft.modid + ".VadeMecum.docs.title.armor", new ItemStack(voidCraft.armors.voidChest));

		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.ETHEREALFRUIT), voidCraft.modid + ".VadeMecum.docs.title.fruit", new ItemStack(voidCraft.items.etherealFruit));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.MOBS), voidCraft.modid + ".VadeMecum.docs.title.mobs", new ItemStack(Items.SKULL, 1, 1));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.BOSSES), voidCraft.modid + ".VadeMecum.docs.title.bosses", new ItemStack(voidCraft.items.ChainedSkull));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.VOID), voidCraft.modid + ".VadeMecum.docs.title.theVoid", new ItemStack(Item.getItemFromBlock(voidCraft.blocks.blockPortalVoid)));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.VOIDICINFUSION), voidCraft.modid + ".VadeMecum.docs.title.voidicInfusion", new ItemStack(voidCraft.items.voidicSuppressor));
		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.XIA), voidCraft.modid + ".VadeMecum.docs.title.xia", new ItemStack(voidCraft.blocks.blockPortalXia));

		addButton(gui, VadeMecumDocumentationEntryList.getEntryID(VadeMecumDocumentationEntryList.Entry.STARFORGE), voidCraft.modid + ".VadeMecum.docs.title.starforge", new ItemStack(voidCraft.blocks.starforgeStation));
	}

	@Override
	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {
		switch (VadeMecumDocumentationEntryList.getEntryFromID(id)) {
			case BLOCKS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.BLOCKS);
				break;
			case MACHINES:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MACHINES);
				break;
			case ITEMS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.ITEMS);
				break;
			case TOOLS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.TOOLS);
				break;
			case WEAPONS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.WEAPONS);
				break;
			case ARMOR:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.ARMOR);
				break;
			case ETHEREALFRUIT:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.ETHEREALFRUIT);
				break;
			case MOBS:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MOBS);
				break;
			case BOSSES:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.BOSSES);
				break;
			case VOID:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.VOID);
				break;
			case VOIDICINFUSION:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.VOIDICINFUSION);
				break;
			case XIA:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.XIA);
				break;
			case STARFORGE:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.STARFORGE);
				break;
			default:
				gui.changeEntry(ClientProxy.vadeMecumEntryList.Docs.MAIN);
				break;
		}
	}

}
