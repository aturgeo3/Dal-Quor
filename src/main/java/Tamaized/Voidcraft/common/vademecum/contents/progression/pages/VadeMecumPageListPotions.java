package Tamaized.Voidcraft.common.vademecum.contents.progression.pages;

import java.util.ArrayList;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.common.vademecum.VadeMecumCraftingAlchemy;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPage;
import Tamaized.Voidcraft.common.vademecum.VadeMecumPageCrafting;
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
