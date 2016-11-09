package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.heimdall;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingInfuser;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListHeimdall implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.blocks.Heimdall).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.Heimdall.pg1"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.Heimdall.pg2"),
				new VadeMecumPageCrafting(new VadeMecumCraftingInfuser(voidCraft.modid+".VadeMecum.recipe.infusion", new ItemStack(Blocks.BEACON), new ItemStack(voidCraft.blocks.Heimdall, 1))) };
	}

}
