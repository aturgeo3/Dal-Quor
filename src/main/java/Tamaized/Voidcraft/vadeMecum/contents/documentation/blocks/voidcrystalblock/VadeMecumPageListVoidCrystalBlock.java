package Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.voidcrystalblock;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidCrystalBlock implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Void Crystal Block", "This block is the product of bringing together nine Void Crystals. These blocks cannot do much on their own, but holding them gives the same sensation felt when close to the bottom layer of bedrock in the Overworld. Could bringing these blocks close to the void have an effect?"),
				new VadeMecumPage("", "This block is used to construct a portal to the Void. Create a formation in the same way as you would to construct a nether portal frame. After doing so, collect an Obsidian Flask and toss it onto the top face of the bottom layer frame. If done correctly a portal will be open, leading to the Void."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(voidCraft.items.voidcrystal) }, new ItemStack(voidCraft.blocks.blockVoidcrystal))) };
	}

}
