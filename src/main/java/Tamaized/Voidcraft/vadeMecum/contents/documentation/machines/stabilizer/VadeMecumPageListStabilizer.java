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
				new VadeMecumPage("Reality Stabilizer", "Throughout the Void, there are tears in space and time. It is unknown if these Holes in Reality occur naturally, or if they are the product some other powerful force. In either case, one may wish to remove them from the terrain for one reason or another. To do this, one must construct a powerful machine capable of containing these rifts. That is where this machine, the Reality Stabilizer, comes into play."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
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
