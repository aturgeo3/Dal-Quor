package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.stabilizer;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListStabilizer implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.blocks.realityStabilizer).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.realityStabilizer"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(voidCraft.blocks.voidInfuserInert),
						new ItemStack(voidCraft.items.diamondDust),
						new ItemStack(voidCraft.blocks.voidInfuserInert),
						new ItemStack(voidCraft.items.diamondDust),
						new ItemStack(voidCraft.items.voidStar),
						new ItemStack(voidCraft.items.diamondDust),
						new ItemStack(voidCraft.blocks.voidInfuserInert),
						new ItemStack(voidCraft.blocks.voidicCharger),
						new ItemStack(voidCraft.blocks.voidInfuserInert) }, new ItemStack(voidCraft.blocks.realityStabilizer, 1))) };
	}

}
