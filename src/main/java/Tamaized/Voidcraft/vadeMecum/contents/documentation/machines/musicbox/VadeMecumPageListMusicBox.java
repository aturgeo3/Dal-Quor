package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.musicbox;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMusicBox implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.blocks.voidBox).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.voidBox"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(Blocks.JUKEBOX),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.items.voidStar),
						new ItemStack(voidCraft.items.voidCloth) }, new ItemStack(voidCraft.blocks.voidBox, 1))) };
	}

}
