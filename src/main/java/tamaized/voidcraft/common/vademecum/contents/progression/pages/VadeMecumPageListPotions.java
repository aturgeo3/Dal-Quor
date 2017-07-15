package tamaized.voidcraft.common.vademecum.contents.progression.pages;

import net.minecraft.item.ItemStack;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

import java.util.ArrayList;

public class VadeMecumPageListPotions implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumPage> pages = new ArrayList<IVadeMecumPage>();
		pages.add(new VadeMecumPage("voidcraft.VadeMecum.progression.title.potions", "voidcraft.VadeMecum.progression.desc.potions"));
		if (cap.hasCategory(IVadeMecumCapability.Category.Flame))
			pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskFire))));
		if (cap.hasCategory(IVadeMecumCapability.Category.Freeze))
			pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskFreeze))));
		if (cap.hasCategory(IVadeMecumCapability.Category.Shock))
			pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskShock))));
		if (cap.hasCategory(IVadeMecumCapability.Category.AcidSpray))
			pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskAcid))));
		if (cap.hasCategory(IVadeMecumCapability.Category.Implosion))
			pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskVoid))));
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
