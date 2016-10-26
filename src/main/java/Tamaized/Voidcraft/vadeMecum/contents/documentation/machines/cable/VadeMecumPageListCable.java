package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.cable;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListCable implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Voidic Cable", "The Voidic Generator is certainly able to supply power, however, it may not be able to reach all the machines you would like it to. However, by combining Redstone, which naturally transmits power, with a Void Crystal Block, which in and of itself is a conduit for the Void's energies, one creates Void Cable. These cables will automatically connect to machines and each other when placed in the world. When connected to a something storing Voidic Power, they will relay"),
				new VadeMecumPage("", "it to all other machines they are connected to."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(voidCraft.blocks.blockVoidcrystal),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE) }, new ItemStack(voidCraft.blocks.voidicCable, 8))) };
	}

}
