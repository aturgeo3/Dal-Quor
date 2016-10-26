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
				new VadeMecumPage("Void Music Box", "A special jukebox which was first designed by Void Liches. It is special in the sense that it can read any fully functional music disk. This music box can take input disks and be extracted from using other machines, such as a hopper. It also has an auto-insert function and will auto-play the next pending disc. This, paired with its loop functionality, can be used to easily create a space which always has music playing in the background."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
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
