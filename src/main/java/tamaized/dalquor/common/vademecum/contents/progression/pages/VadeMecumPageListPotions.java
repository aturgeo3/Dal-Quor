package tamaized.dalquor.common.vademecum.contents.progression.pages;

import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.vademecum.IVadeMecumPage;
import tamaized.dalquor.common.vademecum.IVadeMecumPageProvider;
import tamaized.dalquor.common.vademecum.VadeMecumPage;

import java.util.ArrayList;
import java.util.List;

public class VadeMecumPageListPotions implements IVadeMecumPageProvider {

	@Override
	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) { // TODO
		List<IVadeMecumPage> pages = new ArrayList<>();
		pages.add(new VadeMecumPage("dalquor.VadeMecum.progression.title.potions", "dalquor.VadeMecum.progression.desc.potions"));
		/*if (cap.hasCategory(IVadeMecumCapability.Category.Flame))
			pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskFire))));
		if (cap.hasCategory(IVadeMecumCapability.Category.Freeze))
			pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskFreeze))));
		if (cap.hasCategory(IVadeMecumCapability.Category.Shock))
			pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskShock))));
		if (cap.hasCategory(IVadeMecumCapability.Category.AcidSpray))
			pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskAcid))));
		if (cap.hasCategory(IVadeMecumCapability.Category.Implosion))
			pages.add(new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", new ItemStack(VoidCraft.items.obsidianFlaskVoid))));*/
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
