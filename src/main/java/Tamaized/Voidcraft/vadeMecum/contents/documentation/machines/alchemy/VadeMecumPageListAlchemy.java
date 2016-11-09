package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.alchemy;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListAlchemy implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.blocks.voidicAlchemyTable).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.voidicAlchemyTable.pg1"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.voidicAlchemyTable.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
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
