package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.chainedskull;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListChainedSkull {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Chained Skull", "Oddly, covering a Wither's skull in Charred Bones followed by Molten Chains transforms the skull. You do not know if it is your mind playing tricks on you or if this skull is alive... breathing... speaking to you. It wants you to place it down. It tells you that you can end the world with it, that you should.                                                 Placing down the skull will result in explosions and a boss fight, be prepared."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.burnBone),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.burnBone),
						new ItemStack(Items.SKULL, 1, 1),
						new ItemStack(voidCraft.items.burnBone),
						new ItemStack(voidCraft.items.MoltenvoidChain),
						new ItemStack(voidCraft.items.burnBone),
						new ItemStack(voidCraft.items.MoltenvoidChain) }, new ItemStack(voidCraft.items.ChainedSkull)))};
	}

}
