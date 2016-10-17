package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.alchemy;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListAlchemy {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Voidic Alchemy Table", "Consuming Ethereal Fruit and their fertilized variants provide many great benefits. However, if brewing and farming in the Overworld has taught you anything, it would be that some of the most potent potions are created from enhanced crops. This holds true for the Voidic Plants as well. By encasing a Brewing Stand in Void Bricks and creating an interface of Void Cables and an Inert Void Infusion Altar, one creates a special workspace"),
				new VadeMecumPage("", "specifically for brewing with these strange plants."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.voidicCable),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.voidicCable),
						new ItemStack(Items.BREWING_STAND),
						new ItemStack(voidCraft.blocks.voidicCable),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.voidInfuserInert),
						new ItemStack(voidCraft.blocks.blockVoidbrick) }, new ItemStack(voidCraft.blocks.voidicAlchemyTable, 1))) };
	}

}
