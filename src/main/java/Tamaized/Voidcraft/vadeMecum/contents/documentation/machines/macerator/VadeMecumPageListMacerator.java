package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.macerator;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingInfuser;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListMacerator implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Void Infused Macerator", "This Machine is the product of infusing a standard Furnace with Liquid Void. It takes Voidic Power to grind an input ore into four Void Infused dusts. You can process these dusts into a regular ingot using mundane machines."),
				new VadeMecumPageCrafting(new VadeMecumCraftingInfuser("Infusion Recipe", new ItemStack(Blocks.FURNACE), new ItemStack(voidCraft.blocks.voidMacerator, 1))) };
	}

}
