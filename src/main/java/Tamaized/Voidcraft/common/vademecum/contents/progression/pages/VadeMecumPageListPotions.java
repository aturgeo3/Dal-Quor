package tamaized.voidcraft.common.vademecum.contents.progression.pages;

import java.util.ArrayList;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumCraftingAlchemy;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import tamaized.voidcraft.common.vademecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListPotions implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumPage> pages = new ArrayList<IVadeMecumPage>();
		pages.add(new VadeMecumPage("voidcraft.VadeMecum.progression.title.potions", "voidcraft.VadeMecum.progression.desc.potions"));
		if (cap.hasCategory(IVadeMecumCapability.Category.Flame)) pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskFire))));
		if (cap.hasCategory(IVadeMecumCapability.Category.Freeze)) pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskFreeze))));
		if (cap.hasCategory(IVadeMecumCapability.Category.Shock)) pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskShock))));
		if (cap.hasCategory(IVadeMecumCapability.Category.AcidSpray)) pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskAcid))));
		if (cap.hasCategory(IVadeMecumCapability.Category.Implosion)) pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskVoid))));
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
